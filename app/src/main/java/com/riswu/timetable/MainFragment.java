package com.riswu.timetable;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.*;
import com.riswu.timetable.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {
  public static MainFragment getInstance() {
    return new MainFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    this.setHasOptionsMenu(true);

    MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    Repository repository = Repository.getInstance(this.getActivity().getApplication());
    viewModel.has6ThClass.setValue(repository.getPreferences().has6ThClass);
    viewModel.times.setValue(repository.getTimes());
    viewModel.subjects.setValue(repository.getSubjects());

    FragmentMainBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
    binding.setLifecycleOwner(this);
    binding.setViewModel(viewModel);
    binding.setFragment(this);
    return binding.getRoot();
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
    super.onCreateOptionsMenu(menu, menuInflater);
    menuInflater.inflate(R.menu.menu_main, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.preferences:
        FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(
          android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out);
        fragmentTransaction.addToBackStack(null);
        EditPreferencesFragment fragment = EditPreferencesFragment.getInstance();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
        return true;
    }
    return false;
  }

  public void editTime(String id) {
    FragmentManager fragmentManager = this.getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.setCustomAnimations(
      android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out);
    fragmentTransaction.addToBackStack(null);
    EditTimeFragment fragment = EditTimeFragment.getInstance(id);
    fragmentTransaction.replace(R.id.container, fragment);
    fragmentTransaction.commit();
  }

  public void editSubject(String id) {
    FragmentManager fragmentManager = this.getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.setCustomAnimations(
      android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out);
    fragmentTransaction.addToBackStack(null);
    EditSubjectFragment fragment = EditSubjectFragment.getInstance(id);
    fragmentTransaction.replace(R.id.container, fragment);
    fragmentTransaction.commit();
  }
}
