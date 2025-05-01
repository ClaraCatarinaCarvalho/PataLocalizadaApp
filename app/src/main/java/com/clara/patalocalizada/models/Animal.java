package com.clara.patalocalizada.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Animal implements Parcelable {
    private int imagemResId;
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private String sexo;

    public Animal(int imagemResId, String nome, String especie, String raca, int idade, String sexo) {
        this.imagemResId = imagemResId;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.sexo = sexo;
    }

    protected Animal(Parcel in) {
        imagemResId = in.readInt();
        nome = in.readString();
        especie = in.readString();
        raca = in.readString();
        idade = in.readInt();
        sexo = in.readString();
    }

    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imagemResId);
        dest.writeString(nome);
        dest.writeString(especie);
        dest.writeString(raca);
        dest.writeInt(idade);
        dest.writeString(sexo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getImagemResId() { return imagemResId; }
    public String getNome() { return nome; }
    public String getEspecie() { return especie; }
    public String getRaca() { return raca; }
    public int getIdade() { return idade; }
    public String getSexo() { return sexo; }
}
