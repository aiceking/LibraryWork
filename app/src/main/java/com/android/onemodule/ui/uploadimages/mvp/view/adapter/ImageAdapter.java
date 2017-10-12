package com.android.onemodule.ui.uploadimages.mvp.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.onemodule.R;
import com.android.onemodule.R2;
import com.bumptech.glide.Glide;
import com.code19.library.DensityUtil;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by radio on 2017/9/25.
 */

public class ImageAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    private LinearLayout.LayoutParams lp;
    public ImageAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        int width=DensityUtil.getScreenW(context)/3;
        lp=new LinearLayout.LayoutParams(width,width);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.work_images_gridview, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.ivImages.setLayoutParams(lp);
        Glide.with(context)
                .load(Uri.fromFile(new File(list.get(i))))
                .placeholder(com.android.cloud.R.mipmap.imageplaceholder)
                .error(com.android.cloud.R.mipmap.imageplaceholder).centerCrop().into(viewHolder.ivImages);
        return view;
    }

    static class ViewHolder {
        @BindView(R2.id.iv_images)
        ImageView ivImages;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
