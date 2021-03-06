package cn.szuer.publicboard.service.Impl;

import cn.szuer.publicboard.dto.UserDto;
import cn.szuer.publicboard.dto.param.ChangeEmailParam;
import cn.szuer.publicboard.dto.param.ChangePasswordParam;
import cn.szuer.publicboard.dto.param.ForgetPasswordParam;
import cn.szuer.publicboard.dto.param.RegisterParam;
import cn.szuer.publicboard.mapper.UserInfoMapper;
import cn.szuer.publicboard.model.UserInfo;
import cn.szuer.publicboard.model.UserInfoExample;
import cn.szuer.publicboard.reponse.BaseResponse;
import cn.szuer.publicboard.service.UserService;
import cn.szuer.publicboard.utils.AuthenticationUtil;
import cn.szuer.publicboard.utils.CodeRandomUtil;
import cn.szuer.publicboard.utils.MailUtil;
import cn.szuer.publicboard.utils.MinioUtil;
import cn.szuer.publicboard.utils.mapsturctconverter.UserConverter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private AuthenticationUtil authenticationUtil;

    @Autowired
    private MinioUtil minioUtil;

    //注入converter,实现类在target里可以看到，由mapstruct自动生成
    @Autowired
    private UserConverter userConverter;

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    PasswordEncoder encoder;

    /**
     * 添加用户
     */
    @Override
    public int addUser(RegisterParam registerParam,HttpSession session)
    {
        //验证
        int ret=mailUtil.verify(session,registerParam.getEmail(),registerParam.getCode());
        if (ret!=0)
            return ret;

        //查找数据库中有没有该学号或该邮箱的记录
        UserInfoExample example=new UserInfoExample();
        example.or().andEmailEqualTo(registerParam.getEmail());
        List<UserInfo> users=userInfoMapper.selectByExample(example);
        //该邮箱已注册
        if (users.size()>0)
            return 5;
        //学号已注册
        if (userInfoMapper.selectByPrimaryKey(registerParam.getUserid())!=null)
            return 6;

        else
        {
            //向数据库中插入数据,注册账户
            UserInfo user=new UserInfo();
            user.setUserid(registerParam.getUserid());
            user.setUsername(String.valueOf(registerParam.getUserid()));
            user.setLogintime(new Date());
            user.setPassword(encoder.encode(registerParam.getPassword()));
            user.setEmail(registerParam.getEmail());
            int res=userInfoMapper.insertSelective(user);
            if (res==1)
                return 0;
            else
                return 1;
        }
    }
    
    
    @Override
    public List<UserDto> getAll() {
        // UserInfoExample example = new UserInfoExample(); 
        // List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        List<UserInfo> userInfos = userInfoMapper.selectAll();
        List<UserDto> userDtos =  userConverter.UserInfos2UserDtos(userInfos);
        return userDtos;
    }

    /**
     * 根据页面参数返回对应数量的records
     */
    @Override
    public PageInfo<UserDto> getByPage(int pageNum, int pageSize) {
        
        PageHelper.startPage(pageNum, pageSize);
        //查询数据库中的所有用户信息
        List<UserInfo> userInfos = userInfoMapper.selectAll();
        //使用converter将Userinfo拷贝到userDto
        List<UserDto> userDtos =  userConverter.UserInfos2UserDtos(userInfos);
        //先根据查出来的userinfos生成PageInfo
        PageInfo pageInfo = new PageInfo<>(userInfos);
        //再进行setList操作，转成userDtos
        pageInfo.setList(userDtos);
    
        return pageInfo;

    }


    /**
     * 获取个人信息
     */
    @Override
    public UserDto getProfile() {

       //查询当前登录用户的用户信息
       UserInfo userInfo = userInfoMapper.selectByPrimaryKey(authenticationUtil.getAuthenticatedId());
       //将userInfo转成前端展示所需的userDto
       UserDto userDto = userConverter.UserInfo2UserDto(userInfo);
       return userDto;
    }

    /**
     * 修改个人信息
     */
    @Override
    public int updateProfile(MultipartFile multipartFile, UserDto userDto) {
     
         
         UserInfo userinfo = userConverter.userDto2UserInfo(userDto);
         userinfo.setUserid(authenticationUtil.getAuthenticatedId());
         
         BaseResponse<String> uploadResponse;

         //如果头像进行了修改
         if(multipartFile!=null )
        {
              System.out.println("==============上传头像================");
              //上传头像并获得上传返回体 
              uploadResponse= minioUtil.uploadAvatar(multipartFile, "avatar");
               if(uploadResponse.getCode()==9001)
                    return 9001;
              //从返回体中获得上传头像的uuid
              userinfo.setHeadimage(uploadResponse.getData());
         } 

         //插入个人信息
         int res = userInfoMapper.updateByPrimaryKeySelective(userinfo);
        
         if(res!=0)
            return 11;
         else
            return 22; 

    }

    /**
     * 修改密码
     */
    @Override
    public boolean changePassword(ChangePasswordParam param)
    {
        //从数据库中找到用户密码
        Integer userid=authenticationUtil.getAuthenticatedId();
        UserInfo user=userInfoMapper.selectByPrimaryKey(userid);

        //与输入的密码做匹配
        boolean ifMatch=encoder.matches(param.getOldPassword(),user.getPassword());

        if (ifMatch)
        {
            //如果匹配则更新密码
            user.setPassword(encoder.encode(param.getNewPassword()));
            userInfoMapper.updateByPrimaryKey(user);
        }

        return ifMatch;
    }

    /**
     * 切换匿名模式
     */
    @Override
    public boolean switchMode()
    {
        // 将数据库中该用户的匿名模式取反
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(authenticationUtil.getAuthenticatedId());
        userInfo.setAnonymousmode(!userInfo.getAnonymousmode());
        if(userInfoMapper.updateByPrimaryKeySelective(userInfo)>0)
           return true;
        else
           return false;

    }

    /**
     * 发送验证码
     */
    @Override
    public String sendCode(String to)
    {
        String code=CodeRandomUtil.verifyCode(6);
        String subject="PublicBoard";
        StringBuilder text=new StringBuilder();
        text.append("<p>")
                .append("你的验证码为:")
                .append("<b style:font-size=30px>").append(code).append("</b>")
                .append(",5分钟后失效")
                .append("</p>");
        try
        {
            mailUtil.send(to,subject,text.toString());
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
        return code;
    }

    /**
     * 重置密码
     */
    @Override
    public int forget(HttpSession session,ForgetPasswordParam param)
    {
        //验证
        int ret=mailUtil.verify(session,param.getEmail(),param.getCode());
        if (ret!=0)
            return ret;

        //更新密码
        UserInfoExample example=new UserInfoExample();
        example.or().andEmailEqualTo(param.getEmail());
        List<UserInfo> userInfos=userInfoMapper.selectByExample(example);

        UserInfo user=userInfos.get(0);
        user.setPassword(encoder.encode(param.getNewPassword()));
        userInfoMapper.updateByPrimaryKeySelective(user);
        return 0;

    }

    /**
     * 判断邮箱是否存在
     */
    @Override
    public boolean ifExist(String email)
    {
        UserInfoExample example=new UserInfoExample();
        example.or().andEmailEqualTo(email);
        List<UserInfo> userInfos=userInfoMapper.selectByExample(example);
        if (userInfos==null||userInfos.size()==0)
            return false;
        return true;
    }

    /**
     * 修改邮箱
     */
    @Override
    public int changeEmail(HttpSession session, ChangeEmailParam changeEmailParam)
    {
        
          //验证
        int ret=mailUtil.verify(session, changeEmailParam.getEmail(), changeEmailParam.getCode());
        if (ret!=0)
            return ret;

        UserInfo userInfo =new UserInfo();
        userInfo.setUserid(authenticationUtil.getAuthenticatedId());
        userInfo.setEmail(changeEmailParam.getEmail());
        
        userInfoMapper.updateByPrimaryKeySelective(userInfo);

        return 0;
    }


}
