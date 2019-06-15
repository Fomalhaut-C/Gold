package com.fomalhaut.gold;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.fomalhaut.gold.Fragment.BankFragment;
import com.fomalhaut.gold.Fragment.ForwardFragment;
import com.fomalhaut.gold.Fragment.GoldFragment;
import com.fomalhaut.gold.Fragment.MeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager main_vp;
    private TabLayout main_tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        main_vp = (ViewPager) findViewById(R.id.main_vp);
        main_tl = (TabLayout) findViewById(R.id.main_tl);
        initVp();
        initTl();
    }

    private void initTl() {
        main_tl.addTab(main_tl.newTab().setText("黄金").setIcon(R.drawable.selector_gold));
        main_tl.addTab(main_tl.newTab().setText("期货").setIcon(R.drawable.selector_forward));
//        main_tl.addTab(main_tl.newTab().setText("银行").setIcon(R.drawable.selector_bank));
        main_tl.addTab(main_tl.newTab().setText("我").setIcon(R.drawable.selector_me));
        main_tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                main_vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        changeIconImgBottomMargin(main_tl,3);
    }

    private void initVp() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new GoldFragment(MainActivity.this));
        fragmentList.add(new ForwardFragment());
        fragmentList.add(new BankFragment());
        fragmentList.add(new MeFragment());
        main_vp.setAdapter(new MyVpAdapter(getSupportFragmentManager(),fragmentList));
        main_vp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                main_tl.getTabAt(position).select();
            }
        });
    }

    private void changeIconImgBottomMargin(ViewGroup parent, int px) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            if (child instanceof ViewGroup) {
                changeIconImgBottomMargin((ViewGroup) child, px);
            } else if (child instanceof ImageView) {
                ViewGroup.MarginLayoutParams lp = ((ViewGroup.MarginLayoutParams) child.getLayoutParams());
                lp.bottomMargin = 0;
                child.requestLayout();
            }
        }
    }

    private class MyVpAdapter extends FragmentStatePagerAdapter{
        List<Fragment> fragmentList;
        public MyVpAdapter(FragmentManager fm,List <Fragment> fragmentList) {
            super(fm);
            this.fragmentList=fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position) ;
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
