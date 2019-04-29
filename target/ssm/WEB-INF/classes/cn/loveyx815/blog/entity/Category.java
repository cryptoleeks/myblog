package cn.loveyx815.blog.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class Category {
    String pid;
    String pname;
    List<Map<String ,Object>> childList;

}
