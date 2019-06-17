package com.fomalhaut.gold.Fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fomalhaut.gold.Bean.Forward;
import com.fomalhaut.gold.Bean.Gold;
import com.fomalhaut.gold.R;
import com.fomalhaut.gold.Utils.HttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForwardFragment extends Fragment {


    private static RecyclerView forward_rv;
    private static List<Forward> forwardList = new ArrayList<>();
    private static final int SUCCESS = 1;
    private static final int FAIL = -1;

    private Context context;
    private MyHandler handler;

    public ForwardFragment(Context context) {
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forward, container, false);
        initView(view);
        handler = new MyHandler();
        return view;
    }

    private void initView(View view) {
        forward_rv = view.findViewById(R.id.forward_rv);
        forward_rv.setLayoutManager(new LinearLayoutManager(context));
//        initData();
    }

    /*private void initData() {
        String address = "https://api.jisuapi.com/gold/shfutures?appkey=3069248ceca810cc";
        HttpUtils.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String json = response.body().string();
                Message message = new Message();
                message.what = SUCCESS;
                message.obj = json;
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
    }*/

    private static class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case SUCCESS:
                    String json = (String) msg.obj;
                    Forward forward = new Gson().fromJson(json, Forward.class);
                    forwardList.add(forward);
                    forward_rv.setAdapter(new MyAdapter());
                    break;
                case FAIL:
                    Context context = (Context) msg.obj;
                    Toast.makeText(context, "请求数据失败,请检查网络状态", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.forward_rv_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Forward.ResultBean resultBean = forwardList.get(0).getResult().get(position);
            holder.forward_item_tv_name.setText(resultBean.getTypename());
            holder.forward_item_tv_closePri.setText(resultBean.getPrice());
            holder.forward_item_tv_time.setText((int) System.currentTimeMillis());
            holder.forward_item_tv_limit.setText(resultBean.getChangequantity());
        }

        @Override
        public int getItemCount() {
            return forwardList.get(0).getResult().size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            View view;
            TextView forward_item_tv_name;
            TextView forward_item_tv_closePri;
            TextView forward_item_tv_time;
            TextView forward_item_tv_limit;

            ViewHolder(View view) {
                super(view);
                this.view = view;
                this.forward_item_tv_name = (TextView) view.findViewById(R.id.forward_item_tv_name);
                this.forward_item_tv_closePri = (TextView) view.findViewById(R.id.forward_item_tv_closePri);
                this.forward_item_tv_time = (TextView) view.findViewById(R.id.forward_item_tv_time);
                this.forward_item_tv_limit = (TextView) view.findViewById(R.id.forward_item_tv_limit);
            }
        }
    }

}
