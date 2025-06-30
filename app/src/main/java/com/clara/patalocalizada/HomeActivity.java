package com.clara.patalocalizada;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    private ImageView banner;
    private int[] images = {R.drawable.banner, R.drawable.sejavoluntario};
    private int currentImageIndex = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        banner = findViewById(R.id.banner);
        iniciarTrocaDeBanner();
    }

    public void adoption(View view) {
        startActivity(new Intent(this, AdoptionActivity.class));
    }

    public void moredetails(View view) {
        startActivity(new Intent(this, MoreDetailsActivity.class));
    }

    public void scanQRCode(View view) {
        startActivity(new Intent(this, ScanActivity.class));
    }

    public void openReportLostAnimal(View view) {
        startActivity(new Intent(this, ReportLostAnimalActivity.class));
    }

    public void openLostAnimalsList(View view) {
        startActivity(new Intent(this, LostAnimalsListActivity.class));
    }

    private void iniciarTrocaDeBanner() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentImageIndex = (currentImageIndex + 1) % images.length;
                banner.setImageResource(images[currentImageIndex]);
                handler.postDelayed(this, 8000);
            }
        }, 5000);
    }
}
