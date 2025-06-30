package com.clara.patalocalizada;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LostAnimalsListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewLostAnimals;
    private LostAnimalAdapter adapter;
    private List<LostAnimal> lostAnimalList = ReportLostAnimalActivity.lostAnimalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_animals_list);

        recyclerViewLostAnimals = findViewById(R.id.recyclerViewLostAnimals);
        recyclerViewLostAnimals.setLayoutManager(new LinearLayoutManager(this));

        // Se não houverem animais, exibe uma mensagem
        if (lostAnimalList.isEmpty()) {
            Toast.makeText(this, "Não há animais desaparecidos cadastrados.", Toast.LENGTH_SHORT).show();
        }

        adapter = new LostAnimalAdapter(this, lostAnimalList);
        recyclerViewLostAnimals.setAdapter(adapter);
    }

    // Adapter para exibir os animais
    public static class LostAnimalAdapter extends RecyclerView.Adapter<LostAnimalAdapter.LostAnimalViewHolder> {

        private final Context context;
        private final List<LostAnimal> lostAnimalList;

        public LostAnimalAdapter(Context context, List<LostAnimal> lostAnimalList) {
            this.context = context;
            this.lostAnimalList = lostAnimalList;
        }

        @Override
        public LostAnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_lost_animal, parent, false);
            return new LostAnimalViewHolder(view);
        }

        @Override
        public void onBindViewHolder(LostAnimalViewHolder holder, int position) {
            LostAnimal lostAnimal = lostAnimalList.get(position);
            holder.textViewName.setText(lostAnimal.nome);
            holder.textViewSexo.setText(lostAnimal.sexo);
            holder.textViewIdade.setText(lostAnimal.idade);
            holder.textViewContacto.setText(lostAnimal.contacto);
            holder.textViewChip.setText(lostAnimal.chip);
            holder.textViewRaca.setText(lostAnimal.raca);
            holder.textViewCor.setText(lostAnimal.cor);

            // Carregar a imagem
            if (lostAnimal.imagemUri != null) {
                holder.imageViewAnimal.setImageURI(lostAnimal.imagemUri);
            }
        }

        @Override
        public int getItemCount() {
            return lostAnimalList.size();
        }

        public static class LostAnimalViewHolder extends RecyclerView.ViewHolder {
            TextView textViewName, textViewSexo, textViewIdade, textViewContacto, textViewChip, textViewRaca, textViewCor;
            ImageView imageViewAnimal;

            public LostAnimalViewHolder(View itemView) {
                super(itemView);
                textViewName = itemView.findViewById(R.id.textViewName);
                textViewSexo = itemView.findViewById(R.id.textViewSexo);
                textViewIdade = itemView.findViewById(R.id.textViewIdade);
                textViewContacto = itemView.findViewById(R.id.textViewContacto);
                textViewChip = itemView.findViewById(R.id.textViewChip);
                textViewRaca = itemView.findViewById(R.id.textViewRaca);
                textViewCor = itemView.findViewById(R.id.textViewCor);
                imageViewAnimal = itemView.findViewById(R.id.imageViewAnimal);
            }
        }
    }
}
