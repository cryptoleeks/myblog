package cn.loveyx815.blog.web;

import cn.loveyx815.blog.entity.Lable;
import cn.loveyx815.blog.entity.OneCategory;
import cn.loveyx815.blog.entity.TwoCategory;
import cn.loveyx815.blog.service.AdminService;
import cn.loveyx815.blog.service.CommentService;
import cn.loveyx815.blog.service.UserService;
import cn.loveyx815.blog.util.Utils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping(value = "/admin")
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @RequestMapping(value = "/views/index")
    public String index(){
        return "/adminindex";
    }
    @RequestMapping(value = "/lable/view")
    public String toView(){
        return "/adminlable";
    }
    @RequestMapping(value = "/blog/view")
    public String toViewBlog(){
        return "/adminblog";
    }

    @RequestMapping(value = "/comment/view")
    public String toViewComment(){
        return "/admincomment";
    }
    @RequestMapping(value = "/category/view")
    public String toViewCategory(){
        return "/admincategory";
    }
    @RequestMapping(value = "/message/view")
    public String toViewMessage(){
        return "/adminmessage";
    }
    @RequestMapping(value = "/views/blog")
    public String blog(){
        return "/admin/index";
    }
    @RequestMapping(value = "/userhtml")
    public String toUser(){
        return "/admin/userlist";
    }

    @RequestMapping(value = "/user/{curpage}/{size}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUserList(@PathVariable String curpage,@PathVariable String size){
        Integer indexstart=0;
        Integer limitsize=10;
        if (!StringUtils.isEmpty(curpage)){
            indexstart=Integer.parseInt(curpage);
        }
        if (!StringUtils.isEmpty(size)){
            limitsize=Integer.parseInt(size);
        }
        List<Map<String ,Object>> list=adminService.getAllUserLimit((indexstart-1)*limitsize,limitsize);
        return Utils.JSONDataReturn(list,"200");
    }
    @RequestMapping(value = "/blog/{curpage}/{size}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getBlogList(@PathVariable String curpage,@PathVariable String size){
        Integer indexstart=0;
        Integer limitsize=10;
        if (!StringUtils.isEmpty(curpage)){
            indexstart=Integer.parseInt(curpage);
        }
        if (!StringUtils.isEmpty(size)){
            limitsize=Integer.parseInt(size);
        }
        List<Map<String ,Object>> list=adminService.getBlogList((indexstart-1)*limitsize,limitsize);
        return Utils.JSONDataReturn(list,"200");
    }

    @RequestMapping(value = "/comment/{curpage}/{size}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getCommentList(@PathVariable String curpage,@PathVariable String size){
        Integer indexstart=0;
        Integer limitsize=10;
        if (!StringUtils.isEmpty(curpage)){
            indexstart=Integer.parseInt(curpage);
        }
        if (!StringUtils.isEmpty(size)){
            limitsize=Integer.parseInt(size);
        }
        List<Map<String ,Object>> list=commentService.getCommentForAdmin((indexstart-1)*limitsize,limitsize);
        return Utils.JSONDataReturn(list,"200");
    }

    @RequestMapping(value = "/category/{curpage}/{size}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getCategoryList(@PathVariable String curpage,@PathVariable String size){
        Integer indexstart=0;
        Integer limitsize=10;
        if (!StringUtils.isEmpty(curpage)){
            indexstart=Integer.parseInt(curpage);
        }
        if (!StringUtils.isEmpty(size)){
            limitsize=Integer.parseInt(size);
        }
        List<Map<String ,Object>> list=adminService.getCategoryForAdmin((indexstart-1)*limitsize,limitsize);
        return Utils.JSONDataReturn(list,"200");
    }


    @RequestMapping(value = "/lable/{curpage}/{size}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getLableList(@PathVariable String curpage,@PathVariable String size){
        Integer indexstart=0;
        Integer limitsize=10;
        if (!StringUtils.isEmpty(curpage)){
            indexstart=Integer.parseInt(curpage);
        }
        if (!StringUtils.isEmpty(size)){
            limitsize=Integer.parseInt(size);
        }
        List<Map<String ,Object>> list=adminService.getAllLableLimit((indexstart-1)*limitsize,limitsize);
        return Utils.JSONDataReturn(list,"200");
    }

    @RequestMapping(value = "/user/count",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUserCount(){
      Integer count=adminService.getUserCount();

        return Utils.JSONDataReturn(count,"200");
    }

    @RequestMapping(value = "/comment/count",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getCommentCount(){
        Integer count=commentService.getCommentCount();

        return Utils.JSONDataReturn(count,"200");
    }

    @RequestMapping(value = "/lable/count",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getLableCount(){
        Integer count=adminService.getLableCount();

        return Utils.JSONDataReturn(count,"200");
    }

    @RequestMapping(value = "/blog/count",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getBlogCount(){
        Integer count=adminService.getBlogCount();

        return Utils.JSONDataReturn(count,"200");
    }
    @RequestMapping(value = "/category/count",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getCategoryCount(){
        Integer count=adminService.getCategoryCount();

        return Utils.JSONDataReturn(count,"200");
    }
    @RequestMapping(value = "/message/count",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getMessageCount(){
        Integer count=adminService.getMessageCount();

        return Utils.JSONDataReturn(count,"200");
    }

    @RequestMapping(value = "/user/del/{id}")
    @ResponseBody
    public Map<String, Object> delUserById(@PathVariable String id){
        userService.delUserById(id);
        return Utils.JSONDataReturn("删除成功","200");
    }

    @RequestMapping(value = "/lable/del/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> delLableById(@PathVariable String id){
        adminService.delLableById(id);
        return Utils.JSONDataReturn("删除成功","200");
    }
    @RequestMapping(value = "/comment/del/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> delCommentById(@PathVariable String id){
        commentService.delCommentById(id);
        return Utils.JSONDataReturn("删除成功","200");
    }

    @RequestMapping(value = "/message/del/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> delMessageById(@PathVariable String id){
        adminService.delMessageById(id);
        return Utils.JSONDataReturn("删除成功","200");
    }

    @RequestMapping(value = "/lable/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateLable(@PathVariable String id, HttpServletRequest request){
        String lablename=request.getParameter("lablename");
        Lable lable=new Lable();
        lable.setlName(lablename);
        lable.setId(id);
        adminService.updateLable(lable);
        return Utils.JSONDataReturn("修改成功","200");
    }

    @RequestMapping(value = "/lable",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveLable( HttpServletRequest request){
        String id= UUID.randomUUID().toString();
        String lablename=request.getParameter("lablename");
        Lable lable=new Lable();
        lable.setlName(lablename);
        lable.setId(id);
        adminService.saveLable(lable);
        return Utils.JSONDataReturn("添加成功","200");
    }

    @RequestMapping(value = "/category/{sid}",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateSecondCategory( @PathVariable String sid, HttpServletRequest request){

        String name=request.getParameter("sname");
        adminService.updateSecond(sid,name);
        return Utils.JSONDataReturn("修改成功","200");
    }
    @RequestMapping(value = "/category/firstcategory",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveFirstCategory( HttpServletRequest request){
        String id= UUID.randomUUID().toString();
        String firstname=request.getParameter("firstname");
        Integer maxcodeid=adminService.getMaxId();
        adminService.saveToCode(id,maxcodeid,firstname);
        OneCategory category= new OneCategory();
        category.setcCodeId(maxcodeid);
        category.setId(id);
        adminService.saveFirstCatgory(category);
        return Utils.JSONDataReturn("添加成功","200");
    }

    @RequestMapping(value = "/category/secondcategory",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveSecondCategory( HttpServletRequest request){
        String id= UUID.randomUUID().toString();
        String firstId=request.getParameter("firstid");
        String secondname=request.getParameter("secondname");
        Integer fid;
        if (!StringUtils.isEmpty(firstId)){
            fid=Integer.parseInt(firstId);
        }
        else {
            return Utils.JSONDataReturn("数据转换异常","301");
        }
        Integer maxcodeid=adminService.getMaxId();
        adminService.saveToCode(id,maxcodeid,secondname);
        TwoCategory category= new TwoCategory();
        category.setcCodeId(maxcodeid);
        category.setId(id);
        category.setcCodePid(fid);
        adminService.saveSecondCatgory(category);
        return Utils.JSONDataReturn("添加成功","200");
    }

}
