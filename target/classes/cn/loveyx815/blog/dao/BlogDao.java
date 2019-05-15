package cn.loveyx815.blog.dao;

import cn.loveyx815.blog.entity.Blog;
import cn.loveyx815.blog.entity.Comment;
import cn.loveyx815.blog.entity.Lable;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface BlogDao {
    List<Map<String ,Object>> getCategory();
    List<Blog> getBlogByKid(
            @Param("kidid") String  kidid
    );
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blog
     *
     * @mbg.generated Mon Apr 22 16:15:48 GMT+08:00 2019
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blog
     *
     * @mbg.generated Mon Apr 22 16:15:48 GMT+08:00 2019
     */
    int insert(Blog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blog
     *
     * @mbg.generated Mon Apr 22 16:15:48 GMT+08:00 2019
     */
    int insertSelective(Blog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blog
     *
     * @mbg.generated Mon Apr 22 16:15:48 GMT+08:00 2019
     */
    Blog selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blog
     *
     * @mbg.generated Mon Apr 22 16:15:48 GMT+08:00 2019
     */
    int updateByPrimaryKeySelective(Blog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blog
     *
     * @mbg.generated Mon Apr 22 16:15:48 GMT+08:00 2019
     */
    int updateByPrimaryKey(Blog record);


    Map<String ,Object> getArticleById(String id);

    void updateValueById(String id);


    int getCountById1(@Param("tabid") String tabid);

    List<Map<String, Object>> getCategoryContentByPage(@Param("cur") int cur, @Param("curSize")int curSize,@Param("tabid") String tabid);

    List<Map<String ,Object>> getBlogByUser(@Param("id") String id);


    List<Map<String, Object>> getBlogByCategory(String categoryid);

    Integer getBlogCount();

    int getCountBySerach(@Param("serach") String serachstr);

    List<Map<String, Object>> getAllSerach(@Param("serach")String serachstr);

    String  getCategoryName(@Param("tabid") String tabid);
}