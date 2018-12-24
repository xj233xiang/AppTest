package com.example.administrator.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.administrator.test.adapter.MainPagerAdapter;
import com.example.administrator.test.fragment.AppMainFragment;
import com.example.administrator.test.fragment.ShoppingcarMainFragment;
import com.example.administrator.test.fragment.StoreMainFragment;
import com.example.administrator.test.fragment.UserMainFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView bottomNavigationView;
    private Fragment app_main,shoppingcar_main,store_main,user_main;
    private Fragment[] fragments;
    private Integer lastfragment=0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    swithFragment(lastfragment,0);
                    return true;
                case R.id.navigation_store:
                    swithFragment(lastfragment,1);
                    return true;
                case R.id.navigation_shoppingCar:
                    swithFragment(lastfragment,2);
                    return true;
                case R.id.navigation_me:
                    swithFragment(lastfragment,3);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
    }


    private void initFragment(){
        app_main=new AppMainFragment();
        shoppingcar_main=new ShoppingcarMainFragment();
        store_main=new StoreMainFragment();
        user_main=new UserMainFragment();
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_view,app_main).show(app_main).commit();
        fragments=new Fragment[]{app_main,shoppingcar_main,store_main,user_main};
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void swithFragment(int lastfragment,int index){
//        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
//        transaction.hide(fragments[lastfragment]);//隐藏上个Fragment
//        if(fragments[index].isAdded()==false)
//        {
//            transaction.add(R.id.main_view,fragments[index]);
//        }
//        transaction.show(fragments[index]).commitAllowingStateLoss();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_view,fragments[index]).commit();
    }

}
