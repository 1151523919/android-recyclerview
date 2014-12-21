package com.sqisland.android.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class AutofitRecyclerView extends RecyclerView {
  private GridLayoutManager manager;
  private int columnWidth = -1;

  public AutofitRecyclerView(Context context) {
    super(context);
    init(context, null);
  }

  public AutofitRecyclerView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public AutofitRecyclerView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {
    if (attrs != null) {
      int[] attrsArray = {
          android.R.attr.columnWidth
      };
      TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
      columnWidth = array.getDimensionPixelSize(0, -1);
    }

    manager = new GridLayoutManager(getContext(), 1);
    setLayoutManager(manager);
  }

  @Override
  protected void onMeasure(int widthSpec, int heightSpec) {
    super.onMeasure(widthSpec, heightSpec);
    if (columnWidth > 0) {
      int spanCount = getMeasuredWidth() / columnWidth;
      manager.setSpanCount(spanCount);
    }
  }
}
