package com.example.discgolf;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.discgolf.ser.Course;
import com.example.discgolf.ser.Fairway;
import com.example.discgolf.ser.Serialize;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class FairwayFragment extends Fragment {

    TabLayout tabLayout;
    Spinner parSpinner;

    TextView playerName1;
    TextView playerName2;
    TextView playerName3;
    TextView playerName4;
    TextView playerName5;

    TextView playerScore1;
    TextView playerScore2;
    TextView playerScore3;
    TextView playerScore4;
    TextView playerScore5;

    ImageView playerScoreAdd1;
    ImageView playerScoreAdd2;
    ImageView playerScoreAdd3;
    ImageView playerScoreAdd4;
    ImageView playerScoreAdd5;
    ImageView playerScoreRemove1;
    ImageView playerScoreRemove2;
    ImageView playerScoreRemove3;
    ImageView playerScoreRemove4;
    ImageView playerScoreRemove5;

    ConstraintLayout player1;
    ConstraintLayout player2;
    ConstraintLayout player3;
    ConstraintLayout player4;
    ConstraintLayout player5;

    ArrayList<Fairway> playerThrows1;
    ArrayList<Fairway> playerThrows2;
    ArrayList<Fairway> playerThrows3;
    ArrayList<Fairway> playerThrows4;
    ArrayList<Fairway> playerThrows5;

    ArrayList<Fairway> fairways;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fairway, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        parSpinner  = view.findViewById(R.id.fairway_par_spinner);
        playerName1 = view.findViewById(R.id.fairway_p_1_name);
        playerName2 = view.findViewById(R.id.fairway_p_2_name);
        playerName3 = view.findViewById(R.id.fairway_p_3_name);
        playerName4 = view.findViewById(R.id.fairway_p_4_name);
        playerName5 = view.findViewById(R.id.fairway_p_5_name);

        playerScore1 = view.findViewById(R.id.fairway_p_1_score);
        playerScore2 = view.findViewById(R.id.fairway_p_2_score);
        playerScore3 = view.findViewById(R.id.fairway_p_3_score);
        playerScore4 = view.findViewById(R.id.fairway_p_4_score);
        playerScore5 = view.findViewById(R.id.fairway_p_5_score);

        playerScoreAdd1 = view.findViewById(R.id.fairway_p_1_score_add);
        playerScoreAdd2 = view.findViewById(R.id.fairway_p_2_score_add);
        playerScoreAdd3 = view.findViewById(R.id.fairway_p_3_score_add);
        playerScoreAdd4 = view.findViewById(R.id.fairway_p_4_score_add);
        playerScoreAdd5 = view.findViewById(R.id.fairway_p_5_score_add);

        playerScoreRemove1 = view.findViewById(R.id.fairway_p_1_score_remove);
        playerScoreRemove2 = view.findViewById(R.id.fairway_p_2_score_remove);
        playerScoreRemove3 = view.findViewById(R.id.fairway_p_3_score_remove);
        playerScoreRemove4 = view.findViewById(R.id.fairway_p_4_score_remove);
        playerScoreRemove5 = view.findViewById(R.id.fairway_p_5_score_remove);

        player1 = view.findViewById(R.id.fairway_p_1);
        player2 = view.findViewById(R.id.fairway_p_2);
        player3 = view.findViewById(R.id.fairway_p_3);
        player4 = view.findViewById(R.id.fairway_p_4);
        player5 = view.findViewById(R.id.fairway_p_5);


        SharedPreferences playerData = requireActivity().getSharedPreferences("player_data", Context.MODE_PRIVATE);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, getResources().getStringArray(R.array.pars));
        adapter.setDropDownViewResource(R.layout.spinner_item);
        parSpinner.setAdapter(adapter);

        ArrayList<Course> courses = Serialize.instance.deSerializeData(requireContext().getApplicationContext(), "course_templates");
        int selectedCourse = playerData.getInt("selected_course", 0);
        if (selectedCourse == 0) {
            selectedCourse = courses.size() - 1;
        }
        fairways = courses.get(selectedCourse).getSavedPars();

        parSpinner.setSelection(fairways.get(0).getPar());

        // Init first hole title
        ((MainActivity)requireActivity()).setTitle(courses.get(selectedCourse).getName()
                + ", Hole 1"
                + " / Par " + (fairways.get(0).getPar() + 2));


        tabLayout = view.findViewById(R.id.tab_layout);
        int finalSelectedCourse = selectedCourse;
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ((MainActivity)requireActivity()).setTitle(courses.get(finalSelectedCourse).getName()
                        + ", Hole " + (tab.getPosition() + 1)
                        + " / Par " + (fairways.get(tab.getPosition()).getPar() + 2));

                updateScores(playerThrows1, playerScore1);
                updateScores(playerThrows2, playerScore2);
                updateScores(playerThrows3, playerScore3);
                updateScores(playerThrows4, playerScore4);
                updateScores(playerThrows5, playerScore5);

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        if ( !playerData.getString("player_1_name", "null").equals("") ) {
            playerName1.setText(playerData.getString("player_1_name", "null"));
            playerThrows1 = new ArrayList<>();
            for (int i=0; i<18; i++) {
                playerThrows1.add(new Fairway(i, fairways.get(i).getPar(), 0, 0));
            }
        } else {
            player1.setVisibility(View.GONE);
        }
        if ( !playerData.getString("player_2_name", "null").equals("") ) {
            playerName2.setText(playerData.getString("player_2_name", "null"));
            playerThrows2 = new ArrayList<>();
            for (int i=0; i<18; i++) {
                playerThrows2.add(new Fairway(i, fairways.get(i).getPar(), 0, 0));
            }
        } else {
            player2.setVisibility(View.GONE);
        }
        if ( !playerData.getString("player_3_name", "null").equals("") ) {
            playerName3.setText(playerData.getString("player_3_name", "null"));
            playerThrows3 = new ArrayList<>();
            for (int i=0; i<18; i++) {
                playerThrows3.add(new Fairway(i, fairways.get(i).getPar(), 0, 0));
            }
        } else {
            player3.setVisibility(View.GONE);
        }
        if ( !playerData.getString("player_4_name", "null").equals("") ) {
            playerName4.setText(playerData.getString("player_4_name", "null"));
            playerThrows4 = new ArrayList<>();
            for (int i=0; i<18; i++) {
                playerThrows4.add(new Fairway(i, fairways.get(i).getPar(), 0, 0));
            }
        } else {
            player4.setVisibility(View.GONE);
        }
        if ( !playerData.getString("player_5_name", "null").equals("") ) {
            playerName5.setText(playerData.getString("player_5_name", "null"));
            playerThrows5 = new ArrayList<>();
            for (int i=0; i<18; i++) {
                playerThrows5.add(new Fairway(i, fairways.get(i).getPar(), 0, 0));
            }
        } else {
            player5.setVisibility(View.GONE);
        }

        playerScoreAdd1.setOnClickListener(view1 -> {
            addScore(playerThrows1);
            updateScores(playerThrows1, playerScore1);
        });
        playerScoreAdd2.setOnClickListener(view2 -> {
            addScore(playerThrows2);
            updateScores(playerThrows2, playerScore2);
        });
        playerScoreAdd3.setOnClickListener(view3 -> {
            addScore(playerThrows3);
            updateScores(playerThrows3, playerScore3);
        });
        playerScoreAdd4.setOnClickListener(view4 -> {
            addScore(playerThrows4);
            updateScores(playerThrows4, playerScore4);
        });
        playerScoreAdd5.setOnClickListener(view5 -> {
            addScore(playerThrows5);
            updateScores(playerThrows5, playerScore5);
        });

        playerScoreRemove1.setOnClickListener(view1 -> {
            removeScore(playerThrows1);
            updateScores(playerThrows1, playerScore1);
        });
        playerScoreRemove2.setOnClickListener(view1 -> {
            removeScore(playerThrows2);
            updateScores(playerThrows2, playerScore2);
        });
        playerScoreRemove3.setOnClickListener(view1 -> {
            removeScore(playerThrows3);
            updateScores(playerThrows3, playerScore3);
        });
        playerScoreRemove4.setOnClickListener(view1 -> {
            removeScore(playerThrows4);
            updateScores(playerThrows4, playerScore4);
        });
        playerScoreRemove5.setOnClickListener(view1 -> {
            removeScore(playerThrows5);
            updateScores(playerThrows5, playerScore5);
        });

    }

    @SuppressLint("SetTextI18n")
    private void updateScores(ArrayList<Fairway> playerThrows, TextView playerScore) throws NullPointerException {
        try {
            if (playerThrows.get(tabLayout.getSelectedTabPosition()).getThrowsTaken() != 0) {
                playerScore.setText(Integer.toString(playerThrows1.get(tabLayout.getSelectedTabPosition()).getThrowsTaken()));
            } else {
                playerScore1.setText("E");
            }
        } catch (NullPointerException ignored) {}
    }
    private void addScore(ArrayList<Fairway> playerThrows) {
        if (playerThrows.get(tabLayout.getSelectedTabPosition()).getThrowsTaken() == 0) {
            playerThrows.get(tabLayout.getSelectedTabPosition()).addThrows(fairways.get(tabLayout.getSelectedTabPosition()).getPar() + 2);
        } else {
            playerThrows.get(tabLayout.getSelectedTabPosition()).addThrows(1);
        }
    }

    private void removeScore(ArrayList<Fairway> playerThrows) {
        if (playerThrows.get(tabLayout.getSelectedTabPosition()).getThrowsTaken() == 0) {
            playerThrows.get(tabLayout.getSelectedTabPosition()).removeThrows(fairways.get(tabLayout.getSelectedTabPosition()).getPar() + 1);
        } else {
            playerThrows.get(tabLayout.getSelectedTabPosition()).removeThrows(1);
        }
    }
}