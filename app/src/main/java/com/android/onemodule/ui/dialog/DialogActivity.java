package com.android.onemodule.ui.dialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.cloud.help.DateHelp;
import com.android.cloud.help.DialogHelp;
import com.android.cloud.widget.dialog.EditDialog;
import com.android.cloud.widget.dialog.OneTitleDialog;
import com.android.cloud.widget.dialog.TitleAndMessageDialog;
import com.android.onemodule.R;
import com.android.onemodule.R2;
import com.bigkoo.pickerview.TimePickerView;
import java.util.Calendar;
import java.util.Date;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogActivity extends AppCompatActivity {

    @BindView(R2.id.btn_loading)
    Button btnLoading;
    @BindView(R2.id.btn_title)
    Button btnTitle;
    @BindView(R2.id.btn_title_message)
    Button btnTitleMessage;
    @BindView(R2.id.btn_edittext)
    Button btnEdittext;
    @BindView(R2.id.btn_date)
    Button btnDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_activity_dialog);
        ButterKnife.bind(this);
    }

    @OnClick({R2.id.btn_loading, R2.id.btn_title, R2.id.btn_title_message, R2.id.btn_edittext, R2.id.btn_date})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.btn_loading) {
            DialogHelp.getInstance().showLoadingDialog(this, "加载中", true);

        } else if (i == R.id.btn_title) {
            DialogHelp.getInstance().showOneTitleDialog(this, "确认要提交吗?", "再想想", "提交", new OneTitleDialog.onBtnClickListener() {
                @Override
                public void onSure() {
                    Toast.makeText(DialogActivity.this, "确定", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancle() {
                    Toast.makeText(DialogActivity.this, "取消", Toast.LENGTH_SHORT).show();
                }
            });

        } else if (i == R.id.btn_title_message) {
            DialogHelp.getInstance().showTitleAndMessageDialog(this, "提示", "确认要提交吗？", "再想想", "提交", new TitleAndMessageDialog.onBtnClickListener() {
                @Override
                public void onSure() {
                    Toast.makeText(DialogActivity.this, "确定", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancle() {
                    Toast.makeText(DialogActivity.this, "取消", Toast.LENGTH_SHORT).show();
                }
            });

        } else if (i == R.id.btn_edittext) {
            DialogHelp.getInstance().showEditDialog(this, "请填写内容", "取消", "确定", new EditDialog.onBtnClickListener() {
                @Override
                public void onSure(String message) {
                    Toast.makeText(DialogActivity.this, "输入内容为：" + message, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancle() {
                    Toast.makeText(DialogActivity.this, "取消", Toast.LENGTH_SHORT).show();
                }
            });

        } else if (i == R.id.btn_date) {
            Calendar startDate = Calendar.getInstance();
            Calendar endDate = Calendar.getInstance();
            endDate.set(2100, 12, 31);
            DialogHelp.getInstance().showDateDialog(this, startDate, endDate, new TimePickerView.OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    Toast.makeText(DialogActivity.this, DateHelp.getTime(date), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

}
