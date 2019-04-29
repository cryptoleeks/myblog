package cn.loveyx815.blog.service.impl;

import cn.loveyx815.blog.dao.*;
import cn.loveyx815.blog.entity.Lable;
import cn.loveyx815.blog.entity.OneCategory;
import cn.loveyx815.blog.entity.TwoCategory;
import cn.loveyx815.blog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private LableDao lableDao;
    @Autowired
    private BlogDao blogDao;
    @Autowired
    private OneCategoryDao oneCategoryDao;
    @Autowired
    private TwoCategoryDao twoCategoryDao;
    @Autowired
    private MessageDao messageDao;
    @Override
    public List<Map<String, Object>> getAllUser() {

        return adminDao.getAllUser();
    }

    @Override
    public Integer getUserCount() {
        return adminDao.getUserCount();
    }

    @Override
    public List<Map<String, Object>> getAllUserLimit(Integer indexstart, Integer limitsize) {
        return userDao.getAllUserLimit(indexstart,limitsize);
    }

    @Override
    public Integer getLableCount() {
        return lableDao.getLableCount();
    }

    @Override
    public List<Map<String, Object>> getAllLableLimit(int indexstart, Integer limitsize) {
        return (List<Map<String, Object>>) lableDao.getAllLableAndValue(indexstart,limitsize);
    }

    @Override
    public void updateLable(Lable lable) {
        lableDao.updateByPrimaryKeySelective(lable);
    }

    @Override
    public void delLableById(String id) {
        lableDao.deleteByPrimaryKey(id);
    }

    @Override
    public void saveLable(Lable lable) {
        lableDao.insertSelective(lable);
    }

    @Override
    public List<Map<String, Object>> getBlogList(int i, Integer limitsize) {
        return adminDao.getAllBlog(i,limitsize);
    }

    @Override
    public Integer getBlogCount() {
        return blogDao.getBlogCount();
    }

    @Override
    public Integer getMaxId() {
        return adminDao.getMaxId();
    }

    @Override
    public void saveToCode(String id, Integer maxcodeid, String firstname) {
        adminDao.saveCateCode(id,maxcodeid,firstname);
    }

    @Override
    public void saveFirstCatgory(OneCategory category) {
        oneCategoryDao.insertSelective(category);
    }

    @Override
    public void saveSecondCatgory(TwoCategory category) {
        twoCategoryDao.insertSelective(category);
    }

    @Override
    public Integer getCategoryCount() {
        return adminDao.getCategoryCount();
    }

    @Override
    public List<Map<String, Object>> getCategoryForAdmin(int i, Integer limitsize) {
        return adminDao.getCategoryForAdmin(i,limitsize);
    }

    @Override
    public void updateSecond(String sid, String name) {
        adminDao.updateSecond(sid,name);
    }

    @Override
    public Integer getMessageCount() {
        return messageDao.getMessageCount();
    }

    @Override
    public void delMessageById(String id) {
        messageDao.deleteByPrimaryKey(id);
    }


}
