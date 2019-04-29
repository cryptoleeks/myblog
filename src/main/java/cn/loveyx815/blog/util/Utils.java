package cn.loveyx815.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static Map<String ,Object> JSONDataReturn(Object data, String code){
        Map<String ,Object> json=new HashMap<>();
        if(code!=null&&"200".equals(code)){

            json.put("msg","接口调用成功");

        }else {

            json.put("msg","接口调用失败");
        }
        json.put("code",code);
        json.put("data",data);
        return json;
    }

    public static String  getFormatTime(Date date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(date);
    }
}
