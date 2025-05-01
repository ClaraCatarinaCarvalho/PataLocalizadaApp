package com.clara.patalocalizada;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QrResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_result);

        TextView textView = findViewById(R.id.qr_result_text);

        String qrData = getIntent().getStringExtra("qrData");
        textView.setText(qrData != null ? qrData : "Nenhum dado encontrado no QR Code.");
    }
}
