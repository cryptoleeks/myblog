package cn.loveyx815.blog.service.impl;

import cn.loveyx815.blog.dao.CommentDao;
import cn.loveyx815.blog.entity.Comment;
import cn.loveyx815.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao dao;
    public Map<String,Object> getCommentByPage(int curPage, int curSize, String id){
        List<Map<String, Object>> list=null;
        Map<String,Object> map=null;
        int count=dao.getCountById(id);
        int totalPage= (int) Math.ceil(count*1.0/curSize);
        if (curPage>totalPage){
            return new HashMap<>();
        }
        list= dao.getCommentByPage((curPage-1)*curSize,curSize,id);
        if (!list.isEmpty()){
            StringBuffer content=new StringBuffer();
            int i=0;
            map=new HashMap<>();
            for (Map<String,Object> comment:list) {
                content.append("<div class=\"layui-row\">\n");
                content.append("                                            <ul class=\"flow-default\" id=\"comment");
                content.append(i);
                content.append("\"><span name=\"span");
                content.append(i);
                content.append("\">");
                content.append(comment.get("name"));
                content.append(":</span>");
                content.append(comment.get("content"));
                content.append("<span style=\"float: right\" name=\"date");
                content.append(i);
                content.append("\">日期：");
                content.append(comment.get("time"));
                content.append("</span></ul>\n </div>\n<hr>");
                i++;
            }
            map.put("content",content);
        }
        map.put("totolpage",totalPage);
        return  map;
    }

    public void addComment(String content, String uid, String id, Date date, String cid){
        Comment comment=new Comment();
        comment.setId(cid);
        comment.setcId(uid);
        comment.setcContent(content);
        comment.setcRefBlog(id);
        comment.setcTime(date);

        dao.insertSelective(comment);
    }

    @Override
    public List<Map<String, Object>> getAllCommentByUser(String uid) {
        List<Map<String, Object>> list =dao.getAllCommentByUser(uid);
        return list;
    }

    @Override
    public List<Map<String, Object>> getCommentForAdmin(int curpage, Integer cursize) {
        return dao.getCommentForAdmin(curpage,cursize);
    }

    @Override
    public Integer getCommentCount() {
        return dao.getCommentCount();
    }

    @Override
    public void delCommentById(String id) {
        dao.deleteByPrimaryKey(id);
    }
}
