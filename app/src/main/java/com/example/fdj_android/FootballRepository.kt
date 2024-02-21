
import com.example.fdj_android.FootballLeague
import com.example.fdj_android.FootballTeam

class FootballRepository(private val apiService: FootballApiService) {
    suspend fun getAllLeagues(): List<FootballLeague> {
        val res = apiService.getAllLeagues()
        val leagues = res.leagues

        return leagues
    }

    suspend fun searchAllTeams(leagueName: String?): List<FootballTeam> {
        val res = apiService.searchAllTeams(leagueName)
        val teams = res.teams
        return teams
    }
}
