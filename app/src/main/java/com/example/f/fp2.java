package com.example.f;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class fp2 extends AppCompatActivity {

    private EditText loginNameEditText, loginEmailEditText;
    private Button loginButton;
    private DatabaseReference usersDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fp2);

        // Initialize views
        loginNameEditText = findViewById(R.id.login_name);
        loginEmailEditText = findViewById(R.id.login_email);
        loginButton = findViewById(R.id.login_button);

        // Firebase database reference for users
        usersDatabaseReference = FirebaseDatabase.getInstance().getReference("users");

        // Login button click listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String enteredName = loginNameEditText.getText().toString().trim();
        String enteredEmail = loginEmailEditText.getText().toString().trim();

        // Check for empty fields
        if (TextUtils.isEmpty(enteredName) || TextUtils.isEmpty(enteredEmail)) {
            Toast.makeText(fp2.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate email format
        if (!isValidEmail(enteredEmail)) {
            Toast.makeText(fp2.this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if user exists in Firebase database
        usersDatabaseReference.orderByChild("name").equalTo(enteredName)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // User exists, now check if email matches
                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                String dbEmail = userSnapshot.child("email").getValue(String.class);
                                if (dbEmail != null && dbEmail.equals(enteredEmail)) {
                                    // Email matches, proceed to MainActivity3
                                    String userName = userSnapshot.child("name").getValue(String.class);  // Retrieve the user's name

                                    Intent intent = new Intent(fp2.this, hp.class);
                                    intent.putExtra("userName", userName);  // Pass the userName
                                    startActivity(intent);
                                    finish();  // Close current activity
                                } else {
                                    Toast.makeText(fp2.this, "Email does not match", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Toast.makeText(fp2.this, "User not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(fp2.this, "Database error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Email validation method
    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
