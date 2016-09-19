package com.wibell.scorpio.wibell.bean;

/**
 * Created by Scorpio on 2016/9/7.
 */
public class PersonBean {
    private int id;
    private String ip;
    private String alias;
    private String token;

    public PersonBean() {
    }

    public PersonBean(int id, String ip, String alias, String token) {
        super();
        this.id = id;
        this.ip = ip;
        this.alias = alias;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ID:" + id + "IP:" + ip + " Alias:" + alias + "Token:" + token;
    }
}
