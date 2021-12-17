package cn.szuer.publicboard;

import cn.szuer.publicboard.dto.NewsSendDto;
import cn.szuer.publicboard.dto.param.AddNewsParam;
import cn.szuer.publicboard.dto.param.RegisterParam;

import cn.szuer.publicboard.service.NewsService;
import com.github.pagehelper.PageInfo;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import cn.szuer.publicboard.reponse.BaseResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UrlOnlineTests {

    private RestTemplate template = new RestTemplate();

    private List<String> cookies =new ArrayList<>();

    /**
     * 在其他单元测试前进行login操作，获得cookie所需的Sessionid
     * @throws IOException
     */
    @Before
    public void GetCookies() throws IOException
    {
        String url = "http://localhost/user/login";

        //更改请求头Header, 修改MediaType为APPLICATION_JSON
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> param = new HashMap<>();
        param.put("userid", "2019010101");
        param.put("password", "123456789");
        //请求体的参数，一定要转成String, 才能被接受
        String value = mapper.writeValueAsString(param);
        // System.out.println(value);

        //HttpEntity包含消息头和消息体
        HttpEntity<String> requEntity = new HttpEntity<String>(value, headers);
        //获得ResponseEntity， 包括响应体对象、响应头和响应状态， BaseResponse.class表明响应体的类型
        ResponseEntity<BaseResponse> responseEntity = template.postForEntity(url, requEntity, BaseResponse.class);
        cookies.add(responseEntity.getHeaders().get("set-cookie").get(0).toString());
        System.out.println(cookies);
    }


    /**
     * getForEntity方法只返回的包括响应体对象、响应头和响应状态，
     */
    // @Test
    // public void testgetAll(){
    //     try{
    //         String url = "http://localhost/user/all";
    //         ResponseEntity<BaseResponse> entity = template.getForEntity(url, BaseResponse.class);
    //         // HttpStatus code = entity.getStatusCode();
    //         // System.err.println(code.toString());
    //         // System.err.println(entity.getBody().getCode());
    //         assertEquals(entity.getStatusCode(), HttpStatus.OK);
    //         assertTrue(entity.getBody().getMsg().equals("success"));
    //     }catch(Exception e){
    //         e.printStackTrace();
    //     }
    // }

    /**
     * 查看用户单元测试
     */
    @Test
    @Rollback
    public void testAdminCheckUser()
    {
        try{

            HttpHeaders headers = new HttpHeaders();
            // List<String> cookies =new ArrayList<>();
            //cookies.add("JSESSIONID=FEA9AE832F68F7A0F1AC5D52D60AC841; Path=/; HttpOnly");
            System.out.println(cookies);
            //请求头添加cookie，用于传输Sessionid
            headers.put(HttpHeaders.COOKIE,cookies);
            HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
            String url = "http://localhost/user/admin/checkuser?page=1&size=5";
            // ResponseEntity<String> entity = template.getForEntity(url, String.class);
            ResponseEntity<BaseResponse> entity = template.exchange(url, HttpMethod.GET, httpEntity, BaseResponse.class);
            // HttpStatus code = entity.getStatusCode();
            // System.err.println(code);
            System.err.println(entity.getBody().getData().toString());

            //检测HTTP状态码
            assertEquals(entity.getStatusCode(), HttpStatus.OK);
            //检测返回体携带的msg是否与controller中所设一致
            assertEquals(entity.getBody().getMsg(), "success");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 表单提交，接口入参“没有”@requestbody注解
     */
    // @Test
    // public void testLoginbyForm(){
    //     try{
    //         //默认为Form表单提交
    //         String url = "http://localhost/user/login";
    //         //Post以Form表单方式提交必须用LinkedMultiValueMap
    //         LinkedMultiValueMap<String, Integer> param = new LinkedMultiValueMap<>();
    //         param.add("userid", 2019010101);
    //         param.add("password", 1234);
    //         //获得ResponseEntity， 包括响应体对象、响应头和响应状态， String.class表明响应体被转化为String类型
    //         ResponseEntity<String> ResponseEntity = template.postForEntity(url, param, String.class);
    //         HttpStatus code = ResponseEntity.getStatusCode();
    //         System.err.println(code);
    //         System.err.println(ResponseEntity.toString());
    //     }catch(Exception e)
    //     {
    //         e.printStackTrace();
    //     }
    // }


    /**
     * payload提交，接口入参“有”@requestbody
     * 若controller使用了@requestbody注解，
     * 则前端的提交方式变为Payload,需要修改Header中的ContentType,
     * 否则会报unsupported Media Type
     **/


    /**
     * 登录接口单元测试
     */
    @Test
    public void testLogin() throws IOException{

      try{
        String url = "http://localhost/user/login";
        RegisterParam registerParam= new RegisterParam();
        ResponseEntity<BaseResponse> responseEntity;

        /**
         *  测试用例1：登录成功
         */
        registerParam.setUserid(2019010101);
        registerParam.setPassword("123456789");
        System.out.println("测试用例1:"+registerParam);
        responseEntity = template.postForEntity(url,registerParam,BaseResponse.class);
        //assert测试
        //getStatusCode获得响应状态，该响应状态是HTTP自带的，并非服务端设置的
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        //getbody获得响应体，getCode获得相应体的状态码，getMsg获得响应体中的信息
        assertEquals(responseEntity.getBody().getCode(), 200);
        assertEquals(responseEntity.getBody().getMsg(), "登录成功");

        /**
         *  测试用例2：登录失败
         */
        registerParam.setUserid(2019010101);
        registerParam.setPassword("12345");
        System.out.println("测试用例2:"+registerParam);
        responseEntity = template.postForEntity(url,registerParam,BaseResponse.class);
         //assert测试
         //getStatusCode获得响应状态，该响应状态是HTTP自带的，并非服务端设置的
         assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
         //getbody获得响应体，getCode获得相应体的状态码，getMsg获得响应体中的信息
         assertEquals(responseEntity.getBody().getCode(), 500);
         assertEquals(responseEntity.getBody().getMsg(), "登录失败,用户名或密码有误");

      }catch(Exception e)
      {
        e.printStackTrace();
      }

    }

    
    /**
     * 编辑帖子接口单元测试
    */
    @Test
    public void testAddNews() throws IOException{
    try{
        String url = "http://localhost/news/add";
        //初始化编辑帖子参数对象
        AddNewsParam addNewsParam= new AddNewsParam();

        //初始化响应码
        BaseResponse baseResponse;

        /**
         *  测试用例1：账号被禁用测试
         */
        addNewsParam.setUserid(2019040404);
        addNewsParam.setNewstitle("标题：测试帖子");
        addNewsParam.setContent("内容：测试帖子");
        addNewsParam.setNewstypeid(1);
        System.out.println("测试用例1:"+ addNewsParam);
        baseResponse= template.postForObject(url, addNewsParam, BaseResponse.class);
        //assert测试
        //getCode()获取返回的响应码，getMsg()获取返回的信息
        assertEquals(baseResponse.getCode(),500);
        assertEquals(baseResponse.getMsg(),"发布失败!当前账号被禁用!");

        /**
         * 测试用例2：帖子类型被禁用测试
         */
        addNewsParam.setUserid(2019010101);
        addNewsParam.setNewstitle("标题：测试帖子");
        addNewsParam.setContent("内容：测试帖子");
        addNewsParam.setNewstypeid(3);
        System.out.println("测试用例2:"+ addNewsParam);
        baseResponse = template.postForObject(url, addNewsParam, BaseResponse.class);
        //assert测试
        //getCode()获取返回的响应码，getMsg()获取返回的信息
        assertEquals(baseResponse.getCode(),500);
        assertEquals(baseResponse.getMsg(),"发布失败!帖子类型被禁用!");

        /**
         *  测试用例3：用户为正常用户状态测试
         */
        addNewsParam.setUserid(2019010101);
        addNewsParam.setNewstitle("标题：测试帖子");
        addNewsParam.setContent("内容：测试帖子");
        addNewsParam.setNewstypeid(1);
        System.out.println("测试用例3:"+ addNewsParam);
        baseResponse = template.postForObject(url, addNewsParam, BaseResponse.class);
        //assert测试
        //getCode()获取返回的响应码，getMsg()获取返回的信息
        assertEquals(baseResponse.getCode(),200);
        assertEquals(baseResponse.getMsg(),"发布成功！");

        /**
         * 测试用例4：用户为匿名用户状态测试
         */
        addNewsParam.setUserid(2019050505);
        addNewsParam.setNewstitle("标题：测试帖子");
        addNewsParam.setContent("内容：测试帖子");
        addNewsParam.setNewstypeid(1);
        System.out.println("测试用例4:"+ addNewsParam);
        baseResponse = template.postForObject(url, addNewsParam, BaseResponse.class);
        //assert测试
        //getCode()获取返回的响应码，getMsg()获取返回的信息
        assertEquals(baseResponse.getCode(), 200);
        assertEquals(baseResponse.getMsg(),"发布成功，使用匿名账号！");

    }catch(Exception e)
    {
        e.printStackTrace();
    }
    }




    /**
     * payload提交，接口入参“有”@requestbody
     * 若controller使用了@requestbody注解，
     * 则前端的提交方式变为Payload,需要修改Header中的ContentType,
     * 否则会报unsupported Media Type
     **/
    @Test
    public void testAddnewsbyPayload1() throws IOException{
        try{
            //接口的url
            String url = "http://localhost/news/add";

            //更改请求头Header, 修改MediaType为APPLICATION_JSON
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);


            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> param = new HashMap<>();
            param.put("userid", "2019010101");
            param.put("title", "帖子标题3456");
            param.put("content","帖子内容3456");
            param.put("newstype","1");
            //请求体的参数，一定要转成String, 才能被接受
            String value = mapper.writeValueAsString(param);
            System.out.println(value);

            AddNewsParam addNewsParam= new AddNewsParam();
            addNewsParam.setUserid(2019010101);
            addNewsParam.setNewstitle("标题444：测试帖子");
            addNewsParam.setContent("内容444：测试帖子");
            addNewsParam.setNewstypeid(1);

            //获得ResponseEntity， 包括响应体对象、响应头和响应状态， BaseResponse.class表明响应体的类型
            BaseResponse baseResponse = template.postForObject(url, addNewsParam, BaseResponse.class);

            //assert测试
            assertEquals(baseResponse.getMsg(), "发布成功！");
            System.out.println(baseResponse.getMsg());
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查看帖子详情接口单元测试
     */
    @Test
    public void testViewNewsDetails() throws IOException{
        try{

            HttpHeaders headers = new HttpHeaders();
            System.out.println(cookies);
            //请求头添加cookie，用于传输Sessionid
            headers.put(HttpHeaders.COOKIE,cookies);
            HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
            String url;
            /**
             * 测试用例1：用户被禁用，获取失败
             */
            //合成get请求url
            url = "http://localhost/news/view?userid=2019040404&newsid=3";

            ResponseEntity<BaseResponse> entity1 = template.exchange(url, HttpMethod.GET, httpEntity, BaseResponse.class);
            System.err.println(entity1.getBody().getData().toString());

            //检测HTTP状态码
            assertEquals(entity1.getStatusCode(), HttpStatus.OK);
            //检测返回体携带的msg是否与controller中所设一致
            assertEquals(entity1.getBody().getMsg(), "获取失败！账号处于封禁状态");

            /**
             * 测试用例2：获取成功
             */
            url = "http://localhost/news/view?userid=2019010101&newsid=3";

            ResponseEntity<BaseResponse> entity2 = template.exchange(url, HttpMethod.GET, httpEntity, BaseResponse.class);
            System.err.println(entity2.getBody().getData().toString());

            //检测HTTP状态码
            assertEquals(entity2.getStatusCode(), HttpStatus.OK);
            //检测返回体携带的msg是否与controller中所设一致
            assertEquals(entity2.getBody().getMsg(), "获取成功！");

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查看不同类型帖子接口单元测试
     */
    @Test
    public void testViewDiffNews() throws IOException{
        try{

            HttpHeaders headers = new HttpHeaders();
            System.out.println(cookies);
            //请求头添加cookie，用于传输Sessionid
            headers.put(HttpHeaders.COOKIE,cookies);
            HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
            String url;
            /**
             * 测试用例1：该类型没有记录，获取失败
             */
            //合成get请求url
            url = "http://localhost/news/viewDiffNews?typeid=3&page=1&size=5";

            ResponseEntity<BaseResponse> entity1 = template.exchange(url, HttpMethod.GET, httpEntity, BaseResponse.class);
            //System.err.println(entity1.getBody().getData().toString());

            //检测HTTP状态码
            assertEquals(entity1.getBody().getCode(), 500);
            //检测返回体携带的msg是否与controller中所设一致
            assertEquals(entity1.getBody().getMsg(), "该类型包含帖子数为0，获取失败！");

            /**
             * 测试用例2：获取成功
             */
            url = "http://localhost/news/viewDiffNews?typeid=1&page=1&size=5";

            ResponseEntity<BaseResponse> entity2 = template.exchange(url, HttpMethod.GET, httpEntity, BaseResponse.class);
            System.err.println(entity2.getBody().getData().toString());

            //检测HTTP状态码
            assertEquals(entity2.getStatusCode(), HttpStatus.OK);
            //检测返回体携带的msg是否与controller中所设一致
            assertEquals(entity2.getBody().getMsg(), "获取成功！");

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 获取帖子类型单元测试
     */
    @Test
    public void testGetNewsType() throws IOException{
        try{

            HttpHeaders headers = new HttpHeaders();
            System.out.println(cookies);
            //请求头添加cookie，用于传输Sessionid
            headers.put(HttpHeaders.COOKIE,cookies);
            HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
            String url;
            /**
             * 测试用例1：获取成功
             */
            //合成get请求url
            url = "http://localhost/news/getNewsType";

            ResponseEntity<BaseResponse> entity1 = template.exchange(url, HttpMethod.GET, httpEntity, BaseResponse.class);
            System.err.println(entity1.getBody().getData().toString());

            //检测HTTP状态码
            assertEquals(entity1.getBody().getCode(), 200);
            //检测返回体携带的msg是否与controller中所设一致
            assertEquals(entity1.getBody().getMsg(), "获取成功！");


        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }


}
