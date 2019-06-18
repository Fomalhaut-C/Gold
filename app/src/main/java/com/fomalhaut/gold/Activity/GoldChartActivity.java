package com.fomalhaut.gold.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fomalhaut.gold.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

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
    private LineChart gold_lc;
    private List<Entry> entryList = new ArrayList<>();

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
        gold_lc = (LineChart) findViewById(R.id.gold_lc);
        gold_tv_closePri.setText(getIntent().getStringExtra("close"));
        gold_tv_limit.setText(getIntent().getStringExtra("limit"));
        gold_tv_openPri.setText(getIntent().getStringExtra("open"));
        gold_tv_yesDayPic.setText(getIntent().getStringExtra("yes"));
        gold_tv_highPic.setText(getIntent().getStringExtra("high"));
        gold_tv_lowPic.setText(getIntent().getStringExtra("low"));
        setData();
    }

    private void setData() {
        float close = Float.parseFloat(getIntent().getStringExtra("close"));
        entryList.add(new Entry(1,close));
        entryList.add(new Entry(2,close-2));
        entryList.add(new Entry(3,close-5));
        entryList.add(new Entry(4,close+8));
        entryList.add(new Entry(5,close-12));
        LineDataSet lineDataSet = new LineDataSet(entryList, "");
        LineData lineData = new LineData(lineDataSet);
        gold_lc.getLegend().setEnabled(false);
        gold_lc.getDescription().setEnabled(false);
        gold_lc.getAxisRight().setEnabled(false);
        XAxis xAxis = gold_lc.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        YAxis axisLeft = gold_lc.getAxisLeft();
        axisLeft.setLabelCount(10);
        gold_lc.setData(lineData);
        gold_lc.invalidate();
    }
}
