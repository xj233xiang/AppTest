package com.example.administrator.test.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.test.R;
import com.example.administrator.test.adapter.MainPagerAdapter;

public class AppMainFragment extends Fragment implements ViewPager.OnPageChangeListener,ViewPager.OnTouchListener {
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
    public boolean isAutoPlay=true;
    // 切换间隔时间
    public static final int VIEW_PAGER_DELAY=2000;
    public static int VIEW_PAGER_START_INDEX=0;

    public static final int EVENT_VIEW_PAGER_CHANGE=0;

    MainPagerAdapter mainPagerAdapter=new MainPagerAdapter(getActivity());
    ViewPager mainViewPager;
    LinearLayout liveIndicator;
    ImageView[] indicatorImage;

    public static Integer VIEW_PAGER_SIZE;
    Integer currentImageIndex;

    MainVPHandler mHandler=new MainVPHandler(this);
    Thread mThread = new Thread(){
        @Override
        public void run() {
            super.run();
            while (true) {
                mHandler.sendEmptyMessage(EVENT_VIEW_PAGER_CHANGE);
                try {
                    Thread.sleep(AppMainFragment.VIEW_PAGER_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_app_main,container,false);
        mainViewPager=(ViewPager)root.findViewById(R.id.app_main_view_pager);
        liveIndicator=(LinearLayout)root.findViewById(R.id.app_main_view_pager_live_indicator);
        VIEW_PAGER_SIZE=1;
        mainViewPager.setCurrentItem(VIEW_PAGER_START_INDEX);
        currentImageIndex=VIEW_PAGER_START_INDEX;
        initPagerView();
        initLiveIndicator();
        mThread.start();
        return root;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void initPagerView(){
        VIEW_PAGER_SIZE=3;
    }

    /**
     * 生成图片对应小点
     */
    public void initLiveIndicator(){
        indicatorImage = new ImageView[mainPagerAdapter.getCount()];
        for (int i = 0; i < indicatorImage.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.setMargins(5, 0, 5, 0);
            imageView.setLayoutParams(params);
            //如果当前是第一个 设置为选中状态
            if (i == 0) {
                imageView.setImageResource(R.drawable.indicator_select);
            } else {
                imageView.setImageResource(R.drawable.indicator_no_select);
            }
            indicatorImage[i] = imageView;
            //添加到父容器
            liveIndicator.addView(imageView);
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentImageIndex = position;
        if(mainPagerAdapter.getCount()>0){
            position %= indicatorImage.length;
            int total = indicatorImage.length;

            for (int i = 0; i < total; i++) {
                if (i == position) {
                    indicatorImage[i].setImageResource(R.drawable.indicator_select);
                } else {
                    indicatorImage[i].setImageResource(R.drawable.indicator_no_select);
                }
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //点击时暂停
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                isAutoPlay = false;
                break;
            case MotionEvent.ACTION_UP:
                isAutoPlay = true;
                break;
        }
        return false;
    }
}
