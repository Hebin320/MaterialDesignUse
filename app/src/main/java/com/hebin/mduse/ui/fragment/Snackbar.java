package com.hebin.mduse.ui.fragment;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hebin.mduse.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Snackbar extends Fragment {


    @Bind(R.id.flb_black)
    FloatingActionButton flbBlack;
    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_snackbar, container, false);
        ButterKnife.bind(this, view);
        flbBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.design.widget.Snackbar.make(coordinatorLayout,
                        "Snackbar", android.support.design.widget.Snackbar.LENGTH_LONG)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(
                                        getActivity(),
                                        "snackbar OK clicked",
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
