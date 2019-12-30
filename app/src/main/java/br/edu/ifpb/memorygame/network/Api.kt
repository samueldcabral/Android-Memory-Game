package br.edu.ifpb.memorygame.network

import br.edu.ifpb.memorygame.retrofit_model.Products
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("products.json")
    fun getComics(@Query("page") page : String,
                  @Query("access_token") ts : String) : Call<Products>
}