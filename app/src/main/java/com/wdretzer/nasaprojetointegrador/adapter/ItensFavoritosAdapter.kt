package com.wdretzer.nasaprojetointegrador.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.data.NasaItens

class ItensFavoritosAdapter (private val favoritos: MutableList<NasaItens>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavouriteViewHolder(
            inflater.inflate(R.layout.item_fav_img, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FavouriteViewHolder -> holder.bind(favoritos[position])
        }
    }

    override fun getItemCount(): Int = favoritos.size
}

class FavouriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imagemFav: ImageView= view.findViewById(R.id.planeta_item)
    private val textFav: TextView = view.findViewById(R.id.img_number)

    private val favourite: ImageButton = view.findViewById(R.id.btn_fav)

    fun bind(itensFav: NasaItens) {

        textFav.text = itensFav.items.first().data.first().title
        Glide.with(imagemFav.context).load(itensFav.items.first().links.first().href).into(imagemFav)
        favourite.setImageResource(if (itensFav.isFavourite) R.drawable.icon_heart else R.drawable.heart)
    }
}