package com.ravinada.sps.domain.local

import com.ravinada.sps.domain.TvShowsDetailDomain

fun TvShowsDetailDomain.toFavoriteTvShowsEntity(): FavoriteTvShowsEntity {
    return FavoriteTvShowsEntity(
        id = this.id ?: 0,
        poster_path = this.posterPath ?: "",
        overview = this.overview ?: "",
        title = this.originalName ?: "",
        vote_average = (this.voteAverage ?: 0f).toFloat(),
        release_date = this.firstAirDate ?: ""
    )
}
