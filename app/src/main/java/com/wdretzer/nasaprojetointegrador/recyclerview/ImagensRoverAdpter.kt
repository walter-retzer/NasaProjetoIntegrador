package com.wdretzer.nasaprojetointegrador.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.data.RoverItens


class ImagensRoverAdpter(
//    private val action: (RoverItens) -> Unit,
//    private val detailAction: (RoverItens) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffUtil = AsyncListDiffer<RoverItens>(this, DIFF_UTIL)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ImagensRoversViewHolder(
            inflater.inflate(R.layout.item_planeta_imagem, parent, false)
            //inflater.inflate(R.layout.item_planeta_imagem, parent, false), detailAction, action
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
//        val newList =
//            diffUtil.currentList.map { nasa ->
//                if (nasa.href == item.href) item
//                else nasa
//            }
//        diffUtil.submitList(newList)
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
//    detailAction: (RoverItens) -> Unit,
//    private val action: (RoverItens) -> Unit
) : RecyclerView.ViewHolder(view) {

    var imagemPlanetas: ImageView = view.findViewById(R.id.planeta_item)
    var imagemNumber: TextView = view.findViewById(R.id.img_number)
    private val favourite: ImageButton = view.findViewById<ImageButton>(R.id.btn_fav)
    private var itemCorrente: RoverItens? = null

//    init {
//        view.setOnClickListener {
//            itemCorrente?.let {
//                detailAction.invoke(it)
//            }
//        }
//    }

    fun bind(item: RoverItens) {

        imagemNumber.text = "Imagem ${(itemViewType + 1)}"
//        favourite.setImageResource(if (item.isFavourite) R.drawable.icon_heart_fav else R.drawable.icon_heart)
//
//        favourite.setOnClickListener {
//
//            if (item.isFavourite) {
//                Toast.makeText(imagemPlanetas.context, "Item Desavoritado!", Toast.LENGTH_SHORT)
//                    .show()
//            } else {
//                Toast.makeText(imagemPlanetas.context, "Item Favoritado!", Toast.LENGTH_SHORT)
//                    .show()
//            }
//            action.invoke(item)
//        }

        Glide.with(imagemPlanetas.context)
            .load(item.img_src)
            .placeholder(R.drawable.simple_background)
            .error(R.drawable.icon_error)
            .into(imagemPlanetas)

        itemCorrente = item
        item.img_src.let {
            Glide.with(imagemPlanetas.context)
                .load(it)
                .placeholder(R.drawable.simple_background)
                .error(R.drawable.icon_error)
                .into(imagemPlanetas)
        }
    }
}
