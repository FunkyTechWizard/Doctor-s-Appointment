package com.kashyap.docappointment.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.kashyap.docappointment.DoctorListAdapter;
import com.kashyap.docappointment.R;
import com.kashyap.docappointment.model.Doctor;

public class DoctorAppointmentFragment extends Fragment {

    private RecyclerView recyclerView;
    private DoctorListAdapter doctorListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize the RecyclerView and its adapter
        recyclerView = view.findViewById(R.id.recyclerView);
        List<Doctor> doctorList = getDummyDoctorList(); // You can replace this with your actual doctor list
        doctorListAdapter = new DoctorListAdapter(doctorList);

        // Set the RecyclerView's layout manager and adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(doctorListAdapter);
    }

    // Replace this method with your actual implementation to get the list of doctors
    private List<Doctor> getDummyDoctorList() {
        List<Doctor> doctors = new ArrayList<>();
        List<Doctor> dummyList = new ArrayList<>();
        dummyList.add(new Doctor("Dr. Disha Patel", "A gynecologist is a medical doctor specialized in women's reproductive health and providing care for the female reproductive system", R.drawable.disha_patel));
        dummyList.add(new Doctor("Dr. Raj Patel", "Dr. Raj Patel is a skilled orthopedic doctor dedicated to diagnosing and providing expert care for musculoskeletal conditions and injuries", R.drawable.raj_patel));
        dummyList.add(new Doctor("Dr. Kashyap Surti", "Dr. Kashyap Surti is a highly skilled neurosurgeon specializing in surgical interventions and treatments for neurological conditions to improve patients' brain and nervous system health", R.drawable.kashyap_surti));

        // Add more doctors here if needed
        return dummyList;
    }
}
