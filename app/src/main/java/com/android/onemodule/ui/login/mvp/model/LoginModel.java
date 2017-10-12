package com.android.onemodule.ui.login.mvp.model;

import com.android.cloud.help.SharedPreferencesHelp;
import com.android.onemodule.ui.login.contract.LoginContract;

import java.util.List;
import java.util.Map;

/**
 * Created by radio on 2017/8/8.
 */

public class LoginModel implements LoginContract.Model{
    private String name;
    private String phone;
    private List<String> roles;
    private String city;
    private String address;
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public void saveLoginUser(Map map) {
        SharedPreferencesHelp.saveString("phone", (String) map.get("phone"));
        SharedPreferencesHelp.saveString("password", (String) map.get("password"));
    }
}
