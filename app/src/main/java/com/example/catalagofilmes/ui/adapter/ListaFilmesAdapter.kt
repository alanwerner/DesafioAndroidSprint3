package com.example.catalagofilmes.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catalagofilmes.R
import com.example.catalagofilmes.model.Filme

class ListaFilmesAdapter() : RecyclerView.Adapter<ListaFilmesAdapter.ViewHolder>() {

    private val filmes = arrayListOf<Filme>()
    lateinit var itemClickListener: (filme : Filme) -> Unit

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun vincula(filme: Filme) {
            itemView.rootView.setOnClickListener{
                itemClickListener.invoke(filme)
            }
            val imagem = itemView.findViewById<ImageView>(R.id.filme_item_imagem)
            Glide.with(imagem).load("https://image.tmdb.org/t/p/w500${filme.poster_path}").into(imagem)

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.filme_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filme = filmes[position]
        holder.vincula(filme)
    }

    override fun getItemCount(): Int = filmes.size

    fun atualiza(filmes: List<Filme>) {
        this.filmes.clear()
        this.filmes.addAll(filmes)
        notifyDataSetChanged()
    }
}