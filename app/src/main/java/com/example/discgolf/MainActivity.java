package com.example.discgolf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load home fragment
        getSupportFragmentManager().beginTransaction().add(R.id.container, new HomeFragment()).commit();

        setTitle("Home");
    }

    @Override
    public void onBackPressed() {
        goToFragment(new HomeFragment());
    }

    public void setTitle(String title) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }

    public void goToFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}