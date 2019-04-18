package com.example.application;

import com.github.scribejava.core.builder.api.DefaultApi20;

public class MyApi extends DefaultApi20 {

    public MyApi() {}

    private static class InstanceHolder {
        private static final MyApi INSTANCE = new MyApi();
    }

    public static MyApi instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "http://192.168.1.102:8080/uaa/oauth/token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return null;
    }


}
