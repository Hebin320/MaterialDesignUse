package com.hebin.mduse.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;


import com.hebin.mduse.R;
import com.hebin.mduse.entity.RecyclerBean;
import com.hebin.mduse.ui.activity.RecyclerDetailActivity;
import com.hebin.mduse.listener.RecyclerItemClickListener;
import com.hebin.mduse.uitl.Utils;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<RecyclerBean> list = new ArrayList<>();
    private static final int ANIMATED_ITEMS_COUNT = 4;
    private Activity context;
    private boolean animateItems = false;
    private int lastAnimatedPosition = -1;

    public RecyclerAdapter(Activity context , List<RecyclerBean> mlist) {
        TypedValue mTypedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        list = updateItems(mlist,true);
        this.context = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivMain;

        public int position;

        public ViewHolder(View v) {
            super(v);
            ivMain = (ImageView) v.findViewById(R.id.ivMain);
        }
    }


    private void runEnterAnimation(View view, int position) {
        if (!animateItems || position >= ANIMATED_ITEMS_COUNT - 1) {
            return;
        }

        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            view.setTranslationY(Utils.getScreenHeight(context));
            view.animate()
                    .translationY(0)
                    .setStartDelay(100 * position)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setDuration(700)
                    .start();
        }
    }

    public List<RecyclerBean> updateItems(List<RecyclerBean> books, boolean animated) {
        List<RecyclerBean> list = new ArrayList<>();
        animateItems = animated;
        lastAnimatedPosition = -1;
        list.addAll(books);
        notifyDataSetChanged();
        return list;
    }


    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        runEnterAnimation(holder.itemView, position);
        RecyclerBean recyclerBean = list.get(position);
        holder.ivMain.setBackgroundResource(recyclerBean.getImg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private RecyclerBean getMain(int pos) {
        return list.get(pos);
    }


    public RecyclerItemClickListener.OnItemClickListener onItemClickListener = new RecyclerItemClickListener.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            RecyclerBean main = getMain(position);
            Intent intent = new Intent(context, RecyclerDetailActivity.class);
            intent.putExtra("main", main);

            ActivityOptionsCompat options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(context,
                            view.findViewById(R.id.ivMain),context.getString(R.string.transition_book_img));

            ActivityCompat.startActivity(context, intent, options.toBundle());

        }
    };
}
