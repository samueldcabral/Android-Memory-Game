package br.edu.ifpb.memorygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_five_eight.*
import kotlinx.android.synthetic.main.activity_five_six.*

class FiveEightActivity : AppCompatActivity() {
    private lateinit var tvClicked : TextView
    private lateinit var handler : Handler
    private lateinit var imageButton : ImageButton
    private lateinit var ibHome : ImageButton
    private lateinit var ibRetry : ImageButton
    private lateinit var buttons : Array<ImageButton>
    private lateinit var productList : HashMap<Long,String>
    private lateinit var anim2 : Animation
    private var MATCH_QUANTITY = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_five_eight)

        this.tvClicked = findViewById(R.id.tvClicked)
        this.handler = Handler()
        this.imageButton = findViewById(R.id.ib_58_invi)
        this.ibHome = findViewById(R.id.ib_58_home)
        this.ibRetry = findViewById(R.id.ib_58_retry)
        this.anim2 = AnimationUtils.loadAnimation(this, R.anim.anim2)

        ibHome.setOnClickListener{
            val it = Intent(this, MainActivity::class.java)
            startActivity(it)
        }

        ibRetry.setOnClickListener{
            resetGame()
        }

        this.buttons = arrayOf(ib_58_1,ib_58_2,ib_58_3,ib_58_4,ib_58_5,
            ib_58_6,ib_58_7,ib_58_8,ib_58_9,ib_58_10,
            ib_58_11,ib_58_12,ib_58_13,ib_58_14,ib_58_15,
            ib_58_16,ib_58_17,ib_58_18,ib_58_19,ib_58_20,
            ib_58_21,ib_58_22,ib_58_23,ib_58_24,ib_58_25,
            ib_58_26,ib_58_27,ib_58_28,ib_58_29,ib_58_30,
            ib_58_31,ib_58_32,ib_58_33,ib_58_34,ib_58_35,
            ib_58_36,ib_58_37,ib_58_38,ib_58_39,ib_58_40)

        this.productList = intent.extras?.get("productArr") as HashMap<Long, String>

        newGame(buttons, productList, anim2)

    }

    fun resetGame() {
        newGame(buttons, productList, anim2)
    }

    fun shuffleCards(productList : HashMap<Long, String>) : MutableList<Long>{

        val array : MutableList<Long> = mutableListOf()
        var index = 0

        for(product in productList) {
            Picasso.get().load(product.value).into(this.imageButton)

            //5x8 grid requires four of the same product to match
            //since the quantity of cards is 40, there will be 10 matches of four cards
            if(index < 10) {
                for(i in 1..MATCH_QUANTITY){
                    array.add(product.key)
                }
            }
            index++
        }

        array.shuffle()

        return array
    }
    fun newGame(buttons : Array<ImageButton>, productList : HashMap<Long, String>, anim2 : Animation) {
        var clicked : Int = 0
        var lastClicked: MutableList<Int> = ArrayList<Int>()
        var pairsFound : Int = 0
        var array = shuffleCards(productList)


        for(button in buttons) {
            button.setImageBitmap(null)
        }

        this.tvClicked.text = "You have found ${pairsFound} pairs!"

        for((index,button) in buttons.withIndex()) {

            button.contentDescription = "BACK"
            button.setBackgroundResource(R.drawable.cardback_small)

            button.setOnClickListener {

                if(button.contentDescription == "BACK" && clicked < 4){

                    button.contentDescription = array[index].toString()
                    button.setBackgroundResource(R.drawable.cardfront2_small)

                    Picasso.get()
                        .load(productList.get(array[index]))
                        .noFade()
                        .centerCrop(Gravity.TOP)
                        .fit()
                        .into(button)


                    if (clicked < 3) {
                        lastClicked.add(index)
                    }

                    clicked++
                }

                if(clicked == 1) {

                    this.handler.postDelayed({
                        turnCardToTheBack(buttons[lastClicked[0]])

                        clicked = 0
                        lastClicked = ArrayList<Int>()
                    }, 3000)
                }
                else if(clicked == 2 ) {
                    this.handler.removeCallbacksAndMessages(null);
//
                    if(button.contentDescription == array[lastClicked[0]].toString()){
                        this.handler.postDelayed({
                            turnCardToTheBack(buttons[lastClicked[0]])
                            turnCardToTheBack(button)

                            clicked = 0
                            lastClicked = ArrayList<Int>()
                        }, 1000)

                    }else {
                        setClickableButtons(buttons, false)

                        this.handler.postDelayed({
                            turnCardToTheBack(buttons[lastClicked[0]])
                            turnCardToTheBack(button)

                            clicked = 0
                            lastClicked = ArrayList<Int>()
                            setClickableButtons(buttons, true)
                        }, 1000)
                    }
                }else if(clicked == 3) {
                    this.handler.removeCallbacksAndMessages(null);
                    if(button.contentDescription == array[lastClicked[0]].toString()){

                        this.handler.postDelayed({
                            turnCardToTheBack(buttons[lastClicked[0]])
                            turnCardToTheBack(buttons[lastClicked[1]])
                            turnCardToTheBack(button)

                            clicked = 0
                            lastClicked = ArrayList<Int>()
                        }, 1000)

                    }else {
                        setClickableButtons(buttons, false)

                        this.handler.postDelayed({
                            turnCardToTheBack(buttons[lastClicked[0]])
                            turnCardToTheBack(buttons[lastClicked[1]])
                            turnCardToTheBack(button)

                            clicked = 0
                            lastClicked = ArrayList<Int>()
                            setClickableButtons(buttons, true)
                        }, 1000)
                    }
                }

                else if(clicked == 4) {
                    this.handler.removeCallbacksAndMessages(null);

                    if(button.contentDescription == array[lastClicked[1]].toString()){

                        pairsFound++

                        isGameOver(pairsFound)

                        button.startAnimation(anim2)
                        buttons[lastClicked[2]].startAnimation(anim2)
                        buttons[lastClicked[1]].startAnimation(anim2)
                        buttons[lastClicked[0]].startAnimation(anim2)

                        button.isClickable = false
                        buttons[lastClicked[2]].isClickable = false
                        buttons[lastClicked[1]].isClickable = false
                        buttons[lastClicked[0]].isClickable = false

                        clicked = 0
                        lastClicked = ArrayList<Int>()
                        this.tvClicked.text = "You have found ${pairsFound} pairs!"

                    }else {
                        setClickableButtons(buttons, false)

                        this.handler.postDelayed({
                            turnCardToTheBack(buttons[lastClicked[0]])
                            turnCardToTheBack(buttons[lastClicked[1]])
                            turnCardToTheBack(buttons[lastClicked[2]])
                            turnCardToTheBack(button)

                            clicked = 0
                            lastClicked = ArrayList<Int>()
                            setClickableButtons(buttons, true)
                        }, 1000)

                    }
                }
            }
        }
    }

    fun turnCardToTheBack(card : ImageButton) {
        card.contentDescription = "BACK"
        card.setImageBitmap(null)
        card.setBackgroundResource(R.drawable.cardback_small)
    }

    fun isGameOver(pairsFound : Int) {
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
                it.putExtra("escolha", "3")
                startActivity(it)
            }
            dialog.setView(layout)
            dialog.create().show()
        }
    }

    fun setClickableButtons(buttons : Array<ImageButton>, status : Boolean) {
        for(button in buttons) {
            button.isClickable = status
        }
    }
}

