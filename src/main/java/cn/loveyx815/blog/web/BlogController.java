package cn.loveyx815.blog.web;

import cn.loveyx815.blog.entity.Blog;
import cn.loveyx815.blog.entity.Lable;
import cn.loveyx815.blog.service.BlogService;
import cn.loveyx815.blog.service.CommentService;
import cn.loveyx815.blog.service.LableService;
import cn.loveyx815.blog.util.CookieUtil;
import cn.loveyx815.blog.util.RedisUtil;
import cn.loveyx815.blog.util.Utils;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.*;

@Controller
/*@RequestMapping(value = "/blog")*/
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private LableService lableService;
    @Autowired
    private CommentService commentService;

    private Logger logger= LoggerFactory.getLogger(BlogController.class);



    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/")
    public String defaultToIndex() {
        return "index";
    }

    @RequestMapping(value = "/message")
    public String toMessage() {
        return "message";
    }
    @RequestMapping(value = "/blog/viewlink")
    public String toViewBlog() {
        return "bloglist";
    }

    @RequestMapping(value = "/blog/viewlink/{categoryid}/{page}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> viewBlogList(@PathVariable String categoryid, @PathVariable String page, HttpServletRequest request, HttpServletResponse response) {
        if (categoryid!=null){
            CookieUtil.removeCookie(request,response,"categoryid");
        }
        int curpage=Integer.parseInt(page);
        int cursize=10;
        Map<String,Object> map= blogService.getCategoryContentByPage(curpage,cursize,categoryid);
        return Utils.JSONDataReturn(map,"200");
    }

    @RequestMapping(value = "/blog/lable", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getIndexLableCount() {
        return Utils.JSONDataReturn(blogService.getLable(), "200");

        // return blogService.getCategory();
    }

    @RequestMapping(value = "/blog/category", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getIndexCategory() {
        return Utils.JSONDataReturn(blogService.getCategory(), "200");

        // return blogService.getCategory();
    }

    @RequestMapping(value = "/blog/lable/bq", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getLable() {
        return Utils.JSONDataReturn(lableService.getLable(), "200");

        // return blogService.getCategory();
    }


    @RequestMapping(value = "/blog/login", method = RequestMethod.GET)
    public String redLogin() {
        return "login";
    }

    @RequestMapping(value = "/blog/write", method = RequestMethod.GET)
    public String writeBlog() {
        return "edit";
    }
    /**
    * @Description: 保存博客
    * @Param: [str, request]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: Yonggang Shi
    * @Date: 2019/5/2 0002
    */
    @RequestMapping(value = "/blog/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveBlog(@RequestBody String str, HttpServletRequest request,HttpServletResponse response) {
        String bq = "";//标签初始化
        Blog blog = new Blog();
        List<Lable> lableList = new ArrayList<>();
        Lable lable = null;
        JSONObject json = null;
        json = JSONObject.parseObject(str);
        Set<String> keys = json.keySet();
        for (String key : keys) {
            String value = json.getString(key);
            if (StringUtils.equals(value, "on")) {
                String[] split = StringUtils.split(key, ",");
                //对自定义标签和已有标签分类保存和添加
                if (StringUtils.contains(key, "bq")) {
                    lable = new Lable();
                    String lableid = UUID.randomUUID().toString();
                    lable.setId(lableid);
                    lable.setlName(split[1]);
                    lableList.add(lable);
                    if (StringUtils.isEmpty(bq)) {
                        bq += lableid;
                    } else {
                        bq += "," + lableid;
                    }

                } else {
                    if (StringUtils.isEmpty(bq)) {
                        bq += key;
                    } else {
                        bq += "," + key;
                    }
                }
            }
        }
        //新建的标签保存
        if (!lableList.isEmpty()) {
            blogService.saveLable(lableList);
        }
        String content = json.getString("test-editormd-html-code");
        String md = json.getString("test-editormd-markdown-doc");
        String title = json.getString("title");
        String categoryselect = json.getString("categoryselect");
        String categoryselect2 = json.getString("categoryselect2");
        String cover=json.getString("cover");
        if(StringUtils.isEmpty(cover)){
            cover="http://loveyx815.cn/resources/upload/default.jpg" +
                    "";
        }
        String uid = null;
        //获取用户凭证
        Cookie token = CookieUtil.getCookie(request, "token");
        String cookiekey = null;
        Jedis jedis = RedisUtil.getInstance().getJedis();
        if (jedis != null && token != null) {
            cookiekey = token.getValue();
            //去用户权限认证中心的缓存中拿出用户信息
            String jsonstr = jedis.get(cookiekey);
            JSONObject jsonObject = JSONObject.parseObject(jsonstr);
            uid = jsonObject.get("id").toString();//获取当前用户id
            blog.setcPicAdr(cover);
            blog.setcContent(content);
            blog.setcMd(md);
            blog.setcLableId(bq);
            blog.setcTime(new Date());
            blog.setcTitle(title);
            blog.setcUid(uid);
            blog.setcFirstId(categoryselect);
            blog.setcSecondId(categoryselect2);
            blog.setId(UUID.randomUUID().toString());
            blogService.saveBlog(blog);
            CookieUtil.removeCookie(request,response,"cover");
        } else {
            return Utils.JSONDataReturn("发布失败！", "300");
        }
        return Utils.JSONDataReturn(blog.getId(), "200");
    }

    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    public void hello(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "editormd-image-file", required = false) MultipartFile attach) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            String rootPath = request.getSession().getServletContext().getRealPath("/resources/upload/");

            /**
             * 文件路径不存在则需要创建文件路径
             */
            File filePath = new File(rootPath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            String filename=UUID.randomUUID().toString();
            String picadr="http://loveyx815.cn/resources/upload/"+filename;
            //最终文件名
            File realFile = new File(rootPath + File.separator + filename);
            FileUtils.copyInputStreamToFile(attach.getInputStream(), realFile);
            //保存博客内容临时图片路径
            CookieUtil.setCookie(response,"cover",picadr);
            //下面response返回的json格式是editor.md所限制的，规范输出就OK
            response.getWriter().write("{\"success\": 1, \"message\":\"上传成功\",\"url\":\"" + picadr+ "\"}");
        } catch (Exception e) {
            try {
                response.getWriter().write("{\"success\":0}");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }


    @RequestMapping(value = "/view/{id}",method = RequestMethod.GET)

    public String getMdToHtml(HttpServletResponse response,HttpServletRequest request,@PathVariable String id){
        Cookie token = CookieUtil.getCookie( request, "token");
        if (token==null){
            String uuid = UUID.randomUUID().toString();
            CookieUtil.setCookie(response,"visitor",uuid);
            CookieUtil.setCookie(response,uuid+"-blogid",id);
        }
        else {
            Jedis jedis = RedisUtil.getInstance().getJedis();
            String user = jedis.get(token.getValue());
            JSONObject jsonObject = JSONObject.parseObject(user);
            CookieUtil.setCookie(response,jsonObject.getString("id")+"-blogid",id);
        }


        return "view";
    }


    @RequestMapping(value = "/blog/tab/{tabid}/{page}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getTabContentPageById(@PathVariable String tabid, @PathVariable String page){
        int curPage=Integer.parseInt(page);
        int curSize=10;
        Map<String, Object> map = blogService.getCategoryContentByPage(curPage, curSize, tabid);
        return Utils.JSONDataReturn(map,"200");
    }
    /** 
    * @Description: 搜索博客 
    * @Param: [request] 
    * @return: java.util.Map<java.lang.String,java.lang.Object> 
    * @Author: Yonggang Shi
    * @Date: 2019/5/2 0002 
    */ 
    @RequestMapping(value = "/blog/serach",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAllSerach(HttpServletRequest request){
       String serachstr = request.getParameter("serach");
       //获取后台的查询数据，返回HTML代码
        Map<String, Object> map = blogService.getAllSerach(serachstr);
        if (map.isEmpty()){
            return Utils.JSONDataReturn("没有查到数据哦","300");
        }
        return Utils.JSONDataReturn(map,"200");
    }
    /**
    * @Description: 根据文章ID查看文章
    * @Param: [id]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: Yonggang Shi
    * @Date: 2019/5/2 0002
    */
    @RequestMapping(value = "/blog/view/article/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getArticleById(@PathVariable String id){
        //获取文章内容
        Map<String, Object> blog=blogService.getArticleById(id);
        //增加文章浏览次数
        blogService.addCountVisit(id);
        return Utils.JSONDataReturn(blog,"200");
    }

    @RequestMapping(value = "/blog/comment/{id}/{page}")
    @ResponseBody
    public Map<String, Object> getCommentByPage(@PathVariable String id, @PathVariable String page){
        int curPage=Integer.parseInt(page);
        int curSize=10;
        Map<String, Object> map = commentService.getCommentByPage(curPage, curSize, id);
        return Utils.JSONDataReturn(map,"200");
    }

    @RequestMapping(value = "/blog/addcomment/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addComment(@PathVariable String id,HttpServletRequest request) {
        String cid = UUID.randomUUID().toString();
        String content = request.getParameter("content");
        String uid = null;


        Cookie token = CookieUtil.getCookie(request, "token");
        String cookiekey = null;


       /* if (token!=null){
            key=token.getValue();
        }*/
        Jedis jedis = RedisUtil.getInstance().getJedis();
        if (jedis != null && token != null) {
            cookiekey = token.getValue();
            String jsonstr = jedis.get(cookiekey);
            JSONObject jsonObject = JSONObject.parseObject(jsonstr);
            uid = jsonObject.getString("id");//获取当前用户id
        }
        try {
            commentService.addComment(content, uid, id, new Date(), cid);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            return Utils.JSONDataReturn("评论失败", "301");
        }
        return Utils.JSONDataReturn("评论成功", "200");
    }

    @RequestMapping(value = "/blog/del/{id}")
    @ResponseBody
    public Map<String, Object> delArticleById(@PathVariable String id){
        blogService.delArticleById(id);
        //blogService.addCountVisit(id);
        return Utils.JSONDataReturn("删除成功","200");
    }


    @RequestMapping(value = "/blog/edit")

    public String  toEditBlog(){
      return "editblog";
    }

    @RequestMapping(value = "/blog/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateBlog(@PathVariable String id, HttpServletRequest request){
        String title=request.getParameter("title");
        String md=request.getParameter("test-editormd-markdown-doc");
        String content=request.getParameter("test-editormd-html-code");
        Blog blog=new Blog();
        blog.setcTitle(title);
        blog.setcMd(md);
        blog.setcContent(content);
        blog.setId(id);
        blogService.updateBlog(blog);
        return Utils.JSONDataReturn(id,"200");

    }

    @RequestMapping(value = "/blog/comment/user/{uid}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAllCommentByUser(@PathVariable String uid){
       List<Map<String, Object>> list= commentService.getAllCommentByUser(uid);
        return Utils.JSONDataReturn(list,"200");
    }

}
