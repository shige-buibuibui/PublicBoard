package cn.szuer.publicboard.handler;

import cn.szuer.publicboard.dto.UserDto;
import cn.szuer.publicboard.mapper.UserInfoMapper;
import cn.szuer.publicboard.model.UserInfo;
import cn.szuer.publicboard.reponse.BaseResponse;
import cn.szuer.publicboard.service.MyUser;
import cn.szuer.publicboard.utils.mapsturctconverter.UserConverter;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


  /*用于处理登录请求成功后的类
    将返回类信息写入响应体中*/

@Component
public class successHandler implements AuthenticationSuccessHandler
{
    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UserConverter userConverter;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response,Authentication authentication) throws IOException, ServletException
    {
        //设置返回类型
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");//斜杆注意不能漏
        PrintWriter out=response.getWriter();

        //获取用户信息
        BaseResponse<UserDto> baseResponse=new BaseResponse(200,"登录成功");
        MyUser user=(MyUser)authentication.getPrincipal();
        UserInfo userInfo=userInfoMapper.selectByPrimaryKey(Integer.parseInt(user.getUsername()));

        userInfo.setLogintime(new Date());
        userInfoMapper.updateByPrimaryKey(userInfo);

        //将UserInfo转为UserDto写入返回类中
        if (userInfo!=null)
        {
            UserDto userDto=userConverter.UserInfo2UserDto(userInfo);
            baseResponse.setData(userDto);
        }

        //将返回信息写入响应体中
        String res=JSON.toJSONString(baseResponse);
        out.print(res);
    }
}
