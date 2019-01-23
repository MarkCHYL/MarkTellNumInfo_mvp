package com.cares.tellnuminfo.mvp.contract;

import com.cares.tellnuminfo.base.BasePresenter;
import com.cares.tellnuminfo.base.BaseView;

/**
 * @Desc
 * @作者 Mark
 * @时间 2019/1/22
 * @EMail 2285581945@qq.com
 */
public interface MianContract {
    interface Model {
    }

    interface View extends BaseView {
        void showToast(String toastStr);
        void upDateView();
    }

    interface Presenter {
        void searchPhoneInfo(String phone);
    }
}
