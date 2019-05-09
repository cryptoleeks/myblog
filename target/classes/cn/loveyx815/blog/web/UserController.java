package cn.loveyx815.blog.web;


import cn.loveyx815.blog.entity.Message;
import cn.loveyx815.blog.entity.User;
import cn.loveyx815.blog.service.UserService;
import cn.loveyx815.blog.util.CookieUtil;
import cn.loveyx815.blog.util.JsonDateValueProcessor;
import cn.loveyx815.blog.util.RedisUtil;
import cn.loveyx815.blog.util.Utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
   /**
   * @Description: 用户登录
   * @Param: [request, response]
   * @return: java.util.Map<java.lang.String,java.lang.Object>
   * @Author: Yonggang Shi
   * @Date: 2019/5/2 0002
   */
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response ) {

       /* Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> strings = parameterMap.keySet();
        JSONObject jsonObject=null;
        jsonObject=JSONObject.parseObject(strings.iterator().next());*/

        //JSON json = (JSON) JSON.parseObject(parameterMap.);
        String username =  request.getParameter("username");
        //System.out.println(parameterMap);
        /*if (!params.isEmpty()){
            Map<String, Object> params1 = params.toSingleValueMap();
           *//* params.get("username").get(0);
            params.get("password").get(0);*//*
        }*/

        String password =request.getParameter("password");
        //根据前台的名称和密码去后台查找该人
        User user = userService.queryUser(username, password);
        if (user!=null){
            //对Date转化进行处理
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
            net.sf.json.JSONObject jsonuser = net.sf.json.JSONObject.fromObject(user,jsonConfig);

            //登录凭证
            String token = UUID.randomUUID().toString();
            Jedis jedis = RedisUtil.getInstance().getJedis();
            if(jedis!=null){
                jedis.set(token, jsonuser+"");
                jedis.expire(token,1800);
            }
            CookieUtil.setCookie(response,"token",token);
            return Utils.JSONDataReturn("登录成功" , "200");
        }
        return Utils.JSONDataReturn("登录失败" , "301");
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String toRegister(){
        return "register";
    }
    /** 
    * @Description: 用户注册 
    * @Param: [request] 
    * @return: java.util.Map<java.lang.String,java.lang.Object> 
    * @Author: Yonggang Shi
    * @Date: 2019/5/2 0002 
    */ 
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addUser( HttpServletRequest request){
        String path=request.getParameter("file");
        //String name=request.getParameter("name");
        String jj=request.getParameter("jj");
        String date=request.getParameter("date");
        String sex=request.getParameter("sex");
        String username=request.getParameter("username");
        Object queryuser=userService.queryUserByname(username);
        //校验用户名是否存在，存在不允许注册
        if (queryuser!=null){
            return Utils.JSONDataReturn("用户名已被注册","300");
        }
        String password=request.getParameter("password");
        String alias=request.getParameter("alias");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1=null;
        try {

            date1 = sdf.parse(date);
        } catch (ParseException e) {
            logger.error("日期解析异常");
        }
        //  String jsonstr=request.getParameter("json");
       // JSONObject jsonObject = JSONObject.parseObject(jsonstr);
        User user=new User();
        user.setcAlias(alias);
        user.setcBrith(date1);
        user.setcImgAdr(path);
        user.setcJj(jj);
        user.setcName(username);
        user.setcPassword(password);
        user.setcRole("user");
        user.setcTime(new Date());
        user.setcXb(Integer.parseInt(sex));
        user.setId(UUID.randomUUID().toString());
        userService.saveUser(user);
        return Utils.JSONDataReturn("注册成功","200");
    }

    @RequestMapping(value = "/cookie",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getCookieInfo(HttpServletRequest request){
        Cookie token =CookieUtil.getCookie(request,"token");
        if (token!=null){
            String value=token.getValue();
            //List<Map<String,Object>>
            String userStr = RedisUtil.getInstance().getJedis().get(value);
            JSONObject jsonObject = JSONObject.parseObject(userStr);
            return  Utils.JSONDataReturn(jsonObject,"200");
        }

        return Utils.JSONDataReturn("未登录" , "300");
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadUserImg(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "file", required = false)MultipartFile attach){


            String rootPath = request.getSession().getServletContext().getRealPath("/resources/upload/user");

            /**
             * 文件路径不存在则需要创建文件路径
             */
            File filePath = new File(rootPath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }

            //最终文件名
            String path=rootPath + File.separator + attach.getOriginalFilename();
            File realFile = new File(path);
        try {
            FileUtils.copyInputStreamToFile(attach.getInputStream(), realFile);
        } catch (IOException e) {
            return Utils.JSONDataReturn("上传失败","301");
        }
//            String url=path;
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("url","http://localhost:8080/resources/upload/user/"+attach.getOriginalFilename());
            return Utils.JSONDataReturn(jsonObject,"200");
            //下面response返回的json格式是editor.md所限制的，规范输出就OK
           // response.getWriter().write("{\"success\": 1, \"message\":\"上传成功\",\"url\":\"/resources/upload/" + attach.getOriginalFilename() + "\"}");
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String toLogout(HttpServletRequest request,HttpServletResponse response,Model model){
        Cookie token =CookieUtil.getCookie(request,"token");
        Long delcount =0L;
        if (token!=null){
            String value=token.getValue();
            //List<Map<String,Object>>
            RedisUtil.getInstance().getJedis().get(value);
             delcount = RedisUtil.getInstance().getJedis().del(value);
            //return  Utils.JSONDataReturn(JSON.toJSON(userStr),"200");
        }
        if (delcount==1L||(token!=null&&delcount==0L)){
            CookieUtil.removeCookie(request,response,"token");
           /* try {
                request.setCharacterEncoding("UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.error(e.getMessage());
            }
            response.setCharacterEncoding("UTF-8");
            String indexPage = "localhost:8080/index";
            String string="<script>\n" +
                    "layer.msg('登出成功，即将跳转至首页'); \n" +
                    "</script>";
            out.print(string);*/
            return  "index";
        }
        model.addAttribute("error","登出失败");

        return "error";
    }

    @RequestMapping(value = "/userinfo")
    public String toUserTop(){
        return "usertop";
    }

    @RequestMapping(value = "/info/blog/{id}")
    @ResponseBody
    public Map<String, Object> getUserBlog(@PathVariable String id){
        List<Map<String, Object>> list = userService.getBlogByUser(id);
        return Utils.JSONDataReturn(list,"200");
    }
    @RequestMapping(value = "/updata/{id}")
    @ResponseBody
    public Map<String, Object> updataJBInfoById(@PathVariable String id, HttpServletRequest request){
        String path=request.getParameter("file");
        //String name=request.getParameter("name");
        String jj=request.getParameter("jj");
        String date=request.getParameter("date");
        String alias=request.getParameter("alias");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1=null;
        try {

            if (date!=null){
                date1 = sdf.parse(date);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        //  String jsonstr=request.getParameter("json");
        // JSONObject jsonObject = JSONObject.parseObject(jsonstr);
        User user=new User();
        user.setcAlias(alias);
        user.setcBrith(date1);
        user.setcImgAdr(path);
        user.setcJj(jj);
       user.setId(id);


        userService.updataJBInfoById(user);
        return Utils.JSONDataReturn("修改成功","200");

    }

    @RequestMapping(value = "/updata/psw/{uid}",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updatePsw(@PathVariable String uid, HttpServletRequest request){
        String oldpsw=request.getParameter("oldpassword");
        String newpsw=request.getParameter("newpassword");
        User user=userService.getUserById(uid);
        if (StringUtils.equals(oldpsw,user.getcPassword())){
            user=new User();//定义新的用户更新密码
            user.setId(uid);
            user.setcPassword(newpsw);
            userService.updatePassword(user);
            return Utils.JSONDataReturn("密码已更新","200");
        }else {
            return Utils.JSONDataReturn("原密码输入有误","301");
        }
    }

    @RequestMapping(value = "/message/{uid}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addMessage(@PathVariable String uid, HttpServletRequest request){
        String message=request.getParameter("message");
        if (StringUtils.equals(uid,"0")){
            uid="visit"+UUID.randomUUID();//非用户提交以游客方式提交
        }
        Message message1=new Message();
        message1.setcContent(message);
        message1.setcTime(new Date());
        message1.setcUser(uid);
        message1.setId(UUID.randomUUID().toString());
        userService.addMessage(message1);
        return Utils.JSONDataReturn("留言成功","200");
    }
}