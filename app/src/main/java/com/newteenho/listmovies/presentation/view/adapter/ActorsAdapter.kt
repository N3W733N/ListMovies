package com.newteenho.listmovies.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.newteenho.listmovies.R
import com.newteenho.listmovies.data.API_IMG_URL
import com.newteenho.listmovies.data.model.Actors
import kotlinx.android.synthetic.main.item_elenco.view.*

class ActorsAdapter(
    private val actorsList: List<Actors>
) : RecyclerView.Adapter<ActorsAdapter.DetailsViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailsViewHolder {
        val itemViews =
            LayoutInflater.from(parent.context).inflate(R.layout.item_elenco, parent, false)
        return DetailsViewHolder(itemViews)
    }

    override fun getItemCount(): Int {
        return actorsList.size
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bind(actorsList[position])
    }

    class DetailsViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val actorPost = itemView.roundedImageViewActor
        private val actorName = itemView.textActorName
        private val actorRole = itemView.textActorRole


        fun bind(actor: Actors) {
            Glide
                .with(itemView)
                .load(API_IMG_URL + actor.profile_path)
                .centerCrop()
                .into(actorPost)

            actorName.text = actor.name
            actorRole.text = actor.character
        }
    }
}