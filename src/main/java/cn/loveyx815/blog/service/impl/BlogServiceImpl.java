package cn.loveyx815.blog.service.impl;

import cn.loveyx815.blog.dao.BlogDao;
import cn.loveyx815.blog.dao.LableDao;
import cn.loveyx815.blog.entity.Blog;
import cn.loveyx815.blog.entity.Category;
import cn.loveyx815.blog.entity.Lable;
import cn.loveyx815.blog.service.BlogService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao dao;
    @Autowired
    private LableDao lableDao;

    private Logger logger= LoggerFactory.getLogger(BlogDao.class);

    @Override
    public List<Object> getCategory() {
        List<Map<String, Object>> category = dao.getCategory();
        List<Object> categoryList=new ArrayList<Object>();
        String pid=null;
        List<Map<String ,Object>> childList=null;
        Category category1=null;
        for (Map<String,Object> map:category) {
            if (pid==null||!StringUtils.equals(pid,map.get("pid").toString())) {
                if (childList!=null&&!childList.isEmpty()){//类别孩子节点不为空就插入该节点
                    category1.setChildList(childList);
                    categoryList.add(category1);
                }
                pid = map.get("pid").toString();
                category1=new Category();

                category1.setPid(pid);
                category1.setPname(map.get("pname").toString());

                childList=new ArrayList<Map<String, Object>>();
            }


            if(map.get("sid")==null||StringUtils.isEmpty(map.get("sid").toString())){
                categoryList.add(category1);
            }
            else {
                Map<String,Object> child=new HashMap<String, Object>();
                child.put("sid",map.get("sid").toString());
                child.put("sname",map.get("sname").toString());
                childList.add(child);
            }

        }
        return categoryList;
    }

    @Override
    public void saveBlog(Blog blog){
        dao.insertSelective(blog);

    }

    @Override
    public void saveLable(List<Lable> lableList) {
        lableDao.saveLable(lableList);
    }

    @Override
    public Map<String ,Object>  getArticleById(String id){
        return dao.getArticleById(id);
    }

    @Override
    public void addCountVisit(String id){
        dao.updateValueById(id);
    }

    @Override
    public List<Blog> getBlogByKid(String kidid) {
        return null;
    }

    @Override
    public Map<String, Object> getCategoryContentByPage(int curPage, int curSize, String tabid){


        int count=dao.getCountById1(tabid);
        int totalPage= (int) Math.ceil(count*1.0/curSize);
        List<Map<String, Object>> list=null;
        Map<String,Object> map=null;
        if (curPage>totalPage){
            return new HashMap<>();
        }
        list= dao.getCategoryContentByPage((curPage-1)*curSize,curSize,tabid);
        if (!list.isEmpty()){
            String content="";
            int i=0;
            map=new HashMap<>();
            String category="";
            for (Map<String,Object> comment:list) {


                content+="<div class=\"layui-row layui-anim-fadein\n\" id=\"contentlist"+comment.get("id") +"\" name=\"contentlist\" >\n" +
                        "                                <div class=\"layui-col-md3\">\n" +
                        "                                    <div style=\"width: 120px;height: 120px\">\n" +
                        "<img style=\"width: 180px;height: 113px\" src=\""+comment.get("c_pic_adr")+"\" >"+
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"layui-col-md9\">\n" +
                        "                                    <div class=\"layui-card\">\n" +
                        "                                        <div class=\"layui-card-header\">"+
                                                                "<a href=\"/view/"+comment.get("id")+"\"><h3>标题："+comment.get("c_title")+"</h3></a>"
                                                                    +"</div>\n" +
                        "                                        <div class=\"layui-card-body\">\n" +
                        "                                            <div class=\"layui-elip\" id=\"abstract"+comment.get("id") +"\">\n" +
                        "                                            </div>\n" +
                        "                                            <div class=\"layui-hide\t\" id=\"hide"+comment.get("id") +"\">内容："+comment.get("c_content") +"</div>\n" +
                        "                                            <script>\n" +
                        "                                                $(\"#abstract"+comment.get("id") +"\").text($('#hide"+comment.get("id") +"')[0].textContent);\n" +
                        "                                            </script>\n"+"<div class=\"layui-row\">\n"+
                        "<div class=\"layui-col-md5\">\n"+
                        " <i class=\"layui-icon layui-icon-use\">&#xe770;"+comment.get("c_name") +"</i>\n"+
                        " <i class=\"layui-icon layui-icon-date\">&#xe637;"+comment.get("c_time")+"</i>\n"+"</div>"+
                        " <div class=\"layui-col-md3 layui-col-md-offset4\">"+
                        " <i class=\"layui-icon\">&#xe60e;"+comment.get("c_value") +"</i>\n"+
                        " <i class=\"layui-icon\">&#xe63a;"+comment.get("comment") +"</i>\n"+

                        "</div>\n"+ " </div>\n"+
                        "                                        </div>\n"+
                        "                                    </div>\n"+
                        "                                </div>\n"+
                        "                            </div>";













                i++;

            }
            if (list.get(0).get("sname")!=null){
                category= (String) list.get(0).get("sname");
            }
            else {
                category=list.get(0).get("fname").toString();
            }
            map.put("category",category);
            map.put("content",content);
        }
        map.put("totolpage",totalPage);
        return  map;
    }

    @Override
    public void delArticleById(String id) {
        dao.deleteByPrimaryKey(id);
    }

    @Override
    public void updateBlog(Blog blog) {
        dao.updateByPrimaryKeySelective(blog);
    }

    @Override
    public List<Map<String, Object>> getBlogByCategory(String categoryid) {
        return dao.getBlogByCategory(categoryid);
    }

    @Override
    public List<Map<String, Object>> getLable() {
        List<Map<String, Object>> lable = lableDao.getAllLable();

        return lable;
    }

    @Override
    public Map<String, Object> getAllSerach(String serachstr) {
        int count=dao.getCountBySerach(serachstr);
        //int totalPage= (int) Math.ceil(count*1.0/curSize);
        List<Map<String, Object>> list=null;
        Map<String,Object> map=null;
        if (count ==0){
            return  new HashMap<>();
        }
        list= dao.getAllSerach(serachstr);
        if (!list.isEmpty()){
            String content="";
            int i=0;
            map=new HashMap<>();
            String category="";
            for (Map<String,Object> comment:list) {


                content+="<div class=\"layui-row layui-anim-fadein\n\" id=\"sercahlist"+comment.get("id") +"\" name=\"contentlist\" >\n" +
                        "                                <div class=\"layui-col-md3\">\n" +
                        "                                    <div style=\"width: 120px;height: 120px\">\n" +
                        "<img style=\"width: 120px;height: 110px\" src=\""+comment.get("c_pic_adr")+"\" >"+
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                                <div class=\"layui-col-md9\">\n" +
                        "                                    <div class=\"layui-card\">\n" +
                        "                                        <div class=\"layui-card-header\">"+
                        "<a href=\"/view/"+comment.get("id")+"\"><h3>标题："+comment.get("c_title")+"</h3></a>"
                        +"</div>\n" +
                        "                                        <div class=\"layui-card-body\">\n" +
                        "                                            <div class=\"layui-elip\" id=\"serachabstract"+comment.get("id") +"\">\n" +
                        "                                            </div>\n" +
                        "                                            <div class=\"layui-hide\t\" id=\"serachhide"+comment.get("id") +"\">内容："+comment.get("c_content") +"</div>\n" +
                        "                                            <script>\n" +
                        "                                                $(\"#serachabstract"+comment.get("id") +"\").text($('#serachhide"+comment.get("id") +"')[0].textContent);\n" +
                        "                                            </script>\n"+"<div class=\"layui-row\">\n"+
                        "<div class=\"layui-col-md6\">\n"+
                        " <i class=\"layui-icon layui-icon-use\">&#xe770;"+comment.get("c_name") +"</i>\n"+
                        " <i class=\"layui-icon layui-icon-date\">&#xe637;"+comment.get("c_time")+"</i>\n"+"</div>"+
                        " <div class=\"layui-col-md3 layui-col-md-offset3\">"+
                        " <i class=\"layui-icon\">&#xe60e;"+comment.get("c_value") +"</i>\n"+
                        " <i class=\"layui-icon\">&#xe63a;"+comment.get("comment") +"</i>\n"+

                        "</div>\n"+ " </div>\n"+
                        "                                        </div>\n"+
                        "                                    </div>\n"+
                        "                                </div>\n"+
                        "                            </div>";













                i++;

            }

            map.put("content",content);
        }
        map.put("count",count);
        return map;
    }


}
