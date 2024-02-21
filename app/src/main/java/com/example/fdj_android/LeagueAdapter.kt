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
import com.example.fdj_android.FootballTeam

class LeagueAdapter : ListAdapter<FootballTeam, LeagueAdapter.LeagueViewHolder>(LeagueDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.league_item, parent, false)
        return LeagueViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        val team = getItem(position)
        holder.bind(team)
    }

    class LeagueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val leagueNameTextView: TextView = itemView.findViewById(R.id.textLeague)
        private val leagueImageView: ImageView = itemView.findViewById(R.id.imagePlayer)

        fun bind(team: FootballTeam) {
            leagueNameTextView.text = team.strTeam
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
