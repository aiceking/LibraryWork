package com.android.onemodule.ui.login.mvp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.cloud.base.BaseActivity;
import com.android.cloud.help.DialogHelp;
import com.android.onemodule.R;
import com.android.onemodule.R2;
import com.android.onemodule.ui.login.contract.LoginContract;
import com.android.onemodule.ui.login.mvp.presenter.LoginPresenter;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.ViewLoading {

    @BindView(R2.id.ed_login_name)
    EditText edLoginName;
    @BindView(R2.id.ed_login_password)
    EditText edLoginPassword;
    @BindView(R2.id.btn_login)
    Button btnLogin;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPresenter = new LoginPresenter(this,this);
        /**如果登陆过直接登录*/
        loginPresenter.hasLogin();
    }
    @Override
    public void showLoading(String message) {
        DialogHelp.getInstance().showLoadingDialog(this, message,false);
    }

    @Override
    public void closeLoading() {
        DialogHelp.getInstance().closeLoadingDialog();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void loginSuccess(String name) {
        showToast("登录成功,用户名为："+name);
    }

    @Override
    public void loginFailed(String message) {
        showToast(message);
    }

    @Override
    public void getNumSuccess(String num) {
        showToast("请求成功,Num为："+num);
    }

    @Override
    public void getNumFailed(String message) {
        showToast(message);
    }
    @OnClick({R2.id.btn_login, R2.id.btn_login_num})
    public void onViewClicked(View view) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", edLoginName.getText().toString());
        map.put("password", edLoginPassword.getText().toString());
        int i = view.getId();
        if (i == R.id.btn_login) {/**只进行登录*/
            loginPresenter.login(map);

            /**登录之后拿到未播报的数量,Rxjava流式调用*/
        } else if (i == R.id.btn_login_num) {
            loginPresenter.getNum(map);

        }
    }
    @Override
    protected int getLayoutId() {
        return R.layout.work_activity_login;
    }
    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }
}
