package com.clara.patalocalizada;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.clara.patalocalizada.api.ApiService;
import com.clara.patalocalizada.models.ApiInfoResponse.ApiInfoResponse;
import com.clara.patalocalizada.network.RetroFitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    Animation leftAnim;
    Animation bottom_anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ApiService api = RetroFitClient.getInstance().create(ApiService.class);
        api.getApiInfo().enqueue(new Callback<ApiInfoResponse>() {
            @Override
            public void onResponse(Call<ApiInfoResponse> call, Response<ApiInfoResponse> response) {

                if(response.isSuccessful() && response.body() != null){

                    Log.d("API infoResponse", response.body().isSuccess() + "");
                    Toast.makeText(getApplicationContext(), response.body().getMessage() + " | " + response.body().getData().getVersion(), Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<ApiInfoResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        VideoView videoView = findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.splash_screen);
        videoView.start();




        new Handler().postDelayed(() -> {
            Toast.makeText(MainActivity.this, "Bem vindo!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, 1000);
    }
}