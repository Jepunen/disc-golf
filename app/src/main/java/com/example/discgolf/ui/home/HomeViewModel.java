package com.example.discgolf.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> totalRounds;
    private final MutableLiveData<String> totalThrows;
    private final MutableLiveData<String> bestScore;
    private final MutableLiveData<String> newRoundBtn;

    public HomeViewModel() {
        totalRounds = new MutableLiveData<>();
        totalThrows = new MutableLiveData<>();
        bestScore   = new MutableLiveData<>();
        newRoundBtn = new MutableLiveData<>();

        totalRounds.setValue("Total rounds\n7");
        totalThrows.setValue("Total throws\n420");
        bestScore.setValue("Best score\n-2");
        newRoundBtn.setValue("Start a new round");
    }

    public LiveData<String> getTotalRounds() {
        return totalRounds;
    }
    public LiveData<String> getTotalThrows() {
        return totalThrows;
    }
    public LiveData<String> getBestScore() {
        return bestScore;
    }
    public LiveData<String> getNewRoundBtnText() {
        return newRoundBtn;
    }
}