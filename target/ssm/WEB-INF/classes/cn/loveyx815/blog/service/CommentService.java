package cn.loveyx815.blog.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CommentService {
    Map<String,Object> getCommentByPage(int curPage, int curSize, String id);

    void addComment(String content, String uid, String id, Date date, String cid);

    List<Map<String, Object>> getAllCommentByUser(String uid);
    List<Map<String ,Object>> getCommentForAdmin(int curpage, Integer cursize );

    Integer getCommentCount();

    void delCommentById(String id);
}
