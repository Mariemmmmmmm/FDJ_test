import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.example.fdj_android.ApiServiceFactory
import com.example.fdj_android.FootballLeague
import com.example.fdj_android.FootballTeam
import kotlinx.coroutines.launch

class FootballViewModel() : ViewModel() {
    private val apiService = ApiServiceFactory.create()
    private val footballRepository = FootballRepository(apiService)



    private val _leagues = MutableLiveData<List<FootballLeague>>()
    val leagues: LiveData<List<FootballLeague>> = _leagues

    private val _filteredLeagues = MutableLiveData<List<FootballLeague>>()

    private val _teams = MutableLiveData<List<FootballTeam>>()
    val teams: LiveData<List<FootballTeam>> = _teams


    fun getAllLeagues() {
        viewModelScope.launch {
            val leagues = footballRepository.getAllLeagues()
            _leagues.postValue(leagues)
        }
    }

    fun searchAllTeams(leagueName: String?) {
        viewModelScope.launch {
            val teams = footballRepository.searchAllTeams(leagueName)
            _teams.postValue(teams)
        }
    }

    fun filterLeagues(query: String?) {
        val allLeagues = _leagues.value ?: emptyList()
        if (!query.isNullOrEmpty()) {
            val filteredList = allLeagues.filter { league ->
                league.strLeague.contains(query, ignoreCase = true)
            }
            _filteredLeagues.value = filteredList
        } else {
            _filteredLeagues.value = allLeagues
        }
    }
}
