package com.yxld.yxchuangxin.ui.activity.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yxld.yxchuangxin.R;
import com.yxld.yxchuangxin.application.AppConfig;
import com.yxld.yxchuangxin.base.BaseFragment;
import com.yxld.yxchuangxin.ui.activity.main.component.DaggerTestComponent;
import com.yxld.yxchuangxin.ui.activity.main.contract.TestContract;
import com.yxld.yxchuangxin.ui.activity.main.module.TestModule;
import com.yxld.yxchuangxin.ui.activity.main.presenter.TestPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：hu on 2017/5/18
 * 邮箱：365941593@qq.com
 * 描述：
 */

public class TestFragment extends BaseFragment implements TestContract.View {

    @BindView(R.id.tv_show)
    TextView tvShow;

    @Inject
    TestPresenter mPresenter;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.tv_show1)
    TextView tvShow1;

    private int i;
    public TestFragment() {

    }

    @Override
    protected void setupFragmentComponent() {
        DaggerTestComponent
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .testModule(new TestModule(this))
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_laundry, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        Log.e("HJJ", "ArrayListFragment **** onCreateView...");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.startObservable();
            }
        });
        return view;
    }

    @Override
    protected void initDataFromLocal() {

    }

    @Override
    public void setPresenter(TestContract.TestPresenter presenter) {
        mPresenter = (TestPresenter) presenter;
    }

    @Override
    public void setText(String text) {
        button.setText(text);
    }

    @Override
    public void setButton(String text) {
        button.setText(text);
    }

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        Log.e("HJJ", "ArrayListFragment **** onAttach...");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        Log.e("HJJ", "ArrayListFragment **** onCreate...");
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("HJJ", "ArrayListFragment **** onActivityCreated...");
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        Log.e("HJJ", "ArrayListFragment **** onStart...");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.e("HJJ", "ArrayListFragment **** onResume...");
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.e("HJJ", "ArrayListFragment **** onPause...");
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.e("HJJ", "ArrayListFragment **** onStop...");
        // TODO Auto-generated method stub
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.e("HJJ", "ArrayListFragment **** onDestroyView...");
        // TODO Auto-generated method stub
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        Log.e("HJJ", "ArrayListFragment **** onDestroy...");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.e("HJJ", "ArrayListFragment **** onDetach...");
        // TODO Auto-generated method stub
        super.onDetach();
    }

}