package com.example.myapplication.Model.ObjectClass;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LoadMoreScroll extends RecyclerView.OnScrollListener {
    int itemandautien = 0;
    int tongitem = 0;
    int itemloadtruoc = 6;
    RecyclerView.LayoutManager layoutManager;
    ILoadMore iLoadMore;

    public LoadMoreScroll(RecyclerView.LayoutManager layoutManager, ILoadMore iLoadMore){
        this.layoutManager = layoutManager;
        this.iLoadMore = iLoadMore;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        tongitem = layoutManager.getItemCount();

        if (layoutManager instanceof LinearLayoutManager){
            itemandautien = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }else  if (layoutManager instanceof GridLayoutManager){
            itemandautien = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }

        if(tongitem <= (itemandautien + itemloadtruoc)){
//            Log.d("kiemtra", tongitem + " - " + itemandautien);
            iLoadMore.LoadMore(tongitem);
        }
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}
