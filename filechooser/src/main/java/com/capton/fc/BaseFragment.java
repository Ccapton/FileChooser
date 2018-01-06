package com.capton.fc;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by capton on 2017/11/27.
 */

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {

    public abstract int getLayoutId();

    public T binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(getLayoutInflater(),getLayoutId(),container,false);


        return binding.getRoot();
    }



}