package com.example.fdj_android

import LeagueAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class LeagueFragment :  Fragment(){
    private lateinit var viewModel: FootballViewModel
    private lateinit var adapter: LeagueAdapter
    companion object {
        fun newInstance(): LeagueFragment {
            return LeagueFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_league, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[FootballViewModel::class.java]

        adapter = LeagueAdapter()
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getAllLeagues()

        viewModel.teams.observe(viewLifecycleOwner, Observer { teams ->
            adapter.submitList(teams)
        })

        val autoCompleteTextView: AutoCompleteTextView = view.findViewById(R.id.searchView)
        autoCompleteTextView.threshold = 1

        viewModel.leagues.observe(viewLifecycleOwner, Observer { leagues ->
            val leagueNames = leagues.map { it.strLeague }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, leagueNames)
            autoCompleteTextView.setAdapter(adapter)
        })

        autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            val selectedLeagueName = autoCompleteTextView.adapter.getItem(position) as String
            viewModel.searchAllTeams(selectedLeagueName)
            viewModel.filterLeagues(selectedLeagueName)
        }
    }

}