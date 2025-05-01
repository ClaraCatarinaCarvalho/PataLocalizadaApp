package com.clara.patalocalizada;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.clara.patalocalizada.models.Animal;

public class AnimalDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_detail);

        ImageView imageView = findViewById(R.id.imageViewAnimal);
        TextView textNome = findViewById(R.id.textNome);
        TextView textEspecie = findViewById(R.id.textEspecie);
        TextView textRaca = findViewById(R.id.textRaca);
        TextView textIdade = findViewById(R.id.textIdade);
        TextView textSexo = findViewById(R.id.textSexo);

        // Verificar se o objeto foi passado corretamente
        Animal animal = getIntent().getParcelableExtra("animal");

        if (animal != null) {
            imageView.setImageResource(animal.getImagemResId());
            textNome.setText(animal.getNome());
            textEspecie.setText("Espécie: " + animal.getEspecie());
            textRaca.setText("Raça: " + animal.getRaca());
            textIdade.setText("Idade: " + animal.getIdade() + " anos");
            textSexo.setText("Sexo: " + animal.getSexo());
        } else {
            textNome.setText("Erro ao carregar animal.");
        }
    }
}
