package com.cares.tellnuminfo.mvp.presenter;

import com.cares.tellnuminfo.api.UrlControl;
import com.cares.tellnuminfo.base.BasePresenter;
import com.cares.tellnuminfo.business.HttpUtils;
import com.cares.tellnuminfo.mvp.contract.MianContract;
import com.cares.tellnuminfo.mvp.model.BaseResponse;
import com.cares.tellnuminfo.mvp.model.bean.PhoneBean;
import com.cares.tellnuminfo.mvp.view.MainView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

/**
 * @Desc
 * @作者 Mark
 * @时间 2019/1/22
 * @EMail 2285581945@qq.com
 */
public class MianPresenter extends BasePresenter implements MianContract.Presenter {

    private MainView mainView;
    private PhoneBean phoneBean;

    public MianPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void searchPhoneInfo(String phone) {
        if (phone.length() < 11) {
            mainView.showToast("请输入正确的手机号码");
            return;
        }
        mainView.showLoading();
        //网络请求处理逻辑
        sengHttp(phone);
    }

    public PhoneBean getPhoneBean() {
        return phoneBean;
    }

    private void sengHttp(final String phone) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("phone", phone);
        params.put("dtype", "json");
        params.put("key", "428cccc8303b75cc6026fc38aba8eeaf");
        HttpUtils httpUtils = new HttpUtils(new HttpUtils.HttpResponse() {
            @Override
            public void onSuccess(Object object) {
                String json = object.toString();
                BaseResponse<PhoneBean> beanBaseResponse = new Gson()
                        .fromJson(json, new TypeToken<BaseResponse<PhoneBean>>() {
                        }.getType());
                phoneBean = beanBaseResponse.getResult();
                mainView.hideLoading();
                mainView.upDateView();
            }

            @Override
            public void onFailed(String error) {
                mainView.showToast(error);
                mainView.hideLoading();
            }
        });
        httpUtils.senfGetHttp(UrlControl.searchPhoneUrl, params);
    }
}
