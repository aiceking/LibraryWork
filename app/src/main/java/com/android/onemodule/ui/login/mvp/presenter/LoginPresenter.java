package com.android.onemodule.ui.login.mvp.presenter;

import android.text.TextUtils;

import com.android.cloud.api.responsebean.BaseResponseBean;
import com.android.cloud.base.BasePresenter;
import com.android.cloud.help.SharedPreferencesHelp;
import com.android.cloud.http.exception.ApiException;
import com.android.cloud.http.observer.RetrofitRxObservableHelp;
import com.android.cloud.http.observer.RetrofitRxObserver;
import com.android.cloud.http.retrofit.RetrofitHelp;
import com.android.onemodule.api.ApiType;
import com.android.onemodule.libraryinit.WorkLibraryInitHelp;
import com.android.onemodule.ui.login.contract.LoginContract;
import com.android.onemodule.ui.login.mvp.model.LoginModel;
import com.android.onemodule.ui.login.mvp.model.NoBoBaoModel;
import com.android.onemodule.ui.login.mvp.view.LoginActivity;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Created by radio on 2017/9/19.
 */

public class LoginPresenter extends BasePresenter<LoginContract.ViewLoading,LoginActivity> implements LoginContract.Presenter {
    public LoginPresenter(LoginContract.ViewLoading view, LoginActivity activity){
        super(view,activity);
    }
    @Override
    public void login(final Map map) {
        RetrofitRxObservableHelp.getObservable(WorkLibraryInitHelp.getInstance().getObservableByType(map, ApiType.login),getActivity(),ActivityEvent.DESTROY)
                .subscribe(new RetrofitRxObserver<LoginModel>() {
                    @Override
                    protected void onStart(Disposable d) {
                        getView().showLoading("登录中...");
                    }
                    @Override
                    protected void onError(ApiException e) {
                        getView().closeLoading();
                        getView().loginFailed(e.getDisPlayMessage());
                    }
                    @Override
                    protected void onSuccess(BaseResponseBean<LoginModel> response) {
                        getView().closeLoading();
                        getView().loginSuccess(response.getValues().getName());
                        /**登录信息保存到本地*/
                        response.getValues().saveLoginUser(map);
                    }
                });
    }
    /**嵌套请求，多嵌套同理*/
    @Override
    public void getNum(Map map) {
        /**切换BaseUrl*/
        RetrofitHelp.getInstance().changeBaseUrl("testbaseurl", "https://gongfaner.zhufaner.com");
        RetrofitRxObservableHelp.getObservable(WorkLibraryInitHelp.getInstance().getObservableByType(map, ApiType.login),getActivity().bindLifecycle())
                .flatMap(new Function<BaseResponseBean<LoginModel>,Observable>() {
                    @Override
                    public Observable apply(@NonNull BaseResponseBean<LoginModel> loginValuesBaseResponseBean) throws Exception {
                        Map map=new HashMap();
                        map.put("manager",loginValuesBaseResponseBean.getValues().getName());
                        return RetrofitRxObservableHelp.getObservable(WorkLibraryInitHelp.getInstance().getObservableByType(map, ApiType.getNoBobaoNum),getActivity().bindLifecycle());
                    }
                })
                .subscribe(new RetrofitRxObserver<NoBoBaoModel>() {
                    @Override
                    protected void onStart(Disposable d) {
                        getView().showLoading("请求中");
                    }
                    @Override
                    protected void onError(ApiException e) {
                        getView().closeLoading();
                        getView().getNumFailed(e.getDisPlayMessage());
                    }
                    @Override
                    protected void onSuccess(BaseResponseBean<NoBoBaoModel> response) {
                        getView().closeLoading();
                        getView().getNumSuccess(response.getValues().getSiteNum());
                    }
                });
    }

    @Override
    public void hasLogin() {
        if (!TextUtils.isEmpty(SharedPreferencesHelp.getString("phone"))){
            Map<String, String> map = new HashMap<>();
            map.put("phone", SharedPreferencesHelp.getString("phone"));
            map.put("password", SharedPreferencesHelp.getString("password"));
            login(map);
        }
    }
}
