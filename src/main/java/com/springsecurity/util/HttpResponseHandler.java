package com.springsecurity.util;

import java.util.HashMap;
import java.util.Map;

public class HttpResponseHandler{


    public static Map<String,Object> buildResponse(String message)
    {
        Map<String,Object> response = new HashMap<>();
        response.put("message",message);
        return response;
    }

    public static Map<String,Object>buildResponse(String message , Object dataObj)
    {
        Map<String,Object> response = new HashMap<>();
        response.put("message",message);
        response.put("data", dataObj);
        return response;
    }
}
