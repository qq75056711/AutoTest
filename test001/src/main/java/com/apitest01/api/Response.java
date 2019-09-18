package com.apitest01.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author xiaomi
 */
public class Response {

    private static final int SUCCESS = 200;
    private static final int ERROR = 0;
    private static final Pattern ERROR_4XX = Pattern.compile("^4(\\d)+");
    private static final Pattern ERROR_5XX = Pattern.compile("^5(\\d)+");

    private int statusCode;
    private Header[] headers;
    private String body;
    private long startTime;
    private long endTime;
    private Locale locale;
    private Request request;
    private String error;

    protected void statusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    protected void headers(Header[] headers) {
        this.headers = headers;
    }

    protected void body(String body) {
        this.body = body;
    }

    protected void startTime(long startTime){
        this.startTime = startTime;
    }

    protected void endTime(long endTime){
        this.endTime = endTime;
    }

    protected void locale(Locale locale){
        this.locale = locale;
    }

    protected void request(Request request) {
        this.request = request;
    }

    protected void error(String error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return this.statusCode == SUCCESS;
    }

    public boolean isError() {
        return this.statusCode == ERROR;
    }

    public int statusCode() {
        return statusCode;
    }

    public Header[] headers() {
        return headers;
    }

    public String body() {
        return body;
    }

    public JSON json() {
        try {
            return JSON.parseObject(body);
        }catch (JSONException e){
            try {
                return JSON.parseArray(body);
            }catch (Exception ex){
                return null;
            }
        }
    }

    public long duration() {
        return endTime - startTime;
    }

    public Locale locale(){
        return locale;
    }

    public Request request() {
        return request;
    }

    public String error() {
        return error;
    }
}
