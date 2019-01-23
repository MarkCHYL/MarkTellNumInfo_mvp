package com.cares.tellnuminfo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cares.tellnuminfo.mvp.model.bean.PhoneBean;
import com.cares.tellnuminfo.mvp.presenter.MianPresenter;
import com.cares.tellnuminfo.mvp.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_province)
    TextView tvProvince;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_carrier)
    TextView tvCarrier;
    @BindView(R.id.et_phone)
    EditText etPhone;

    private MianPresenter mianPresenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mianPresenter = new MianPresenter(this);
        mianPresenter.onAttach(this);
    }

    public void doSeach(View view) {
        mianPresenter.searchPhoneInfo(etPhone.getText().toString());
    }

    @Override
    public void showToast(String toastStr) {
        Toast.makeText(this,toastStr,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void upDateView() {
        PhoneBean phoneBean = mianPresenter.getPhoneBean();
        tvPhone.setText(etPhone.getText().toString());
        tvProvince.setText(phoneBean.getProvince());
        tvCarrier.setText(phoneBean.getCity());
        tvType.setText(phoneBean.getCompany());
    }

    @Override
    public void showLoading() {
        if (progressDialog==null){
            progressDialog = ProgressDialog.show(this,"","正在加载。。。",true,false);
        }else if (progressDialog.isShowing()){
            progressDialog.setTitle("");
            progressDialog.setMessage("正在加载。。。");
        }
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (progressDialog!=null&&progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}
