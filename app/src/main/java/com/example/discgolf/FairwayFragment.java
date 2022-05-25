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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.discgolf.ser.Course;
import com.example.discgolf.ser.Fairway;
import com.example.discgolf.ser.Player;
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
    TextView playerThrowOrder;

    TextView playerScore1;
    TextView playerScore2;
    TextView playerScore3;
    TextView playerScore4;
    TextView playerScore5;

    TextView playerTotal1;
    TextView playerTotal2;
    TextView playerTotal3;
    TextView playerTotal4;
    TextView playerTotal5;

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

    Button saveScorecard;
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Fairway> fairways;
    ArrayList<String> startingOrder;

    SharedPreferences playerData;

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

        playerTotal1 = view.findViewById(R.id.fairway_player_1_total);
        playerTotal2 = view.findViewById(R.id.fairway_player_2_total);
        playerTotal3 = view.findViewById(R.id.fairway_player_3_total);
        playerTotal4 = view.findViewById(R.id.fairway_player_4_total);
        playerTotal5 = view.findViewById(R.id.fairway_player_5_total);

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

        saveScorecard = view.findViewById(R.id.fairway_save_scorecard);
        playerThrowOrder = view.findViewById(R.id.player_throw_order);

        playerData = requireActivity().getSharedPreferences("player_data", Context.MODE_PRIVATE);
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

                updateScores(playerThrows1, playerScore1, playerTotal1);
                updateScores(playerThrows2, playerScore2, playerTotal2);
                updateScores(playerThrows3, playerScore3, playerTotal3);
                updateScores(playerThrows4, playerScore4, playerTotal4);
                updateScores(playerThrows5, playerScore5, playerTotal5);

                parSpinner.setSelection(fairways.get(tab.getPosition()).getPar());

                if (tab.getPosition() == 17) {
                    saveScorecard.setVisibility(View.VISIBLE);
                    playerThrowOrder.setVisibility(View.GONE);
                } else {
                    saveScorecard.setVisibility(View.GONE);
                    playerThrowOrder.setVisibility(View.VISIBLE);
                }
                startingOrder(tab.getPosition() - 1);
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
                playerThrows1.add(new Fairway(i + 1, fairways.get(i).getPar(), 0, 0));
            }
        } else {
            player1.setVisibility(View.GONE);
        }
        if ( !playerData.getString("player_2_name", "null").equals("") ) {
            playerName2.setText(playerData.getString("player_2_name", "null"));
            playerThrows2 = new ArrayList<>();
            for (int i=0; i<18; i++) {
                playerThrows2.add(new Fairway(i + 1, fairways.get(i).getPar(), 0, 0));
            }
        } else {
            player2.setVisibility(View.GONE);
        }
        if ( !playerData.getString("player_3_name", "null").equals("") ) {
            playerName3.setText(playerData.getString("player_3_name", "null"));
            playerThrows3 = new ArrayList<>();
            for (int i=0; i<18; i++) {
                playerThrows3.add(new Fairway(i + 1, fairways.get(i).getPar(), 0, 0));
            }
        } else {
            player3.setVisibility(View.GONE);
        }
        if ( !playerData.getString("player_4_name", "null").equals("") ) {
            playerName4.setText(playerData.getString("player_4_name", "null"));
            playerThrows4 = new ArrayList<>();
            for (int i=0; i<18; i++) {
                playerThrows4.add(new Fairway(i + 1, fairways.get(i).getPar(), 0, 0));
            }
        } else {
            player4.setVisibility(View.GONE);
        }
        if ( !playerData.getString("player_5_name", "null").equals("") ) {
            playerName5.setText(playerData.getString("player_5_name", "null"));
            playerThrows5 = new ArrayList<>();
            for (int i=0; i<18; i++) {
                playerThrows5.add(new Fairway(i + 1, fairways.get(i).getPar(), 0, 0));
            }
        } else {
            player5.setVisibility(View.GONE);
        }

        playerScoreAdd1.setOnClickListener(view1 -> {
            addScore(playerThrows1);
            updateScores(playerThrows1, playerScore1, playerTotal1);
        });
        playerScoreAdd2.setOnClickListener(view2 -> {
            addScore(playerThrows2);
            updateScores(playerThrows2, playerScore2, playerTotal2);
        });
        playerScoreAdd3.setOnClickListener(view3 -> {
            addScore(playerThrows3);
            updateScores(playerThrows3, playerScore3, playerTotal3);
        });
        playerScoreAdd4.setOnClickListener(view4 -> {
            addScore(playerThrows4);
            updateScores(playerThrows4, playerScore4, playerTotal4);
        });
        playerScoreAdd5.setOnClickListener(view5 -> {
            addScore(playerThrows5);
            updateScores(playerThrows5, playerScore5, playerTotal5);
        });

        playerScoreRemove1.setOnClickListener(view1 -> {
            removeScore(playerThrows1);
            updateScores(playerThrows1, playerScore1, playerTotal1);
        });
        playerScoreRemove2.setOnClickListener(view1 -> {
            removeScore(playerThrows2);
            updateScores(playerThrows2, playerScore2, playerTotal2);
        });
        playerScoreRemove3.setOnClickListener(view1 -> {
            removeScore(playerThrows3);
            updateScores(playerThrows3, playerScore3, playerTotal3);
        });
        playerScoreRemove4.setOnClickListener(view1 -> {
            removeScore(playerThrows4);
            updateScores(playerThrows4, playerScore4, playerTotal4);
        });
        playerScoreRemove5.setOnClickListener(view1 -> {
            removeScore(playerThrows5);
            updateScores(playerThrows5, playerScore5, playerTotal5);
        });

        startingOrder(4);

        parSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fairways.get(tabLayout.getSelectedTabPosition()).setPar(i);
                ((MainActivity)requireActivity()).setTitle(courses.get(finalSelectedCourse).getName()
                        + ", Hole " + (tabLayout.getSelectedTabPosition() + 1)
                        + " / Par " + (fairways.get(tabLayout.getSelectedTabPosition()).getPar() + 2));
                courses.get(finalSelectedCourse).getSavedPars().get(tabLayout.getSelectedTabPosition()).setPar(i);
                Serialize.instance.serializeData(requireContext().getApplicationContext(), "course_templates", courses);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        saveScorecard.setOnClickListener(view1 -> {
            if (playerThrows1 != null) {
                String p1Name = playerData.getString("player_1_name", "Player 1");
                players.add(new Player(p1Name, playerThrows1));
            }
            if (playerThrows2 != null) {
                String p2Name = playerData.getString("player_2_name", "Player 2");
                players.add(new Player(p2Name, playerThrows2));
            }
            if (playerThrows3 != null) {
                String p3Name = playerData.getString("player_3_name", "Player 3");
                players.add(new Player(p3Name, playerThrows3));
            }
            if (playerThrows4 != null) {
                String p4Name = playerData.getString("player_4_name", "Player 4");
                players.add(new Player(p4Name, playerThrows4));
            }
            if (playerThrows5 != null) {
                String p5Name = playerData.getString("player_5_name", "Player 5");
                players.add(new Player(p5Name, playerThrows5));
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void updateScores(ArrayList<Fairway> playerThrows, TextView playerScore, TextView playerTotal) {
        try {
            if (playerThrows.get(tabLayout.getSelectedTabPosition()).getThrowsTaken() != 0) {
                playerScore.setText(Integer.toString(playerThrows.get(tabLayout.getSelectedTabPosition()).getThrowsTaken()));
            } else {
                playerScore.setText("E");
            }
            int totalScore = 0;
            for ( int i=0; i<18; i++ ) {
                if ( playerThrows.get(i).getThrowsTaken() != 0) {
                    totalScore += playerThrows.get(i).getThrowsTaken() - (fairways.get(i).getPar() + 2);
                }
            }
            playerTotal.setText("( " + totalScore + " )");
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
            playerThrows.get(tabLayout.getSelectedTabPosition()).addThrows(fairways.get(tabLayout.getSelectedTabPosition()).getPar() + 1);
        } else {
            playerThrows.get(tabLayout.getSelectedTabPosition()).removeThrows(1);
        }
    }

    @SuppressLint("SetTextI18n")
    private void startingOrder(int hole) {

        int amountOfPlayers = playerData.getInt("amount_of_players", 0);
        String p1Name = playerData.getString("player_1_name", "Player 1");
        String p2Name = playerData.getString("player_2_name", "Player 2");
        String p3Name = playerData.getString("player_3_name", "Player 3");
        String p4Name = playerData.getString("player_4_name", "Player 4");
        String p5Name = playerData.getString("player_5_name", "Player 5");

        ArrayList<Player> playerThrows = new ArrayList<>();
        try {
            if ( playerThrows1 != null ) {
                playerThrows.add(new Player(p1Name, playerThrows1));
            }
            if ( playerThrows2 != null ) {
                playerThrows.add(new Player(p2Name, playerThrows2));
            }
            if ( playerThrows3 != null ) {
                playerThrows.add(new Player(p3Name, playerThrows3));
            }
            if ( playerThrows4 != null ) {
                playerThrows.add(new Player(p4Name, playerThrows4));
            }
            if ( playerThrows5 != null ) {
                playerThrows.add(new Player(p5Name, playerThrows5));
            }
        } catch (NullPointerException ignored) {}

        int playerIndexZeroThrows;
        int lowestThrow;
        int indexLowestOfThrow;

        startingOrder = new ArrayList<>();
        for (int i=0; i<playerThrows.size(); i++) {
            playerIndexZeroThrows = playerThrows.get(0).getFairways().get(hole).getThrowsTaken();
            lowestThrow = playerIndexZeroThrows;
            indexLowestOfThrow = i;
            for (int n=1; n<playerThrows.size(); n++) {
                if (playerThrows.get(n).getFairways().get(hole).getThrowsTaken() < lowestThrow) {
                    lowestThrow = playerThrows.get(n).getFairways().get(hole).getThrowsTaken();
                    indexLowestOfThrow = n;
                }
                else if (playerThrows.get(n).getFairways().get(hole).getThrowsTaken() == lowestThrow) {
                    for ( int t=1; t<hole; t++ ) {
                        if ( playerThrows.get(n).getFairways().get(hole - t).getThrowsTaken() < playerThrows.get(indexLowestOfThrow).getFairways().get(hole - t).getThrowsTaken()) {
                            lowestThrow = playerThrows.get(n).getFairways().get(hole).getThrowsTaken();
                            indexLowestOfThrow = n;
                        } else if ( playerThrows.get(n).getFairways().get(hole - t).getThrowsTaken() > playerThrows.get(indexLowestOfThrow).getFairways().get(hole - t).getThrowsTaken() ) {
                            lowestThrow = playerThrows.get(indexLowestOfThrow).getFairways().get(hole - t).getThrowsTaken();
                        }
                    }
                }
            }
            startingOrder.add(playerThrows.get(indexLowestOfThrow).getName());
            playerThrows.remove(indexLowestOfThrow);
        }
        startingOrder.add(playerThrows.get(0).getName());
        StringBuilder throwOrderText = new StringBuilder();
        for ( String s : startingOrder ) {
            throwOrderText.append(s + " ");
        }
        playerThrowOrder.setText(throwOrderText);

    }
}