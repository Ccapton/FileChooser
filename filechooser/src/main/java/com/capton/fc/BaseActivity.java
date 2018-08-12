package com.capton.fc;

import android.Manifest;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.capton.fc.databinding.ActivityBaseBinding;

/**
 * Created by capton on 2017/11/27.
 *
 * @param <T>
 */

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    public ActivityBaseBinding baseBinding;
    public T binding;

    public static final int CODE_RECORD_AUDIO = 0;
    public static final int CODE_GET_ACCOUNTS = 1;
    public static final int CODE_READ_PHONE_STATE = 2;
    public static final int CODE_CALL_PHONE = 3;
    public static final int CODE_CAMERA = 4;
    public static final int CODE_ACCESS_FINE_LOCATION = 5;
    public static final int CODE_ACCESS_COARSE_LOCATION = 6;
    public static final int CODE_READ_EXTERNAL_STORAGE = 7;
    public static final int CODE_WRITE_EXTERNAL_STORAGE = 8;
    public static final int CODE_MULTI_PERMISSION = 100;

    public static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
    public static final String PERMISSION_GET_ACCOUNTS = Manifest.permission.GET_ACCOUNTS;
    public static final String PERMISSION_READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    public static final String PERMISSION_CALL_PHONE = Manifest.permission.CALL_PHONE;
    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    public static final String PERMISSION_ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String PERMISSION_ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final String PERMISSION_READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String PERMISSION_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    public static final String[] requestPermissions = {
//            PERMISSION_RECORD_AUDIO,
//            PERMISSION_GET_ACCOUNTS,
//            PERMISSION_READ_PHONE_STATE,
//            PERMISSION_CALL_PHONE,
//            PERMISSION_CAMERA,
//            PERMISSION_ACCESS_FINE_LOCATION,
//            PERMISSION_ACCESS_COARSE_LOCATION,
            PERMISSION_READ_EXTERNAL_STORAGE,
            PERMISSION_WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_base, null, false);
        binding = DataBindingUtil.inflate(getLayoutInflater(), getLayoutId(), baseBinding.container, true);

        setContentView(baseBinding.getRoot());

        setShowMoreIcon(false);
        setClickListener(true);

        PermissionUtils.requestMultiPermissions(this, mPermissionGrant, getPermissions());
    }

    public void setBackIcon(int drawableRes) {
        baseBinding.back.setImageResource(drawableRes);
    }

    /**
     * 设置主题颜色
     *
     * @param colorRes
     */
    public void setThemeColor(int colorRes) {
        baseBinding.topActionBar.setBackgroundResource(colorRes);
    }

    /**
     * 返回一个权限数组 默认是 requestPermissions (String [])
     *
     * @return
     */
    public abstract String[] getPermissions();

    /**
     * 获取子类布局id
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 是否展示返回按钮
     *
     * @param show
     */
    public void setShowBackIcon(boolean show) {
        if (show)
            baseBinding.back.setVisibility(View.VISIBLE);
        else
            baseBinding.back.setVisibility(View.INVISIBLE);
    }

    /**
     * 是否展示更多按钮
     *
     * @param show
     */
    public void setShowMoreIcon(boolean show) {
        if (show)
            baseBinding.more.setVisibility(View.VISIBLE);
        else
            baseBinding.more.setVisibility(View.INVISIBLE);
    }

    /**
     * 是否展示右边文字
     *
     * @param show
     */
    public void setShowRightText(boolean show) {
        if (show)
            baseBinding.rightText.setVisibility(View.VISIBLE);
        else
            baseBinding.rightText.setVisibility(View.INVISIBLE);
    }

    /**
     * 抽象点击函数
     */
    public abstract void setClickListener();

    /**
     * 设置右边文字
     *
     * @param text
     */
    public void setRightText(String text) {
        if (text != null)
            baseBinding.rightText.setText(text);
    }

    private void setClickListener(boolean isBase) {
        baseBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        baseBinding.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMore();
            }
        });
        baseBinding.rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRightText();
            }
        });
    }

    /**
     * 点击更多按钮
     */
    public abstract void clickMore();

    /**
     * 点击右侧文字
     */
    public abstract void clickRightText();


    /**
     * 更换"更多"按钮的图标，例如变成发送、或者完成的图标
     */
    public void setMoreIcon(int resId) {
        baseBinding.more.setImageResource(resId);
    }

    public void setTitle(String title) {
        baseBinding.title.setText(title);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, mPermissionGrant);
    }

    private PermissionUtils.PermissionGrant mPermissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                case PermissionUtils.CODE_MULTI_PERMISSION:
                    setClickListener();
                    break;
            }
        }
    };
}