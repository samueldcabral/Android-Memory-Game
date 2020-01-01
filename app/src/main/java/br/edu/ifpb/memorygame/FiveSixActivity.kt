package br.edu.ifpb.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import br.edu.ifpb.memorygame.R.color.colorAccent
import kotlinx.android.synthetic.main.activity_five_six.*
import kotlinx.android.synthetic.main.activity_main.*

class FiveSixActivity : AppCompatActivity() {

    private lateinit var tvClicked : TextView
    private lateinit var handler : Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_five_six)

        this.tvClicked = findViewById(R.id.tvClicked)
        this.handler = Handler()

        val array: MutableList<String> = mutableListOf(
            "aa", "bb", "cc", "dd", "ee", "ff", "gg", "hh", "ii", "jj",
            "aa", "bb", "cc", "dd", "ee", "ff", "gg", "hh", "ii", "jj",
            "aa", "bb", "cc", "dd", "ee", "ff", "gg", "hh", "ii", "jj"
        )

//        array.shuffle()
        val buttons: Array<Button> = arrayOf(
            bt_56_1,
            bt_56_2,
            bt_56_3,
            bt_56_4,
            bt_56_5,
            bt_56_6,
            bt_56_7,
            bt_56_8,
            bt_56_9,
            bt_56_10,
            bt_56_11,
            bt_56_12,
            bt_56_13,
            bt_56_14,
            bt_56_15,
            bt_56_16,
            bt_56_17,
            bt_56_18,
            bt_56_19,
            bt_56_20,
            bt_56_21,
            bt_56_22,
            bt_56_23,
            bt_56_24,
            bt_56_25,
            bt_56_26,
            bt_56_27,
            bt_56_28,
            bt_56_29,
            bt_56_30
        )

        var clicked: Int = 0
        var lastClicked: MutableList<Int> = ArrayList<Int>()
        var pairsFound : Int = 0

        for ((index, button) in buttons.withIndex()) {
            button.text = "BACK"
//            button.textSize = 0.0F
            button.setOnClickListener {
                if (button.text == "BACK" && clicked < 3) {
                    button.text = array[index]

                    if (clicked < 2) {
                        lastClicked.add(index)
                    }

                    clicked++
                    //this.tvClicked.text = "Clicked = ${clicked} | last clicked ${lastClicked}"
                }

                if (clicked == 1) {

                    this.handler.postDelayed({
                        buttons[lastClicked[0]].text = "BACK"
                        clicked = 0
                        //this.tvClicked.text = "Clicked = ${clicked} | last clicked ${lastClicked}"
                        lastClicked = ArrayList<Int>()
                    }, 3000)

                }else if (clicked == 2) {
                    this.handler.removeCallbacksAndMessages(null);

                    if(button.text == array[lastClicked[0]]){
                        this.handler.postDelayed({
                            buttons[lastClicked[0]].text = "BACK"
                            button.text = "BACK"
                            clicked = 0
                            //this.tvClicked.text = "Clicked = ${clicked} | last clicked ${lastClicked}"
                            lastClicked = ArrayList<Int>()
                        }, 3000)

                    }else {
                        areButtonsClickable(buttons, false)

                        this.handler.postDelayed({
                            clicked = 0
                            //this.tvClicked.text = "Clicked = ${clicked} | last clicked ${lastClicked}"
                            buttons[lastClicked[0]].text = "BACK"
                            button.text = "BACK"
                            lastClicked = ArrayList<Int>()
                            areButtonsClickable(buttons, true)
                        }, 1000)
                    }
                }else if (clicked == 3) {
                    this.handler.removeCallbacksAndMessages(null);

                    if (button.text == array[lastClicked[1]]) {
                        pairsFound++
                        button.text = "ACHOU"
                        buttons[lastClicked[1]].text = "ACHOU"
                        buttons[lastClicked[0]].text = "ACHOU"

                        button.isClickable = false
                        buttons[lastClicked[1]].isClickable = false
                        buttons[lastClicked[0]].isClickable = false

                        button.setBackgroundColor(resources.getColor(R.color.colorAccent))
                        buttons[lastClicked[1]].setBackgroundColor(resources.getColor(colorAccent))
                        buttons[lastClicked[0]].setBackgroundColor(resources.getColor(colorAccent))

                        clicked = 0
                        lastClicked = ArrayList<Int>()
                        this.tvClicked.text = "You have found ${pairsFound} pairs!"
                    } else {
                        areButtonsClickable(buttons, false)

                        this.handler.postDelayed({
                            clicked = 0
                            this.tvClicked.text = "Clicked = ${clicked} ${button.text}"
                            buttons[lastClicked[1]].text = "BACK"
                            buttons[lastClicked[0]].text = "BACK"
                            button.text = "BACK"
                            lastClicked = ArrayList<Int>()
                            areButtonsClickable(buttons, true)
                        }, 1000)

                    }
                }
            }
        }
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
}

