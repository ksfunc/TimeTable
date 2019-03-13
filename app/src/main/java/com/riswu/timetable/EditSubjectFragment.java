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
import com.riswu.timetable.databinding.FragmentEditSubjectBinding;

public class EditSubjectFragment extends Fragment {
  private String id;
  private EditSubjectViewModel viewModel;

  public static EditSubjectFragment getInstance(String id) {
    EditSubjectFragment fragment = new EditSubjectFragment();
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
        EditSubjectViewModel viewModel = new EditSubjectViewModel(EditSubjectFragment.this.getActivity().getApplication());
        Repository repository = Repository.getInstance(EditSubjectFragment.this.getActivity().getApplication());
        Subject subject = repository.getSubjects().get(EditSubjectFragment.this.id);
        viewModel.name.setValue(subject.name);
        viewModel.room.setValue(subject.room);
        return (T) viewModel;
      }
    }).get(EditSubjectViewModel.class);

    FragmentEditSubjectBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_subject, container, false);
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
    Subject subject = new Subject(this.viewModel.name.getValue(), this.viewModel.room.getValue());
    Repository repository = Repository.getInstance(this.getActivity().getApplication());
    repository.updateSubject(this.id, subject);

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