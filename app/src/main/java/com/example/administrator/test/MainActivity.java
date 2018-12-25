package com.example.administrator.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.test.adapter.BottomAdapter;
import com.example.administrator.test.adapter.MainPagerAdapter;
import com.example.administrator.test.fragment.AppMainFragment;
import com.example.administrator.test.fragment.ShoppingcarMainFragment;
import com.example.administrator.test.fragment.StoreMainFragment;
import com.example.administrator.test.fragment.UserMainFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.black));
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_store:
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.crimson));
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_shoppingCar:
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.blue));
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_me:
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.purple));
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };
    private ViewPager.OnPageChangeListener vOnPageChageListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            bottomNavigationView.getMenu().getItem(position).setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout=findViewById(R.id.testChange);
        initViewPager();
    }


    private void initViewPager(){
       bottomNavigationView=(BottomNavigationView)findViewById(R.id.navigation);
       viewPager=(ViewPager)findViewById(R.id.main_view);
       bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
       setupViewPaget(viewPager);
       viewPager.addOnPageChangeListener(vOnPageChageListener);
    }

   private void setupViewPaget(ViewPager viewPager){
       BottomAdapter adapter=new BottomAdapter(getSupportFragmentManager());
       adapter.addFragment(new AppMainFragment());
       adapter.addFragment(new StoreMainFragment());
       adapter.addFragment(new ShoppingcarMainFragment());
       adapter.addFragment(new UserMainFragment());
       viewPager.setAdapter(adapter);
   }

}
