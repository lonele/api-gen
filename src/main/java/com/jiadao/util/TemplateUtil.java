
package com.jiadao.util;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateUtil {
 
   
 
    public static void main(String[] args) {
        Object[] obj = new Object[]{"张三", String.format("%.2f", 10.155), 10};
        System.out.println(processFormat("您好%s，晚上好！您目前余额：%s元，积分：%d", obj));
        System.out.println(processMessage("您好{0}，晚上好！您目前余额：{1}元，积分：{2}", obj));
 
        Map map = new HashMap();
        map.put("name", "张三");
        map.put("money", String.format("%.2f", 10.155));
        map.put("point", 10);
        System.out.println(processTemplate("您好${name}，晚上好！您目前余额：${money}元，积分：${point}", map));
        // System.out.println(processFreemarker("您好${name}，晚上好！您目前余额：${money}元，积分：${point}", map));
    }
 
    /**
     * String.format渲染模板
     * @param template 模版
     * @param params   参数
     * @return
     */
    public static String processFormat(String template, Object... params) {
        if (template == null || params == null)
            return null;
        return String.format(template, params);
    }
 
    /**
     * MessageFormat渲染模板
     * @param template 模版
     * @param params   参数
     * @return
     */
    public static String processMessage(String template, Object... params) {
        if (template == null || params == null)
            return null;
        return MessageFormat.format(template, params);
    }
 
   
    /** 
    * 简单实现${}模板功能 
    * 如${aa} cc ${bb} 其中 ${aa}, ${bb} 为占位符. 可用相关变量进行替换 
    * @param template 模板字符串 
    * @param params     替换的变量值 
    * @param defaultNullReplaceVals  默认null值替换字符, 如果不提供, 则为字符串"" 
    * @return 返回替换后的字符串, 如果模板字符串为null, 则返回null 
    */  
    public static String processTemplate(String template, Map<String, Object> params) {
        if (template == null)
            return null;
        if ( params == null){
            params = Collections.emptyMap();  
        }
        StringBuffer sb = new StringBuffer(template.length());
        Matcher matcher = Pattern.compile("\\$\\{(\\w+)\\}").matcher(template);
        while (matcher.find()) {
            String param = matcher.group(1);
            Object value = params.get(param);
            matcher.appendReplacement(sb, value == null ? "{"+param+" not found}" : value.toString());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /** 
    * 简单实现${}模板功能 
    * 如${aa} cc ${bb} 其中 ${aa}, ${bb} 为占位符. 可用相关变量进行替换 
    * @param templateStr 模板字符串 
    * @param data     替换的变量值 
    * @param defaultNullReplaceVals  默认null值替换字符, 如果不提供, 则为字符串"" 
    * @return 返回替换后的字符串, 如果模板字符串为null, 则返回null 
    */  
      
@SuppressWarnings("unchecked")  
public static String simpleTemplate(String templateStr, Map<String, ?> data, String... defaultNullReplaceVals) {  
    if(templateStr == null) return null;  
      
    if(data == null) data = Collections.EMPTY_MAP;  
          
    String nullReplaceVal = defaultNullReplaceVals.length > 0 ? defaultNullReplaceVals[0] : "";  
    // Pattern pattern = Pattern.compile("\\$\\{([^}]+)}");  
    Pattern pattern = Pattern.compile("\\$\\{(\\w+)}");  
          
    StringBuffer newValue = new StringBuffer(templateStr.length());  
  
    Matcher matcher = pattern.matcher(templateStr);  
  
    while (matcher.find()) {  
        String key = matcher.group(1);  
        String r = data.get(key) != null ? data.get(key).toString() : nullReplaceVal;  
        matcher.appendReplacement(newValue, r.replaceAll("\\\\", "\\\\\\\\")); //这个是为了替换windows下的文件目录在java里用\\表示  
    }  
  
    matcher.appendTail(newValue);  
  
    return newValue.toString();  
}  
 
    // public static Configuration cfg;
 
    // static {
    //     cfg = new Configuration(new Version("2.3.23"));
    // }
    // /**
    //  * Freemarker渲染模板
    //  * @param template 模版
    //  * @param params   参数
    //  * @return
    //  */
    // public static String processFreemarker(String template, Map<String, Object> params) {
    //     if (template == null || params == null)
    //         return null;
    //     try {
    //         StringWriter result = new StringWriter();
    //         Template tpl = new Template("strTpl", template, cfg);
    //         tpl.process(params, result);
    //         return result.toString();
    //     } catch (Exception e) {
    //         return null;
    //     }
    // }
 
}