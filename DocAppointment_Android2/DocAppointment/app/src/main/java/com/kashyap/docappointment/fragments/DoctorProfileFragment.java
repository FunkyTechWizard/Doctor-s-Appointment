package com.kashyap.docappointment.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kashyap.docappointment.R;

import java.util.ArrayList;
import java.util.Random;

public class DoctorProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";

    public DoctorProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doctor_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button nextButton = view.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchAndPopulateData();
            }
        });

        fetchAndPopulateData();
    }

    private void fetchAndPopulateData() {
        Random random = new Random();
        int index = random.nextInt(4);

        ArrayList<String> listofIDS = new ArrayList<>();
        listofIDS.add("VCMvMiJEnqotLa4w4O3a");
        listofIDS.add("VHI38g9kxvSXR2cskTC1");
        listofIDS.add("n4YFf94TbPP3u9rAqmfC");
        listofIDS.add("zkRE4xNq98cssiN9S3JL");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("profiles").document(listofIDS.get(index));

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        ImageView profileImage = requireView().findViewById(R.id.profileImage);

                        String docImg = document.getString("DocIMG");
                        String name = document.getString("Name");
                        String department = document.getString("Department");
                        Long experience = document.getLong("Experience");
                        String education = document.getString("Education");

                        Glide.with(requireContext())
                                .load(docImg)
                                .centerCrop()
                                .into(profileImage);

                        String experienceString = String.valueOf(experience);

                        TextView nameTextView = requireView().findViewById(R.id.nameTextView);
                        TextView departmentTextView = requireView().findViewById(R.id.departmentTextView);
                        TextView experienceTextView = requireView().findViewById(R.id.experienceTextView);
                        TextView educationTextView = requireView().findViewById(R.id.educationTextView);

                        nameTextView.setText("Name: " + name);
                        departmentTextView.setText("Department: " + department);
                        experienceTextView.setText("Experience: " + experienceString);
                        educationTextView.setText("Education: " + education);
                    } else {
                        // Handle case when document doesn't exist
                        // Log.d(TAG, "No such document");
                    }
                } else {
                    // Handle errors
                    // Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
    }
}
