package com.riswu.timetable;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Repository {
  private static Map<Context, Repository> pool = new HashMap<>();
  private SharedPreferences preferences;
  private Map<String, Time> times;
  private Map<String, Subject> subjects;

  private static class UserData {
    public Map<String, Time> times;
    public Map<String, Subject> subjects;
  }

  private static Map<String, Time> createDefaultTimes() {
    Map<String, Time> times = new HashMap<>();
    for (String id : new String[] { "1", "2", "3", "4", "5", "6" }) {
      times.put(id, new Time(0, 0, 0, 0));
    }
    return times;
  }

  private static Map<String, Subject> createDefaultSubjects() {
    Map<String, Subject> subjects = new HashMap<>();
    for (String day : new String[] { "mon", "tue", "wed", "thu", "fri" }) {
      for (String ord : new String[] { "1", "2", "3", "4", "5", "6" }) {
        subjects.put(day + "-" + ord, new Subject("", ""));
      }
    }
    return subjects;
  }

  public static synchronized Repository getInstance(Context context) {
    Context applicationContext = context.getApplicationContext();
    if (Repository.pool.containsKey(applicationContext)) {
      return Repository.pool.get(applicationContext);
    } else {
      Repository repository = new Repository(applicationContext);
      Repository.pool.put(applicationContext, repository);
      return repository;
    }
  }

  private Repository(Context context) {
    this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
    // this.preferences.edit().putString("user_data", "").apply();
    String jsonRow = this.preferences.getString("user_data", "");
    if (!TextUtils.isEmpty(jsonRow)) {
      Gson gson = new Gson();
      UserData userData = gson.fromJson(jsonRow, Repository.UserData.class);
      this.times = userData.times;
      this.subjects = userData.subjects;
    } else {
      this.times = Repository.createDefaultTimes();
      this.subjects = Repository.createDefaultSubjects();
    }
  }

  public synchronized Map<String, Time> getTimes() {
    return new HashMap<>(this.times);
  }

  public synchronized Map<String, Subject> getSubjects() {
    return new HashMap<>(this.subjects);
  }

  public synchronized void updateTime(String id, Time time) {
    this.times.put(id, time);
    this.save();
  }

  public synchronized void updateSubject(String id, Subject subject) {
    this.subjects.put(id, subject);
    this.save();
  }

  private void save() {
    UserData userData = new UserData();
    userData.times = this.times;
    userData.subjects = this.subjects;
    Gson gson = new Gson();
    this.preferences.edit().putString("user_data", gson.toJson(userData)).apply();
  }
}
