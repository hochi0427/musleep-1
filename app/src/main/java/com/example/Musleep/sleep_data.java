package com.example.Musleep;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.utils.Easing;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link sleep_data#newInstance} factory method to
 * create an instance of this fragment.
 */
public class sleep_data extends Fragment {
    PieChart pieChart;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public sleep_data() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment sleep_data.
     */
    // TODO: Rename and change types and number of parameters
    public static sleep_data newInstance(String param1, String param2) {
        sleep_data fragment = new sleep_data();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            pieChart.invalidate();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sleep_data, container, false);
        pieChart = (PieChart) view.findViewById(R.id.piechart);

        pieChart.setUsePercentValues(true); //如果啟用此選項,則圖表中的值將以百分比形式繪製,而不是以原始值繪製
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5); //將額外的偏移量(在圖表檢視周圍)附加到自動計算的偏移量
        //較高的值表明速度會緩慢下降 例如如果它設定為0,它會立即停止。1是一個無效的值,並將自動轉換為0.999f。
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        //設定中間字型
        pieChart.setCenterText("實際睡眠時間\n\n7小時56分鐘");
        pieChart.setCenterTextSize(23);
        pieChart.setCenterTextColor(Color.parseColor("#7c7877"));
        pieChart.setRotationEnabled(false);
        pieChart.setDrawHoleEnabled(true); //設定為true將餅中心清空
        pieChart.setHoleColor(Color.WHITE); //繪製在PieChart中心的顏色
        pieChart.setTransparentCircleRadius(60f); //設定在最大半徑的百分比餅圖中心孔半徑(最大=整個圖的半徑),預設為50%
        pieChart.setEntryLabelTextSize(16); //控制label大小9

        ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(34, "清醒"));
        yValues.add(new PieEntry(23, "快速動眼期"));
        yValues.add(new PieEntry(14, "淺層"));
        yValues.add(new PieEntry(35, "深層"));

        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(20f);
        //add many colors
        ArrayList<Integer> colors=new ArrayList<Integer>();

        for(int c: ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for(int c: ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for(int c: ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for(int c: ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for(int c: ColorTemplate.PASTEL_COLORS)
            colors.add(c);
//        colors.add(ColorTemplate.getHoloBlue();
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(18);
        data.setValueTextColor(Color.WHITE);

        pieChart.setData(data);

        Legend l = pieChart.getLegend();
        l.setEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setForm(Legend.LegendForm.DEFAULT);
        l.setYOffset(20);
        l.setFormSize(15);
        l.setFormToTextSpace(10);
        l.setYEntrySpace(40);
        l.setDrawInside(false);
        l.setTextSize(18);
        l.setTextColor(Color.parseColor("#7c7877"));

        return view;


    }



}

