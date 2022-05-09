package com.example.discgolf.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.discgolf.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView totalRounds = binding.totalRoundsText;
        final TextView totalThrows = binding.totalThrowsText;
        final TextView bestScore   = binding.bestScoreText;
        final Button newRoundBtn   = binding.startNewRoundBtn;
        homeViewModel.getTotalRounds().observe(getViewLifecycleOwner(), totalRounds::setText);
        homeViewModel.getTotalThrows().observe(getViewLifecycleOwner(), totalThrows::setText);
        homeViewModel.getBestScore().observe(getViewLifecycleOwner(), bestScore::setText);
        homeViewModel.getNewRoundBtnText().observe(getViewLifecycleOwner(), newRoundBtn::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}