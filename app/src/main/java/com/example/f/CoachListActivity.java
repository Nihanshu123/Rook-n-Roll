package com.example.f;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.f.EnrollmentConfirmationActivity;
import com.example.f.EnrollmentManager;

import java.util.List;

public class CoachListActivity extends AppCompatActivity {

    private String[] coachNames = {
            "Aarav Mehta",
            "Sneha Kapoor",
            "Vikram Singh",
            "Priya Sharma",
            "Rohan Desai",
            "Nisha Gupta",
            "Karan Joshi",
            "Meera Iyer",
            "Anil Reddy",
            "Pooja Menon"
    };

    private String[] coachDetails = {
            "Experience: 10 years\nSpecialty: Opening strategies",
            "Experience: 8 years\nSpecialty: Endgame techniques",
            "Experience: 12 years\nSpecialty: Middle game tactics",
            "Experience: 5 years\nSpecialty: Chess psychology",
            "Experience: 15 years\nSpecialty: Tournament preparation",
            "Experience: 7 years\nSpecialty: Defensive strategies",
            "Experience: 9 years\nSpecialty: Chess for kids",
            "Experience: 11 years\nSpecialty: Rapid and blitz formats",
            "Experience: 6 years\nSpecialty: Strategy development",
            "Experience: 4 years\nSpecialty: Online coaching"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_list);

        // Get the enrolled courses from EnrollmentManager
        List<String> enrolledCourses = EnrollmentManager.getInstance().getEnrolledCourses();

        // Top section to show enrolled courses
        TextView enrolledCourseTextView = findViewById(R.id.enrolled_course_text);
        if (enrolledCourses.isEmpty()) {
            enrolledCourseTextView.setText("Enrolled Course: None");
        } else {
            StringBuilder courses = new StringBuilder("Enrolled Courses: ");
            for (String course : enrolledCourses) {
                courses.append(course).append(", ");
            }
            // Remove the last comma and space
            courses.setLength(courses.length() - 2);
            enrolledCourseTextView.setText(courses.toString());
        }

        LinearLayout coachListLayout = findViewById(R.id.coach_list_layout);
        for (int i = 0; i < coachNames.length; i++) {
            createCoachButton(coachListLayout, coachNames[i], coachDetails[i]);
        }
    }

    private void createCoachButton(LinearLayout parent, String coachName, String coachDetail) {
        Button button = new Button(this);
        button.setText(coachName);
        button.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        button.setTextSize(18);
        button.setPadding(16, 16, 16, 16);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoachListActivity.this, EnrollmentConfirmationActivity.class);
                intent.putExtra("name", coachName);
                intent.putExtra("details", coachDetail);
                startActivityForResult(intent, 1); // Use startActivityForResult to get the result
            }
        });
        parent.addView(button);

        // Display the details below the button
        TextView detailsTextView = new TextView(this);
        detailsTextView.setText(coachDetail);
        detailsTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        detailsTextView.setPadding(16, 0, 16, 16);
        parent.addView(detailsTextView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Update the displayed enrolled courses after returning from EnrollmentConfirmationActivity
            List<String> enrolledCourses = EnrollmentManager.getInstance().getEnrolledCourses();
            StringBuilder courses = new StringBuilder("Enrolled Courses: ");
            for (String course : enrolledCourses) {
                courses.append(course).append(", ");
            }
            // Remove the last comma and space

            TextView enrolledCourseTextView = findViewById(R.id.enrolled_course_text);
            enrolledCourseTextView.setText(courses.toString());
        }
    }
}
