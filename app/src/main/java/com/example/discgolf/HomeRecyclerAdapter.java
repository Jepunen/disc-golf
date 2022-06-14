package com.example.discgolf;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discgolf.ser.Course;
import com.example.discgolf.ser.Player;
import com.example.discgolf.ser.Serialize;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<Course> playedCourses;

    public HomeRecyclerAdapter(Context context) {
        this.context = context;
        @SuppressLint("SdCardPath") File f = new File("/data/data/com.example.discgolf/files/played_courses");
        if(!f.exists() && !f.isDirectory()) {
            playedCourses = new ArrayList<>();
            Serialize.instance.serializeData(context,"played_courses", playedCourses);
        }
        this.playedCourses = Serialize.instance.deSerializeData(context, "played_courses");
        Collections.reverse(playedCourses);
    }

    @NonNull
    @Override
    public HomeRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_card_view, parent, false);
        return new HomeRecyclerAdapter.MyViewHolder(view);
    }

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int pos) {

        Course course = playedCourses.get(pos);

        holder.cardTitle.setText(course.getName());
        holder.dateTextView.setText(course.getDate());
        StringBuilder players = new StringBuilder();
        for (Player player : course.getPlayers()) {
            players.append(player.getName()).append(" ");
        }
        holder.playerNames.setText(players);

        boolean expanded = course.isExpanded();
        holder.tableLayout.setVisibility(expanded ? View.VISIBLE : View.GONE);
        holder.itemView.setOnClickListener(view -> {
            boolean isExpanded = course.isExpanded();
            course.setExpanded(!isExpanded);
            notifyItemChanged(pos);
        });

        holder.itemView.setOnLongClickListener(view -> {
            new AlertDialog.Builder(context)
                    .setTitle("Deleting a scorecard " + course.getName())
                    .setMessage("Are you sure?")
                    .setPositiveButton("Confirm", (dialog, which) -> {
                        playedCourses.remove(pos);
                        Collections.reverse(playedCourses);
                        Serialize.instance.serializeData(context,"played_courses", playedCourses);
                        Collections.reverse(playedCourses);
                        notifyDataSetChanged();
                    })
            .setNegativeButton("Cancel", (dialog, which) -> {
                // do nothing
            }).show();
            return false;
        });

        holder.tableLayout.setColumnCollapsed(1, true);
        holder.tableLayout.setColumnCollapsed(2, true);
        holder.tableLayout.setColumnCollapsed(3, true);
        holder.tableLayout.setColumnCollapsed(4, true);
        holder.tableLayout.setColumnCollapsed(5, true);
        Player player;
        for ( int i=0; i < course.getPlayers().size(); i++ ) {
            switch (i) {
                case 0:
                    holder.tableLayout.setColumnCollapsed(1, false);
                    player = course.getPlayers().get(0);
                    holder.playerNameP1.setText(player.getName());
                    holder.totalScoreP1.setText(Integer.toString(player.getTotalScore()));
                    setScore(player, holder.hole1P1, 0);
                    setScore(player, holder.hole2P1, 1);
                    setScore(player, holder.hole3P1, 2);
                    setScore(player, holder.hole4P1, 3);
                    setScore(player, holder.hole5P1, 4);
                    setScore(player, holder.hole6P1, 5);
                    setScore(player, holder.hole7P1, 6);
                    setScore(player, holder.hole8P1, 7);
                    setScore(player, holder.hole9P1, 8);
                    setScore(player, holder.hole10P1, 9);
                    setScore(player, holder.hole11P1, 10);
                    setScore(player, holder.hole12P1, 11);
                    setScore(player, holder.hole13P1, 12);
                    setScore(player, holder.hole14P1, 13);
                    setScore(player, holder.hole15P1, 14);
                    setScore(player, holder.hole16P1, 15);
                    setScore(player, holder.hole17P1, 16);
                    setScore(player, holder.hole18P1, 17);

                    setColor(player, holder.hole1P1, 0);
                    setColor(player, holder.hole2P1, 1);
                    setColor(player, holder.hole3P1, 2);
                    setColor(player, holder.hole4P1, 3);
                    setColor(player, holder.hole5P1, 4);
                    setColor(player, holder.hole6P1, 5);
                    setColor(player, holder.hole7P1, 6);
                    setColor(player, holder.hole8P1, 7);
                    setColor(player, holder.hole9P1, 8);
                    setColor(player, holder.hole10P1, 9);
                    setColor(player, holder.hole11P1, 10);
                    setColor(player, holder.hole12P1, 11);
                    setColor(player, holder.hole13P1, 12);
                    setColor(player, holder.hole14P1, 13);
                    setColor(player, holder.hole15P1, 14);
                    setColor(player, holder.hole16P1, 15);
                    setColor(player, holder.hole17P1, 16);
                    setColor(player, holder.hole18P1, 17);
                    break;
                case 1:
                    holder.tableLayout.setColumnCollapsed(2, false);
                    player = course.getPlayers().get(1);
                    holder.playerNameP2.setText(player.getName());
                    holder.totalScoreP2.setText(Integer.toString(player.getTotalScore()));
                    setScore(player, holder.hole1P2, 0);
                    setScore(player, holder.hole2P2, 1);
                    setScore(player, holder.hole3P2, 2);
                    setScore(player, holder.hole4P2, 3);
                    setScore(player, holder.hole5P2, 4);
                    setScore(player, holder.hole6P2, 5);
                    setScore(player, holder.hole7P2, 6);
                    setScore(player, holder.hole8P2, 7);
                    setScore(player, holder.hole9P2, 8);
                    setScore(player, holder.hole10P2, 9);
                    setScore(player, holder.hole11P2, 10);
                    setScore(player, holder.hole12P2, 11);
                    setScore(player, holder.hole13P2, 12);
                    setScore(player, holder.hole14P2, 13);
                    setScore(player, holder.hole15P2, 14);
                    setScore(player, holder.hole16P2, 15);
                    setScore(player, holder.hole17P2, 16);
                    setScore(player, holder.hole18P2, 17);

                    setColor(player, holder.hole1P2, 0);
                    setColor(player, holder.hole2P2, 1);
                    setColor(player, holder.hole3P2, 2);
                    setColor(player, holder.hole4P2, 3);
                    setColor(player, holder.hole5P2, 4);
                    setColor(player, holder.hole6P2, 5);
                    setColor(player, holder.hole7P2, 6);
                    setColor(player, holder.hole8P2, 7);
                    setColor(player, holder.hole9P2, 8);
                    setColor(player, holder.hole10P2, 9);
                    setColor(player, holder.hole11P2, 10);
                    setColor(player, holder.hole12P2, 11);
                    setColor(player, holder.hole13P2, 12);
                    setColor(player, holder.hole14P2, 13);
                    setColor(player, holder.hole15P2, 14);
                    setColor(player, holder.hole16P2, 15);
                    setColor(player, holder.hole17P2, 16);
                    setColor(player, holder.hole18P2, 17);
                    break;
                case 2:
                    holder.tableLayout.setColumnCollapsed(3, false);
                    player = course.getPlayers().get(2);
                    holder.playerNameP3.setText(player.getName());
                    holder.totalScoreP3.setText(Integer.toString(player.getTotalScore()));
                    setScore(player, holder.hole1P3, 0);
                    setScore(player, holder.hole2P3, 1);
                    setScore(player, holder.hole3P3, 2);
                    setScore(player, holder.hole4P3, 3);
                    setScore(player, holder.hole5P3, 4);
                    setScore(player, holder.hole6P3, 5);
                    setScore(player, holder.hole7P3, 6);
                    setScore(player, holder.hole8P3, 7);
                    setScore(player, holder.hole9P3, 8);
                    setScore(player, holder.hole10P3, 9);
                    setScore(player, holder.hole11P3, 10);
                    setScore(player, holder.hole12P3, 11);
                    setScore(player, holder.hole13P3, 12);
                    setScore(player, holder.hole14P3, 13);
                    setScore(player, holder.hole15P3, 14);
                    setScore(player, holder.hole16P3, 15);
                    setScore(player, holder.hole17P3, 16);
                    setScore(player, holder.hole18P3, 17);

                    setColor(player, holder.hole1P3, 0);
                    setColor(player, holder.hole2P3, 1);
                    setColor(player, holder.hole3P3, 2);
                    setColor(player, holder.hole4P3, 3);
                    setColor(player, holder.hole5P3, 4);
                    setColor(player, holder.hole6P3, 5);
                    setColor(player, holder.hole7P3, 6);
                    setColor(player, holder.hole8P3, 7);
                    setColor(player, holder.hole9P3, 8);
                    setColor(player, holder.hole10P3, 9);
                    setColor(player, holder.hole11P3, 10);
                    setColor(player, holder.hole12P3, 11);
                    setColor(player, holder.hole13P3, 12);
                    setColor(player, holder.hole14P3, 13);
                    setColor(player, holder.hole15P3, 14);
                    setColor(player, holder.hole16P3, 15);
                    setColor(player, holder.hole17P3, 16);
                    setColor(player, holder.hole18P3, 17);
                    break;
                case 3:
                    holder.tableLayout.setColumnCollapsed(4, false);
                    player = course.getPlayers().get(3);
                    holder.playerNameP4.setText(player.getName());
                    holder.totalScoreP4.setText(Integer.toString(player.getTotalScore()));
                    setScore(player, holder.hole1P4, 0);
                    setScore(player, holder.hole2P4, 1);
                    setScore(player, holder.hole3P4, 2);
                    setScore(player, holder.hole4P4, 3);
                    setScore(player, holder.hole5P4, 4);
                    setScore(player, holder.hole6P4, 5);
                    setScore(player, holder.hole7P4, 6);
                    setScore(player, holder.hole8P4, 7);
                    setScore(player, holder.hole9P4, 8);
                    setScore(player, holder.hole10P4, 9);
                    setScore(player, holder.hole11P4, 10);
                    setScore(player, holder.hole12P4, 11);
                    setScore(player, holder.hole13P4, 12);
                    setScore(player, holder.hole14P4, 13);
                    setScore(player, holder.hole15P4, 14);
                    setScore(player, holder.hole16P4, 15);
                    setScore(player, holder.hole17P4, 16);
                    setScore(player, holder.hole18P4, 17);

                    setColor(player, holder.hole1P4, 0);
                    setColor(player, holder.hole2P4, 1);
                    setColor(player, holder.hole3P4, 2);
                    setColor(player, holder.hole4P4, 3);
                    setColor(player, holder.hole5P4, 4);
                    setColor(player, holder.hole6P4, 5);
                    setColor(player, holder.hole7P4, 6);
                    setColor(player, holder.hole8P4, 7);
                    setColor(player, holder.hole9P4, 8);
                    setColor(player, holder.hole10P4, 9);
                    setColor(player, holder.hole11P4, 10);
                    setColor(player, holder.hole12P4, 11);
                    setColor(player, holder.hole13P4, 12);
                    setColor(player, holder.hole14P4, 13);
                    setColor(player, holder.hole15P4, 14);
                    setColor(player, holder.hole16P4, 15);
                    setColor(player, holder.hole17P4, 16);
                    setColor(player, holder.hole18P4, 17);
                    break;
                case 4:
                    holder.tableLayout.setColumnCollapsed(5, false);
                    player = course.getPlayers().get(4);
                    holder.playerNameP5.setText(player.getName());
                    holder.totalScoreP5.setText(Integer.toString(player.getTotalScore()));
                    setScore(player, holder.hole1P5, 0);
                    setScore(player, holder.hole2P5, 1);
                    setScore(player, holder.hole3P5, 2);
                    setScore(player, holder.hole4P5, 3);
                    setScore(player, holder.hole5P5, 4);
                    setScore(player, holder.hole6P5, 5);
                    setScore(player, holder.hole7P5, 6);
                    setScore(player, holder.hole8P5, 7);
                    setScore(player, holder.hole9P5, 8);
                    setScore(player, holder.hole10P5, 9);
                    setScore(player, holder.hole11P5, 10);
                    setScore(player, holder.hole12P5, 11);
                    setScore(player, holder.hole13P5, 12);
                    setScore(player, holder.hole14P5, 13);
                    setScore(player, holder.hole15P5, 14);
                    setScore(player, holder.hole16P5, 15);
                    setScore(player, holder.hole17P5, 16);
                    setScore(player, holder.hole18P5, 17);

                    setColor(player, holder.hole1P5, 0);
                    setColor(player, holder.hole2P5, 1);
                    setColor(player, holder.hole3P5, 2);
                    setColor(player, holder.hole4P5, 3);
                    setColor(player, holder.hole5P5, 4);
                    setColor(player, holder.hole6P5, 5);
                    setColor(player, holder.hole7P5, 6);
                    setColor(player, holder.hole8P5, 7);
                    setColor(player, holder.hole9P5, 8);
                    setColor(player, holder.hole10P5, 9);
                    setColor(player, holder.hole11P5, 10);
                    setColor(player, holder.hole12P5, 11);
                    setColor(player, holder.hole13P5, 12);
                    setColor(player, holder.hole14P5, 13);
                    setColor(player, holder.hole15P5, 14);
                    setColor(player, holder.hole16P5, 15);
                    setColor(player, holder.hole17P5, 16);
                    setColor(player, holder.hole18P5, 17);
                    break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return playedCourses.size();
    }

    @SuppressLint("SetTextI18n")
    public void setScore(Player player, TextView textview, int hole) {
        if ( player.getFairways().get(hole).getThrowsTaken() != 0 ) {
            textview.setText(Integer.toString(player.getFairways().get(hole).getThrowsTaken()));
        } else {
            textview.setText("");
        }
    }
    public void setColor(Player player, TextView textView, int hole) {
        if ( player.getFairways().get(hole).getThrowsTaken() < (player.getFairways().get(hole).getPar() + 2) && player.getFairways().get(hole).getThrowsTaken() != 0) {
            textView.setBackgroundColor(context.getResources().getColor(R.color.green));
        } else if ( player.getFairways().get(hole).getThrowsTaken() > (player.getFairways().get(hole).getPar() + 2) ) {
            textView.setBackgroundColor(context.getResources().getColor(R.color.red));
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;
        CardView card;
        TextView dateTextView;
        TextView cardTitle, playerNames, playerNameP1, playerNameP2, playerNameP3, playerNameP4, playerNameP5;
        TextView totalScoreP1, totalScoreP2, totalScoreP3, totalScoreP4, totalScoreP5;
        TextView hole1P1, hole1P2, hole1P3, hole1P4, hole1P5;
        TextView hole2P1, hole2P2, hole2P3, hole2P4, hole2P5;
        TextView hole3P1, hole3P2, hole3P3, hole3P4, hole3P5;
        TextView hole4P1, hole4P2, hole4P3, hole4P4, hole4P5;
        TextView hole5P1, hole5P2, hole5P3, hole5P4, hole5P5;
        TextView hole6P1, hole6P2, hole6P3, hole6P4, hole6P5;
        TextView hole7P1, hole7P2, hole7P3, hole7P4, hole7P5;
        TextView hole8P1, hole8P2, hole8P3, hole8P4, hole8P5;
        TextView hole9P1, hole9P2, hole9P3, hole9P4, hole9P5;
        TextView hole10P1, hole10P2, hole10P3, hole10P4, hole10P5;
        TextView hole11P1, hole11P2, hole11P3, hole11P4, hole11P5;
        TextView hole12P1, hole12P2, hole12P3, hole12P4, hole12P5;
        TextView hole13P1, hole13P2, hole13P3, hole13P4, hole13P5;
        TextView hole14P1, hole14P2, hole14P3, hole14P4, hole14P5;
        TextView hole15P1, hole15P2, hole15P3, hole15P4, hole15P5;
        TextView hole16P1, hole16P2, hole16P3, hole16P4, hole16P5;
        TextView hole17P1, hole17P2, hole17P3, hole17P4, hole17P5;
        TextView hole18P1, hole18P2, hole18P3, hole18P4, hole18P5;
        TableLayout tableLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.card_card);
            cardTitle = itemView.findViewById(R.id.card_course_title);
            playerNames = itemView.findViewById(R.id.cardview_players);
            tableLayout = itemView.findViewById(R.id.table_layout);
            recyclerView = itemView.findViewById(R.id.recyclerView);
            dateTextView = itemView.findViewById(R.id.card_course_date);

            totalScoreP1 = itemView.findViewById(R.id.cardview_p1_total);
            totalScoreP2 = itemView.findViewById(R.id.cardview_p2_total);
            totalScoreP3 = itemView.findViewById(R.id.cardview_p3_total);
            totalScoreP4 = itemView.findViewById(R.id.cardview_p4_total);
            totalScoreP5 = itemView.findViewById(R.id.cardview_p5_total);

            playerNameP1 = itemView.findViewById(R.id.cardview_player_name_1);
            playerNameP2 = itemView.findViewById(R.id.cardview_player_name_2);
            playerNameP3 = itemView.findViewById(R.id.cardview_player_name_3);
            playerNameP4 = itemView.findViewById(R.id.cardview_player_name_4);
            playerNameP5 = itemView.findViewById(R.id.cardview_player_name_5);

            hole1P1 = itemView.findViewById(R.id.card_p1_1);
            hole1P2 = itemView.findViewById(R.id.card_p2_1);
            hole1P3 = itemView.findViewById(R.id.card_p3_1);
            hole1P4 = itemView.findViewById(R.id.card_p4_1);
            hole1P5 = itemView.findViewById(R.id.card_p5_1);

            hole2P1 = itemView.findViewById(R.id.card_p1_2);
            hole2P2 = itemView.findViewById(R.id.card_p2_2);
            hole2P3 = itemView.findViewById(R.id.card_p3_2);
            hole2P4 = itemView.findViewById(R.id.card_p4_2);
            hole2P5 = itemView.findViewById(R.id.card_p5_2);

            hole3P1 = itemView.findViewById(R.id.card_p1_3);
            hole3P2 = itemView.findViewById(R.id.card_p2_5);
            hole3P3 = itemView.findViewById(R.id.card_p3_3);
            hole3P4 = itemView.findViewById(R.id.card_p4_3);
            hole3P5 = itemView.findViewById(R.id.card_p5_3);

            hole4P1 = itemView.findViewById(R.id.card_p1_4);
            hole4P2 = itemView.findViewById(R.id.card_p2_6);
            hole4P3 = itemView.findViewById(R.id.card_p3_4);
            hole4P4 = itemView.findViewById(R.id.card_p4_4);
            hole4P5 = itemView.findViewById(R.id.card_p5_4);

            hole5P1 = itemView.findViewById(R.id.card_p1_5);
            hole5P2 = itemView.findViewById(R.id.card_p2_7);
            hole5P3 = itemView.findViewById(R.id.card_p3_5);
            hole5P4 = itemView.findViewById(R.id.card_p4_5);
            hole5P5 = itemView.findViewById(R.id.card_p5_5);

            hole6P1 = itemView.findViewById(R.id.card_p1_6);
            hole6P2 = itemView.findViewById(R.id.card_p2_8);
            hole6P3 = itemView.findViewById(R.id.card_p3_6);
            hole6P4 = itemView.findViewById(R.id.card_p4_6);
            hole6P5 = itemView.findViewById(R.id.card_p5_6);

            hole7P1 = itemView.findViewById(R.id.card_p1_7);
            hole7P2 = itemView.findViewById(R.id.card_p2_9);
            hole7P3 = itemView.findViewById(R.id.card_p3_7);
            hole7P4 = itemView.findViewById(R.id.card_p4_7);
            hole7P5 = itemView.findViewById(R.id.card_p5_7);

            hole8P1 = itemView.findViewById(R.id.card_p1_8);
            hole8P2 = itemView.findViewById(R.id.card_p2_10);
            hole8P3 = itemView.findViewById(R.id.card_p3_8);
            hole8P4 = itemView.findViewById(R.id.card_p4_8);
            hole8P5 = itemView.findViewById(R.id.card_p5_8);

            hole9P1 = itemView.findViewById(R.id.card_p1_9);
            hole9P2 = itemView.findViewById(R.id.card_p2_11);
            hole9P3 = itemView.findViewById(R.id.card_p3_9);
            hole9P4 = itemView.findViewById(R.id.card_p4_9);
            hole9P5 = itemView.findViewById(R.id.card_p5_9);

            hole10P1 = itemView.findViewById(R.id.card_p1_10);
            hole10P2 = itemView.findViewById(R.id.card_p2_12);
            hole10P3 = itemView.findViewById(R.id.card_p3_10);
            hole10P4 = itemView.findViewById(R.id.card_p4_10);
            hole10P5 = itemView.findViewById(R.id.card_p5_10);

            hole11P1 = itemView.findViewById(R.id.card_p1_11);
            hole11P2 = itemView.findViewById(R.id.card_p2_13);
            hole11P3 = itemView.findViewById(R.id.card_p3_11);
            hole11P4 = itemView.findViewById(R.id.card_p4_11);
            hole11P5 = itemView.findViewById(R.id.card_p5_11);

            hole12P1 = itemView.findViewById(R.id.card_p1_12);
            hole12P2 = itemView.findViewById(R.id.card_p2_14);
            hole12P3 = itemView.findViewById(R.id.card_p3_12);
            hole12P4 = itemView.findViewById(R.id.card_p4_12);
            hole12P5 = itemView.findViewById(R.id.card_p5_12);

            hole13P1 = itemView.findViewById(R.id.card_p1_13);
            hole13P2 = itemView.findViewById(R.id.card_p2_15);
            hole13P3 = itemView.findViewById(R.id.card_p3_13);
            hole13P4 = itemView.findViewById(R.id.card_p4_13);
            hole13P5 = itemView.findViewById(R.id.card_p5_13);

            hole14P1 = itemView.findViewById(R.id.card_p1_14);
            hole14P2 = itemView.findViewById(R.id.card_p2_16);
            hole14P3 = itemView.findViewById(R.id.card_p3_14);
            hole14P4 = itemView.findViewById(R.id.card_p4_14);
            hole14P5 = itemView.findViewById(R.id.card_p5_14);

            hole15P1 = itemView.findViewById(R.id.card_p1_15);
            hole15P2 = itemView.findViewById(R.id.card_p2_17);
            hole15P3 = itemView.findViewById(R.id.card_p3_15);
            hole15P4 = itemView.findViewById(R.id.card_p4_15);
            hole15P5 = itemView.findViewById(R.id.card_p5_15);

            hole16P1 = itemView.findViewById(R.id.card_p1_16);
            hole16P2 = itemView.findViewById(R.id.card_p2_18);
            hole16P3 = itemView.findViewById(R.id.card_p3_16);
            hole16P4 = itemView.findViewById(R.id.card_p4_16);
            hole16P5 = itemView.findViewById(R.id.card_p5_16);

            hole17P1 = itemView.findViewById(R.id.card_p1_17);
            hole17P2 = itemView.findViewById(R.id.card_p2_19);
            hole17P3 = itemView.findViewById(R.id.card_p3_17);
            hole17P4 = itemView.findViewById(R.id.card_p4_17);
            hole17P5 = itemView.findViewById(R.id.card_p5_17);

            hole18P1 = itemView.findViewById(R.id.card_p1_18);
            hole18P2 = itemView.findViewById(R.id.card_p2_20);
            hole18P3 = itemView.findViewById(R.id.card_p3_18);
            hole18P4 = itemView.findViewById(R.id.card_p4_18);
            hole18P5 = itemView.findViewById(R.id.card_p5_18);


        }
    }
}
