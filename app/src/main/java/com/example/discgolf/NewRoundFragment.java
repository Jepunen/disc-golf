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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.discgolf.ser.Course;
import com.example.discgolf.ser.Fairway;
import com.example.discgolf.ser.Serialize;

import java.io.File;
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

    EditText newCourseName;
    EditText playerName_1;
    EditText playerName_2;
    EditText playerName_3;
    EditText playerName_4;
    EditText playerName_5;

    int amountOfPlayers = 1;
    ArrayList<Course> courses;

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

        newCourseName = view.findViewById(R.id.new_course_name);
        playerName_1 = view.findViewById(R.id.player_1_name);
        playerName_2 = view.findViewById(R.id.player_2_name);
        playerName_3 = view.findViewById(R.id.player_3_name);
        playerName_4 = view.findViewById(R.id.player_4_name);
        playerName_5 = view.findViewById(R.id.player_5_name);


        @SuppressLint("SdCardPath") File f = new File("/data/data/com.example.discgolf/files/course_templates");
        if(!f.exists() && !f.isDirectory()) {
            ArrayList<Course> templates = new ArrayList<>();
            Serialize.instance.serializeData(requireActivity().getApplicationContext(),"course_templates", templates);
        }

        courses = Serialize.instance.deSerializeData(requireActivity().getApplicationContext(),"course_templates");
        if ( courses.size() == 0 ) {
            courses.add(new Course());
        }

        ArrayList<String> fillSpinner = new ArrayList<>();
        for (Course temp : courses) {
            fillSpinner.add(temp.getName());
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
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

        startRound.setOnClickListener(view1 -> {

            int selectedCourse = courseSelectSpinner.getSelectedItemPosition();
            if (selectedCourse == 0) {
                ArrayList<Fairway> fairways = new ArrayList<>();
                for ( int i=0; i<18; i++ ) {
                    fairways.add(new Fairway(1));
                }
                courses.add(new Course(newCourseName.getText().toString(), 18, fairways));
                Serialize.instance.serializeData(requireActivity().getApplicationContext(),"course_templates", courses);
            }

            SharedPreferences playerDetails = requireActivity().getSharedPreferences("player_data", Context.MODE_PRIVATE);
            playerDetails.edit().putInt("amount_of_players", amountOfPlayers).apply();
            playerDetails.edit().putInt("selected_course", selectedCourse).apply();
            playerDetails.edit().putString("player_1_name", playerName_1.getText().toString()).apply();
            playerDetails.edit().putString("player_2_name", playerName_2.getText().toString()).apply();
            playerDetails.edit().putString("player_3_name", playerName_3.getText().toString()).apply();
            playerDetails.edit().putString("player_4_name", playerName_4.getText().toString()).apply();
            playerDetails.edit().putString("player_5_name", playerName_5.getText().toString()).apply();

            ((MainActivity)requireActivity()).goToFragment(new FairwayFragment());
        });

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
            amountOfPlayers = 2;
        });
        addPlayer2.setOnClickListener(view1 -> {
            addPlayer2.setVisibility(View.GONE);
            addPlayer3.setVisibility(View.VISIBLE);
            addPlayerConstraint3.setVisibility(View.VISIBLE);
            amountOfPlayers = 3;
        });
        addPlayer3.setOnClickListener(view1 -> {
            addPlayer3.setVisibility(View.GONE);
            addPlayer4.setVisibility(View.VISIBLE);
            addPlayerConstraint4.setVisibility(View.VISIBLE);
            amountOfPlayers = 4;
        });
        addPlayer4.setOnClickListener(view1 -> {
            addPlayer4.setVisibility(View.GONE);
            addPlayerConstraint5.setVisibility(View.VISIBLE);
            amountOfPlayers = 5;
        });
    }
}