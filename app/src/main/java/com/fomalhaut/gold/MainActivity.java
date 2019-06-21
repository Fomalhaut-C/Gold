package com.fomalhaut.gold;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fomalhaut.gold.Activity.GoldChartActivity;
import com.fomalhaut.gold.Activity.LoginActivity;
import com.fomalhaut.gold.Bean.Gold;
import com.fomalhaut.gold.Utils.HttpUtils;
import com.fomalhaut.gold.Utils.SharedPreferencesUtils;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.header.FunGameHitBlockHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    private static RecyclerView gold_rv;
    private static List<Gold> GoldList = new ArrayList<>();
    private static final int SUCCESS = 1;
    private static final int FAIL = -1;
    private MyHandler handler;
    private DrawerLayout drawerlayout;
    private SmartRefreshLayout gold_srl;
    private Toolbar toolbar;
    private NavigationView main_nav;
    private MyAdapter adapter = new MyAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBar(MainActivity.this);
        handler = new MyHandler();
        initView();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        }
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
    }*/
}
