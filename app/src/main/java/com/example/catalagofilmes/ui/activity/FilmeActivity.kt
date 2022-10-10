package com.example.catalagofilmes.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.catalagofilmes.R
import com.example.catalagofilmes.model.Filme

class FilmeActivity : AppCompatActivity(R.layout.activity_filme) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val filme = intent.getSerializableExtra("Filme") as Filme

        val imagem = findViewById<ImageView>(R.id.activity_filme_imagem)
        val titulo = findViewById<TextView>(R.id.activity_filme_titulo)
        val lancamento = findViewById<TextView>(R.id.activity_filme_lancamento)
        val explicit = findViewById<ImageView>(R.id.activity_filme_explicit)
        val sinapse = findViewById<TextView>(R.id.activity_filme_sinapse)

        if (filme != null) {
            if (filme.backdrop_path != "") {
                Glide.with(imagem).load("https://image.tmdb.org/t/p/w500${filme.backdrop_path}")
                    .into(imagem)
            } else {
                imagem.visibility = View.GONE
            }

            if (filme.title != "") {
                titulo.text = filme.title
            } else {
                titulo.visibility = View.GONE
            }

            if (filme.release_date != "") {
                lancamento.text = filme.release_date
            } else {
                lancamento.visibility = View.GONE
            }

            if (filme.adult == true) {
                explicit.visibility = View.VISIBLE
            }

            if (filme.overview != "") {
                sinapse.text = filme.overview
            } else {
                sinapse.visibility = View.GONE
            }
        }
    }
}