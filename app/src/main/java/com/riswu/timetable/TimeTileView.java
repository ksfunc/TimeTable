package com.riswu.timetable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TimeTileView extends LinearLayout {
  public TimeTileView(Context context) {
    this(context, null);
  }

  public TimeTileView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public TimeTileView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    LayoutInflater.from(context).inflate(R.layout.view_time_tile, this);
  }

  public void setNum(String num) {
    if (num != null) {
      ((TextView) this.findViewById(R.id.num)).setText(num);
    }
  }

  public void setTime(String time) {
    if (time != null) {
      ((TextView) this.findViewById(R.id.time)).setText(time);
    }
  }
}
