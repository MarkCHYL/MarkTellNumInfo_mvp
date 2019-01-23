package com.cares.tellnuminfo.base;

import android.content.Context;

/**
 * @Desc
 * @作者 Mark
 * @时间 2019/1/22
 * @EMail 2285581945@qq.com
 */
public class BasePresenter {

    private Context mContext;

    public void onAttach(Context context) {
        this.mContext = context;
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onDestroy() {
        mContext = null;
    }

}
