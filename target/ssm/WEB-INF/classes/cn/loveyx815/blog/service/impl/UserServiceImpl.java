package cn.loveyx815.blog.service.impl;

import cn.loveyx815.blog.dao.BlogDao;
import cn.loveyx815.blog.dao.MessageDao;
import cn.loveyx815.blog.dao.UserDao;
import cn.loveyx815.blog.entity.Message;
import cn.loveyx815.blog.entity.User;
import cn.loveyx815.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private BlogDao blogDao;

    @Autowired
    private MessageDao messageDao;
    public User queryUser(String username,String password) {
        return  userDao.selectByUser(username,password);

    }

    @Override
    public void saveUser(User user) {
        userDao.insertSelective(user);
    }

    @Override
    public void updataJBInfoById(User user) {
        userDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<Map<String, Object>> getBlogByUser(String id) {
        List<Map<String, Object>> list = blogDao.getBlogByUser(id);
        return list;
    }

    @Override
    public User getUserById(String uid) {
        return userDao.selectByPrimaryKey(uid);
    }

    @Override
    public void updatePassword(User user) {
        userDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public void addMessage(Message message) {
        messageDao.insertSelective(message);
    }

    @Override
    public void delUserById(String id) {
        userDao.deleteByPrimaryKey(id);
    }

}
