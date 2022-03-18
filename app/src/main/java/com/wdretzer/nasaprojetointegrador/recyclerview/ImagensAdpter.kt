package com.wdretzer.nasaprojetointegrador.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.data.DataItens

class ImagensAdpter(
    private val list: List<DataItens>,
    private val detailAction: (DataItens) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ImagensViewHolder(
            inflater.inflate(R.layout.item_planeta_imagem, parent, false), detailAction
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ImagensViewHolder -> holder.bind(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

}

class ImagensViewHolder(view: View, detailAction: (DataItens) -> Unit) : RecyclerView.ViewHolder(view) {
    var imagemPlanetas: ImageView = view.findViewById(R.id.planeta_item)
    private var itemCorrente: DataItens? = null

    init {
        view.setOnClickListener {
            itemCorrente?.let {
                detailAction.invoke(it)
            }
        }
    }

    fun bind(item: DataItens) {

        Glide.with(imagemPlanetas.context)
            .load(item.links.first().href)
            .into(imagemPlanetas)

        itemCorrente = item

        item.links.first().href?.let {
            Glide.with(imagemPlanetas.context)
                .load(it)
                .into(imagemPlanetas) }
    }

}
