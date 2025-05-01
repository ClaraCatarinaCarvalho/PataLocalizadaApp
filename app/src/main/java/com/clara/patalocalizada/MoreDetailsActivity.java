package com.clara.patalocalizada;

import android.os.Bundle;
import android.widget.Toast;
import android.app.AlertDialog;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MoreDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);
        // Referencia os CardViews do layout pelos seus IDs
        CardView voluntariadoCard = findViewById(R.id.card_voluntariado);
        CardView doacoesCard = findViewById(R.id.card_doacoes);
        CardView eventosCard = findViewById(R.id.card_eventos);
        CardView apadrinhamentoCard = findViewById(R.id.card_apadrinhamento);

        voluntariadoCard.setOnClickListener(v -> showDialog("Voluntariado",
                "Quer ser voluntário?\n" +
                        "Ligue para: 930 434 653\n\n" +
                        "Funções:\n• Ir a centros comerciais para angariar alimentos\n" +
                        "• Realizado cerca de 2 vezes por ano\n" +
                        "• Tudo combinado por telemóvel"));

        doacoesCard.setOnClickListener(v -> showDialog("Doações",
                "Doações monetárias podem ser feitas via MBWay: 912 069 444\n\n" +
                        "Também pode aparecer em uma das nossas recolhas solidárias!"));

        eventosCard.setOnClickListener(v -> showDialog("Próximos Eventos",
                "📅 Recolha Solidária: 20 de julho\n" +
                        "🎪 Feira no Parque da Cidade: 15 de julho"));

        apadrinhamentoCard.setOnClickListener(v -> showDialog("Apadrinhamentos",
                "🐶 Apadrinhar um cão ou gato: 15€/mês\n" +
                        "🏠 Apadrinhar uma casota: 50€/ano\n" +
                        "👵 Apadrinhar um animal sénior: 30€/mês"));
    }

    private void showDialog(String titulo, String mensagem) {
        new AlertDialog.Builder(this)
                .setTitle(titulo)
                .setMessage(mensagem)
                .setPositiveButton("Fechar", null)
                .show();
    }
}
