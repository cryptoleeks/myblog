package cn.loveyx815.blog.service;

import cn.loveyx815.blog.entity.Message;
import cn.loveyx815.blog.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public User queryUser(
            String username,
            String password
    );

    void saveUser(User user);

    void updataJBInfoById(User user);

    List<Map<String, Object>> getBlogByUser(String id);

    User getUserById(String uid);

    void updatePassword(User user);



    void addMessage(Message message1);

    void delUserById(String id);
}
