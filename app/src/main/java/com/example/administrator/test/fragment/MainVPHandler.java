package com.example.administrator.test.fragment;

import android.os.Handler;
import android.os.Message;

import com.example.administrator.test.fragment.AppMainFragment;

import java.lang.ref.WeakReference;

public class MainVPHandler extends Handler {
    private WeakReference<AppMainFragment> mWeakReference;

    public MainVPHandler(AppMainFragment fragment) {
        mWeakReference = new WeakReference<AppMainFragment>(fragment);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case AppMainFragment.EVENT_VIEW_PAGER_CHANGE:       //设置ViewPager的显示图片
                AppMainFragment fragment = mWeakReference.get();
                if (fragment.isAutoPlay) {
                    if(fragment.currentImageIndex<fragment.VIEW_PAGER_SIZE)
                        fragment.mainViewPager.setCurrentItem(++fragment.currentImageIndex);
                    else{
                        fragment.mainViewPager.setCurrentItem(AppMainFragment.VIEW_PAGER_START_INDEX);
                        fragment.currentImageIndex=AppMainFragment.VIEW_PAGER_START_INDEX;
                    }

                }

                break;
        }

    }

}
