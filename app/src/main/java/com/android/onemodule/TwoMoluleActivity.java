package com.android.onemodule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.cloud.libraryinit.BaseLibraryInitHelp;
import com.android.onemodule.api.NetApiService;
import com.android.onemodule.libraryinit.WorkLibraryInitHelp;
import com.android.onemodule.ui.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TwoMoluleActivity extends AppCompatActivity {
    @BindView(R2.id.btn_ceshi)
    Button btnCeshi;
    @BindView(R2.id.btn_shengchan)
    Button btnShengchan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_activity_two_molule);
        ButterKnife.bind(this);
    }

    @OnClick({R2.id.btn_ceshi, R2.id.btn_shengchan})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.btn_ceshi) {
            /**切换到测试站*/
            WorkLibraryInitHelp.getInstance().changeDebug(true);

        } else if (i == R.id.btn_shengchan) {
            /**切换到生产站*/
            WorkLibraryInitHelp.getInstance().changeDebug(false);
        }
        startActivity(new Intent(this, MainActivity.class));
    }
}
