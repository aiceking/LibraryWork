package com.android.onemodule.api;

import com.android.cloud.api.responsebean.BaseResponseBean;
import com.android.onemodule.ui.login.mvp.model.LoginModel;
import com.android.onemodule.ui.login.mvp.model.NoBoBaoModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by radio on 2017/9/20.
 */

public interface NetApiService {
    /**登录*/
    @FormUrlEncoded
    @POST
    Observable<BaseResponseBean<LoginModel>> Login(@Url String url,@FieldMap Map<String, String> request);

    /**未播报工地数,（可切换BaseUrl）*/
    @Headers({"Domain-Name:testbaseurl"})
    @FormUrlEncoded
    @POST
    Observable<BaseResponseBean<NoBoBaoModel>> getNoBobaoNum(@Url String url,@FieldMap Map<String, String> request);
}
