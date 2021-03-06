package cn.szuer.publicboard.controller;

import cn.szuer.publicboard.dto.NewsDetailSendDto;
import cn.szuer.publicboard.dto.NewsSendDto;
import cn.szuer.publicboard.dto.TypeSendDto;
import cn.szuer.publicboard.dto.param.AddCommentParam;
import cn.szuer.publicboard.dto.param.AddNewsParam;
import cn.szuer.publicboard.dto.param.AddReplyParam;
import cn.szuer.publicboard.dto.param.SearchParam;

import cn.szuer.publicboard.reponse.BaseResponse;
import cn.szuer.publicboard.service.NewsService;
import java.util.List;

import cn.szuer.publicboard.utils.MinioUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;




/**
 * 帖子管理
 */
@RestController
@RequestMapping("/news")
public class NewsController
{
    @Autowired
    NewsService newsService;

    @Autowired
    MinioUtil minioUtil;
    
    /**
     * 查看帖子表
     * @param pageNum 页数
     * @param pageSize 每页记录数
     * @return
     */
    @GetMapping("/admin/checkNews")
    public BaseResponse<PageInfo<NewsSendDto>> showNews(@RequestParam(name = "page",required = true,defaultValue = "1") int pageNum,
                                                        @RequestParam(name = "size",required = true,defaultValue = "10")int pageSize)
    {
        return new BaseResponse<PageInfo<NewsSendDto>>(200, "success", newsService.getByPage(pageNum, pageSize));
    }

    /**
     * 编辑帖子
     * @param addNewsParam
     * @return
     */
    // @PostMapping("/add")
    // public BaseResponse<NewsSendDto> add(@RequestBody AddNewsParam addNewsParam)
    // {
    //     int res = newsService.add(addNewsParam);

    //     if(res==21) {
    //         return new BaseResponse(500, "发布失败!当前账号被禁用!");
    //     }
    //     else if(res==22) {
    //         return new BaseResponse(500, "发布失败!帖子类型被禁用!");
    //     }
    //     else if(res==11) {
    //         return new BaseResponse(200, "发布成功！");
    //     }
    //     else if(res==12) {
    //         return new BaseResponse(200, "发布成功，使用匿名账号！");
    //     }

    //     return new BaseResponse(500, "发送失败，未知错误！");
    // }

    /**
     * 查看帖子详情
     * @param newsid
     * @return
     */
    @GetMapping("/view")
    public BaseResponse<NewsDetailSendDto> view(@RequestParam(name="newsid") Integer newsid)
    {
        BaseResponse<NewsDetailSendDto> response = newsService.view(newsid);
        return response;
    }


    /**
     * 按类型查看所有帖子
     * @param typeid 类型编号
     * @param pageNum 页数
     * @param pageSize 每页记录数
     * @return
     */
    // @GetMapping("/viewDiffNews")
    // public BaseResponse<PageInfo<NewsSendDto>> viewDiffNews(@RequestParam(name="typeid") Integer typeid,
    //                                               @RequestParam(name = "page") Integer pageNum,
    //                                               @RequestParam(name = "size")Integer pageSize)
    // {
    //     BaseResponse<PageInfo<NewsSendDto>> response = newsService.viewDiffNews(typeid,pageNum,pageSize);
    //     return response;
    // }

    
    /**
     * 获取所有帖子类型
     * @return
     */
    @GetMapping("/getNewsType")
    public BaseResponse<List<TypeSendDto>> getNewsType()
    {
        BaseResponse<List<TypeSendDto>> response = newsService.getNewsType();
        return response;
    }
    
     /**
      * 发布帖子带图片
      * @param multipartFiles
      * @param addNewsParam
      * @return
      */
    @PostMapping("/addwithimages")
    public BaseResponse addWithImages(@RequestParam(name = "image", required = false) List<MultipartFile> multipartFiles ,AddNewsParam addNewsParam)
    {
        int res = newsService.addWithImages(multipartFiles, addNewsParam);

        if(res==21) {
            return new BaseResponse(500, "发布失败!当前账号被禁用!");
        }
        else if(res==9001) {
            return new BaseResponse(500, "图片上传失败!");
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
     * 帖子点赞与取消点赞
     * @param newsid
     * @return
     */
    @GetMapping("/likenews")
    public BaseResponse<List<Integer>> likeNews(@RequestParam(name = "newsid")Integer newsid)
    {
        BaseResponse<List<Integer>> response = newsService.likeNews(newsid);
        return response;
    }

    /**
     * 帖子评论
     * @param addCommentParam
     * @return
     */
    @PostMapping("/comment")
    public BaseResponse<NewsDetailSendDto> comment(@RequestBody AddCommentParam addCommentParam)
    {
        BaseResponse<NewsDetailSendDto> response = newsService.comment(addCommentParam);
        return response;
    }

    /**
     * 帖子回复
     * @param addReplyParam
     * @return
     */
    @PostMapping("/reply")
    public BaseResponse<NewsDetailSendDto> reply(@RequestBody AddReplyParam addReplyParam)
    {
        BaseResponse<NewsDetailSendDto> response = newsService.reply(addReplyParam);
        return response;
    }

    /** 
     * 搜索帖子
     * @param param
     * @return
     */
    @PostMapping("/search")
    public BaseResponse<PageInfo<NewsSendDto>> searchNews(@RequestBody SearchParam param)
    {
        PageInfo<NewsSendDto> pageInfo=newsService.searchNews(param);
        if (pageInfo==null)
        {
            return new BaseResponse<PageInfo<NewsSendDto>>(500,"没有相关的信息", null);
        }
        return new BaseResponse<PageInfo<NewsSendDto>>(200,"搜索成功",pageInfo);
    }

    /**
     * 查看我的帖子
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/mynews")
    public  BaseResponse<PageInfo<NewsSendDto>> getMyNews(@RequestParam(name = "page",required = true,defaultValue = "1") int pageNum,
                                  @RequestParam(name = "size",required = true,defaultValue = "10") int pageSize)
    {
         return new BaseResponse<PageInfo<NewsSendDto>>(200, "success", newsService.getMyNews(pageNum, pageSize));
    }

}