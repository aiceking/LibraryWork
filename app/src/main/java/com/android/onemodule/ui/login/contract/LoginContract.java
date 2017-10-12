package com.android.onemodule.ui.login.contract;

import com.android.cloud.base.BaseViewLoading;

import java.util.Map;

/**
 * Created by radio on 2017/9/19.
 */

public interface LoginContract {
    interface Model {
        void saveLoginUser(Map map);
    }
    interface ViewLoading extends BaseViewLoading {
        /**只进行登录*/
        void loginSuccess(String name);
        void loginFailed(String message);
        /**登录之后拿到未播报的数量*/
        void getNumSuccess(String num);
        void getNumFailed(String message);
    }
    interface Presenter {
        /**登录*/
        void login(Map map);
        /**拿到未播报的数量*/
        void getNum(Map map);
        /**已经登录过的信息已经保存，不需要输入直接登录*/
        void hasLogin();
    }
}
