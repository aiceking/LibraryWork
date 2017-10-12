package com.android.onemodule.ui.uploadimages.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.cloud.base.BaseActivity;
import com.android.cloud.help.DialogHelp;
import com.android.onemodule.R;
import com.android.onemodule.R2;
import com.android.onemodule.ui.uploadimages.contract.UploadImgContract;
import com.android.onemodule.ui.uploadimages.mvp.presenter.UploadImgPresenter;
import com.android.onemodule.ui.uploadimages.mvp.view.adapter.ImageAdapter;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UpLoadImgsActivity extends BaseActivity implements UploadImgContract.View {
    @BindView(R2.id.tv_uploadimg_names)
    TextView tvUploadimgNames;
    private int IMAGE_PICKER = 10241;
    @BindView(R2.id.btn_select)
    Button btnSelect;
    @BindView(R2.id.gv_images)
    GridView gvImages;
    @BindView(R2.id.btn_upload)
    Button btnUpload;
    private ImageAdapter adapter;
    private UploadImgPresenter uploadImgPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uploadImgPresenter = new UploadImgPresenter(this, this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_PICKER && resultCode == ImagePicker.RESULT_CODE_ITEMS && data != null) {
            ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            uploadImgPresenter.getSelectImage(images);
        }
    }

    @OnClick({R2.id.btn_select, R2.id.btn_upload})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.btn_select) {
            Intent intent = new Intent(this, ImageGridActivity.class);
            startActivityForResult(intent, IMAGE_PICKER);

        } else if (i == R.id.btn_upload) {
            uploadImgPresenter.uploadImgs("/api/upload-image");

        }
    }
    @Override
    public void showSelectImage(List<String> list) {
        if (adapter == null) {
            adapter = new ImageAdapter(list, this);
            gvImages.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showLoading(String msg) {
        DialogHelp.getInstance().showLoadingDialog(this, msg,false);
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
    public void upLoadImgsSuccess(List<String> list) {
        String s="";
        for (String name : list) {
            s=s+name+"\n";
        }
        tvUploadimgNames.setText("成功上传"+list.size()+"张图片,服务器返回文件名为：\n"+s);
    }
    @Override
    public void upLoadImgsFailed(String message) {
        showToast(message);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.work_activity_up_load_imgs;
    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }
}
