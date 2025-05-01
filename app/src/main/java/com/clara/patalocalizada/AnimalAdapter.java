package com.clara.patalocalizada;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.clara.patalocalizada.models.Animal;
import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {
    private Context context;
    private List<Animal> animais;

    public AnimalAdapter(Context context, List<Animal> animais) {
        this.context = context;
        this.animais = animais;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_animal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Animal animal = animais.get(position);
        holder.imageView.setImageResource(animal.getImagemResId());
        holder.textNome.setText(animal.getNome());

        // Passar objeto corretamente
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, AnimalDetailActivity.class);
            intent.putExtra("animal", animal);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return animais.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textNome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewAnimal);
            textNome = itemView.findViewById(R.id.textNome);
        }
    }


    public void atualizarLista(List<Animal> novaLista) {
        this.animais = novaLista;
        notifyDataSetChanged();
    }
}