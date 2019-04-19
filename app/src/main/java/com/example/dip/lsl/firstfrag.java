package com.example.dip.lsl;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class firstfrag extends Fragment {

    int x;
    public firstfrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_firstfrag, container, false);
        final Button b = v.findViewById(R.id.b1);
        final TextView a = v.findViewById(R.id.a1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.setText("new one"+x);
            }
        });
        return v;
    }

}
