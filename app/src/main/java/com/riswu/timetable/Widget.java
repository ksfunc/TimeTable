package com.riswu.timetable;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.Calendar;

public class Widget extends AppWidgetProvider {
  public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
    // Construct the RemoteViews object
    String day = Widget.getDayId();
    if (day.equals("sun") || day.equals("sat")) {
      RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_empty);

      Intent intent = new Intent(context, MainActivity.class);
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      PendingIntent pendingIntent = PendingIntent.getActivity(context, appWidgetId, intent, 0);
      views.setOnClickPendingIntent(R.id.widget_root, pendingIntent);

      // Instruct the widget manager to update the widget
      appWidgetManager.updateAppWidget(appWidgetId, views);
    } else {
      RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
      Repository repository = Repository.getInstance(context.getApplicationContext());
      views.setTextViewText(R.id.subject_tile_view_1_start,
        String.format("%02d:%02d", repository.getTimes().get("1").startHour, repository.getTimes().get("1").startMinute));
      views.setTextViewText(R.id.subject_tile_view_1_name, repository.getSubjects().get(day + "-1").name);
      views.setTextViewText(R.id.subject_tile_view_1_room, repository.getSubjects().get(day + "-1").room);
      views.setTextViewText(R.id.subject_tile_view_2_start,
        String.format("%02d:%02d", repository.getTimes().get("2").startHour, repository.getTimes().get("2").startMinute));
      views.setTextViewText(R.id.subject_tile_view_2_name, repository.getSubjects().get(day + "-2").name);
      views.setTextViewText(R.id.subject_tile_view_2_room, repository.getSubjects().get(day + "-2").room);
      views.setTextViewText(R.id.subject_tile_view_3_start,
        String.format("%02d:%02d", repository.getTimes().get("3").startHour, repository.getTimes().get("3").startMinute));
      views.setTextViewText(R.id.subject_tile_view_3_name, repository.getSubjects().get(day + "-3").name);
      views.setTextViewText(R.id.subject_tile_view_3_room, repository.getSubjects().get(day + "-3").room);
      views.setTextViewText(R.id.subject_tile_view_4_start,
        String.format("%02d:%02d", repository.getTimes().get("4").startHour, repository.getTimes().get("4").startMinute));
      views.setTextViewText(R.id.subject_tile_view_4_name, repository.getSubjects().get(day + "-4").name);
      views.setTextViewText(R.id.subject_tile_view_4_room, repository.getSubjects().get(day + "-4").room);
      views.setTextViewText(R.id.subject_tile_view_5_start,
        String.format("%02d:%02d", repository.getTimes().get("5").startHour, repository.getTimes().get("5").startMinute));
      views.setTextViewText(R.id.subject_tile_view_5_name, repository.getSubjects().get(day + "-5").name);
      views.setTextViewText(R.id.subject_tile_view_5_room, repository.getSubjects().get(day + "-5").room);
      views.setTextViewText(R.id.subject_tile_view_6_start,
        String.format("%02d:%02d", repository.getTimes().get("6").startHour, repository.getTimes().get("6").startMinute));
      views.setTextViewText(R.id.subject_tile_view_6_name, repository.getSubjects().get(day + "-6").name);
      views.setTextViewText(R.id.subject_tile_view_6_room, repository.getSubjects().get(day + "-6").room);

      Intent intent = new Intent(context, MainActivity.class);
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      PendingIntent pendingIntent = PendingIntent.getActivity(context, appWidgetId, intent, 0);
      views.setOnClickPendingIntent(R.id.widget_root, pendingIntent);

      // Instruct the widget manager to update the widget
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

