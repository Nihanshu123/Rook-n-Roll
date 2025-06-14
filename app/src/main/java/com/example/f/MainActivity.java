package com.example.f;


import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity    extends AppCompatActivity {

    // URLs for tournament websites
    private final String[] tournamentUrls = {
            "https://schachbundesliga.de/",
            "https://womengrandprix.fide.com/",
            "https://www.europechess.org/",
            "https://worldyouthrb2024.com/",
            "https://www.chess.com/events/2024-fide-chess-world-championship",
            "https://www.chess.com/events/info/2024-champions-chess-tour-finals",
            "https://sparkassen-chess-trophy.de/index.php/en/",
            "https://schaakbond.nl/",
            "https://lichess.org/broadcast/34th-nato-chess-championship/round-1/gSqRAsn5",
            "https://www.chess.com/events/2024-fide-world-rapid-chess-championship"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the tournament cards and buttons
        for (int i = 0; i < tournamentUrls.length; i++) {
            setupTournamentCard(i);
        }
    }

    private void setupTournamentCard(int index) {
        // Get the card and button for the current tournament
        CardView cardView = findViewById(getResources().getIdentifier("tournamentCard" + (index + 1), "id", getPackageName()));
        Button viewTournamentButton = findViewById(getResources().getIdentifier("tournamentButton" + (index + 1), "id", getPackageName()));

        // Set the button's onClickListener
        viewTournamentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the tournament URL in a web browser
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(tournamentUrls[index]));
                startActivity(browserIntent);
            }
        });
    }
}
