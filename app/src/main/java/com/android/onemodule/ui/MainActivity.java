package com.android.onemodule.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.onemodule.R;
import com.android.onemodule.R2;
import com.android.onemodule.ui.dialog.DialogActivity;
import com.android.onemodule.ui.login.mvp.view.LoginActivity;
import com.android.onemodule.ui.uploadimages.mvp.view.UpLoadImgsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R2.id.btn_login)
    Button btnLogin;
    @BindView(R2.id.btn_upload)
    Button btnUpload;
    @BindView(R2.id.btn_diolog)
    Button btnDiolog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_activity_main);
        ButterKnife.bind(this);

    }
    @OnClick({R2.id.btn_login, R2.id.btn_upload,R2.id.btn_diolog})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.btn_login) {
            startActivity(new Intent(this, LoginActivity.class));

        } else if (i == R.id.btn_upload) {
            startActivity(new Intent(this, UpLoadImgsActivity.class));

        } else if (i == R.id.btn_diolog) {
            startActivity(new Intent(this, DialogActivity.class));

        }
    }
}
