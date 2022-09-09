package com.example.catalagofilmes.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catalagofilmes.R
import com.example.catalagofilmes.model.Filme

class ListaFilmesAdapter(
    private val context: Context,
    filmes: List<Filme> = emptyList()
) : RecyclerView.Adapter<ListaFilmesAdapter.ViewHolder>(){

    private val filmes = filmes.toMutableList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var filme: Filme

        fun vincula(filme: Filme) {
            this.filme = filme
            val imagemFilme = itemView.findViewById<TextView>(R.id.nota_item_imagem)
            imagemFilme.visibility = View.GONE

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.filme_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filme = filmes[position]
        holder.vincula(filme)
    }

    override fun getItemCount(): Int = filmes.size

    fun atualiza(notas: List<Filme>) {
        notifyItemRangeRemoved(0, this.filmes.size)
        this.filmes.clear()
        this.filmes.addAll(notas)
        notifyItemInserted(this.filmes.size)
    }
}