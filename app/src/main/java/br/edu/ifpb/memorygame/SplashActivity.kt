package br.edu.ifpb.memorygame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageView
import br.edu.ifpb.memorygame.network.Api
import br.edu.ifpb.memorygame.retrofit_model.Product
import br.edu.ifpb.memorygame.retrofit_model.Products
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SplashActivity : AppCompatActivity() {
    private lateinit var handler : Handler
    private var splashTime : Long = 1500
    private lateinit var ivLogo : ImageView
    private lateinit var ivBrain : ImageView
    private lateinit var productsArr : MutableList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val anim1 = AnimationUtils.loadAnimation(this, R.anim.anim1)
        this.ivLogo = findViewById(R.id.ivSplashLogo)
        this.ivBrain = findViewById(R.id.ivSplashLogoCerebro)

        this.ivLogo.startAnimation(anim1)
        this.ivBrain.startAnimation(anim1)

        this.handler = Handler()
        this.handler.postDelayed({
            goToMainActivity()
        }, this.splashTime)

    }

    fun goToMainActivity() {
        val it = Intent(this, MainActivity::class.java)
        startActivity(it)
        finish()
    }
}
