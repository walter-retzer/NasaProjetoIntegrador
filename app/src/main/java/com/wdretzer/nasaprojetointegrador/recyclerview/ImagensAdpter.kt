package com.wdretzer.nasaprojetointegrador.recyclerview

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


class ImagensAdpter(
    private val action: (NasaItens) -> Unit,
    private val detailAction: (NasaItens) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffUtil = AsyncListDiffer<NasaItens>(this, DIFF_UTIL)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ImagensViewHolder(
            inflater.inflate(R.layout.item_planeta_imagem, parent, false), detailAction, action
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ImagensViewHolder -> holder.bind(diffUtil.currentList[position])
        }
    }

    override fun getItemCount(): Int = diffUtil.currentList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun updateItem(item: NasaItens) {
        val newList =
            diffUtil.currentList.map { nasa ->
                if (nasa.href == item.href) item
                else nasa
            }
        diffUtil.submitList(newList)
    }

    fun updateList(newItens: List<NasaItens>) {
        diffUtil.submitList(diffUtil.currentList.plus(newItens))
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<NasaItens>() {
            override fun areItemsTheSame(oldItem: NasaItens, newItem: NasaItens): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: NasaItens, newItem: NasaItens): Boolean {
                return oldItem.links == newItem.links
            }
        }
    }
}


class ImagensViewHolder(
    view: View,
    detailAction: (NasaItens) -> Unit,
    private val action: (NasaItens) -> Unit
) : RecyclerView.ViewHolder(view) {
    var imagemPlanetas: ImageView = view.findViewById(R.id.planeta_item)
    var imagemNumber: TextView = view.findViewById(R.id.img_number)
    private val favourite: ImageButton = view.findViewById<ImageButton>(R.id.btn_fav)
    private var itemCorrente: NasaItens? = null

    init {
        view.setOnClickListener {
            itemCorrente?.let {
                detailAction.invoke(it)
            }
        }
    }

    fun bind(item: NasaItens) {

        imagemNumber.text = "Imagem ${(itemViewType + 1)}"
        favourite.setImageResource(if (item.isFavourite) R.drawable.icon_heart_fav else R.drawable.icon_heart)

        favourite.setOnClickListener {

            if (item.isFavourite) {
                Toast.makeText(imagemPlanetas.context, "Item Desavoritado!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(imagemPlanetas.context, "Item Favoritado!", Toast.LENGTH_SHORT)
                    .show()
            }
            action.invoke(item)
        }

        Glide.with(imagemPlanetas.context)
            .load(item.links.first().href)
            .placeholder(R.drawable.astronauta_inicio)
            .error(R.drawable.icon_error)
            .into(imagemPlanetas)

        itemCorrente = item
        item.links.first().href.let {
            Glide.with(imagemPlanetas.context)
                .load(it)
                .into(imagemPlanetas)
        }
    }
}

