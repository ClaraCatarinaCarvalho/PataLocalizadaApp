package com.clara.patalocalizada;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.clara.patalocalizada.models.Animal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AdoptionActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AnimalAdapter adapter;
    private List<Animal> animalList;
    private List<Animal> listaFiltradaAtual = new ArrayList<>();

    private Button btnFiltros;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption);

        recyclerView = findViewById(R.id.recyclerViewAnimais);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        btnFiltros = findViewById(R.id.btnFiltros);
        searchView = findViewById(R.id.searchView);

        animalList = new ArrayList<>();
        animalList.add(new Animal(R.drawable.gizmo, "Gizmo", "Gato", "Europeu Comum", 2, "M"));
        animalList.add(new Animal(R.drawable.benny, "Benny", "Cão", "Serra da Estrela", 3, "M"));
        animalList.add(new Animal(R.drawable.sofia, "Sofia", "Cão", "Rafeiro", 10, "F"));
        animalList.add(new Animal(R.drawable.lolita, "Lolita", "Gato", "Desconhecido", 5, "F"));
        animalList.add(new Animal(R.drawable.amora, "Amora", "Cão", "Yorkshire", 9, "F"));
        animalList.add(new Animal(R.drawable.blackfriday, "Black Friday", "Cão", "Labrador", 6, "M"));
        animalList.add(new Animal(R.drawable.lilith, "Lilith", "Gato", "Desconhecido", 3, "F"));
        animalList.add(new Animal(R.drawable.francisca, "Francisca", "Cão", "Bulldog Francês", 5, "F"));
        animalList.add(new Animal(R.drawable.lua, "Lua", "Gato", "Rafeiro", 6, "F"));
        animalList.add(new Animal(R.drawable.luna, "Luna", "Cão", "Boxer", 9, "F"));
        animalList.add(new Animal(R.drawable.loki, "Loki", "Gato", "Maincoone e Siamês", 3, "M"));
        animalList.add(new Animal(R.drawable.jessie, "Jessie", "Gato", "Rafeiro", 4, "F"));
        animalList.add(new Animal(R.drawable.joao, "João", "Gato", "Rafeiro", 4, "M"));
        animalList.add(new Animal(R.drawable.mariana, "Mariana", "Cão", "Rafeiro", 11, "F"));
        animalList.add(new Animal(R.drawable.amendoa, "Amendoa", "Gato", "Rafeiro", 5, "F"));
        animalList.add(new Animal(R.drawable.castanha, "Castanha", "Gato", "Rafeiro", 5, "F"));
        animalList.add(new Animal(R.drawable.susu, "Susu", "Gato", "Desconhecido", 3, "M"));
        animalList.add(new Animal(R.drawable.yami, "Yami", "Gato", "Desconhecido", 1, "F"));
        animalList.add(new Animal(R.drawable.ofelia, "Ofélia", "Cão", "Desconhecido", 9, "F"));
        animalList.add(new Animal(R.drawable.amelia, "Amelia", "Gato", "Europeu Comum", 1, "F"));
        animalList.add(new Animal(R.drawable.valentim, "Valentim", "Gato", "Desconhecido", 2, "M"));
        animalList.add(new Animal(R.drawable.princesa, "Princesa", "Cão", "PitBull", 5, "F"));
        animalList.add(new Animal(R.drawable.belchior, "Belchior", "Gato", "Desconhecido", 4, "M"));
        animalList.add(new Animal(R.drawable.megan, "Megan", "Gato", "Desconhecido", 6, "F"));
        animalList.add(new Animal(R.drawable.pacoca, "Paçoca", "Cão", "Desconhecido", 10, "F"));
        animalList.add(new Animal(R.drawable.tiffany, "Tiffany", "Gato", "Siamês", 4, "F"));
        animalList.add(new Animal(R.drawable.estrela, "Estrela", "Cão", "Rafeiro", 5, "F"));
        animalList.add(new Animal(R.drawable.edgar, "Edgar", "Cão", "Rafeiro", 8, "M"));
        animalList.add(new Animal(R.drawable.nyx, "Nyx", "Gato", "Siamês", 6, "F"));
        animalList.add(new Animal(R.drawable.milu, "Milu", "Cão", "Rafeiro", 11, "M"));
        animalList.add(new Animal(R.drawable.hajam, "Hajam", "Gato", "Rafeiro", 3, "M"));
        animalList.add(new Animal(R.drawable.henry, "Henry", "Cão", "Desconhecido", 7, "M"));
        animalList.add(new Animal(R.drawable.joseph, "Joseph", "Gato", "Pelo Curto Brasileiro", 5, "M"));
        animalList.add(new Animal(R.drawable.sirius, "Sirius", "Gato", "Bobtail Americano", 3, "M"));

        // Inicializar o adapter com a lista de animais
        adapter = new AnimalAdapter(this, animalList);
        recyclerView.setAdapter(adapter);

        listaFiltradaAtual.addAll(animalList); // Inicializa com todos os animais

        btnFiltros.setOnClickListener(v -> mostrarDialogFiltros());

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filtrarPorRaca(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filtrarPorRaca(newText);
                return true;
            }
        });
    }

    private void filtrarPorRaca(String raca) {
        List<Animal> listaFiltrada = listaFiltradaAtual.stream()
                .filter(animal -> animal.getRaca().toLowerCase().contains(raca.toLowerCase()))
                .collect(Collectors.toList());

        adapter.atualizarLista(listaFiltrada);
    }

    private void filtrarAnimais(String tipo) {
        if (tipo.equals("Todos")) {
            listaFiltradaAtual = new ArrayList<>(animalList);
        } else if (tipo.equals("Idade (Ascendente)")) {
            listaFiltradaAtual = animalList.stream()
                    //.sorted((a1, a2) -> Integer.compare(a1.getIdade(), a2.getIdade()))
                    .sorted(Comparator.comparingInt(Animal::getIdade)) //Ordena os animais do mais novo para o mais velho
                    .collect(Collectors.toList());
        } else if (tipo.equals("Idade (Descendente)")) {
            listaFiltradaAtual = animalList.stream()
                    .sorted((a1, a2) -> Integer.compare(a2.getIdade(), a1.getIdade())) //Ordena do mais velho para o mais novo
                    .collect(Collectors.toList());
        } else if (tipo.equals("Nomes A-Z")) {
            listaFiltradaAtual = animalList.stream()
                    .sorted(Comparator.comparing(Animal::getNome, String.CASE_INSENSITIVE_ORDER))//Ordena os nomes dos animais em ordem alfabética crescente, ignorando maiúsculas e minúsculas
                    .collect(Collectors.toList());
        } else if (tipo.equals("Nomes Z-A")) {
            listaFiltradaAtual = animalList.stream()
                    .sorted((a1, a2) -> a2.getNome().compareToIgnoreCase(a1.getNome())) //Ordena os nomes dos animais em ordem alfabética decrescente
                    .collect(Collectors.toList());
        } else {
            listaFiltradaAtual = animalList.stream()
                    .filter(animal -> animal.getEspecie().equalsIgnoreCase(tipo)) //Se o valor de tipo não for um dos critérios acima, ele assume que é o nome de uma espécie e filtra os animais para mostrar apenas os daquela espécie
                    .collect(Collectors.toList());
        }

        adapter.atualizarLista(listaFiltradaAtual);
    }

    private void mostrarDialogFiltros() {
        // Criar uma lista de opções de filtro
        String[] filtros = {"Todos", "Idade (Ascendente)", "Idade (Descendente)", "A-Z", "Z-A", "Gato", "Cão"};

        // Criar o AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Escolha um filtro")
                .setItems(filtros, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // A opção escolhida
                        String filtroEscolhido = filtros[which];

                        // Aplicar o filtro selecionado
                        filtrarAnimais(filtroEscolhido);
                    }
                });

        // Exibir o diálogo
        builder.create().show();

    }
    public void moredetails(View view) {
        Intent intent = new Intent(AdoptionActivity.this, MoreDetailsActivity.class);
        startActivity(intent);
        finish();
    }
}
