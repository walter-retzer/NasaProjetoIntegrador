package com.wdretzer.nasaprojetointegrador.favoritos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.data.NasaItens


class ItensFavoritosAdapter(
    private val action: (NasaItens) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffUtil = AsyncListDiffer<NasaItens>(this, DIFF_UTIL)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FavouriteViewHolder(
            inflater.inflate(R.layout.item_fav_img, parent, false), action
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FavouriteViewHolder -> holder.bind(diffUtil.currentList[position])
        }
    }

    override fun getItemCount(): Int = diffUtil.currentList.size

    fun updateList(newItens: MutableList<NasaItens>) {
        diffUtil.submitList(diffUtil.currentList.plus(newItens))
    }

    fun updateItem(item: NasaItens) {
        val list = mutableListOf(item)
        diffUtil.submitList(diffUtil.currentList.minus(list))
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<NasaItens>() {
            override fun areItemsTheSame(oldItem: NasaItens, newItem: NasaItens): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: NasaItens, newItem: NasaItens): Boolean {
                return oldItem.links.first() == newItem.links.first()
            }
        }
    }
}


class FavouriteViewHolder(
    view: View,
    private val action: (NasaItens) -> Unit
) : RecyclerView.ViewHolder(view) {

    private val imagemFav: ImageView = view.findViewById(R.id.planeta_item)
    private val textFav: TextView = view.findViewById(R.id.img_number)
    private val favourite: ImageButton = view.findViewById(R.id.btn_fav)

    fun bind(itensFav: NasaItens) {

        textFav.text = itensFav.data.first().title

        Glide.with(imagemFav.context)
            .load(itensFav.links.first().href)
            .placeholder(R.drawable.new_star_background)
            .error(R.drawable.icon_error)
            .into(imagemFav)

        favourite.setImageResource(if (itensFav.isFavourite) R.drawable.icon_heart else R.drawable.icon_heart_fav)

        favourite.setOnClickListener {
            action.invoke(itensFav)
            Toast.makeText(favourite.context, "Item Desfavoritado!", Toast.LENGTH_SHORT).show()
            favourite.setImageResource(if (itensFav.isFavourite) R.drawable.icon_heart else R.drawable.icon_heart_fav)
        }
    }
}
