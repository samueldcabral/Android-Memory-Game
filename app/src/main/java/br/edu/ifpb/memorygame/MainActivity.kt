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

        this.btGoTo45 = findViewById(R.id.bt_main_goto45)
        this.btGoTo56 = findViewById(R.id.bt_main_goto56)
        this.btGoTo58 = findViewById(R.id.bt_main_goto58)

        this.btGoTo45.setOnClickListener{
            val it = Intent(this, FourFiveActivity::class.java)
            startActivity(it)
//            finish()
        }

        this.btGoTo56.setOnClickListener{
            val it = Intent(this, FiveSixActivity::class.java)
            startActivity(it)
//            finish()
        }

        this.btGoTo58.setOnClickListener{
            val it = Intent(this, FiveEightActivity::class.java)
            startActivity(it)
//            finish()
        }

    }
}
