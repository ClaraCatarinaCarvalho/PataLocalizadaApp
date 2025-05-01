package com.clara.patalocalizada;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
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
        EdgeToEdge.enable(this);
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
        Intent intent = new Intent(HomeActivity.this, AdoptionActivity.class);
        startActivity(intent);
    }
    public void moredetails(View view) {
        Intent intent = new Intent(HomeActivity.this, MoreDetailsActivity.class);
        startActivity(intent);
    }

    private void iniciarTrocaDeBanner() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Ao atingir a última imagem, o índice reinicia do começo (loop)
                currentImageIndex = (currentImageIndex + 1) % images.length;
                banner.setImageResource(images[currentImageIndex]);
                handler.postDelayed(this, 8000); // Troca a imagem a cada 8 segundos
            }
        }, 5000);
    }

    public void scanQRCode(View view) {
        Intent intent = new Intent(HomeActivity.this, ScanActivity.class);
        startActivity(intent);
    }

}


