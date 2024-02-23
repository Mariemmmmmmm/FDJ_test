package com.example.fdj_android

import FootballTeam
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class TeamDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_team_details, container, false)
        val imageBanner: ImageView = view.findViewById(R.id.imageBanner)
        val textTeamName: TextView = view.findViewById(R.id.textTeamName)
        val textDescription: TextView = view.findViewById(R.id.textDescription)
        val strTeamFanart1: ImageView = view.findViewById(R.id.imageView1)
        val strTeamFanart2: ImageView = view.findViewById(R.id.imageView2)
        val strTeamFanart3: ImageView = view.findViewById(R.id.imageView3)
        val strTeamFanart4: ImageView = view.findViewById(R.id.imageView4)





        val footballTeam = arguments?.getParcelable<FootballTeam>(ARG_FOOTBALL_TEAM)

        footballTeam?.let {
            Glide.with(requireContext())
                .load(it.strTeamBadge)
                .into(imageBanner)

            textTeamName.text = it.strTeam
            textDescription.text = it.strDescriptionFR ?: ""

            Glide.with(requireContext())
                .load(it.strTeamFanart1)
                .into(strTeamFanart1)

            Glide.with(requireContext())
                .load(it.strTeamFanart2)
                .into(strTeamFanart2)

            Glide.with(requireContext())
                .load(it.strTeamFanart3)
                .into(strTeamFanart3)

            Glide.with(requireContext())
                .load(it.strTeamFanart4)
                .into(strTeamFanart4)

        }

        val backButton: ImageButton = view.findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val LeagueFragment = LeagueFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, LeagueFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return view }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    companion object {
        private const val ARG_FOOTBALL_TEAM = "footballTeam"

        fun newInstance(footballTeam: FootballTeam): TeamDetailsFragment {
            val fragment = TeamDetailsFragment()
            val args = Bundle()
            args.putParcelable(ARG_FOOTBALL_TEAM, footballTeam)
            fragment.arguments = args
            return fragment
        }
    }
}