package com.example.discgolf;

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

import com.example.discgolf.ser.Course;

import java.util.ArrayList;

public class NewRoundFragment extends Fragment {

    Spinner courseSelectSpinner;
    Button startRound;
    ConstraintLayout createNewCourse;
    ConstraintLayout addPlayerConstraint;
    ConstraintLayout addPlayerConstraint2;
    ConstraintLayout addPlayerConstraint3;
    ConstraintLayout addPlayerConstraint4;
    ConstraintLayout addPlayerConstraint5;
    ImageView addPlayer;
    ImageView addPlayer2;
    ImageView addPlayer3;
    ImageView addPlayer4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_round, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((MainActivity)requireActivity()).setTitle("Start a new round");

        courseSelectSpinner = view.findViewById(R.id.select_course_spinner);
        createNewCourse = view.findViewById(R.id.create_new_course_container);
        startRound = view.findViewById(R.id.go_to_fairway_1_btn);

        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course());
        courses.add(new Course("Siltamäki", 18, new ArrayList<com.example.discgolf.ser.Fairway>()));

        ArrayList<String> fillSpinner = new ArrayList<>();
        fillSpinner.add(courses.get(0).getName());
        fillSpinner.add(courses.get(1).getName());

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_spinner_dropdown_item, fillSpinner);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSelectSpinner.setAdapter(spinnerAdapter);

        courseSelectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    createNewCourse.setVisibility(View.GONE);
                } else {
                    createNewCourse.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        startRound.setOnClickListener(view1 -> ((MainActivity)requireActivity()).goToFragment(new FairwayFragment()));

        addPlayer  = view.findViewById(R.id.add_player);
        addPlayer2 = view.findViewById(R.id.add_player2);
        addPlayer3 = view.findViewById(R.id.add_player3);
        addPlayer4 = view.findViewById(R.id.add_player4);

        addPlayerConstraint  = view.findViewById(R.id.player_1_constraint);
        addPlayerConstraint2 = view.findViewById(R.id.player_2_constraint);
        addPlayerConstraint3 = view.findViewById(R.id.player_3_constraint);
        addPlayerConstraint4 = view.findViewById(R.id.player_4_constraint);
        addPlayerConstraint5 = view.findViewById(R.id.player_5_constraint);

        addPlayer.setOnClickListener(view1 -> {
            addPlayer.setVisibility(View.GONE);
            addPlayer2.setVisibility(View.VISIBLE);
            addPlayerConstraint2.setVisibility(View.VISIBLE);
        });
        addPlayer2.setOnClickListener(view1 -> {
            addPlayer2.setVisibility(View.GONE);
            addPlayer3.setVisibility(View.VISIBLE);
            addPlayerConstraint3.setVisibility(View.VISIBLE);
        });
        addPlayer3.setOnClickListener(view1 -> {
            addPlayer3.setVisibility(View.GONE);
            addPlayer4.setVisibility(View.VISIBLE);
            addPlayerConstraint4.setVisibility(View.VISIBLE);
        });
        addPlayer4.setOnClickListener(view1 -> {
            addPlayer4.setVisibility(View.GONE);
            addPlayerConstraint5.setVisibility(View.VISIBLE);
        });
    }
}