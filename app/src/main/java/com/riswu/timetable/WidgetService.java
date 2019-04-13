package com.riswu.timetable;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class WidgetService extends Service {
  private final BroadcastReceiver receiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      switch (intent.getAction()) {
        case Intent.ACTION_AIRPLANE_MODE_CHANGED:
          Widget.update(context);
          break;
        case Intent.ACTION_DATE_CHANGED:
          Widget.update(context);
          break;
        case Intent.ACTION_TIMEZONE_CHANGED:
          Widget.update(context);
          break;
      }
    }
  };

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    this.getApplicationContext().registerReceiver(this.receiver, new IntentFilter(Intent.ACTION_DATE_CHANGED));
    this.getApplicationContext().registerReceiver(this.receiver, new IntentFilter(Intent.ACTION_TIMEZONE_CHANGED));
  }

  @Override
  public void onDestroy() {
    this.getApplicationContext().unregisterReceiver(this.receiver);
    super.onDestroy();
  }
}
