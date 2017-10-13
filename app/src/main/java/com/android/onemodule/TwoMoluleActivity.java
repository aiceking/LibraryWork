package com.android.onemodule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.onemodule.libraryinit.WorkLibraryInitHelp;
import com.android.onemodule.ui.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = "/workModule/1")
public class TwoMoluleActivity extends AppCompatActivity {
    @BindView(R2.id.btn_ceshi)
    Button btnCeshi;
    @BindView(R2.id.btn_shengchan)
    Button btnShengchan;
    @BindView(R2.id.btn_module)
    Button btnModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_activity_two_molule);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==5&&resultCode==RESULT_OK){
            if (data!=null){
                btnModule.setText(data.getStringExtra("test"));
            }
        }
    }

    @OnClick({R2.id.btn_ceshi, R2.id.btn_shengchan,R2.id.btn_module})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.btn_ceshi) {
            /**切换到测试站*/
            WorkLibraryInitHelp.getInstance().changeDebug(true);
            startActivity(new Intent(this, MainActivity.class));
        } else if (i == R.id.btn_shengchan) {
            /**切换到生产站*/
            WorkLibraryInitHelp.getInstance().changeDebug(false);
            startActivity(new Intent(this, MainActivity.class));
        }else if (i==R.id.btn_module){
            ARouter.getInstance().build("/workModule_other/2").withString("test","我要看超人").navigation(this, 5);
        }
    }
}
