package com.wdretzer.nasaprojetointegrador.favoritos

import android.annotation.SuppressLint
import android.os.Handler
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
import com.wdretzer.nasaprojetointegrador.data.FavouritesItens


class ItensFavoritosAdapter(
    private val action: (FavouritesItens) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val diffUtil = AsyncListDiffer<FavouritesItens>(this, DIFF_UTIL)

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

    fun updateList(newItens: MutableList<FavouritesItens>) {
        diffUtil.submitList(diffUtil.currentList.plus(newItens))
    }

    fun updateItem(item: FavouritesItens) {
        val list = mutableListOf(item)
        diffUtil.submitList(diffUtil.currentList.minus(list))
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<FavouritesItens>() {
            override fun areItemsTheSame(
                oldItem: FavouritesItens,
                newItem: FavouritesItens
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: FavouritesItens,
                newItem: FavouritesItens
            ): Boolean {
                return oldItem.img == newItem.img
            }
        }
    }
}


class FavouriteViewHolder(
    view: View,
    private val action: (FavouritesItens) -> Unit
) : RecyclerView.ViewHolder(view) {

    private val imagemFav: ImageView = view.findViewById(R.id.planeta_item)
    private val textFav: TextView = view.findViewById(R.id.img_number)
    private val favourite: ImageButton = view.findViewById(R.id.btn_fav)

    @SuppressLint("SetTextI18n")
    fun bind(itensFav: FavouritesItens) {

        textFav.text = itensFav.title

        Glide.with(imagemFav.context)
            .load(itensFav.img)
            .placeholder(R.drawable.new_star_background)
            .error(R.drawable.icon_error)
            .into(imagemFav)

        favourite.setImageResource(R.drawable.icon_heart_fav)

        favourite.setOnClickListener {
            favourite.setImageResource(R.drawable.icon_heart)
            Toast.makeText(favourite.context, "Item Desfavoritado!", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({
                action.invoke(itensFav)
            }, 500)
        }
    }
}
