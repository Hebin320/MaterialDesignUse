package com.hebin.mduse.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.hebin.mduse.listener.AdListener;
import com.hebin.mduse.R;
import com.hebin.mduse.adapter.RecyclerAdapter;
import com.hebin.mduse.entity.RecyclerBean;
import com.hebin.mduse.listener.RecyclerItemClickListener;
import com.hebin.mduse.adapter.RecyclerVpAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class MainFragment extends Fragment {


    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.vp_books)
    AutoScrollViewPager vpBooks;
    @Bind(R.id.ll_point)
    LinearLayout llPoint;
    @Bind(R.id.header)
    RecyclerViewHeader header;
    @Bind(R.id.recyclerView_gird)
    RecyclerView recyclerViewGird;

    private List<RecyclerBean> mList = new ArrayList<>();
    private String[] title = {"测试文字_01", "测试文字_02", "测试文字_03", "测试文字_04", "测试文字_05", "测试文字_06", "测试文字_07", "测试文字_08", "测试文字_09", "测试文字_10"};
    private int[] imgPath = {R.mipmap.ic_recyclerview_01, R.mipmap.ic_recyclerview_02, R.mipmap.ic_recyclerview_03, R.mipmap.ic_recyclerview_04, R.mipmap.ic_recyclerview_05,
            R.mipmap.ic_recyclerview_06, R.mipmap.ic_recyclerview_07, R.mipmap.ic_recyclerview_08, R.mipmap.ic_recyclerview_09, R.mipmap.ic_recyclerview_10};
    private int[] vpImgPath = {R.mipmap.ic_viewpager_01, R.mipmap.ic_viewpager_02, R.mipmap.ic_viewpager_03};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewHeader header = (RecyclerViewHeader) view.findViewById(R.id.header);
        AutoScrollViewPager viewPager = (AutoScrollViewPager) view.findViewById(R.id.vp_books);
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < vpImgPath.length; i++) {
            RecyclerVpFragment recyclerVpFragment = RecyclerVpFragment.newInstance(vpImgPath[i]);
            fragmentList.add(recyclerVpFragment);
        }
        RecyclerVpAdapter bAdapter = new RecyclerVpAdapter(getChildFragmentManager(), fragmentList);
        viewPager.setAdapter(bAdapter);
        LinearLayout llPoint = (LinearLayout) header.findViewById(R.id.ll_point);
        viewPager.addOnPageChangeListener(new AdListener(AdListener.setImageView(getActivity(), llPoint, fragmentList)));
        viewPager.setCurrentItem(0);
        viewPager.startAutoScroll();
        viewPager.setInterval(5000);
        //开启Viewpager的自动轮播
        header.attachTo(recyclerView, true);
        setData();
        RecyclerAdapter mAdapter = new RecyclerAdapter(getActivity(), mList);
        //RecyclerView子项的点击事件
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mAdapter.onItemClickListener));
        recyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void setData() {
        mList.clear();
        for (int i = 0; i < title.length; i++) {
            RecyclerBean recyclerBean = new RecyclerBean();
            recyclerBean.setImg(imgPath[i]);
            recyclerBean.setInfo(title[i]);
            recyclerBean.setTitle(title[i]);
            recyclerBean.setCatalog(title[i]);
            recyclerBean.setAuthor_intro(title[i]);
            recyclerBean.setSummary(title[i]);
            recyclerBean.setImglarge(imgPath[i]);
            mList.add(recyclerBean);
        }
    }
}
