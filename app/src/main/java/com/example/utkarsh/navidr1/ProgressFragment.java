package com.example.utkarsh.navidr1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * Created by Utkarsh on 13-04-2016.
 */
public class ProgressFragment extends android.support.v4.app.Fragment {
    private ProgressBar firstBar;
    private View view;

    public ProgressFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.progressbar, container, false);
        firstBar = (ProgressBar) view.findViewById(R.id.progressBar4);
        firstBar.setVisibility(View.VISIBLE);
        firstBar.setMax(100);
        firstBar.setProgress(25);
        firstBar.getProgressDrawable().setColorFilter(
                Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
        firstBar.setScaleY(2f);
        TextView t1 = (TextView)view.findViewById(R.id.text1);
        t1.setText(25 + "");
        TextView t2 = (TextView)view. findViewById(R.id.text2);
        t2.setText("25" + "/" + "100" + "");
        return view;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
