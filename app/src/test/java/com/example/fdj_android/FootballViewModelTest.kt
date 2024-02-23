import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.fdj_android.FootballLeague
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FootballViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: FootballViewModel

    @Mock
    private lateinit var repository: FootballRepository

    @Mock
    private lateinit var leaguesObserver: Observer<List<FootballLeague>>

    @Mock
    private lateinit var teamsObserver: Observer<List<FootballTeam>>

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = Mockito.mock(FootballRepository::class.java)

        viewModel = FootballViewModel(repository)

        viewModel.leagues.observeForever(leaguesObserver)
        viewModel.teams.observeForever(teamsObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testGetAllLeagues() {
        val fakeLeagues = listOf(
            FootballLeague("1", "League 1","Soccer","Championship"),
            FootballLeague("2", "League 2","Soccer","Serie A")
        )
        runBlocking {
            Mockito.`when`(repository.getAllLeagues()).thenReturn(fakeLeagues)
            viewModel.getAllLeagues()
            Mockito.verify(repository).getAllLeagues()
            Mockito.verify(leaguesObserver).onChanged(fakeLeagues)
        }
    }

    @Test
    fun testSearchAllTeams() {
        val leagueName = "Premier League"
        val fakeTeams = listOf(
            FootballTeam("1", "Team 1", "Badge 1","ee","Stade Brestois 29","Soccer","","","","","","",""),
            FootballTeam("2", "Team 2", "Badge 2","ee","Clermont Foot Auvergne 63","Soccer","","","","","","","")
        )
        runBlocking {
            Mockito.`when`(repository.searchAllTeams(leagueName)).thenReturn(fakeTeams)
            viewModel.searchAllTeams(leagueName)
            Mockito.verify(repository).searchAllTeams(leagueName)
            Mockito.verify(teamsObserver).onChanged(fakeTeams)
        }
    }
}