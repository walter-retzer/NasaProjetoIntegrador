package com.wdretzer.nasaprojetointegrador.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wdretzer.nasaprojetointegrador.R
import com.wdretzer.nasaprojetointegrador.model.Dados

class ImagensAdpter(private val list: MutableList<Dados>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ImagensViewHolder(
            inflater.inflate(R.layout.item_planeta_imagem, parent, false)
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ImagensViewHolder -> holder.bind(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

}

class ImagensViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var imagemPlanetas: ImageView = view.findViewById(R.id.planeta_item)

    fun bind(item: Dados) {

        Glide.with(imagemPlanetas.context)
            .load(item.image)
            .into(imagemPlanetas)
    }
}
