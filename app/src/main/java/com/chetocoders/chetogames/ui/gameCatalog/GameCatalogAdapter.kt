package com.chetocoders.chetogames.ui.gameCatalog

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chetocoders.chetogames.databinding.CardViewItemGameBinding
import com.chetocoders.chetogames.ui.inflate
import kotlin.properties.Delegates

class GameCatalogAdapter(dataList: List<GameCatalogItem> = emptyList(), private val listener: (GameCatalogItem) -> Unit) :
    RecyclerView.Adapter<GameCatalogAdapter.ViewHolder>() {

    var dataList: List<GameCatalogItem> by Delegates.observable(dataList) { _, _, _ -> notifyDataSetChanged() }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CardViewItemGameBinding.bind(view)

        fun bind(gameCatalogItem : GameCatalogItem) {
            with(binding){
                //TODO() Crear string xml y remplazarlo
                gameText.text = gameCatalogItem.title +" "+ gameCatalogItem.platform
                //TODO() añiadir imagen del juego
            }

        }
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = viewGroup.inflate()
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val item = dataList[position]
        viewHolder.bind(item)
        viewHolder.itemView.setOnClickListener { listener(item) }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataList.size

}