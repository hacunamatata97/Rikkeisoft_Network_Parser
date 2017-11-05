package com.example.hacunamatata.rikkeisoft_network_parser.rikkeisoft_network_parser.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MyItemDecorator extends RecyclerView.ItemDecoration {
    private int itemSpace;

    public MyItemDecorator(int itemSpace) {
        this.itemSpace = itemSpace;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.bottom = itemSpace;
        outRect.top = itemSpace;
        outRect.left = itemSpace;
        outRect.right = itemSpace;
    }
}
