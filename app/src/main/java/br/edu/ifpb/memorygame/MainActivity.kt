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
    private lateinit var btGoTo4by5 : Button
    private lateinit var btGoTo5by6 : Button
    private lateinit var btGoTo5by8 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btGoTo4by5 = findViewById(R.id.bt_main_goto45)
        this.btGoTo5by6 = findViewById(R.id.bt_main_goto56)
        this.btGoTo5by8 = findViewById(R.id.bt_main_goto58)

        this.btGoTo4by5.setOnClickListener{
            val it = Intent(this, InstructionActivity::class.java)
            it.putExtra("escolha", "2")
            startActivity(it)
        }

        this.btGoTo5by6.setOnClickListener{
            val it = Intent(this, InstructionActivity::class.java)
            it.putExtra("escolha", "3")
            startActivity(it)
        }

        this.btGoTo5by8.setOnClickListener{
            val it = Intent(this, InstructionActivity::class.java)
            it.putExtra("escolha", "4")
            startActivity(it)
        }
    }
}
