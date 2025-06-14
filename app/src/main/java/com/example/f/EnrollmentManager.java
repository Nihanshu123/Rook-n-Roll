package com.example.f;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentManager {
    private static EnrollmentManager instance;
    private List<String> enrolledCourses;

    private EnrollmentManager() {
        enrolledCourses = new ArrayList<>(); // Initialize the list
    }

    public static EnrollmentManager getInstance() {
        if (instance == null) {
            instance = new EnrollmentManager();
        }
        return instance;
    }

    public List<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void addEnrolledCourse(String course) {
        enrolledCourses.add(course); // Add course to the list
    }
}
