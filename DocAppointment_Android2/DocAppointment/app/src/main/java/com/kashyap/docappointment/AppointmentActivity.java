package com.kashyap.docappointment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kashyap.docappointment.DataBase.DatabaseHelper;

import java.util.Calendar;

public class AppointmentActivity extends AppCompatActivity {

    private EditText etName, etEmail, etMedicalIssue;
    private DatePicker appointment_date;
    private Button btnSubmit;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        appointment_date = findViewById(R.id.appointment_date);
        etMedicalIssue = findViewById(R.id.etMedicalIssue);

        btnSubmit = findViewById(R.id.btnSubmit);
        databaseHelper = new DatabaseHelper(this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String medicalIssue = etMedicalIssue.getText().toString().trim();

                // Get the selected appointment date from the DatePicker
                int day = appointment_date.getDayOfMonth();
                int month = appointment_date.getMonth();
                int year = appointment_date.getYear();

                // Create a Calendar object and set the selected date
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);

                // Get the time in milliseconds (epoch time) for the selected date
                long appointmentDate = calendar.getTimeInMillis();

                // Insert appointment details into the database
                long rowId = databaseHelper.insertAppointment(name, email,appointmentDate, medicalIssue);
                if (rowId != -1) {
                    Toast.makeText(AppointmentActivity.this, "Appointment details saved successfully.", Toast.LENGTH_SHORT).show();
                    finish();
                    // You can also perform other actions after successful insertion
                } else {
                    Toast.makeText(AppointmentActivity.this, "Failed to save appointment details.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
