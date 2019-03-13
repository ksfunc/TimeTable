package com.riswu.timetable;

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
import com.riswu.timetable.databinding.FragmentEditPreferencesBinding;

public class EditPreferencesFragment extends Fragment {
  private EditPreferencesViewModel viewModel;

  public static EditPreferencesFragment getInstance() {
    return new EditPreferencesFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    this.setHasOptionsMenu(true);
    ActionBar actionBar = ((AppCompatActivity) this.getActivity()).getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);

    this.viewModel = ViewModelProviders.of(this, new ViewModelProvider.NewInstanceFactory() {
      @Override
      public <T extends ViewModel> T create(Class<T> modelClass) {
        EditPreferencesViewModel viewModel = new EditPreferencesViewModel(EditPreferencesFragment.this.getActivity().getApplication());
        Repository repository = Repository.getInstance(EditPreferencesFragment.this.getActivity().getApplication());
        Preferences preferences = repository.getPreferences();
        viewModel.has6ThClass.setValue(preferences.has6ThClass);
        return (T) viewModel;
      }
    }).get(EditPreferencesViewModel.class);

    FragmentEditPreferencesBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_preferences, container, false);
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

  public void save() {
    Preferences preferences = new Preferences(this.viewModel.has6ThClass.getValue());
    Repository repository = Repository.getInstance(this.getActivity().getApplication());
    repository.updatePreferences(preferences);

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