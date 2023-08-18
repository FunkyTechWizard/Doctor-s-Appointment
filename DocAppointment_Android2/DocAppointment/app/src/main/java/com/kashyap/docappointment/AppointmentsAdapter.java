package com.kashyap.docappointment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kashyap.docappointment.model.Appointment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.AppointmentViewHolder> {

    private List<Appointment> appointmentsList;

    public AppointmentsAdapter(List<Appointment> appointmentsList) {
        this.appointmentsList = appointmentsList;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointment, parent, false);
        return new AppointmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        Appointment appointment = appointmentsList.get(position);
        holder.tvName.setText("Name : "+appointment.getName());
        holder.tvEmail.setText("Email : "+appointment.getEmail());
        holder.tvMedicalIssue.setText("Medical Issuse : "+appointment.getMedicalIssue());
        holder.tvAppointmentDate.setText("Appointment Date : "+formatDate(appointment.getAppointmentDate()));
    }

    @Override
    public int getItemCount() {
        return appointmentsList.size();
    }

    private String formatDate(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

    static class AppointmentViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvEmail, tvDob, tvMedicalIssue, tvAppointmentDate;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvMedicalIssue = itemView.findViewById(R.id.tvMedicalIssue);
            tvAppointmentDate = itemView.findViewById(R.id.tvAppointmentDate);
        }
    }
}
