package com.example.utkarsh.navidr1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * Created by Utkarsh on 13-04-2016.
 */

public class HomeFragment  extends android.support.v4.app.Fragment {

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("arpit1", "working");
        View rootView = inflater.inflate(R.layout.home_frag, container, false);

        return rootView;
    }

}