package com.riswu.timetable;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

public class EditSubjectViewModel extends AndroidViewModel {
  public MutableLiveData<String> name = new MutableLiveData<>();
  public MutableLiveData<String> room = new MutableLiveData<>();

  public EditSubjectViewModel(Application application) {
    super(application);
    this.name.setValue("");
    this.room.setValue("");
  }
}
