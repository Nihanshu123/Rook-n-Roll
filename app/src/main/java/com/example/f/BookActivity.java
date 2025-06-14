package com.example.f;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.io.File;

public class BookActivity extends AppCompatActivity {

    private Button btnDownload1, btnDownload2, btnDownload3, btnDownload4, btnDownload5,
            btnDownload6, btnDownload7, btnDownload8, btnDownload9, btnDownload10;
    private FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        storage = FirebaseStorage.getInstance();

        btnDownload1 = findViewById(R.id.downloadButton1);
        btnDownload2 = findViewById(R.id.downloadButton2);
        btnDownload3 = findViewById(R.id.downloadButton3);
        btnDownload4 = findViewById(R.id.downloadButton4);
        btnDownload5 = findViewById(R.id.downloadButton5);
        btnDownload6 = findViewById(R.id.downloadButton6);
        btnDownload7 = findViewById(R.id.downloadButton7);
        btnDownload8 = findViewById(R.id.downloadButton8);
        btnDownload9 = findViewById(R.id.downloadButton9);
        btnDownload10 = findViewById(R.id.downloadButton10);

        btnDownload1.setOnClickListener(v -> downloadBook("gs://seproj-5a93b.appspot.com/books/book1.pdf", "book1.pdf"));
        btnDownload2.setOnClickListener(v -> downloadBook("gs://seproj-5a93b.appspot.com/books/book2.pdf","book2.pdf"));
        btnDownload3.setOnClickListener(v -> downloadBook("gs://seproj-5a93b.appspot.com/books/book3.pdf", "book3.pdf"));
        btnDownload4.setOnClickListener(v -> downloadBook("gs://seproj-5a93b.appspot.com/books/book4.pdf", "book4.pdf"));
        btnDownload5.setOnClickListener(v -> downloadBook("gs://seproj-5a93b.appspot.com/books/book5.pdf", "book5.pdf"));
        btnDownload6.setOnClickListener(v -> downloadBook("gs://seproj-5a93b.appspot.com/books/book6.pdf", "book6.pdf"));
        btnDownload7.setOnClickListener(v -> downloadBook("gs://seproj-5a93b.appspot.com/books/book7.pdf", "book7.pdf"));
        btnDownload8.setOnClickListener(v -> downloadBook("gs://seproj-5a93b.appspot.com/books/book8.pdf", "book8.pdf"));
        btnDownload9.setOnClickListener(v -> downloadBook("gs://seproj-5a93b.appspot.com/books/book9.pdf", "book9.pdf"));
        btnDownload10.setOnClickListener(v -> downloadBook("gs://seproj-5a93b.appspot.com/books/book10.pdf", "book10.pdf"));
    }

    private void downloadBook(String filePath, String fileName) {
        File localFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
        Toast.makeText(this, "Starting download...", Toast.LENGTH_SHORT).show();

        StorageReference storageRef = storage.getReferenceFromUrl(filePath);
        storageRef.getFile(localFile).addOnSuccessListener(taskSnapshot -> {
            Toast.makeText(BookActivity.this, "Download successful", Toast.LENGTH_SHORT).show();

        }).addOnFailureListener(e -> {
            Log.e("BookActivity", "Download failed: " + e.getMessage());
            Toast.makeText(BookActivity.this, "Download failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }


    }

