package com.setoalan.chinachef;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private String mDrawerTitles[];

    public NavigationDrawerAdapter(String drawerTitles[]) {
        mDrawerTitles = drawerTitles;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        int holderId;
        TextView mDrawerTitle;

        public ViewHolder(View itemView,int ViewType) {
            super(itemView);
            if (ViewType == TYPE_ITEM) {
                mDrawerTitle = (TextView) itemView.findViewById(R.id.drawer_item_text);
                holderId = 1;
            } else {
                holderId = 0;
            }
        }

    }

    @Override
    public NavigationDrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_drawer,parent,false);
            return new ViewHolder(view, viewType);
        } else if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_drawer_header,parent,false);
            return new ViewHolder(view, viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(NavigationDrawerAdapter.ViewHolder holder, int position) {
        if (holder.holderId == 1)
            holder.mDrawerTitle.setText(mDrawerTitles[position - 1]);
    }

    @Override
    public int getItemCount() {
        return mDrawerTitles.length + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

}