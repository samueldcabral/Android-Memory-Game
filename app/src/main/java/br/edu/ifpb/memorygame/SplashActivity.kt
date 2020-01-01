package br.edu.ifpb.memorygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageView
import br.edu.ifpb.memorygame.network.Api
import br.edu.ifpb.memorygame.retrofit_model.Product
import br.edu.ifpb.memorygame.retrofit_model.Products
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SplashActivity : AppCompatActivity() {
    private lateinit var handler : Handler
    private var splashTime : Long = 1500
    private lateinit var ivLogo : ImageView
    private lateinit var ivBrain : ImageView
    private lateinit var productsArr : MutableList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val anim1 = AnimationUtils.loadAnimation(this, R.anim.anim1)
        this.ivLogo = findViewById(R.id.ivSplashLogo)
        this.ivBrain = findViewById(R.id.ivSplashLogoCerebro)

        this.ivLogo.startAnimation(anim1)
        this.ivBrain.startAnimation(anim1)

//        this.handler = Handler()
//        this.handler.postDelayed({
//            goToMainActivity()
//        }, this.splashTime)

        getProducts()
    }

    fun goToMainActivity(productsArr : MutableList<Product>) {
        var productList : HashMap<Long, String> = HashMap()

        for(product in productsArr) {
            productList.put(product.id, product.image.src)
        }

        val it = Intent(this, MainActivity::class.java)
        it.putExtra("productArr", productList)

        startActivity(it)
        finish()
    }

    fun getProducts() {

        val BASE_URL = "https://shopicruit.myshopify.com/admin/"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(Api::class.java)

        val callback = service.getComics("1", "c32313df0d0ef512ca64d5b336a0d7c6")

        callback.enqueue(object : Callback<Products> {

            override fun onFailure(call: Call<Products>, t: Throwable) {
                Log.e("APP_MEMORY", "On failure ${t}")
            }

            override fun onResponse(call: Call<Products>, response: Response<Products>) {

                if(response.isSuccessful) {
                    if(response.body() != null) {
                        var response = response.body()
                        productsArr = response!!.products

                        goToMainActivity(productsArr)

                        Log.e("APP_MEMORY", "Deu certo ${response}")

//                        for(product in productsArr){
//                            Log.e("APP_MEMORY", "Produto = ${product.id}")
//                        }

                    }else {
                        Log.e("APP_MEMORY", "response.body() ==  (is) null")
                    }
                }else {
                    Log.e("APP_MEMORY", "response.isSuccessful is not TRUE")
                }
            }

        })
    }
}
