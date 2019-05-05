package cn.loveyx815.blog.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AdminDao {
    List<Map<String, Object>> getAllUser();

    Integer getUserCount();

    List<Map<String ,Object>> getAllBlog(
            @Param("indexstart") Integer indexstart,
            @Param("pagesize") Integer pagesize) ;
    Integer getMaxId();
    void saveCateCode(
            @Param("id") String id,
                     @Param("cid") int cid,
                     @Param("cname") String cname);

    Integer getCategoryCount();

    List<Map<String, Object>> getCategoryForAdmin(
            @Param("cursize") int i,
            @Param("pagesize") Integer limitsize);

    void updateSecond(@Param("sid") String sid,@Param("name") String name);

    void delCategoryFirstById(@Param("id") Integer id);

    void delCategorySecondById(@Param("id") Integer id);
}
