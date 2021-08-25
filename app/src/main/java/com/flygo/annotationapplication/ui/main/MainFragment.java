package com.flygo.annotationapplication.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flygo.annotationapplication.R;
import com.flygo.myannotation.Flygo;

@Flygo
public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BindUtil3.bind(this);
    }

    @OnClick({R.id.btn1,R.id.btn2})
    public void onViewClick(View view){
        switch (view.getId()) {
            case R.id.btn1:
                Log.e("MainFragment", "btn1 clicked");
                break;
            case R.id.btn2:
                Log.e("MainFragment", "btn2 clicked");
                break;
        }
    }

    @OnLongClick({R.id.btn1,R.id.btn2})
    public boolean onViewLongClick(View view){
        switch (view.getId()) {
            case R.id.btn1:
                Log.e("MainFragment", "btn1 long clicked");
                break;
            case R.id.btn2:
                Log.e("MainFragment", "btn2 long clicked");
                break;
        }
        return false;
    }

}