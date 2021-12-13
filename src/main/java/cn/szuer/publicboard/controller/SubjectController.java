package cn.szuer.publicboard.controller;

import cn.szuer.publicboard.dto.SubjectSendDto;
import cn.szuer.publicboard.dto.TypeSendDto;
import cn.szuer.publicboard.dto.param.AddSubjectParam;
import cn.szuer.publicboard.reponse.BaseResponse;
import cn.szuer.publicboard.service.SubjectService;
import cn.szuer.publicboard.utils.mapsturctconverter.SubjectConverter;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * 话题管理
 */
@RestController
@RequestMapping("/subject")
public class SubjectController
{
    @Autowired
    SubjectService subjectService;
    @Autowired
    SubjectConverter subjectConverter;
    
    /**
     * 查看话题表
     * @param pageNum 页数
     * @param pageSize 每页记录数
     * @return
     */
    @GetMapping("/admin/checksubject")
    public BaseResponse<PageInfo<SubjectSendDto>> showSubject(@RequestParam(name = "page",required = true,defaultValue = "1") int pageNum,
                                                              @RequestParam(name = "size",required = true,defaultValue = "10")int pageSize)
    {
        return new BaseResponse<PageInfo<SubjectSendDto>>(200, "success", subjectService.getByPage(pageNum, pageSize));
    }

    /**
     * 编辑话题
     * @param addSubjectParam
     * @return
     */
    @PostMapping("/add")
    public BaseResponse add(@RequestBody AddSubjectParam addSubjectParam)
    {
        int res = subjectService.add(addSubjectParam);

        if(res==21) {
            return new BaseResponse(500, "发布失败!当前账号被禁用!");
        }
        else if(res==22) {
            return new BaseResponse(500, "发布失败!话题类型被禁用!");
        }
        else if(res==11) {
            return new BaseResponse(200, "发布成功！");
        }
        else if(res==12) {
            return new BaseResponse(200, "发布成功，使用匿名账号！");
        }
        return new BaseResponse(500, "发送失败，未知错误！");
    }
    /**
     * 查看话题详情
     * @param userid
     * @param subjectid
     * @return
     */
    @GetMapping("/view")
    public BaseResponse<SubjectSendDto> view(@RequestParam(name="userid") Integer userid,
                                             @RequestParam(name="subjectid") Integer subjectid)
    {
        BaseResponse<SubjectSendDto> response = subjectService.view(userid,subjectid);
        return response;
    }

    /**
     * 按类型查看所有话题
     * @param typeid
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/viewDiffSubject")
    public BaseResponse<PageInfo<SubjectSendDto>> viewDiffSubject(@RequestParam(name="typeid") Integer typeid,
                                                                  @RequestParam(name = "page") Integer pageNum,
                                                                  @RequestParam(name = "size")Integer pageSize)
    {
        BaseResponse<PageInfo<SubjectSendDto>> response = subjectService.viewDiffSubject(typeid,pageNum,pageSize);
        return response;
    }


    /**
     * 获取所有话题类型
     * @return
     */
    @GetMapping("/getSubjectType")
    public BaseResponse<List<TypeSendDto>> getSubjectType()
    {
        BaseResponse<List<TypeSendDto>> response = subjectService.getSubjectType();
        return response;
    }

    /**
     * 发布话题带图片
     * @param multipartFiles
     * @param addSubjectParam
     * @return
     */
    @PostMapping("/addwithimages")
    public BaseResponse addWithImages(@RequestParam(name = "image", required = false) List<MultipartFile> multipartFiles ,AddSubjectParam addSubjectParam)
    {
        int res = subjectService.addWithImages(multipartFiles, addSubjectParam);

        if(res==21) {
            return new BaseResponse(500, "发布失败!当前账号被禁用!");
        }
        else if(res==9001)
        {
            return new BaseResponse(500, "图片上传失败！");
        }
        else if(res==11) {
            return new BaseResponse(200, "发布成功！");
        }
        else if(res==12) {
            return new BaseResponse(200, "发布成功，使用匿名账号！");
        }

        return new BaseResponse(500, "发送失败，未知错误！");
    }

}