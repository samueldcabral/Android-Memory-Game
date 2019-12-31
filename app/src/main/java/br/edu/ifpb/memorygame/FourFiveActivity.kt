package br.edu.ifpb.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TextView
import br.edu.ifpb.memorygame.network.Api
import br.edu.ifpb.memorygame.retrofit_model.Products
import kotlinx.android.synthetic.main.activity_four_five.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FourFiveActivity : AppCompatActivity() {
    private lateinit var tvClicked : TextView
    private lateinit var handler : Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_four_five)

        this.tvClicked = findViewById(R.id.tvClicked)
        this.handler = Handler()

        val array : MutableList<String> = mutableListOf("Marron 5", "John Mayer", "Arctic Monkeys", "Imagine Dragons", "The Strokes",
            "Kings of Leon", "Foo Fighters", "Queen", "The Killers", "Paramore",
            "Marron 5", "John Mayer", "Arctic Monkeys", "Imagine Dragons", "The Strokes",
            "Kings of Leon", "Foo Fighters", "Queen", "The Killers", "Paramore")

        array.shuffle()
        val buttons : Array<Button> = arrayOf(bt_45_1, bt_45_2, bt_45_3, bt_45_4, bt_45_5, bt_45_6, bt_45_7, bt_45_8, bt_45_9, bt_45_10,
            bt_45_11, bt_45_12, bt_45_13, bt_45_14, bt_45_15, bt_45_16, bt_45_17, bt_45_18, bt_45_19, bt_45_20)

        var clicked : Int = 0
        var lastClicked : Int = -1

        for((index,button) in buttons.withIndex()) {
            button.text = "BACK"
//            button.textSize = 0.0F
            button.setOnClickListener {
                if(button.text == "BACK" && clicked < 2){
                    button.text = array[index]

                    if(clicked == 0) {
                        lastClicked = index
                    }

                    clicked++
                    this.tvClicked.text = "Clicked = ${clicked}"
                }

                if(clicked == 1) {

                    this.handler.postDelayed({
                        buttons[lastClicked].text = "BACK"
                        clicked = 0
                        this.tvClicked.text = "Clicked = ${clicked}"
                    }, 3000)
                }

                if(clicked == 2) {
                    this.handler.removeCallbacksAndMessages(null);

                    if(button.text == array[lastClicked]){
                        button.text = "ACHOU"
                        button.isClickable = false
                        buttons[lastClicked].text = "ACHOU"
                        buttons[lastClicked].isClickable = false
                        clicked = 0
                        this.tvClicked.text = "Clicked = ${clicked}"
                    }else {
                        areButtonsClickable(buttons, false)

                        this.handler.postDelayed({
                            clicked = 0
                            this.tvClicked.text = "Clicked = ${clicked} ${button.text}"
                            buttons[lastClicked].text = "BACK"
                            button.text = "BACK"
                            areButtonsClickable(buttons, true)
                        }, 1000)

                    }
                }
            }
        }

        getProducts()
    }
    fun areButtonsClickable(buttons : Array<Button>, status : Boolean) {

        if(status){
            for(button in buttons) {
                button.isClickable = true
            }
        }else{
            for(button in buttons) {
                button.isClickable = false
            }
        }


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
                        var productsArr = response!!.products

                        Log.e("APP_MEMORY", "Deu certo ${response}")

                        for(product in productsArr){
                            Log.e("APP_MEMORY", "Produto = ${product.id}")
                        }

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
