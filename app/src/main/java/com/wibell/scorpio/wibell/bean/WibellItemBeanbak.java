package com.wibell.scorpio.wibell.bean;


import com.wibell.scorpio.wibell.holder.WibellItemHolder;

public class WibellItemBeanbak extends WibellItemHolder {
    private String id;
    private String ip;
    private String alias;
    private String token;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public WibellItemBeanbak(String id, String ip, String alias, String token) {
        this.id = id;
        this.ip = ip;
        this.alias = alias;
        this.token = token;
    }
}
