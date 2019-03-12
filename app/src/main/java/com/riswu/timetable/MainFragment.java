package com.riswu.timetable;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.riswu.timetable.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {
  public static MainFragment getInstance() {
    return new MainFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);

    MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    Repository repository = Repository.getInstance(this.getActivity().getApplication());
    viewModel.times.setValue(repository.getTimes());
    viewModel.subjects.setValue(repository.getSubjects());

    FragmentMainBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
    binding.setLifecycleOwner(this);
    binding.setViewModel(viewModel);
    binding.setFragment(this);
    // binding.getRoot().findViewById(R.id.row_6).setVisibility(View.GONE);
    return binding.getRoot();
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
