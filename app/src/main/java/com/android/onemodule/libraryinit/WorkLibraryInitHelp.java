package com.android.onemodule.libraryinit;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.cloud.api.responsebean.BaseResponseBean;
import com.android.cloud.help.ToastHelp;
import com.android.cloud.libraryinit.BaseLibraryInitHelp;
import com.android.onemodule.api.ApiType;
import com.android.onemodule.api.NetApiService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by radio on 2017/10/12.
 */

public class WorkLibraryInitHelp {
    private static WorkLibraryInitHelp workLibraryInitHelp;
    private BaseLibraryInitHelp baseLibraryInitHelp;
    private HashMap<ApiType,String> hashMap;

    public void setActivityMap(HashMap<String, Class> activityMap) {
        this.activityMap = activityMap;
    }
    public  Intent getIntentByType(Context context, String type){
        if (activityMap.containsKey(type)){
        return  new Intent(context,activityMap.get(type));
        }else{
           return null;
        }
    }
    private HashMap<String,Class> activityMap;
    private WorkLibraryInitHelp(){
        hashMap=new HashMap<>();
    }
    public static WorkLibraryInitHelp getInstance(){
        if (workLibraryInitHelp==null){
            synchronized (WorkLibraryInitHelp.class){
                if (workLibraryInitHelp==null){
                    workLibraryInitHelp=new WorkLibraryInitHelp();
                }
            }
        }
        return workLibraryInitHelp;
    }
    public BaseLibraryInitHelp getBaseLibraryInitHelp() {
        return baseLibraryInitHelp;
    }
    public void changeDebug(boolean debug){
        baseLibraryInitHelp.changeDebug(debug,NetApiService.class);
    }
    public void setBaseLibraryInitHelp(BaseLibraryInitHelp baseLibraryInitHelp) {
        this.baseLibraryInitHelp = baseLibraryInitHelp;
    }
    public void addApi(ApiType apitype,String api){
        if (!hashMap.containsKey(apitype)){
        hashMap.put(apitype,api);
        }
    }
    public  Observable getObservableByType(Map map, ApiType apitype){
        Observable apiObservable=null;
        BaseLibraryInitHelp<NetApiService> baseLibraryInitHelp=BaseLibraryInitHelp.getInstance();
        if (TextUtils.isEmpty(hashMap.get(apitype))){
            ToastHelp.showToast("接口未配置");
            return null;
        }
        switch (apitype){
            case login:
                apiObservable= baseLibraryInitHelp.getNetService().Login(hashMap.get(apitype),map);
                break;
            case getNoBobaoNum:
                apiObservable=baseLibraryInitHelp.getNetService().getNoBobaoNum(hashMap.get(apitype),map);
                break;
        }
        return apiObservable;
    }
}
