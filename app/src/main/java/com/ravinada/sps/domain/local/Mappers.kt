package com.ravinada.sps.domain.local

import com.ravinada.sps.domain.MovieDetailDomain

fun MovieDetailDomain.toFavoriteMoviesEntity(): FavoriteTvShowsEntity {
    return FavoriteTvShowsEntity(
        id = this.id ?: 0,
        poster_path = this.poster_path ?: "",
        overview = this.overview ?: "",
        title = this.title ?: "",
        vote_average = (this.vote_average ?: 0f).toFloat(),
        release_date = this.release_date ?: ""
    )
}
