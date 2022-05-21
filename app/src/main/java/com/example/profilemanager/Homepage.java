package com.example.profilemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.profilemanager.databinding.ActivityMainBinding;

public class Homepage extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.coordinatorLayout.setOnClickListener(item -> {
            switch (item.getId()) {
                case R.id.codeforces:
                    replaceFragment(new CodeforcesFragment());
                    break;
                case R.id.leetcode:
                    replaceFragment(new LeetcodeFragment());
                    break;
                case R.id.codechef:
                    replaceFragment(new CodechefFragment());
                    break;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}