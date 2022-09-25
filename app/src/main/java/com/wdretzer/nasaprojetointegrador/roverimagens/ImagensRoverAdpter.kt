package com.wdretzer.nasaprojetointegrador.roverimagens

import android.annotation.SuppressLint
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
import com.wdretzer.nasaprojetointegrador.data.RoverItens


class ImagensRoverAdpter(
    private val action: (RoverItens) -> Unit,
    private val detailAction: (RoverItens) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffUtil = AsyncListDiffer<RoverItens>(this, DIFF_UTIL)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ImagensRoversViewHolder(
            inflater.inflate(R.layout.item_planeta_imagem, parent, false), detailAction, action
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ImagensRoversViewHolder -> holder.bind(diffUtil.currentList[position])
        }
    }

    override fun getItemCount(): Int = diffUtil.currentList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun updateItem(item: RoverItens) {
        val newList =
            diffUtil.currentList.map { rover ->
                if (rover.imgRover == item.imgRover) item
                else rover
            }
        diffUtil.submitList(newList)
    }

    fun updateList(newItens: List<RoverItens>) {
        diffUtil.submitList(diffUtil.currentList.plus(newItens))
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<RoverItens>() {
            override fun areItemsTheSame(oldItem: RoverItens, newItem: RoverItens): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: RoverItens, newItem: RoverItens): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}


class ImagensRoversViewHolder(
    view: View,
    private val action: (RoverItens) -> Unit,
    private val detailAction: (RoverItens) -> Unit
) : RecyclerView.ViewHolder(view) {

    var imagemPlanetas: ImageView = view.findViewById(R.id.planeta_item)
    var imagemNumber: TextView = view.findViewById(R.id.img_number)
    private val favourite: ImageButton = view.findViewById(R.id.btn_fav)
    private var itemCorrente: RoverItens? = null

    init {
        view.setOnClickListener {
            itemCorrente?.let {
                action.invoke(it)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: RoverItens) {

        imagemNumber.text = "Imagem ${(itemViewType + 1)}"
        favourite.setImageResource(if (item.isFavouriteRoverImg) R.drawable.icon_heart_fav else R.drawable.icon_heart)

        favourite.setOnClickListener {
            if (item.isFavouriteRoverImg) {
                Toast.makeText(imagemPlanetas.context, "Item Desavoritado!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(imagemPlanetas.context, "Item Favoritado!", Toast.LENGTH_SHORT)
                    .show()
            }
            detailAction.invoke(item)

        }

        Glide.with(imagemPlanetas.context)
            .load(item.imgRover)
            .placeholder(R.drawable.simple_background)
            .error(R.drawable.icon_error)
            .into(imagemPlanetas)

        itemCorrente = item
        item.imgRover.let {
            Glide.with(imagemPlanetas.context)
                .load(it)
                .placeholder(R.drawable.simple_background)
                .error(R.drawable.icon_error)
                .into(imagemPlanetas)
        }
    }
}
