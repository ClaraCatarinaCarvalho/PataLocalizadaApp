package com.clara.patalocalizada.api;

import com.clara.patalocalizada.models.Animal;
import com.clara.patalocalizada.models.ApiInfoResponse.ApiInfoResponse;
import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface ApiService {

    // ----------- INFO API -----------
    @GET("api/index.php")
    Call<ApiInfoResponse> getApiInfo();

    // ----------- AUTENTICAÇÃO & UTILIZADORES -----------
    @FormUrlEncoded
    @POST("api/signup.php")
    Call<ResponseBody> registerUser(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("api/login.php")
    Call<JsonObject> loginUser(@Field("email") String email, @Field("password") String password);

    @GET("api/getuser.php")
    Call<JsonObject> getUser(@Header("Authorization") String authHeader);

    @GET("api/verificaremailrole.php")
    Call<JsonObject> getUserByEmail(@Query("email") String email);

    @POST("api/enviarcodigo.php")
    Call<JsonObject> enviarCodigo(@Body JsonObject dados);

    @POST("api/updateuserpassword.php")
    Call<JsonObject> updateUserPassword(@Header("Authorization") String token, @Body JsonObject dados);

    @Multipart
    @POST("api/updateusers.php")
    Call<JsonObject> updateUser(
            @Header("Authorization") String authHeader,
            @Part("id") RequestBody id,
            @Part("nome") RequestBody nome,
            @Part("email") RequestBody email,
            @Part("password") RequestBody password
    );

    // ----------- ANIMAIS -----------
    @GET("api/getanimais.php")
    Call<JsonObject> getAllAnimais();  // { "animais": [...] }

    @GET("api/getanimalbyid.php")
    Call<JsonObject> getAnimalById(@Query("id") int id);  // { "animal": {...} }

    @FormUrlEncoded
    @POST("api/addanimal.php")
    Call<JsonObject> addAnimal(
            @Field("nome") String nome,
            @Field("sexo") String sexo,
            @Field("idade") int idade,
            @Field("raca") String raca,
            @Field("id_utilizador") int idUtilizador
    );

    @FormUrlEncoded
    @POST("api/animais_delete.php")
    Call<JsonObject> deleteAnimal(@Field("id") int id);

    // ----------- PRODUTOS -----------
    @GET("api/produtos.php")
    Call<JsonObject> getAllProdutos();  // { "produtos": [...] }

    @GET("api/produtos.php")
    Call<JsonObject> getProdutoById(@Query("id") int id);  // { "produto": {...} }

    // ----------- UTILIZADORES -----------
    @GET("api/utilizadores.php")
    Call<JsonObject> getAllUtilizadores();  // [ {...}, {...} ]

    @GET("api/utilizadores.php")
    Call<JsonObject> getUtilizadorById(@Query("id") int id);  // { ... }

    // ----------- DESAPARECIMENTOS -----------
    @GET("api/desaparecimentos.php")
    Call<JsonObject> getAllDesaparecimentos();  // { "desaparecimentos": [...] }

    @GET("api/desaparecimentos.php")
    Call<JsonObject> getDesaparecimentoById(@Query("id") int id);  // { "desaparecimento": {...} }

    // ----------- ALERTAS -----------
    @GET("api/getalertas.php")
    Call<JsonObject> getAllAlertas();

    @GET("api/getalertabyid.php")
    Call<JsonObject> getAlertaById(@Query("id") int id);

    // ----------- CONSULTAS -----------
    @GET("api/getconsultas.php")
    Call<JsonObject> getAllConsultas();

    @GET("api/getconsultabyid.php")
    Call<JsonObject> getConsultaById(@Query("id") int id);
}


