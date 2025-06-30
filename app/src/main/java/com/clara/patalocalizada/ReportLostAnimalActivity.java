package com.clara.patalocalizada;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportLostAnimalActivity extends AppCompatActivity {

    private EditText editTextName, editTextSexo, editTextIdade, editTextContacto, editTextChip, editTextRaca, editTextCor;
    private ImageView imageViewAnimal;
    private Uri selectedImageUri;
    public static List<LostAnimal> lostAnimalList = new ArrayList<>();

    private final ActivityResultLauncher<Intent> imagePickerLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                        imageViewAnimal.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_lost_animal);

        editTextName = findViewById(R.id.editTextName);
        editTextSexo = findViewById(R.id.editTextSexo);
        editTextIdade = findViewById(R.id.editTextIdade);
        editTextContacto = findViewById(R.id.editTextContacto);
        editTextChip = findViewById(R.id.editTextChip);
        editTextRaca = findViewById(R.id.editTextRaca);
        editTextCor = findViewById(R.id.editTextCor);
        imageViewAnimal = findViewById(R.id.imageViewAnimal);

        Button buttonSelectImage = findViewById(R.id.buttonSelectImage);
        Button buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSelectImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            imagePickerLauncher.launch(intent);
        });

        buttonSubmit.setOnClickListener(v -> {
            String nome = editTextName.getText().toString();
            String sexo = editTextSexo.getText().toString();
            String idade = editTextIdade.getText().toString();
            String contacto = editTextContacto.getText().toString();
            String chip = editTextChip.getText().toString();
            String raca = editTextRaca.getText().toString();
            String cor = editTextCor.getText().toString();

            if (nome.isEmpty() || sexo.isEmpty() || idade.isEmpty() || contacto.isEmpty() ||
                    chip.isEmpty() || raca.isEmpty() || cor.isEmpty() || selectedImageUri == null) {
                Toast.makeText(this, "Preencha todos os campos e selecione uma foto!", Toast.LENGTH_SHORT).show();
            } else {
                lostAnimalList.add(new LostAnimal(nome, sexo, idade, contacto, chip, raca, cor, selectedImageUri));
                Toast.makeText(this, "Animal reportado com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
