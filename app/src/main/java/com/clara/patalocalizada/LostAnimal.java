package com.clara.patalocalizada;

import android.net.Uri;

public class LostAnimal {
    public String nome, sexo, idade, contacto, chip, raca, cor;
    public Uri imagemUri;

    public LostAnimal(String nome, String sexo, String idade, String contacto,
                      String chip, String raca, String cor, Uri imagemUri) {
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.contacto = contacto;
        this.chip = chip;
        this.raca = raca;
        this.cor = cor;
        this.imagemUri = imagemUri;
    }
}
