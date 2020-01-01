package br.edu.ifpb.memorygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import br.edu.ifpb.memorygame.retrofit_model.Product

class InstructionActivity : AppCompatActivity() {
    private lateinit var handler : Handler
    private lateinit var tvQuantity : TextView
    private var splashTime : Long = 3500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction)

        this.tvQuantity = findViewById(R.id.tvInstructionQuantity)

        var quantity : String? = intent.getStringExtra("escolha")
//        var productsArr = intent.getBundleExtra("productArr") as ArrayList<Product>
//
//        for(product in productsArr) {
//            Log.e("APP_MEMORY", "PQPQPQPQPQPQPPQ => Produto = ${product.id}")
//        }

        this.tvQuantity.text = quantity

        this.handler = Handler()
        this.handler.postDelayed({
            goToLevel(quantity!!)
        }, this.splashTime)
    }


    fun goToLevel(difficulty : String) {
        var it : Intent = Intent()

        if(difficulty == "2") {
            it = Intent(this, FourFiveActivity::class.java)
        }else if(difficulty == "3") {
            it = Intent(this, FiveSixActivity::class.java)
        }else if(difficulty == "4") {
            it = Intent(this, FiveEightActivity::class.java)
        }

        startActivity(it)
        finish()
    }
}
