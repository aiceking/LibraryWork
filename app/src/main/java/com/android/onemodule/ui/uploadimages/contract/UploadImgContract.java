package com.android.onemodule.ui.uploadimages.contract;

import com.android.cloud.base.BaseViewLoading;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.List;

/**
 * Created by radio on 2017/9/25.
 */

public interface UploadImgContract {
    interface Model {
    }

    interface View extends BaseViewLoading {
        void upLoadImgsSuccess(List<String> list);
        void upLoadImgsFailed(String message);
        void showSelectImage(List<String> list);
    }

    interface Presenter {
        void uploadImgs(String upLoadImgUrl);
        void getSelectImage(List<ImageItem> list);
    }
}
