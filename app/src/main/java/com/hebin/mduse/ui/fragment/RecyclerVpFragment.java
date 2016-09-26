package com.hebin.mduse.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hebin.mduse.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecyclerVpFragment extends Fragment {

    @Bind(R.id.iv_vp)
    ImageView ivVp;


    public static RecyclerVpFragment newInstance(int param1) {
        RecyclerVpFragment fragment = new RecyclerVpFragment();
        Bundle args = new Bundle();
        args.putInt("imgpath", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_vp, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        if (bundle != null) {
            ivVp.setBackgroundResource(bundle.getInt("imgpath"));
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
