package com.riswu.timetable;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import java.util.Collections;
import java.util.Map;

public class MainViewModel extends AndroidViewModel {
  public MutableLiveData<Boolean> has6ThClass = new MutableLiveData<>();
  public MutableLiveData<Map<String, Time>> times = new MutableLiveData<>();
  public MutableLiveData<Map<String, Subject>> subjects = new MutableLiveData<>();

  public MainViewModel(Application application) {
    super(application);
    this.has6ThClass.setValue(false);
    this.times.setValue(Collections.<String, Time>emptyMap());
    this.subjects.setValue(Collections.<String, Subject>emptyMap());
  }
}
