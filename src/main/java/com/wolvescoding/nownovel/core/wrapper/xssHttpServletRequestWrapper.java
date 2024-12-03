package com.wolvescoding.nownovel.core.wrapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.HashMap;
import java.util.Map;

public class xssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private static final Map<String, String> REPLACE_RULE = new HashMap<>();

    static {
       // REPLACE_RULE.put("script", "");
        REPLACE_RULE.put("<", "&lt;");
        REPLACE_RULE.put(">", "&gt;");
    }
    public xssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }
    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if(values != null){
            int length = values.length;
            String[] escapeValues = new String[length];
            for(int i =0; i<length;i++){
                escapeValues[i] =values[i];
                int index = i;
                REPLACE_RULE.forEach((k,v)->
                    escapeValues[index] = escapeValues[index].replaceAll(k, v));//根据 REPLACE_RULE 中的规则，使用 replaceAll 替换值中的特殊字符。
            }
            return escapeValues;
        }
        return new String[0];//如果为空，就返回一个空数组
    }
}
