package com.example.Musleep;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link sleep_trend#newInstance} factory method to
 * create an instance of this fragment.
 */
public class sleep_trend extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public sleep_trend() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment sleep_trend.
     */
    // TODO: Rename and change types and number of parameters
    public static sleep_trend newInstance(String param1, String param2) {
        sleep_trend fragment = new sleep_trend();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

//        BarChart barChart = findViewById(R.id.barChart);
//        ArrayList<BarEntry> visitors = new ArrayList();
//        visitors.add(new BarEntry( 2014,420));
//        visitors.add(new BarEntry( 2015,475));
//        visitors.add(new BarEntry( 2016,500));
//        visitors.add(new BarEntry( 2017,450));
//        visitors.add(new BarEntry( 2018,475));
//
//        BarDataSet barDataSet = new BarDataSet(visitors,"visitors");
//        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
//        barDataSet.setValueTextColor(Color.BLACK);
//        barDataSet.setValueTextSize(16f);
//
//        BarData barData = new BarData(barDataSet);
//
//        barChart.setFitBars(true);
//        barChart.setData(barData);
//        barChart.getDescription().setText("Bar Chart Example");
//        barChart.animateY(2000);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sleep_trend, container, false);
    }
}