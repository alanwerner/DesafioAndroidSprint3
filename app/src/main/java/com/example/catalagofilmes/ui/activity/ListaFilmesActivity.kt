package com.example.catalagofilmes.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.catalagofilmes.R
import com.example.catalagofilmes.model.Filme
import com.example.catalagofilmes.ui.adapter.ListaFilmesAdapter
import com.example.catalagofilmes.webclient.RetrofitInicializador
import com.example.catalagofilmes.webclient.model.FilmeResposta
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaFilmesActivity : AppCompatActivity() {

    private val adapter by lazy {
        ListaFilmesAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_filmes)
        val recyclerView = findViewById<RecyclerView>(R.id.activity_lista_filmes_recyclerView)
        recyclerView.adapter = adapter

//        lifecycleScope.launch {
//            val listaResposta = RetrofitInicializador().filmesService
//                .buscaTodosCoroutines()
//            val filmes = listaResposta.body()?.map { filmesResposta ->
//                filmesResposta.filme
//            }
//            Log.i("ListaFilmes", "onCreate: $filmes")
//        }


    }
}