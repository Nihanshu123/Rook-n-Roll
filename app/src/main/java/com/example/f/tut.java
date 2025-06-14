package com.example.f;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class tut extends AppCompatActivity {

    private WebView[] webViews = new WebView[10]; // Array to store WebViews

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tut); // Set your layout file here

        // Initialize WebViews and settings
        for (int i = 0; i < webViews.length; i++) {
            String webViewId = "webview" + (i + 1); // Construct the ID
            int resID = getResources().getIdentifier(webViewId, "id", getPackageName());
            webViews[i] = findViewById(resID);
            WebSettings webSettings = webViews[i].getSettings();
            webSettings.setJavaScriptEnabled(true);
            webViews[i].setWebViewClient(new WebViewClient());
        }

        // Load embedded videos
        String[] videos = {
                "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/IU6k-4rKf-g?si=mOYOlkW1SIxbEiuZ\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
                "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/R8o_CVxHWZo?si=_ipWjAp815rK8acT\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
                "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/M72DEPy9alk?si=0WgVB91fgjI6z9Ll\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
                "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/F2ZJFCgyHoU?si=d6ExDpj-gg-FPwMe\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
                "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/DnwmYd_ybdE?si=yeRu-R2HbLVobTyF\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
                "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/1vnbc2tF9UE?si=-NGYyKHHpTmEUerb\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
                "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/r8SV83D5m_k?si=3ibbzgSiDNZpcTre\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
                "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/RsibNyWsCbI?si=SMJqzf97n6Ytm2i3\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
                "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/nBo_S7_hrcI?si=rvpJDLiTtCO2l4qF\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
                "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/rjHCE3UQY8w?si=SxB4xVRI8g07YfSE\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>"
        };







        // Load each video into its respective WebView
        for (int i = 0; i < videos.length; i++) {
            webViews[i].loadData(videos[i], "text/html", "utf-8");
        }
    }


}