package com.fomalhaut.gold.Activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fomalhaut.gold.Bean.Chart;
import com.fomalhaut.gold.Bean.Gold;
import com.fomalhaut.gold.R;
import com.fomalhaut.gold.Utils.HttpUtils;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GoldChartActivity extends AppCompatActivity {

    /**
     * 302.10
     */
    private TextView gold_tv_closePri;
    /**
     * 302.10
     */
    private TextView gold_tv_limit;
    /**
     * 300.10
     */
    private TextView gold_tv_openPri;
    /**
     * 290.12
     */
    private TextView gold_tv_yesDayPic;
    /**
     * 300.10
     */
    private TextView gold_tv_highPic;
    /**
     * 290.12
     */
    private TextView gold_tv_lowPic;
    private static List<Gold> TopList = new ArrayList<>();
    private static List<Chart> ContentList = new ArrayList<>();
    private List<CandleEntry> candleEntries = new ArrayList<>();
    private static final int SUCCESS_TOP = 1;
    private static final int FAIL_TOP = -1;
    private static final int SUCCESS_CONTENT = 2;
    private static final int FAIL_CONTENT = -2;
    private String symbol;
    private CandleStickChart gold_cs;
    private int count_chart = 0;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gold_chart);
        initView();
    }

    private void initView() {
        gold_tv_closePri = (TextView) findViewById(R.id.gold_tv_closePri);
        gold_tv_limit = (TextView) findViewById(R.id.gold_tv_limit);
        gold_tv_openPri = (TextView) findViewById(R.id.gold_tv_openPri);
        gold_tv_yesDayPic = (TextView) findViewById(R.id.gold_tv_yesDayPic);
        gold_tv_highPic = (TextView) findViewById(R.id.gold_tv_highPic);
        gold_tv_lowPic = (TextView) findViewById(R.id.gold_tv_lowPic);
        gold_cs = (CandleStickChart) findViewById(R.id.gold_cs);
        symbol = getIntent().getStringExtra("symbol");
        getData();
        runnable.run();
    }

    private void initData() {
        String address = "http://japi.zhimafix.com:8967/quote/price?symbol=" + symbol;
        HttpUtils.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String json = response.body().string();
                Message message = new Message();
                message.what = SUCCESS_TOP;
                message.obj = json;
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Message message = new Message();
                message.what = FAIL_TOP;
                message.obj = GoldChartActivity.this;
                handler.sendMessage(message);
            }
        });
    }

    private void getData() {
        String address = "http://japi.zhimafix.com:8967/quote/kline?devid=80002&appid=goldtoutiao&uid=&end=-1&type=5&symbol=" + symbol + "&nonceStr=fbcca87e-75f9-4716-96c4-1b9cc06c320e";
        HttpUtils.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message = new Message();
                message.what = SUCCESS_CONTENT;
                message.obj = json;
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.what = SUCCESS_CONTENT;
                message.obj = GoldChartActivity.this;
                handler.sendMessage(message);
            }
        });
    }

    private void setData() {
        if (count_chart < 1){
            setUI();
            for (int i = 0; i < ContentList.get(0).getData().size(); i++) {
                Chart.DataBean dataBean = ContentList.get(0).getData().get(i);
                float high = (float) dataBean.getHigh();
                float low = (float) dataBean.getLow();
                float open = (float) dataBean.getOpen();
                float close = (float) dataBean.getClose();
                candleEntries.add(new CandleEntry(i, high, low, open, close));
            }
        }else {
            candleEntries.remove(0);
            Gold.DataBean dataBean = TopList.get(0).getData().get(0);
            float high = (float) dataBean.getTodayHigh();
            float low = (float) dataBean.getTodayLow();
            float open = (float) dataBean.getTodayOpen();
            float close = (float) dataBean.getBid();
            candleEntries.add(new CandleEntry(ContentList.get(0).getData().size()+ count_chart,high, low, open, close));
        }
        CandleDataSet candleDataSet = new CandleDataSet(candleEntries, "");
        candleDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        candleDataSet.setShadowColor(Color.DKGRAY);//影线颜色
        candleDataSet.setShadowColorSameAsCandle(true);//影线颜色与实体一致
        candleDataSet.setShadowWidth(0.7f);//影线
        candleDataSet.setDecreasingColor(Color.RED);
        candleDataSet.setDecreasingPaintStyle(Paint.Style.FILL);//红涨，实体
        candleDataSet.setIncreasingColor(Color.GREEN);
        candleDataSet.setIncreasingPaintStyle(Paint.Style.STROKE);//绿跌，空心
        candleDataSet.setNeutralColor(Color.RED);//当天价格不涨不跌（一字线）颜色
        candleDataSet.setHighlightLineWidth(1f);//选中蜡烛时的线宽
        candleDataSet.setDrawValues(false);//在图表中的元素上面是否显示数值
        candleDataSet.setShadowColorSameAsCandle(true);
        CandleData candleData = new CandleData(candleDataSet);
        gold_cs.setData(candleData);
        gold_cs.notifyDataSetChanged();
        gold_cs.invalidate();
        count_chart++;
    }

    private void setUI() {
        gold_cs.setNoDataText("You need to provide data for the mChart.");
        gold_cs.getDescription().setEnabled(false);
        gold_cs.setDragEnabled(true);// 是否可以拖拽
        gold_cs.setScaleEnabled(true);// 是否可以缩放
        gold_cs.setDrawGridBackground(false); // 是否显示表格颜色
        gold_cs.setBackgroundColor(Color.BLACK);// 设置背景
        gold_cs.setGridBackgroundColor(Color.WHITE);//设置表格背景色
        gold_cs.setTouchEnabled(true); // enable touch gestures
        gold_cs.setDragEnabled(true);// 是否可以拖拽
        gold_cs.setScaleEnabled(true);// 是否可以缩放
        gold_cs.setHighlightPerDragEnabled(true);
        gold_cs.setPinchZoom(false);// if disabled, scaling can be done on x- and y-axis separately
//        gold_cs.setScaleYEnabled(false);// if disabled, scaling can be done on x-axis
//        gold_cs.setScaleXEnabled(false);// if disabled, scaling can be done on  y-axis
        gold_cs.animateX(2500); // x轴方向
        XAxis xAxis = gold_cs.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
//        xAxis.setGridColor(colorLine);//X轴刻度线颜色
//        xAxis.setTextColor(colorText);//X轴文字颜色
        YAxis leftAxis = gold_cs.getAxisLeft();
        leftAxis.setEnabled(true);
        leftAxis.setLabelCount(7, false);
        leftAxis.setDrawGridLines(true);
        leftAxis.setDrawAxisLine(false);
//        leftAxis.setGridColor(colorLine);
//        leftAxis.setTextColor(colorText);
        YAxis rightAxis = gold_cs.getAxisRight();
        rightAxis.setEnabled(false);
        Legend legend = gold_cs.getLegend();  // 设置比例图标示
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setForm(Legend.LegendForm.SQUARE);// 样式
        legend.setFormSize(6f);// 字号
        legend.setTextColor(Color.WHITE);// 颜色
//        legend.setTypeface();// 字体

        String[] labels = {"红涨", "绿跌"};
        int[] colors = {Color.RED, Color.GREEN};
        legend.setExtra(colors, labels);//设置标注的颜色及内容，设置的效果如下图

        legend.setEnabled(true);//决定显不显示标签
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS_TOP:
                    TopList.clear();
                    String json = (String) msg.obj;
                    Gold gold = new Gson().fromJson(json, Gold.class);
                    TopList.add(gold);
                    Gold.DataBean dataBean = TopList.get(0).getData().get(0);
                    gold_tv_closePri.setText(dataBean.getBid() + "");
                    gold_tv_openPri.setText(dataBean.getTodayOpen() + "");
                    gold_tv_yesDayPic.setText(dataBean.getYesterdayClose() + "");
                    gold_tv_highPic.setText(dataBean.getTodayHigh() + "");
                    gold_tv_lowPic.setText(dataBean.getTodayLow() + "");
                    double limit = dataBean.getBid() - dataBean.getYesterdayClose();
                    if (count < 1){
                        gold_tv_limit.setText(new DecimalFormat("#.##").format(limit / 100) + "%");
                        count++;
                    }else{
                        gold_tv_limit.setText(new DecimalFormat("#.##").format(limit / 100) + "%");
                        setData();
                    }

                    break;
                case FAIL_TOP:
                    Context context = (Context) msg.obj;
                    Toast.makeText(context, "请求数据失败,请检查网络状态", Toast.LENGTH_SHORT).show();
                case SUCCESS_CONTENT:
                    TopList.clear();
                    String json_content = (String) msg.obj;
                    Chart chart = new Gson().fromJson(json_content, Chart.class);
                    ContentList.add(chart);
                    setData();
                    break;
                case FAIL_CONTENT:
                    Context mContext = (Context) msg.obj;
                    Toast.makeText(mContext, "请求数据失败,请检查网络状态", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    });

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            initData();
            handler.postDelayed(this,5000);
        }
    };

    @Override
    protected void onDestroy() {
        if (handler != null){
            handler.removeCallbacks(runnable);
        }
        super.onDestroy();
    }

}
