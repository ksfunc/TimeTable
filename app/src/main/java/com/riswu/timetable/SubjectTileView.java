package com.riswu.timetable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SubjectTileView extends LinearLayout {
  public SubjectTileView(Context context) {
    this(context, null);
  }

  public SubjectTileView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public SubjectTileView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    LayoutInflater.from(context).inflate(R.layout.view_subject_tile, this);
  }

  public void setSubject(Subject subject) {
    if (subject != null) {
      ((TextView) this.findViewById(R.id.name)).setText(subject.name);
      ((TextView) this.findViewById(R.id.room)).setText(subject.room);
    }
  }
}
