package com.riswu.timetable;

public class Time {
  public final int startHour;
  public final int startMinute;
  public final int endHour;
  public final int endMinute;

  public Time(int startHour, int startMinute, int endHour, int endMinute) {
    this.startHour = startHour;
    this.startMinute = startMinute;
    this.endHour = endHour;
    this.endMinute = endMinute;
  }
}