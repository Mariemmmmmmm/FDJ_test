import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fdj_android.R
import com.example.fdj_android.FootballLeague



class LeagueAdapter(private val onItemClick: (FootballTeam) -> Unit) : ListAdapter<FootballTeam, LeagueAdapter.LeagueViewHolder>(LeagueDiffCallback()) {

//class LeagueAdapter : ListAdapter<FootballTeam, LeagueAdapter.LeagueViewHolder>(LeagueDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.league_item, parent, false)
        return LeagueViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        val team = getItem(position)
        holder.bind(team)
        holder.itemView.setOnClickListener {
            onItemClick(team)
        }
    }

    class LeagueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val leagueImageView: ImageView = itemView.findViewById(R.id.imagePlayer)

        fun bind(team: FootballTeam) {
            Glide.with(itemView.context)
                .load(team.strTeamBadge)
                .into(leagueImageView)
        }
    }

    class LeagueDiffCallback : DiffUtil.ItemCallback<FootballTeam>() {
        override fun areItemsTheSame(oldItem: FootballTeam, newItem: FootballTeam): Boolean {
            return oldItem.idLeague == newItem.idLeague
        }

        override fun areContentsTheSame(oldItem: FootballTeam, newItem: FootballTeam): Boolean {
            return oldItem == newItem
        }
    }


}
