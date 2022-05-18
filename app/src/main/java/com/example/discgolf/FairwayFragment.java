package com.example.discgolf;

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

    ConstraintLayout player1;
    ConstraintLayout player2;
    ConstraintLayout player3;
    ConstraintLayout player4;
    ConstraintLayout player5;

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

        player1 = view.findViewById(R.id.fairway_p_1);
        player2 = view.findViewById(R.id.fairway_p_2);
        player3 = view.findViewById(R.id.fairway_p_3);
        player4 = view.findViewById(R.id.fairway_p_4);
        player5 = view.findViewById(R.id.fairway_p_5);

        SharedPreferences playerData = requireActivity().getSharedPreferences("player_data", Context.MODE_PRIVATE);

        if ( !playerData.getString("player_1_name", "null").equals("") ) {
            playerName1.setText(playerData.getString("player_1_name", "null"));
        } else {
            player1.setVisibility(View.GONE);
        }
        if ( !playerData.getString("player_2_name", "null").equals("") ) {
            playerName2.setText(playerData.getString("player_2_name", "null"));
        } else {
            player2.setVisibility(View.GONE);
        }
        if ( !playerData.getString("player_3_name", "null").equals("") ) {
            playerName3.setText(playerData.getString("player_3_name", "null"));
        } else {
            player3.setVisibility(View.GONE);
        }
        if ( !playerData.getString("player_4_name", "null").equals("") ) {
            playerName4.setText(playerData.getString("player_4_name", "null"));
        } else {
            player4.setVisibility(View.GONE);
        }
        if ( !playerData.getString("player_5_name", "null").equals("") ) {
            playerName5.setText(playerData.getString("player_5_name", "null"));
        } else {
            player5.setVisibility(View.GONE);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, getResources().getStringArray(R.array.pars));
        adapter.setDropDownViewResource(R.layout.spinner_item);
        parSpinner.setAdapter(adapter);

        ArrayList<Course> courses = Serialize.instance.deSerializeData(requireContext().getApplicationContext(), "course_templates");
        int selectedCourse = playerData.getInt("selected_course", 0);
        if (selectedCourse == 0) {
            selectedCourse = courses.size() - 1;
        }
        ArrayList<Fairway> fairways = courses.get(selectedCourse).getSavedPars();

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
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}