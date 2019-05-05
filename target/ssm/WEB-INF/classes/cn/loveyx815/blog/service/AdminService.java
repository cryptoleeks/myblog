package cn.loveyx815.blog.service;

import cn.loveyx815.blog.entity.Lable;
import cn.loveyx815.blog.entity.OneCategory;
import cn.loveyx815.blog.entity.TwoCategory;

import java.util.List;
import java.util.Map;

public interface AdminService {
    List<Map<String, Object>> getAllUser();

    Integer getUserCount();


    List<Map<String, Object>> getAllUserLimit(Integer indexstart, Integer limitsize);

    Integer getLableCount();

    List<Map<String, Object>> getAllLableLimit(int i, Integer limitsize);

    void updateLable(Lable lable);

    void delLableById(String id);

    void saveLable(Lable lable);

    List<Map<String, Object>> getBlogList(int i, Integer limitsize);

    Integer getBlogCount();

    Integer getMaxId();

    void saveToCode(String id, Integer maxcodeid, String firstname);

    void saveFirstCatgory(OneCategory category);

    void saveSecondCatgory(TwoCategory category);

    Integer getCategoryCount();

    List<Map<String, Object>> getCategoryForAdmin(int i, Integer limitsize);

    void updateSecond(String sid, String name);

    Integer getMessageCount();

    void delMessageById(String id);

    List<Map<String, Object>> getMessageForAdmin(int i, Integer limitsize);

    void delCategoryFirstById(Integer id);

    void delCategorySecondById(Integer id);
}
