package com.riswu.timetable;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
      MainFragment fragment = MainFragment.getInstance();
      FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
      transaction.add(R.id.container, fragment);
      transaction.commit();
    }
  }
}