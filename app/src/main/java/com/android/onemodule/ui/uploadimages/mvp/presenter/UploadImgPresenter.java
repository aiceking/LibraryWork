package com.android.onemodule.ui.uploadimages.mvp.presenter;

import com.android.cloud.base.BasePresenter;
import com.android.cloud.http.uploadimghelp.UpLoadImgsHelp;
import com.android.onemodule.ui.uploadimages.contract.UploadImgContract;
import com.android.onemodule.ui.uploadimages.mvp.view.UpLoadImgsActivity;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by radio on 2017/9/25.
 */

public class UploadImgPresenter extends BasePresenter<UploadImgContract.View,UpLoadImgsActivity> implements UploadImgContract.Presenter {
private List<String> list_select;
    public UploadImgPresenter(UploadImgContract.View view, UpLoadImgsActivity activity) {
        super(view, activity);
    }
    @Override
    public void uploadImgs(String upLoadImgUrl) {
        if (list_select==null){
            getView().upLoadImgsFailed("图片数量为：0");
            return;
        }
        new UpLoadImgsHelp(upLoadImgUrl).uploadImages(getActivity(), list_select, new UpLoadImgsHelp.UploadImagesListener() {
            @Override
            public void onStart() {
                getView().showLoading("上传中...");
            }
            @Override
            public void onSuccess(List<String> list) {
                getView().closeLoading();
                getView().upLoadImgsSuccess(list);
            }
            @Override
            public void onError(String message) {
                getView().closeLoading();
                getView().upLoadImgsFailed(message);
            }
        });
    }

    @Override
    public void getSelectImage(List<ImageItem> list) {
        if (list_select==null){
         list_select=new ArrayList<>();
        }else{
            list_select.clear();
        }
        for (ImageItem imageItem:list){
            list_select.add(imageItem.path);
        }
        getView().showSelectImage(list_select);
    }


}
