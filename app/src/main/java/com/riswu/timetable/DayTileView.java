package com.riswu.timetable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DayTileView extends LinearLayout {
  public DayTileView(Context context) {
    this(context, null);
  }

  public DayTileView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public DayTileView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    LayoutInflater.from(context).inflate(R.layout.view_day_tile, this);
  }

  public void setDay(String day) {
    if (day != null) {
      ((TextView) this.findViewById(R.id.day)).setText(day);
    }
  }
}
