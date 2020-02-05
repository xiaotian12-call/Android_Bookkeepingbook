package com.me.familybookkeepingbook;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;


/**
 * A simple {@link Fragment} subclass.
 */
public class chertFragment extends Fragment {
    private LineChartView mChart;
    private Map<String,Integer> table = new TreeMap<>();
    private LineChartData mData;
    private AccountRecordViewModel accountRecordViewModel;
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
    public chertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //mChart = container.findViewById(R.id.chart);
        return inflater.inflate(R.layout.fragment_chert, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mChart = view.findViewById(R.id.chart);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        accountRecordViewModel = ViewModelProviders.of(requireActivity()).get(AccountRecordViewModel.class);
        mData = new LineChartData();
        List<AccountRecord> allDate = accountRecordViewModel.getAllAcountRecord().getValue();
        generateValues(allDate);
        generateData();
    }
    private void generateData() {
        List<Line> lines = new ArrayList<>();
        List<PointValue> values = new ArrayList<>();
        int indexX = 0;
        for(Integer value : table.values()){
            values.add(new PointValue(indexX, value));
            indexX++;
        }
        Line line = new Line(values);
        line.setColor(ChartUtils.COLORS[0]);
        line.setShape(ValueShape.CIRCLE);
        line.setPointColor(ChartUtils.COLORS[1]);
        lines.add(line);
        mData.setLines(lines);
        setAxis();
        mChart.setLineChartData(mData);
    }

    private void generateValues(List<AccountRecord> allDate) {
        if(allDate != null){
            for (int i = 0; i < allDate.size(); i++) {
                AccountRecord costBean = allDate.get(i);
                String costDate = costBean.getCostTime();
                Double costMoney = costBean.getCostMoney();
                mAxisXValues.add(new AxisValue(i).setLabel(costDate));
                if(!table.containsKey(costDate)){
                    table.put(costDate,costMoney.intValue());
                    //mAxisXValues.add(new AxisValue(i).setLabel(costDate));
                }else {
                    int originMoney = table.get(costDate);
                    table.put(costDate,originMoney + costMoney.intValue());
                }
            }
        }
    }

    private void setAxis() {
        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.GRAY);  //设置字体颜色
        axisX.setName("日期");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(7); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        mData.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setName("钱");//y轴标注
        axisY.setTextSize(10);//设置字体大小
        mData.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边
    }
}
