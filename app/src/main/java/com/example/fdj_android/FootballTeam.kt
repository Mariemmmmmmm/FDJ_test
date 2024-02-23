import android.os.Parcel
import android.os.Parcelable

data class FootballTeam(
    val idTeam: String?,
    val idLeague: String?,
    val strTeamBadge: String?,
    val strTeam: String?,
    val strAlternate: String?,
    val strSport: String?,
    val strLeague: String?,
    val strStadium: String?,
    val strTeamFanart1: String?,
    val strTeamFanart2: String?,
    val strTeamFanart3: String?,
    val strTeamFanart4: String?,
    val strDescriptionFR: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idTeam)
        parcel.writeString(idLeague)
        parcel.writeString(strTeamBadge)
        parcel.writeString(strTeam)
        parcel.writeString(strAlternate)
        parcel.writeString(strSport)
        parcel.writeString(strLeague)
        parcel.writeString(strStadium)
        parcel.writeString(strTeamFanart1)
        parcel.writeString(strTeamFanart2)
        parcel.writeString(strTeamFanart3)
        parcel.writeString(strTeamFanart4)
        parcel.writeString(strDescriptionFR)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FootballTeam> {
        override fun createFromParcel(parcel: Parcel): FootballTeam {
            return FootballTeam(parcel)
        }

        override fun newArray(size: Int): Array<FootballTeam?> {
            return arrayOfNulls(size)
        }
    }
}
