package com.example.materibangkitrecyclerview

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_DRIVER = "key_driver"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val btnShare: Button = findViewById(R.id.action_share)
        btnShare.setOnClickListener(this)
        val tvDetailName: TextView = findViewById(R.id.tv_detail_name)
        val tvDetailDecs: TextView = findViewById(R.id.tv_detail_desc)
        val imgDetailPhoto: ImageView = findViewById(R.id.img_detail_photo)

        val driver = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DRIVER, Driver::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DRIVER)
        }

        if (driver != null) {
            val photo = driver.photo
            if (photo != null) {
                imgDetailPhoto.setImageResource(photo)
            }

            val name = "${driver.name}"
            tvDetailName.text = name

            val text = "${driver.description.toString()}"
            tvDetailDecs.text = text
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.action_share -> {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Sharing Link")
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://instagram.com/f1?igshid=YmMyMTA2M2Y=")
                startActivity(Intent.createChooser(shareIntent, "Share Link"))
            }
        }
    }
}

