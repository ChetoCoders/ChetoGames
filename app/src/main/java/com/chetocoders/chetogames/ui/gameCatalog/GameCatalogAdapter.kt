import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chetocoders.chetogames.R
import com.chetocoders.chetogames.databinding.CardViewItemGameBinding
import com.chetocoders.chetogames.ui.basicDiffUtil
import com.chetocoders.chetogames.ui.inflate
import com.chetocoders.chetogames.ui.loadUrl
import com.chetocoders.domain.GameDetail

class GameCatalogAdapter(private val listener: (GameDetail) -> Unit) :
    RecyclerView.Adapter<GameCatalogAdapter.ViewHolder>() {

    var listGameDetail: List<GameDetail> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.card_view_item_game, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listGameDetail.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listGameDetail[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { listener(movie) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CardViewItemGameBinding.bind(view)
        fun bind(gameDetail: GameDetail) = with(binding) {
            gameTitle.text = gameDetail.title

            if(gameDetail.platforms.isNullOrEmpty()){
                gamePlatforms.text = "No platforms info"
            } else {
                gamePlatforms.text =   gameDetail.platforms?.joinToString("-"){ it.name.toString() }
            }

            if (gameDetail.cover != null) {
                gameImageView.loadUrl("https:" + gameDetail.cover?.url.toString())
            } else {
                gameImageView.setImageResource(R.drawable.no_image_avaible)
            }

        }
    }
}