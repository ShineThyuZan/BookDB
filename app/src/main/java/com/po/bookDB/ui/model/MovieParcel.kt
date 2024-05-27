package klt.mdy.offlinesupportwithpaging.model.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieParcel(
    val movieId: Int = -1,
    val originalTitle: String = "",
    val movieTitle: String = "",
    val coverUrl: String? = null,
    val posterUrl: String? = null,
    val overview: String? = null,
    val language: String = "",
    val releasedDate: String = "",
    val popularity: Double = -0.0,
    val averageVote: Double = -0.0,
    val totalVote: Double = -0.0,
) : Parcelable