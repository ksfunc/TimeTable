package com.riswu.timetable;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;

import java.util.Calendar;
import java.util.Map;

public class Widget extends AppWidgetProvider {
  public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
    String day = Widget.getDayId();
    if (day.equals("sun") || day.equals("sat")) {
      RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_empty);

      Intent intent = new Intent(context, MainActivity.class);
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      PendingIntent pendingIntent = PendingIntent.getActivity(context, appWidgetId, intent, 0);
      views.setOnClickPendingIntent(R.id.widget_root, pendingIntent);

      appWidgetManager.updateAppWidget(appWidgetId, views);
    } else {
      RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
      Repository repository = Repository.getInstance(context.getApplicationContext());
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

      if (repository.getPreferences().has6ThClass) {
        views.setViewVisibility(R.id.subject_tile_view_6, View.VISIBLE);
      } else {
        views.setViewVisibility(R.id.subject_tile_view_6, View.GONE);
      }

      Intent intent = new Intent(context, MainActivity.class);
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      PendingIntent pendingIntent = PendingIntent.getActivity(context, appWidgetId, intent, 0);
      views.setOnClickPendingIntent(R.id.widget_root, pendingIntent);

      appWidgetManager.updateAppWidget(appWidgetId, views);
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
    // There may be multiple widgets active, so update all of them
    for (int appWidgetId : appWidgetIds) {
      Widget.updateAppWidget(context, appWidgetManager, appWidgetId);
    }
  }

  @Override
  public void onEnabled(Context context) {
    // Enter relevant functionality for when the first widget is created
  }

  @Override
  public void onDisabled(Context context) {
    // Enter relevant functionality for when the last widget is disabled
  }
}

