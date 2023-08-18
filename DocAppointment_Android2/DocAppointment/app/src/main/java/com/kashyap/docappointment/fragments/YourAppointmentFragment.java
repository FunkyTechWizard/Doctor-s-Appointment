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

import com.kashyap.docappointment.AppointmentsAdapter;
import com.kashyap.docappointment.DataBase.DatabaseHelper;
import com.kashyap.docappointment.R;
import com.kashyap.docappointment.model.Appointment;

import java.util.List;

public class YourAppointmentFragment extends Fragment {

    private RecyclerView recyclerView;
    private AppointmentsAdapter appointmentsAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_your_appointment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.appointmentsRecyclerView);
        databaseHelper = new DatabaseHelper(requireContext());

        List<Appointment> appointmentList = getAppointmentsFromDatabase();
        appointmentsAdapter = new AppointmentsAdapter(appointmentList);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(appointmentsAdapter);
    }

    private List<Appointment> getAppointmentsFromDatabase() {
        return databaseHelper.getAllAppointments();
    }
}
