package com.clara.patalocalizada;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.clara.patalocalizada.models.User;

public class LoginActivity extends AppCompatActivity {

    EditText emailEditText;
    EditText passwordEditText;
    User u1;
    User u2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.emailEditText = findViewById(R.id.editTextEmail);
        this.passwordEditText = findViewById(R.id.editTextPassword);

        // Criando usuários de teste
        this.u1 = new User("1", "Clara", "claracatarinacarvalho@gmail.com", "Sorayt07", 930987653);
        this.u2 = new User("2", "José", "joseamorin@gmail.com", "josec", 925678890);

        this.emailEditText.setText(this.u1.getEmail());
        this.passwordEditText.setText(this.u1.getPassword());
        this.emailEditText.setText(this.u2.getEmail());
        this.passwordEditText.setText(this.u2.getPassword());
    }

    public void validarCredenciais(View view) {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Verificar se o email e a senha correspondem a um usuário de teste
        if (validarUserData(email, password)) {
            Toast.makeText(this, "Utilizador logado com sucesso", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // Se não for usuário de teste, verificar o SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String savedEmail = sharedPreferences.getString("user_email", "");
        String savedPassword = sharedPreferences.getString("user_password", "");

        if (email.equals(savedEmail) && password.equals(savedPassword)) {
            Toast.makeText(this, "Utilizador logado com sucesso", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Credenciais erradas", Toast.LENGTH_LONG).show();
        }
    }

    public boolean validarUserData(String email, String password) {
        return (email.equals(this.u1.getEmail()) && password.equals(this.u1.getPassword())) ||
                (email.equals(this.u2.getEmail()) && password.equals(this.u2.getPassword()));
    }

    public void newUserRegister(View view) {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
        finish();
    }
}