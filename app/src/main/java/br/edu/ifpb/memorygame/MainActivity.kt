package br.edu.ifpb.memorygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TextView
import br.edu.ifpb.memorygame.network.Api
import br.edu.ifpb.memorygame.retrofit_model.Products
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var btGoTo45 : Button
    private lateinit var btGoTo56 : Button
    private lateinit var btGoTo58 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var productsArr = intent.getBundleExtra("productArr") as HashMap<Long, String>
//        var productList = intent.extras?.get("productArr") as HashMap<Long, String>
//        var randomList : MutableList<Long> = mutableListOf()
//
//        for(product in productList) {
//            randomList.add(product.key)
//        }
//
//        randomList.shuffle()

        this.btGoTo45 = findViewById(R.id.bt_main_goto45)
        this.btGoTo56 = findViewById(R.id.bt_main_goto56)
        this.btGoTo58 = findViewById(R.id.bt_main_goto58)

        this.btGoTo45.setOnClickListener{
            val it = Intent(this, InstructionActivity::class.java)
            it.putExtra("escolha", "2")
//            it.putExtra("productArr", productList)
            startActivity(it)
//            finish()
        }

        this.btGoTo56.setOnClickListener{
            val it = Intent(this, InstructionActivity::class.java)
            it.putExtra("escolha", "3")
//            it.putExtra("productArr", productList)
            startActivity(it)
//            finish()
        }

        this.btGoTo58.setOnClickListener{
            val it = Intent(this, InstructionActivity::class.java)
            it.putExtra("escolha", "4")
//            it.putExtra("productArr", productList)
            startActivity(it)
//            finish()
        }

    }
}
