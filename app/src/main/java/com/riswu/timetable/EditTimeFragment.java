package com.riswu.timetable;

import android.app.TimePickerDialog;
import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;
import com.riswu.timetable.databinding.FragmentEditTimeBinding;

public class EditTimeFragment extends Fragment {
  private String id;
  private EditTimeViewModel viewModel;

  public static EditTimeFragment getInstance(String id) {
    EditTimeFragment fragment = new EditTimeFragment();
    Bundle args = new Bundle();
    args.putString("id", id);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    this.setHasOptionsMenu(true);
    ActionBar actionBar = ((AppCompatActivity) this.getActivity()).getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);

    Bundle args = this.getArguments();
    this.id = args.getString("id");
    this.viewModel = ViewModelProviders.of(this, new ViewModelProvider.NewInstanceFactory() {
      @Override
      public <T extends ViewModel> T create(Class<T> modelClass) {
        EditTimeViewModel viewModel = new EditTimeViewModel(EditTimeFragment.this.getActivity().getApplication());
        Repository repository = Repository.getInstance(EditTimeFragment.this.getActivity().getApplication());
        Time time = repository.getTimes().get(EditTimeFragment.this.id);
        viewModel.startHour.setValue(time.startHour);
        viewModel.startMinute.setValue(time.startMinute);
        viewModel.endHour.setValue(time.endHour);
        viewModel.endMinute.setValue(time.endMinute);
        return (T) viewModel;
      }
    }).get(EditTimeViewModel.class);

    FragmentEditTimeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_time, container, false);
    binding.setLifecycleOwner(this);
    binding.setViewModel(this.viewModel);
    binding.setFragment(this);
    return binding.getRoot();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        FragmentManager fragmentManager = this.getFragmentManager();
        fragmentManager.popBackStack();
        return true;
    }
    return false;
  }

  public void pickStartTime() {
    TimePickerDialog dialog = new TimePickerDialog(this.getActivity(), new TimePickerDialog.OnTimeSetListener() {
      @Override
      public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        EditTimeFragment.this.viewModel.startHour.setValue(hourOfDay);
        EditTimeFragment.this.viewModel.startMinute.setValue(minute);
      }
    }, this.viewModel.startHour.getValue(), this.viewModel.startMinute.getValue(), true);
    dialog.show();
  }

  public void pickEndTime() {
    TimePickerDialog dialog = new TimePickerDialog(this.getActivity(), new TimePickerDialog.OnTimeSetListener() {
      @Override
      public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        EditTimeFragment.this.viewModel.endHour.setValue(hourOfDay);
        EditTimeFragment.this.viewModel.endMinute.setValue(minute);
      }
    }, this.viewModel.endHour.getValue(), this.viewModel.endMinute.getValue(), true);
    dialog.show();
  }

  public void save() {
    int startHour = this.viewModel.startHour.getValue();
    int startMinute = this.viewModel.startMinute.getValue();
    int endHour = this.viewModel.endHour.getValue();
    int endMinute = this.viewModel.endMinute.getValue();
    Time time = new Time(startHour, startMinute, endHour, endMinute);
    Repository repository = Repository.getInstance(this.getActivity().getApplication());
    repository.updateTime(this.id, time);

    Intent intent = new Intent(this.getActivity().getApplication(), Widget.class);
    intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this.getActivity().getApplication());
    int[] ids = appWidgetManager.getAppWidgetIds(new ComponentName(this.getActivity().getApplication(), Widget.class));
    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
    this.getActivity().getApplication().sendBroadcast(intent);

    FragmentManager fragmentManager = this.getFragmentManager();
    fragmentManager.popBackStack();
  }
}