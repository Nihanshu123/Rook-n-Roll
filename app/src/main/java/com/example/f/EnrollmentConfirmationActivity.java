package com.example.f;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EnrollmentConfirmationActivity extends AppCompatActivity {

    private EditText nameEditText, phoneEditText, detailsEditText;
    private String courseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment_confirmation);

        // Get data from intent
        courseName = getIntent().getStringExtra("name");

        // Set the header text
        TextView headerTextView = findViewById(R.id.confirmation_header);
        headerTextView.setText("Enter Your Details to Enroll in " + courseName);

        nameEditText = findViewById(R.id.name_edit_text);
        phoneEditText = findViewById(R.id.phone_edit_text);
        detailsEditText = findViewById(R.id.details_edit_text);
        Button confirmButton = findViewById(R.id.confirm_button);
        Button startCourseButton = findViewById(R.id.start_course_button);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                String phone = phoneEditText.getText().toString().trim();
                String details = detailsEditText.getText().toString().trim();

                // Validate inputs
                if (validateInputs(name, phone, details)) {
                    // Store enrollment data in Firebase
                    saveEnrollmentData(name, phone, courseName, details);
                }
            }
        });

        // Set OnClickListener for the "Start Course" button
        startCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to StartCourseActivity
                Intent intent = new Intent(EnrollmentConfirmationActivity.this, StartCourseActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateInputs(String name, String phone, String details) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(details)) {
            Toast.makeText(EnrollmentConfirmationActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate phone number format
        if (!isValidPhoneNumber(phone)) {
            Toast.makeText(EnrollmentConfirmationActivity.this, "Invalid phone number format. Please enter 10 digits.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean isValidPhoneNumber(String phone) {
        // Basic validation: check length and digits only (10 digits)
        return phone.matches("\\d{10}");
    }

    private void saveEnrollmentData(String name, String phone, String course, String details) {
        // Initialize Firebase
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("enrollments");
        String enrollmentId = database.push().getKey();
        Enrollment enrollment = new Enrollment(name, phone, course, details);

        database.child(enrollmentId).setValue(enrollment).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                EnrollmentManager.getInstance().addEnrolledCourse(course); // Add to the list of enrolled courses
                Toast.makeText(EnrollmentConfirmationActivity.this, "Enrollment successful!", Toast.LENGTH_SHORT).show();

                // Return the enrolled course back to CoachListActivity
                Intent returnIntent = new Intent();
                returnIntent.putExtra("enrolled_course", course);
                setResult(RESULT_OK, returnIntent);
                finish(); // Close the confirmation activity
            } else {
                Toast.makeText(EnrollmentConfirmationActivity.this, "Enrollment failed. Try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
