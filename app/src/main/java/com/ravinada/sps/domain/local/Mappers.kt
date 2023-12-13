package com.ravinada.sps.domain.local

import com.ravinada.sps.domain.TvShowsDetailDomain

fun TvShowsDetailDomain.toFavoriteTvShowsEntity(): FavoriteTvShowsEntity {
    return FavoriteTvShowsEntity(
        id = this.id ?: 0,
        poster_path = this.poster_path ?: "",
        overview = this.overview ?: "",
        title = this.original_name ?: "",
        vote_average = (this.vote_average ?: 0f).toFloat(),
        release_date = this.first_air_date ?: ""
    )
}
