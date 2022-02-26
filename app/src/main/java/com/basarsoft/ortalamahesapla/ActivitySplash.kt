package com.basarsoft.ortalamahesapla

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class ActivitySplash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        var asagidanGelenButon = AnimationUtils.loadAnimation(this, R.anim.asagidangelenbuton)
        var yukaridanGelenBalon = AnimationUtils.loadAnimation(this, R.anim.yukaridangelenbalon)

        var asagiGidenButon = AnimationUtils.loadAnimation(this, R.anim.asagigidenbuton)
        var yukariGidenBalon = AnimationUtils.loadAnimation(this, R.anim.yukarigidenbalon)

        btnOrtalamaHesaplaAnim.animation = asagidanGelenButon
        imageViewBalon.animation = yukaridanGelenBalon

        btnOrtalamaHesaplaAnim.setOnClickListener {

            btnOrtalamaHesaplaAnim.startAnimation(asagiGidenButon)
            imageViewBalon.startAnimation(yukariGidenBalon)


            object : CountDownTimer(1500,1500) {
                override fun onTick(millisUntilFinished: Long) {
                    TODO("Not yet implemented")
                }

                override fun onFinish() {
                    var intent =
                        Intent(applicationContext, MainActivity::class.java) // javadayken MainActivity.class diyoruz

                    startActivity(intent)
                }

            }.start()





        }


    }
}