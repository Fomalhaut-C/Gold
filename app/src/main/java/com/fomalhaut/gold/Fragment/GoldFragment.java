package com.fomalhaut.gold.Fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fomalhaut.gold.Activity.GoldChartActivity;
import com.fomalhaut.gold.Bean.MobGold;
import com.fomalhaut.gold.R;
import com.fomalhaut.gold.Utils.HttpUtils;
import com.google.gson.Gson;
import com.scwang.smartrefresh.header.FunGameHitBlockHeader;
import com.scwang.smartrefresh.header.fungame.FunGameBase;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoldFragment extends Fragment {

    private static RecyclerView gold_rv;
    private static List<MobGold> GoldList = new ArrayList<>();
    private static final int SUCCESS = 1;
    private static final int FAIL = -1;

    private Context context;
    private MyHandler handler;

    public GoldFragment(Context context) {
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gold, container, false);
        initView(view);
        handler = new MyHandler();
        return view;
    }

    private void initView(View view) {
        gold_rv = view.findViewById(R.id.gold_rv);
        SmartRefreshLayout gold_srl = view.findViewById(R.id.gold_srl);
        gold_srl.setOnRefreshListener(refreshLayout -> {
            initData();
            gold_srl.finishRefresh(2000);//传入false表示刷新失败
        });
        gold_srl.setOnLoadMoreListener(refreshLayout -> {
            gold_srl.finishLoadMore(2000);//传入false表示加载失败
        });
        gold_srl.setRefreshHeader(new FunGameHitBlockHeader(context));
        gold_srl.setRefreshFooter(new FalsifyFooter(context));
        gold_rv.setLayoutManager(new LinearLayoutManager(context));
        initData();
    }

    private void initData() {
//        String address = "https://api.jisuapi.com/gold/shgold?appkey=3069248ceca810cc";
        String address = "http://apicloud.mob.com/gold/spot/query?key=2b60f172130ba";
        HttpUtils.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String json = response.body().string();
                Message message = new Message();
                message.what = SUCCESS;
                Bundle bundle = new Bundle();
                bundle.putString("json", json);
                message.setData(bundle);
                message.obj = context;
                message.obj = getActivity();
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Message message = new Message();
                message.what = FAIL;
                message.obj = context;
                handler.sendMessage(message);
            }
        });
    }

    private static class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS:
                    Context mcontext = (Context) msg.obj;
                    Activity activity = (Activity) msg.obj;
                    Bundle bundle = msg.getData();
                    String json = bundle.getString("json");
                    MobGold gold = new Gson().fromJson(json, MobGold.class);
                    GoldList.add(gold);
                    gold_rv.setAdapter(new MyAdapter(mcontext,activity));
                    break;
                case FAIL:
                    Context context = (Context) msg.obj;
                    Toast.makeText(context, "请求数据失败,请检查网络状态", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private Context context;
        private Activity activity;

        private MyAdapter(Context context,Activity activity) {
            this.context = context;
            this.activity = activity;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.gold_rv_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            //极速
//            Gold.ResultBean resultBean = GoldList.get(0).getResult().get(position);
//            holder.gold_item_tv_name.setText(resultBean.getTypename());
//            holder.gold_item_tv_closePri.setText(resultBean.getPrice());
//            holder.gold_item_tv_time.setText(resultBean.getUpdatetime().substring(11,19));
//            float changepercent = Float.parseFloat(resultBean.getChangepercent().replaceAll("%","").trim());
//            if (changepercent < 0){
//                holder.gold_item_tv_limit.setTextColor(Color.parseColor("#C0362E"));
//                holder.gold_item_tv_limit.setText(resultBean.getChangepercent());
//            }
//            holder.gold_item_tv_limit.setText(resultBean.getChangepercent());

            //Mob
            MobGold.ResultBean resultBean = GoldList.get(0).getResult().get(position);
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
                Intent intent = new Intent(context, GoldChartActivity.class);
                intent.putExtra("close", resultBean.getClosePri());
                intent.putExtra("limit", resultBean.getLimit());
                intent.putExtra("open", resultBean.getOpenPri());
                intent.putExtra("yes", resultBean.getYesDayPic());
                intent.putExtra("high", resultBean.getHighPic());
                intent.putExtra("low", resultBean.getLowPic());
                activity.startActivityForResult(intent,100);
                /*Toast.makeText(context,"你点击了第" + position + "个item",Toast.LENGTH_SHORT).show();*/
            });
        }

        @Override
        public int getItemCount() {
            return GoldList.get(0).getResult().size();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
