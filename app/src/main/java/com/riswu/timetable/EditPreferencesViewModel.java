package com.riswu.timetable;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

public class EditPreferencesViewModel extends AndroidViewModel {
  public MutableLiveData<Boolean> has6ThClass = new MutableLiveData<>();

  public EditPreferencesViewModel(Application application) {
    super(application);
    this.has6ThClass.setValue(false);
  }
}
