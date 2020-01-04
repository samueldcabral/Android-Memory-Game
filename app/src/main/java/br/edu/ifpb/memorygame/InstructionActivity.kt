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
//        var productList = intent.extras?.get("productArr") as HashMap<Long, String>
//        var randomList : MutableList<Long> = mutableListOf()
//
//        for(product in productList) {
//            randomList.add(product.key)
//        }
//
//        randomList.shuffle()

        this.tvQuantity = findViewById(R.id.tvInstructionQuantity)

        this.quantity = intent.getStringExtra("escolha")
//        var productsArr = intent.getBundleExtra("productArr") as ArrayList<Product>
//
//        for(product in productsArr) {
//            Log.e("APP_MEMORY", "PQPQPQPQPQPQPPQ => Produto = ${product.id}")
//        }

        this.tvQuantity.text = this.quantity
        getProducts()

//        this.handler = Handler()
//        this.handler.postDelayed({
//            goToLevel(quantity!!, productList)
//        }, this.splashTime)
    }

    fun goToLevel(difficulty : String, productList : HashMap<Long, String>) {
        var it : Intent = Intent()

        if(difficulty == "2") {
            it = Intent(this, FourFiveActivity::class.java)
        }else if(difficulty == "3") {
            it = Intent(this, FiveSixActivity::class.java)
        }else if(difficulty == "4") {
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

//                        goToMainActivity(productsArr)
                        var productList : HashMap<Long, String> = HashMap()
                        var index = 1;

                        var randomList : MutableList<Long> = mutableListOf()



                        for(product in productsArr) {

//                            Picasso.get().load(product.image.src).into(ib)
                            randomList.add(product.id)
//                            productList.put(product.id, product.image.src)

//                            this@InstructionActivity.progressBar.progress = (index * 100) / productsArr.size
                        }

//                        for(product in productList) {
//                            randomList.add(product.key)
//                        }

                        randomList.shuffle()

                        val buttons : Array<ImageButton> = arrayOf(ib_instruction_1, ib_instruction_2, ib_instruction_3, ib_instruction_4, ib_instruction_5,
                                ib_instruction_6, ib_instruction_7, ib_instruction_8, ib_instruction_9 ,ib_instruction_10,
                            ib_instruction_11, ib_instruction_12, ib_instruction_13, ib_instruction_14, ib_instruction_15,
                            ib_instruction_16, ib_instruction_17, ib_instruction_18, ib_instruction_19 ,ib_instruction_20,
                                    ib_instruction_21, ib_instruction_22, ib_instruction_23, ib_instruction_24, ib_instruction_25,
                            ib_instruction_26, ib_instruction_27, ib_instruction_28, ib_instruction_29 ,ib_instruction_30,
                                    ib_instruction_31, ib_instruction_32, ib_instruction_33, ib_instruction_34, ib_instruction_35,
                            ib_instruction_36, ib_instruction_37, ib_instruction_38, ib_instruction_39 ,ib_instruction_40,
                                    ib_instruction_41, ib_instruction_42, ib_instruction_43, ib_instruction_44, ib_instruction_45,
                            ib_instruction_46, ib_instruction_47, ib_instruction_48, ib_instruction_49 ,ib_instruction_50)

                        var indexe = 0

                        for(product in productsArr) {



//                                Picasso.get().load(product.image.src).into(buttons[indexe])
                                productList.put(product.id, product.image.src)
                                indexe++
                                this@InstructionActivity.progressBar.progress = ((indexe+1) * 100) / productsArr.size


//                            Picasso.get().load(product.image.src).into(ib)
//                            randomList.add(product.id)
//                            productList.put(product.id, product.image.src)

//                            this@InstructionActivity.progressBar.progress = (index * 100) / productsArr.size
                        }
//
//                        for(x in 0..9) {
//                            Picasso.get().load(productsArr).into(ib)
//                        }

                        goToLevel(this@InstructionActivity.quantity, productList)

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
