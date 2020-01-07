package br.edu.ifpb.memorygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import br.edu.ifpb.memorygame.network.Api
import br.edu.ifpb.memorygame.retrofit_model.Image
import br.edu.ifpb.memorygame.retrofit_model.Product
import br.edu.ifpb.memorygame.retrofit_model.Products
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_four_five.*
import kotlinx.android.synthetic.main.activity_instruction.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InstructionActivity : AppCompatActivity() {
    private lateinit var handler : Handler
    private lateinit var tvQuantity : TextView
    private lateinit var progressBar : ProgressBar
    private var splashTime : Long = 3500
    private var quantity : String = ""
    private lateinit var productsArr : MutableList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction)

        this.progressBar = findViewById(R.id.pb_instruction)
        this.tvQuantity = findViewById(R.id.tvInstructionQuantity)
        this.quantity = intent.getStringExtra("escolha")
        this.tvQuantity.text = this.quantity

        getProducts()

    }

    fun goToLevel(matchQuantity : String, productList : HashMap<Long, String>) {
        var it : Intent = Intent()

        if(matchQuantity == "2") {
            it = Intent(this, FourFiveActivity::class.java)
        }else if(matchQuantity == "3") {
            it = Intent(this, FiveSixActivity::class.java)
        }else if(matchQuantity == "4") {
            it = Intent(this, FiveEightActivity::class.java)
        }

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

                        var productList : HashMap<Long, String> = HashMap()
                        var randomList : MutableList<Long> = mutableListOf()

                        for(product in productsArr) {
                            randomList.add(product.id)
                        }

                        randomList.shuffle()

                        var index = 0

                        for(product in productsArr) {
                            productList.put(product.id, product.image.src)
                            index++
                            this@InstructionActivity.progressBar.progress = ((index+1) * 100) / productsArr.size
                        }


                        goToLevel(this@InstructionActivity.quantity, productList)

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
