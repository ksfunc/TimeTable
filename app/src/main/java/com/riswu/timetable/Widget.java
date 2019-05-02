package com.riswu.timetable;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;

public class Widget extends AppWidgetProvider {
  public static void update(Context context) {
    Intent intent = new Intent(context.getApplicationContext(), Widget.class);
    intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context.getApplicationContext());
    int[] ids = appWidgetManager.getAppWidgetIds(new ComponentName(context.getApplicationContext(), Widget.class));
    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
    context.getApplicationContext().sendBroadcast(intent);
  }

  public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
    String day = Widget.getDayId();
    if (day == null) return;

    if (day.equals("sun") || day.equals("sat")) {
      RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_empty);

      Intent intent = new Intent(context, MainActivity.class);
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      PendingIntent pendingIntent = PendingIntent.getActivity(context, appWidgetId, intent, 0);
      views.setOnClickPendingIntent(R.id.widget_root, pendingIntent);

      appWidgetManager.updateAppWidget(appWidgetId, views);
    } else {
      Repository repository = Repository.getInstance(context.getApplicationContext());
      if (repository.getPreferences().has6ThClass) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_6_classes);
        Map<String, Time> times = repository.getTimes();
        Map<String, Subject> subjects = repository.getSubjects();
        String startTime1 = String.format("%02d:%02d", times.get("1").startHour, times.get("1").startMinute);
        views.setTextViewText(R.id.subject_tile_view_1_start, startTime1);
        views.setTextViewText(R.id.subject_tile_view_1_name, subjects.get(day + "-1").name);
        views.setTextViewText(R.id.subject_tile_view_1_room, subjects.get(day + "-1").room);
        String startTime2 = String.format("%02d:%02d", times.get("2").startHour, times.get("2").startMinute);
        views.setTextViewText(R.id.subject_tile_view_2_start, startTime2);
        views.setTextViewText(R.id.subject_tile_view_2_name, subjects.get(day + "-2").name);
        views.setTextViewText(R.id.subject_tile_view_2_room, subjects.get(day + "-2").room);
        String startTime3 = String.format("%02d:%02d", times.get("3").startHour, times.get("3").startMinute);
        views.setTextViewText(R.id.subject_tile_view_3_start, startTime3);
        views.setTextViewText(R.id.subject_tile_view_3_name, subjects.get(day + "-3").name);
        views.setTextViewText(R.id.subject_tile_view_3_room, subjects.get(day + "-3").room);
        String startTime4 = String.format("%02d:%02d", times.get("4").startHour, times.get("4").startMinute);
        views.setTextViewText(R.id.subject_tile_view_4_start, startTime4);
        views.setTextViewText(R.id.subject_tile_view_4_name, subjects.get(day + "-4").name);
        views.setTextViewText(R.id.subject_tile_view_4_room, subjects.get(day + "-4").room);
        String startTime5 = String.format("%02d:%02d", times.get("5").startHour, times.get("5").startMinute);
        views.setTextViewText(R.id.subject_tile_view_5_start, startTime5);
        views.setTextViewText(R.id.subject_tile_view_5_name, subjects.get(day + "-5").name);
        views.setTextViewText(R.id.subject_tile_view_5_room, subjects.get(day + "-5").room);
        String startTime6 = String.format("%02d:%02d", times.get("6").startHour, times.get("6").startMinute);
        views.setTextViewText(R.id.subject_tile_view_6_start, startTime6);
        views.setTextViewText(R.id.subject_tile_view_6_name, subjects.get(day + "-6").name);
        views.setTextViewText(R.id.subject_tile_view_6_room, subjects.get(day + "-6").room);

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, appWidgetId, intent, 0);
        views.setOnClickPendingIntent(R.id.widget_root, pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
      } else {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_5_classes);
        Map<String, Time> times = repository.getTimes();
        Map<String, Subject> subjects = repository.getSubjects();
        String startTime1 = String.format("%02d:%02d", times.get("1").startHour, times.get("1").startMinute);
        views.setTextViewText(R.id.subject_tile_view_1_start, startTime1);
        views.setTextViewText(R.id.subject_tile_view_1_name, subjects.get(day + "-1").name);
        views.setTextViewText(R.id.subject_tile_view_1_room, subjects.get(day + "-1").room);
        String startTime2 = String.format("%02d:%02d", times.get("2").startHour, times.get("2").startMinute);
        views.setTextViewText(R.id.subject_tile_view_2_start, startTime2);
        views.setTextViewText(R.id.subject_tile_view_2_name, subjects.get(day + "-2").name);
        views.setTextViewText(R.id.subject_tile_view_2_room, subjects.get(day + "-2").room);
        String startTime3 = String.format("%02d:%02d", times.get("3").startHour, times.get("3").startMinute);
        views.setTextViewText(R.id.subject_tile_view_3_start, startTime3);
        views.setTextViewText(R.id.subject_tile_view_3_name, subjects.get(day + "-3").name);
        views.setTextViewText(R.id.subject_tile_view_3_room, subjects.get(day + "-3").room);
        String startTime4 = String.format("%02d:%02d", times.get("4").startHour, times.get("4").startMinute);
        views.setTextViewText(R.id.subject_tile_view_4_start, startTime4);
        views.setTextViewText(R.id.subject_tile_view_4_name, subjects.get(day + "-4").name);
        views.setTextViewText(R.id.subject_tile_view_4_room, subjects.get(day + "-4").room);
        String startTime5 = String.format("%02d:%02d", times.get("5").startHour, times.get("5").startMinute);
        views.setTextViewText(R.id.subject_tile_view_5_start, startTime5);
        views.setTextViewText(R.id.subject_tile_view_5_name, subjects.get(day + "-5").name);
        views.setTextViewText(R.id.subject_tile_view_5_room, subjects.get(day + "-5").room);

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, appWidgetId, intent, 0);
        views.setOnClickPendingIntent(R.id.widget_root, pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
      }
    }
  }

  private static String getDayId() {
    String day = null;
    switch (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
      case Calendar.SUNDAY:
        day = "sun";
        break;
      case Calendar.MONDAY:
        day = "mon";
        break;
      case Calendar.TUESDAY:
        day = "tue";
        break;
      case Calendar.WEDNESDAY:
        day = "wed";
        break;
      case Calendar.THURSDAY:
        day = "thu";
        break;
      case Calendar.FRIDAY:
        day = "fri";
        break;
      case Calendar.SATURDAY:
        day = "sat";
        break;
    }
    return day;
  }

  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    for (int appWidgetId : appWidgetIds) {
      Widget.updateAppWidget(context, appWidgetManager, appWidgetId);
    }
  }

  private void startAlarm(Context context) {
    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.add(Calendar.DAY_OF_MONTH, 1);
    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(context, Widget.class);
    intent.setAction("com.riswu.timetable.action.DATE_CHANGED");
    PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
  }

  private void stopAlarm(Context context) {
    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(context, Widget.class);
    PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
    alarmManager.cancel(alarmIntent);
  }

  @Override
  public void onEnabled(Context context) {
    super.onEnabled(context);
    this.startAlarm(context);
  }

  @Override
  public void onDisabled(Context context) {
    super.onDisabled(context);
    this.stopAlarm(context);
  }

  @Override
  public void onReceive(Context context, Intent intent) {
    super.onReceive(context, intent);
    switch (intent.getAction()) {
      case Intent.ACTION_TIMEZONE_CHANGED:
        this.stopAlarm(context);
        this.startAlarm(context);
        Widget.update(context);
        break;
      case "com.riswu.timetable.action.DATE_CHANGED":
        this.stopAlarm(context);
        this.startAlarm(context);
        Widget.update(context);
        break;
    }
  }
}

