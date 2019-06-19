package com.fomalhaut.gold;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
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
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager main_vp;
    private TabLayout main_tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBar(MainActivity.this);
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
        main_tl.addTab(main_tl.newTab().setText("银行").setIcon(R.drawable.selector_bank));
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
        fragmentList.add(new ForwardFragment(MainActivity.this));
        fragmentList.add(new BankFragment());
        fragmentList.add(new MeFragment());
        main_vp.setAdapter(new MyVpAdapter(getSupportFragmentManager(),fragmentList));
        main_vp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                main_tl.getTabAt(position).select();
            }
        });
        main_vp.setOffscreenPageLimit(4);
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

    public void setStatusBar(Activity activity) {
        StatusBarUtil.setColor(activity, getResources().getColor(R.color.colorPrimary));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            StatusBarUtil.setTranslucentForImageViewInFragment(activity,0,null);
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }else {
            //其他的都设置状态栏成半透明的,以下设置半透明是调用第三方ImmersionBar库的，根据个人需求更改，
            StatusBarUtil.setTranslucentForImageViewInFragment(activity,100,null);
        }
    }

    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if ((System.currentTimeMillis() - exitTime) > 2000){
                Toast.makeText(this, "再按一次退出应用程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
