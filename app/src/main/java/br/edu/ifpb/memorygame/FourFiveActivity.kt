package br.edu.ifpb.memorygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.animation.Animation
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
    private lateinit var buttons : Array<ImageButton>
    private lateinit var productList : HashMap<Long,String>
    private lateinit var anim2 : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_four_five)

        this.tvClicked = findViewById(R.id.tvClicked)
        this.imageButton = findViewById(R.id.ib_45_invi)
        this.ibHome = findViewById(R.id.ib_45_home)
        this.ibRetry = findViewById(R.id.ib_45_retry)
        this.handler = Handler()
        this.anim2 = AnimationUtils.loadAnimation(this, R.anim.anim2)

        ibHome.setOnClickListener{
            val it = Intent(this, MainActivity::class.java)
            startActivity(it)
        }

        ibRetry.setOnClickListener{
            resetGame()
        }

        this.buttons = arrayOf(ib_45_1, ib_45_2, ib_45_3, ib_45_4, ib_45_5,
                        ib_45_6, ib_45_7, ib_45_8, ib_45_9, ib_45_10,
                        ib_45_11, ib_45_12, ib_45_13, ib_45_14, ib_45_15,
                        ib_45_16, ib_45_17, ib_45_18, ib_45_19, ib_45_20)

        this.productList = intent.extras?.get("productArr") as HashMap<Long, String>
        var array = shuffleGame(productList)

        newGame(buttons, array, productList, anim2)
    }

    fun resetGame() {
        var array = shuffleGame(productList)
        newGame(buttons, array, productList, anim2)
    }

    fun shuffleGame(productList : HashMap<Long, String>) : MutableList<Long>{

        val array : MutableList<Long> = mutableListOf()
        var index = 0

        for(product in productList) {
            Picasso.get().load(product.value).into(this.imageButton)

            if(index < 10) {
                array.add(product.key)
                array.add(product.key)
            }
            index++
        }

        array.shuffle()

        return array
    }

    fun newGame(buttons : Array<ImageButton>, array : MutableList<Long>, productList : HashMap<Long, String>, anim2 : Animation) {
        var clicked : Int = 0
        var lastClicked : Int = -1
        var pairsFound : Int = 0

        for(button in buttons) {
            button.setImageBitmap(null)
        }

        this.tvClicked.text = "You have found ${pairsFound} pairs!"

        for((index,button) in buttons.withIndex()) {

            button.contentDescription = "BACK"
            button.setBackgroundResource(R.drawable.cardback_small)

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


                    if(clicked == 0) {
                        lastClicked = index
                    }

                    clicked++
                }

                if(clicked == 1) {

                    this.handler.postDelayed({
                        buttons[lastClicked].contentDescription = "BACK"
                        button.setBackgroundResource(R.drawable.cardback_small)
                        buttons[lastClicked].setImageBitmap(null)
                        clicked = 0
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
                            var newGameButton = layout.findViewById<ImageButton>(R.id.ib_dialog_new)
                            homeButton.setOnClickListener{
                                val it = Intent(this, MainActivity::class.java)
                                startActivity(it)
                            }
                            newGameButton.setOnClickListener{
                                val it = Intent(this, InstructionActivity::class.java)
                                it.putExtra("escolha", "2")
                                startActivity(it)
                            }
                            dialog.setView(layout)
                            dialog.create().show()
                        }

                        button.startAnimation(anim2)
                        buttons[lastClicked].startAnimation(anim2)

                        button.isClickable = false
                        buttons[lastClicked].isClickable = false

                        clicked = 0

                        this.tvClicked.text = "You have found ${pairsFound} pairs!"

                    }else {
                        areButtonsClickable(buttons, false)

                        this.handler.postDelayed({
                            clicked = 0
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

}
