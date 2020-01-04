package br.edu.ifpb.memorygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.os.persistableBundleOf
import br.edu.ifpb.memorygame.network.Api
import br.edu.ifpb.memorygame.retrofit_model.Image
import br.edu.ifpb.memorygame.retrofit_model.Product
import br.edu.ifpb.memorygame.retrofit_model.Products
import com.squareup.picasso.Picasso
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
    private lateinit var imageButton : ImageButton
    private lateinit var ibHome : ImageButton
    private lateinit var ibRetry : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_four_five)

        this.tvClicked = findViewById(R.id.tvClicked)
        this.imageButton = findViewById(R.id.ib_45_invi)
        this.ibHome = findViewById(R.id.ib_45_home)
        this.ibRetry = findViewById(R.id.ib_45_retry)
        this.handler = Handler()
        val anim2 = AnimationUtils.loadAnimation(this, R.anim.anim2)

        ibHome.setOnClickListener{
            val it = Intent(this, MainActivity::class.java)
            startActivity(it)
        }

        ibRetry.setOnClickListener{
//            resetGame()
        }


        var productList = intent.extras?.get("productArr") as HashMap<Long, String>
//        var randomList : MutableList<Long> = mutableListOf()
//
        Log.i("APP_MEMORY", "Here you go")


//
//        randomList.shuffle()

//        val array : MutableList<String> = mutableListOf("Marron 5", "John Mayer", "Arctic Monkeys", "Imagine Dragons", "The Strokes",
//            "Kings of Leon", "Foo Fighters", "Queen", "The Killers", "Paramore",
//            "Marron 5", "John Mayer", "Arctic Monkeys", "Imagine Dragons", "The Strokes",
//            "Kings of Leon", "Foo Fighters", "Queen", "The Killers", "Paramore")

        val array : MutableList<Long> = mutableListOf()
        var index = 0

        for(product in productList) {
            Picasso.get().load(product.value).into(this.imageButton)
            Log.i("APP_MEMORY", "Passou aqui ${index}")
            if(index < 10) {
                array.add(product.key)
                array.add(product.key)
            }
            index++
        }

//        for(x in 0..9) {
//            array.add(productList.key)
//            array.add(randomList[x])
//        }

        array.shuffle()

        val buttons : Array<ImageButton> = arrayOf(ib_45_1, ib_45_2, ib_45_3, ib_45_4, ib_45_5,
                                            ib_45_6, ib_45_7, ib_45_8, ib_45_9, ib_45_10,
                                            ib_45_11, ib_45_12, ib_45_13, ib_45_14, ib_45_15,
                                            ib_45_16, ib_45_17, ib_45_18, ib_45_19, ib_45_20)

        var clicked : Int = 0
        var lastClicked : Int = -1
        var pairsFound : Int = 0

        for((index,button) in buttons.withIndex()) {
//            button.text = "BACK"
//            button.textSize = 0.0F
            button.contentDescription = "BACK"

            button.setOnClickListener {
                if(button.contentDescription == "BACK" && clicked < 2){
                    button.contentDescription = array[index].toString()
                    button.setBackgroundResource(R.drawable.cardfront2_small)
                    Picasso.get()
                            .load(productList.get(array[index]))
                            .noFade()

                            .centerCrop(Gravity.TOP)
                        .fit()
                            .into(button)

//                    button.setImageDrawable(productList.get(array[index])!!.drawable)

                    if(clicked == 0) {
                        lastClicked = index
                    }

                    clicked++
                    //this.tvClicked.text = "Clicked = ${clicked}"
                }

                if(clicked == 1) {

                    this.handler.postDelayed({
                        buttons[lastClicked].contentDescription = "BACK"
                        button.setBackgroundResource(R.drawable.cardback_small)
                        buttons[lastClicked].setImageBitmap(null)
                        clicked = 0
                        //this.tvClicked.text = "Clicked = ${clicked}"
                    }, 3000)
                }

                if(clicked == 2) {
                    this.handler.removeCallbacksAndMessages(null);

                    if(button.contentDescription == array[lastClicked].toString()){
                        pairsFound++
                        if(pairsFound == 10) {
                            val dialog = AlertDialog.Builder(this)
                            var inflater = LayoutInflater.from(this)
                            var layout = inflater.inflate(R.layout.layout_dialog, null)
                            var homeButton = layout.findViewById<ImageButton>(R.id.ib_dialog_home)
                            homeButton.setOnClickListener{
                                val it = Intent(this, MainActivity::class.java)
                                startActivity(it)


                            }
                            dialog.setView(layout)
                            dialog.create().show()
                        }
//                        button.text = "ACHOU"
                        button.startAnimation(anim2)
                        buttons[lastClicked].startAnimation(anim2)
//                        button.startAnimation(anim3)
//                        buttons[lastClicked].startAnimation(anim3)
                        button.isClickable = false
//                        buttons[lastClicked].text = "ACHOU"
                        buttons[lastClicked].isClickable = false
                        clicked = 0
                        this.tvClicked.text = "You have found ${pairsFound} pairs!"
                    }else {
                        areButtonsClickable(buttons, false)

                        this.handler.postDelayed({
                            clicked = 0
                            //this.tvClicked.text = "Clicked = ${clicked} ${button.text}"
                            buttons[lastClicked].contentDescription = "BACK"
                            button.contentDescription = "BACK"
                            buttons[lastClicked].setImageBitmap(null)
                            button.setImageBitmap(null)
                            buttons[lastClicked].setBackgroundResource(R.drawable.cardback_small)
                            button.setBackgroundResource(R.drawable.cardback_small)
                            areButtonsClickable(buttons, true)
                        }, 1000)

                    }
                }
            }
        }


    }
    fun areButtonsClickable(buttons : Array<ImageButton>, status : Boolean) {

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

//    fun getProducts() {
//
//        val BASE_URL = "https://shopicruit.myshopify.com/admin/"
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val service = retrofit.create(Api::class.java)
//
//        val callback = service.getComics("1", "c32313df0d0ef512ca64d5b336a0d7c6")
//
//        callback.enqueue(object : Callback<Products> {
//
//            override fun onFailure(call: Call<Products>, t: Throwable) {
//                Log.e("APP_MEMORY", "On failure ${t}")
//            }
//
//            override fun onResponse(call: Call<Products>, response: Response<Products>) {
//
//                if(response.isSuccessful) {
//                    if(response.body() != null) {
//                        var response = response.body()
//                        productsArr = response!!.products
//
//                        Log.e("APP_MEMORY", "Deu certo ${response}")
//
////                        for(product in productsArr){
////                            Log.e("APP_MEMORY", "Produto = ${product.id}")
////                        }
//
//                    }else {
//                        Log.e("APP_MEMORY", "response.body() ==  (is) null")
//                    }
//                }else {
//                    Log.e("APP_MEMORY", "response.isSuccessful is not TRUE")
//                }
//            }
//
//        })
//    }
}
