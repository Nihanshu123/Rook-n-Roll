package com.example.f;

public class Enrollment {
    public String name;
    public String phone;
    public String course;
    public String details;

    public Enrollment() {
        // Default constructor required for calls to DataSnapshot.getValue(Enrollment.class)
    }

    public Enrollment(String name, String phone, String course, String details) {
        this.name = name;
        this.phone = phone;
        this.course = course;
        this.details = details;
    }
}