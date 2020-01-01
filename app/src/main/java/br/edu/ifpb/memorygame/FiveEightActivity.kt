package br.edu.ifpb.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_five_eight.*
import kotlinx.android.synthetic.main.activity_five_six.*

class FiveEightActivity : AppCompatActivity() {
    private lateinit var tvClicked : TextView
    private lateinit var handler : Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_five_eight)

        this.tvClicked = findViewById(R.id.tvClicked)
        this.handler = Handler()

        val array: MutableList<String> = mutableListOf(
            "aa", "bb", "cc", "dd", "ee", "ff", "gg", "hh", "ii", "jj",
            "aa", "bb", "cc", "dd", "ee", "ff", "gg", "hh", "ii", "jj",
            "aa", "bb", "cc", "dd", "ee", "ff", "gg", "hh", "ii", "jj",
            "aa", "bb", "cc", "dd", "ee", "ff", "gg", "hh", "ii", "jj"
        )

        //        array.shuffle()

        val buttons: Array<Button> = arrayOf(bt_58_1,
            bt_58_2,
            bt_58_3,
            bt_58_4,
            bt_58_5,
            bt_58_6,
            bt_58_7,
            bt_58_8,
            bt_58_9,
            bt_58_10,
            bt_58_11,
            bt_58_12,
            bt_58_13,
            bt_58_14,
            bt_58_15,
            bt_58_16,
            bt_58_17,
            bt_58_18,
            bt_58_19,
            bt_58_20,
            bt_58_21,
            bt_58_22,
            bt_58_23,
            bt_58_24,
            bt_58_25,
            bt_58_26,
            bt_58_27,
            bt_58_28,
            bt_58_29,
            bt_58_30,
            bt_58_31,
            bt_58_32,
            bt_58_33,
            bt_58_34,
            bt_58_35,
            bt_58_36,
            bt_58_37,
            bt_58_38,
            bt_58_39,
            bt_58_40
        )

        var clicked: Int = 0
        var lastClicked: MutableList<Int> = ArrayList<Int>()
        var pairsFound : Int = 0

        for ((index, button) in buttons.withIndex()) {
            button.text = "BACK"
//            button.textSize = 0.0F
            button.setOnClickListener {
                if (button.text == "BACK" && clicked < 4) {
                    button.text = array[index]

                    if (clicked < 3) {
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

                    //TODO
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
                    //TODO
                }else if(clicked == 3) {
                    this.handler.removeCallbacksAndMessages(null);

                    if(button.text == array[lastClicked[1]]){
                        this.handler.postDelayed({
                            buttons[lastClicked[0]].text = "BACK"
                            buttons[lastClicked[1]].text = "BACK"
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
                            buttons[lastClicked[1]].text = "BACK"
                            button.text = "BACK"
                            lastClicked = ArrayList<Int>()
                            areButtonsClickable(buttons, true)
                        }, 1000)
                    }

                    //TODO
                } else if (clicked == 4) {
                    this.handler.removeCallbacksAndMessages(null);

                    if (button.text == array[lastClicked[2]]) {
                        pairsFound++
                        button.text = "ACHOU"
                        buttons[lastClicked[1]].text = "ACHOU"
                        buttons[lastClicked[0]].text = "ACHOU"
                        buttons[lastClicked[2]].text = "ACHOU"

                        button.isClickable = false
                        buttons[lastClicked[1]].isClickable = false
                        buttons[lastClicked[0]].isClickable = false
                        buttons[lastClicked[2]].isClickable = false

                        button.setBackgroundColor(resources.getColor(R.color.colorAccent))
                        buttons[lastClicked[1]].setBackgroundColor(resources.getColor(R.color.colorAccent))
                        buttons[lastClicked[0]].setBackgroundColor(resources.getColor(R.color.colorAccent))
                        buttons[lastClicked[2]].setBackgroundColor(resources.getColor(R.color.colorAccent))

                        clicked = 0
                        lastClicked = ArrayList<Int>()
                        this.tvClicked.text = "You have found ${pairsFound} pairs!"
                    } else {
                        areButtonsClickable(buttons, false)

                        this.handler.postDelayed({
                            clicked = 0
                            //this.tvClicked.text = "Clicked = ${clicked} ${button.text}"
                            buttons[lastClicked[1]].text = "BACK"
                            buttons[lastClicked[0]].text = "BACK"
                            buttons[lastClicked[2]].text = "BACK"
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

