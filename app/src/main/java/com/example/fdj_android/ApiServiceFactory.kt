import android.content.Context
import com.example.fdj_android.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceFactory {
    companion object {
        private lateinit var context: Context

        fun initialize(context: Context) {
            this.context = context
        }

        fun create(): FootballApiService {
            val idapi = context.getString(R.string.api_id)
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com/api/v1/json/$idapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(FootballApiService::class.java)
        }
    }
}
