package com.example.profilemanager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.github.mikephil.charting.charts.LineChart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CodeforcesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CodeforcesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private LineChart chart;
    private SeekBar seekx, seeky;

    private FirebaseUser curUser;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private FirebaseAuth mAuth;


    public CodeforcesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CodeforcesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CodeforcesFragment newInstance(String param1, String param2) {
        CodeforcesFragment fragment = new CodeforcesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    final String[] cfUsername = new String[]{""};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        init_database();

        curUser = FirebaseAuth.getInstance().getCurrentUser();
            ref.child(curUser.getUid()).child("cfusername").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()) {
                        cfUsername[0] = String.valueOf(task.getResult().getValue());
                        Log.d("Username", cfUsername[0]);
                    }
                    else {
                        Log.e("Firebase", "Error getting data", task.getException());
                    }
                }
            });
    }

    public void init_database() {
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Users");
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_codeforces, container, false);
    }
}