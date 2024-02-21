import com.example.fdj_android.FootballLeague
import com.example.fdj_android.FootballTeam
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApiService {
    @GET("all_leagues.php")
    suspend fun getAllLeagues(): FootballLeagueResponse

    @GET("search_all_teams.php")
    suspend fun searchAllTeams(@Query("l") leagueName: String?): FootballTeamsResponse
}

data class FootballLeagueResponse(
    val leagues: List<FootballLeague>
)

data class FootballTeamsResponse(
    val teams: List<FootballTeam>
)