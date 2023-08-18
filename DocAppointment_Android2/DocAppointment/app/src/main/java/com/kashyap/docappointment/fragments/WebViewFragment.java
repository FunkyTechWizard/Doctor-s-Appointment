package com.kashyap.docappointment.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kashyap.docappointment.AppointmentsAdapter;
import com.kashyap.docappointment.DataBase.DatabaseHelper;
import com.kashyap.docappointment.R;
import com.kashyap.docappointment.model.Appointment;

import java.util.List;

public class WebViewFragment extends Fragment {
    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_webview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView = view.findViewById(R.id.webView);

        // Enable JavaScript in the WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Load the Google.com page in the WebView
        webView.loadUrl("https://www.google.com/maps/search/nearest+hospitals/@43.7286812,-79.6068731,10z/data=!3m1!4b1?entry=ttu");
    }
}
