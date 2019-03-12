package com.riswu.timetable;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

public class EditTimeViewModel extends AndroidViewModel {
  public MutableLiveData<Integer> startHour = new MutableLiveData<>();
  public MutableLiveData<Integer> startMinute = new MutableLiveData<>();
  public MutableLiveData<Integer> endHour = new MutableLiveData<>();
  public MutableLiveData<Integer> endMinute = new MutableLiveData<>();

  public EditTimeViewModel(Application application) {
    super(application);
    this.startHour.setValue(0);
    this.startMinute.setValue(0);
    this.endHour.setValue(0);
    this.endMinute.setValue(0);
  }
}
