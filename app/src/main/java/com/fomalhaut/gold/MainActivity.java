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
        gold_rv = (RecyclerView) findViewById(R.id.gold_rv);
        gold_srl = (SmartRefreshLayout) findViewById(R.id.gold_srl);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        main_nav = (NavigationView) findViewById(R.id.main_nav);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        View headerView = main_nav.getHeaderView(0);
        TextView tv_user = headerView.findViewById(R.id.nav_user);
        if (SharedPreferencesUtils.getBoolean(MainActivity.this,"isLogin",false)){
            String user = SharedPreferencesUtils.getString(MainActivity.this, "user", "");
            tv_user.setText(user);
        }else {
            tv_user.setText("点击登录");
            headerView.setOnClickListener(v -> {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                SharedPreferencesUtils.ClearData(MainActivity.this);
                finish();
            });
        }
        main_nav.setNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.update_phone:
                    break;
                case R.id.replace_user:
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    SharedPreferencesUtils.ClearData(MainActivity.this);
                    finish();
                    break;
                case R.id.exit:
                    finish();
                    break;
            }
            drawerlayout.closeDrawers();
            return true;
        });
        gold_srl.setOnRefreshListener(refreshLayout -> {
            initData();
            gold_srl.finishRefresh(2000);//传入false表示刷新失败
        });
        gold_srl.setOnLoadMoreListener(refreshLayout -> {
            gold_srl.finishLoadMore(2000);//传入false表示加载失败
        });
        gold_srl.setRefreshHeader(new FunGameHitBlockHeader(this));
        gold_srl.setRefreshFooter(new FalsifyFooter(this));
        gold_rv.setLayoutManager(new LinearLayoutManager(this));
        initData();
        runnable.run();
    }

    private void initData() {
//        String address = "https://api.jisuapi.com/gold/shgold?appkey=3069248ceca810cc";
//        String address = "http://apicloud.mob.com/gold/spot/query?key=2b60f172130ba";
        String address = "http://japi.zhimafix.com:8967/quote/price?symbol=XAUUSD,XPDUSD,XPTUSD,USOIL,XAGUSD,GOLD.comex,PALL.comex,PLAT.comex,PMAP,USDCNH";
        HttpUtils.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String json = response.body().string();
                Message message = new Message();
                message.what = SUCCESS;
                message.obj = json;
                /*Bundle bundle = new Bundle();
                bundle.putString("json", json);
                message.setData(bundle);*/
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Message message = new Message();
                message.what = FAIL;
                message.obj = MainActivity.this;
                handler.sendMessage(message);
            }
        });
    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS:
                    GoldList.clear();
                    String json = (String) msg.obj;
                    /*Bundle bundle = msg.getData();
                    String json = bundle.getString("json");*/
                    Gold gold = new Gson().fromJson(json, Gold.class);
                    GoldList.add(gold);
                    gold_rv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                case FAIL:
                    Context context = (Context) msg.obj;
                    Toast.makeText(context, "请求数据失败,请检查网络状态", Toast.LENGTH_SHORT).show();
            }
        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            initData();
            handler.postDelayed(this,1000);
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerlayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.gold_rv_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            /*MobGold.ResultBean resultBean = GoldList.get(0).getResult().get(position);
            holder.gold_item_tv_name.setText(resultBean.getName());
            holder.gold_item_tv_closePri.setText(resultBean.getClosePri());
            holder.gold_item_tv_time.setText(resultBean.getTime().substring(11, 19));
            if (!resultBean.getLimit().equals("--")) {
                float changepercent = Float.parseFloat(resultBean.getLimit().replaceAll("%", "").trim());
                if (changepercent < 0) {
                    holder.gold_item_tv_limit.setTextColor(Color.parseColor("#C0362E"));
                    holder.gold_item_tv_limit.setText(resultBean.getLimit());
                }
            }
            holder.gold_item_tv_limit.setText(resultBean.getLimit());
            holder.view.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, GoldChartActivity.class);
                intent.putExtra("close", resultBean.getClosePri());
                intent.putExtra("limit", resultBean.getLimit());
                intent.putExtra("open", resultBean.getOpenPri());
                intent.putExtra("yes", resultBean.getYesDayPic());
                intent.putExtra("high", resultBean.getHighPic());
                intent.putExtra("low", resultBean.getLowPic());
                getApplicationContext().startActivity(intent);
            });*/
            Gold.DataBean dataBean = GoldList.get(0).getData().get(position);
            holder.gold_item_tv_name.setText(dataBean.getSymbol());
            holder.gold_item_tv_closePri.setText(dataBean.getBid() + "");
            long time = (long) dataBean.getCtm() + 28800;
            holder.gold_item_tv_time.setText(new SimpleDateFormat("HH:mm:ss").format(new Date(time * 1000)));
            double limit = dataBean.getBid() - dataBean.getYesterdayClose();
            holder.gold_item_tv_limit.setText(new DecimalFormat("#.##").format(limit / 100) + "%");
            holder.view.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, GoldChartActivity.class);
                intent.putExtra("symbol", dataBean.getSymbol());
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return GoldList.get(0).getData().size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            View view;
            TextView gold_item_tv_name;
            TextView gold_item_tv_closePri;
            TextView gold_item_tv_time;
            TextView gold_item_tv_limit;

            ViewHolder(View view) {
                super(view);
                this.view = view;
                this.gold_item_tv_name = (TextView) view.findViewById(R.id.gold_item_tv_name);
                this.gold_item_tv_closePri = (TextView) view.findViewById(R.id.gold_item_tv_closePri);
                this.gold_item_tv_time = (TextView) view.findViewById(R.id.gold_item_tv_time);
                this.gold_item_tv_limit = (TextView) view.findViewById(R.id.gold_item_tv_limit);
            }
        }
    }

    public void setStatusBar(Activity activity) {
        StatusBarUtil.setColor(activity, getResources().getColor(R.color.colorPrimary));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtil.setTranslucentForImageViewInFragment(activity, 0, null);
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            //其他的都设置状态栏成半透明的,以下设置半透明是调用第三方ImmersionBar库的，根据个人需求更改，
            StatusBarUtil.setTranslucentForImageViewInFragment(activity, 100, null);
        }
    }

    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出应用程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (handler != null){
            handler.removeCallbacks(runnable);
        }
        super.onDestroy();
    }
    /*private void initView() {
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
