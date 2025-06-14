package com.example.f;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class fp extends AppCompatActivity {

    private EditText nameEditText, emailEditText;
    private RadioGroup userTypeGroup;
    private Button enterButton, loginButton;

    // Firebase reference
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fp);

        // Initializing views
        nameEditText = findViewById(R.id.name);
        emailEditText = findViewById(R.id.email);
        userTypeGroup = findViewById(R.id.rg);
        enterButton = findViewById(R.id.enter);
        loginButton = findViewById(R.id.login);  // Login button

        // Firebase database instance and reference
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        // Button click listener for saving data
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToFirebase();
            }
        });

        // Button click listener for navigating to MainActivity2
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fp.this, fp2.class);
                startActivity(intent);
            }
        });
    }

    private void saveDataToFirebase() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        int selectedTypeId = userTypeGroup.getCheckedRadioButtonId();

        // Check for empty fields
        if (name.isEmpty() || email.isEmpty() || selectedTypeId == -1) {
            Toast.makeText(fp.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate email format
        if (!isValidEmail(email)) {
            Toast.makeText(fp.this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get user type
        RadioButton selectedRadioButton = findViewById(selectedTypeId);
        String userType = selectedRadioButton.getText().toString();

        // Create a unique key for each user entry
        String userId = databaseReference.push().getKey();

        // Create a User object
        User user = new User(name, email, userType);

        // Save user data to Firebase
        databaseReference.child(userId).setValue(user).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(fp.this, "Data saved successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(fp.this, "Failed to save data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Email validation method
    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // User class representing the data structure
    public static class User {
        public String name, email, userType;

        public User() {
            // Default constructor required for Firebase
        }

        public User(String name, String email, String userType) {
            this.name = name;
            this.email = email;
            this.userType = userType;
        }
    }
}
