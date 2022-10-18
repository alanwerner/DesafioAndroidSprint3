package com.example.catalagofilmes.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.catalagofilmes.R
import com.example.catalagofilmes.database.AppDatabase
import com.example.catalagofilmes.database.dao.FilmeDao
import com.example.catalagofilmes.model.Filme
import com.example.catalagofilmes.repository.Repository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class FilmeActivity : AppCompatActivity(R.layout.activity_filme) {

    private lateinit var dao : FilmeDao
    private val repository by lazy {
        Repository(dao)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = AppDatabase.getInstance(this@FilmeActivity).filmeDao()
        val filme = intent.getSerializableExtra("Filme") as Filme

        val imagem = findViewById<ImageView>(R.id.activity_filme_imagem)
        val titulo = findViewById<TextView>(R.id.activity_filme_titulo)
        val lancamento = findViewById<TextView>(R.id.activity_filme_lancamento)
        val explicit = findViewById<ImageView>(R.id.activity_filme_explicit)
        val sinapse = findViewById<TextView>(R.id.activity_filme_sinapse)
        val fabFavoritos = findViewById<FloatingActionButton>(R.id.activity_filme_fab_favoritos)
        fabFavoritos.setOnClickListener(View.OnClickListener {
            lifecycleScope.launch {
                repository.salva(filme)
            }
        })



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