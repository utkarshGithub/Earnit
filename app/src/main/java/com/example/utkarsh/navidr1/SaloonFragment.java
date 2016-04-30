package com.example.utkarsh.navidr1;

/**
 * Created by Utkarsh on 14-04-2016.
 */
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Utkarsh on 13-04-2016.
 */
public class SaloonFragment extends Fragment {

    private View v;

    public SaloonFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.frag_saloon, container, false);
        Button b= (Button)v.findViewById(R.id.pay1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getContext(),UHF.class);
                startActivity(i);
            }
        });
        return v;
    }

}
