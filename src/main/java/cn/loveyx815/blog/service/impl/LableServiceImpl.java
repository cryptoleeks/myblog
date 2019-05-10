package cn.loveyx815.blog.service.impl;

import cn.loveyx815.blog.dao.LableDao;
import cn.loveyx815.blog.service.LableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class LableServiceImpl implements LableService {

    @Autowired
    private LableDao lableDao;
    @Override
    public Object getLable() {
        List<Map<String, Object>> lableAndValue = lableDao.getAllLableAndValue(0, 15);
        StringBuffer sb=new StringBuffer();
        if (!lableAndValue.isEmpty()){
            for (Map<String ,Object> map: lableAndValue){
               sb.append( "<a class=\"layui-word-aux\" onclick=\"setCookie('categoryid','"+map.get("id")+"')\"  href=\"/blog/viewlink\" title=\""+map.get("name")+"标签下有"+map.get("count")+"篇文章\">\n" +
                        "                            <span class=\"layui-badge layui-bg-gray \">"+map.get("name")+"("+map.get("count")+")</span>\n" +
                        "                        </a>");
            }
            return  sb.toString();
        }
        return "获取标签失败";
    }
}
