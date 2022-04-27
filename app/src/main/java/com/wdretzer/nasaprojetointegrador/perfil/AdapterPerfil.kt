package com.wdretzer.nasaprojetointegrador.perfil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.wdretzer.nasaprojetointegrador.R

class AdapterPerfil(
    private val listaPerfil: MutableList<DadosPerfil>,
    private val action: (DadosPerfil) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PerfilViewHolder(
            inflater.inflate(R.layout.item_perfil, parent, false), action
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PerfilViewHolder -> holder.bind(listaPerfil[position])
        }
    }

    override fun getItemCount(): Int = listaPerfil.size


    class PerfilViewHolder(view: View, private val action: (DadosPerfil) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val photoPerfil: ShapeableImageView = view.findViewById(R.id.photoPerfil)

        fun bind(item: DadosPerfil) {
            photoPerfil.setImageResource(item.imagemPerfil)

            photoPerfil.setOnClickListener {
                item.stateImg = !item.stateImg
                //photoPerfil.setStrokeColorResource(if (item.stateImg) R.color.blue else R.color.cinza_l )
                action.invoke(item)
            }
        }
    }
}
