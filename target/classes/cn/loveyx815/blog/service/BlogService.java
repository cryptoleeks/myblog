package cn.loveyx815.blog.service;

import cn.loveyx815.blog.entity.Blog;
import cn.loveyx815.blog.entity.Lable;

import java.util.List;
import java.util.Map;

public interface BlogService {
    public List<Object> getCategory();
    public  List<Blog> getBlogByKid(String kidid);


    public void saveBlog(Blog blog);

    public void saveLable(List<Lable> lableList);

    Map<String ,Object> getArticleById(String id);

    void addCountVisit(String id);

    Map<String, Object> getCategoryContentByPage(int curPage, int curSize, String tabid);

    void delArticleById(String id);

    void updateBlog(Blog blog);


    List<Map<String, Object>> getBlogByCategory(String categoryid);

    public List<Map<String, Object>> getLable();
}
