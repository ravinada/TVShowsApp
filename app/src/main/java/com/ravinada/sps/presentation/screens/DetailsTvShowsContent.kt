package com.ravinada.sps.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ravinada.sps.R
import com.ravinada.sps.domain.GenreDomain
import com.ravinada.sps.domain.TvShowsDomain
import com.ravinada.sps.presentation.composables.SimilarTvShowItem
import com.ravinada.sps.ui.theme.Green40

@Composable
fun DetailsTvShowContent(
    onClickBack: () -> Unit,
    onClickFavorite: () -> Unit,
    title: String,
    description: String,
    imageBackdrop: String,
    imagePoster: String,
    genres: List<GenreDomain>,
    releaseDate: String,
    voteAverage: String,
    runtime: String,
    isFavoriteTvShow: Boolean,
    similarTvShowsList: List<TvShowsDomain>
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically

        ) {

            Icon(
                modifier = Modifier.clickable {
                    onClickBack()
                },
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = null,
                tint = Color.Black,
            )
            //title
            Text(
                text = "Details",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                fontFamily = FontFamily(Font(R.font.googlesans_regular, FontWeight.Normal)),
                modifier = Modifier.padding(start = 24.dp)
            )

            Icon(
                modifier = Modifier.clickable {
                    onClickFavorite()
                },
                painter = painterResource(id = if (isFavoriteTvShow) R.drawable.ic_love else R.drawable.ic_love_border),
                contentDescription = null,
                tint = Color.Black,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomEnd = 20.dp,
                    bottomStart = 20.dp
                ),
            ) {
                Box {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(210.dp),
                        model = imageBackdrop,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                    )

                    androidx.compose.material3.Card(
                        modifier = Modifier
                            .offset(x = 310.dp, y = 178.dp)
                            .width(54.dp)
                            .height(24.dp)
                            .background(
                                color = Color(0x52252836),
                                shape = RoundedCornerShape(size = 8.dp)
                            )
                            .padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        ),
                    ) {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_favorite),
                                contentDescription = null,
                                tint = Green40,
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = voteAverage,
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.googlesans_regular)),
                                fontWeight = FontWeight(600),
                                color = Green40
                            )
                        }
                    }
                }
            }

            androidx.compose.material3.Card(
                modifier = Modifier
                    .offset(x = 29.dp, y = 150.dp)
                    .width(95.dp)
                    .height(120.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Gray
                ),
                shape = RoundedCornerShape(16.dp),
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp),
                    model = imagePoster,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                )
            }

            //Title
            Text(
                modifier = Modifier
                    .width(210.dp)
                    .height(48.dp)
                    .offset(x = 140.dp, y = 220.dp),
                text = title,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.googlesans_regular, FontWeight.Normal)),
                fontWeight = FontWeight(600),
            )

        }

        Spacer(modifier = Modifier.height(75.dp))

        HorizontalThreeOptions(
            yearRelease = releaseDate,
            duration = runtime,
            genre = if (genres.firstOrNull() == null) "" else genres.firstOrNull()?.name.toString()
        )

        Spacer(modifier = Modifier.height(24.dp))

        //Description Title
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            text = "Description",
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.googlesans_regular, FontWeight.Normal)),
            fontWeight = FontWeight(600),
        )

        Spacer(modifier = Modifier.height(12.dp))

        //Description
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            text = description,
            textAlign = TextAlign.Justify,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.googlesans_regular, FontWeight.Normal)),
            fontWeight = FontWeight(400),
        )

        Spacer(modifier = Modifier.height(24.dp))

        val listGenres = genres.map { it.name }.joinToString(separator = " * ")

        //Genres Action * Horror * Comedy
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            text = listGenres,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.googlesans_regular, FontWeight.Normal)),
            fontWeight = FontWeight(600),
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            text = "Similar shows",
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.googlesans_regular, FontWeight.Normal)),
            fontWeight = FontWeight(600),
        )

        Spacer(modifier = Modifier.height(4.dp))

        LazyRow(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Start,
            content = {
                items(similarTvShowsList) {
                    SimilarTvShowItem(
                        imageUrl = it.posterPath,
                    )
                }
            }
        )
    }
}

@Composable
fun HorizontalThreeOptions(
    yearRelease: String,
    duration: String,
    genre: String,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.Center,
    ) {

        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(id = R.drawable.ic_calendar),
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(4.dp))

        //Year
        Text(
            text = yearRelease,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            fontFamily = FontFamily(Font(R.font.googlesans_regular, FontWeight.Normal)),
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_line),
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_clock),
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(4.dp))

        //Duration
        Text(
            text = duration,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            fontFamily = FontFamily(Font(R.font.googlesans_regular, FontWeight.Normal)),
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_line),
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_ticket),
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(4.dp))

        //Genre
        Text(
            text = genre,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            fontFamily = FontFamily(Font(R.font.googlesans_regular, FontWeight.Normal)),
        )
    }
}


@Preview
@Composable
fun DetailsTvShowContentPrev() {
    val tests = listOf(
        TvShowsDomain(
            id = 1,
            title = "Doctor Who",
            overview = "The Doctor is a Time Lord: a 900 year old alien with 2 hearts, part of a gifted civilization who mastered time travel. The Doctor saves planets for a living—more of a hobby actually, and the Doctor's very, very good at it.",
            posterPath = "https://image.tmdb.org/t/p/original/4edFyasCrkH4MKs6H4mHqlrxA6b.jpg",
            voteAverage = 7.9f,
            releaseDate = "2022-02-17"
        ),
        TvShowsDomain(
            id = 2,
            title = "Los Farad",
            overview = "The day Oskar, a typical local boy, crosses paths with the mysterious and wealthy Farad family, his life changes forever. Oskar enters a winner-take-all game, the world of international arms trafficking. In Marbella where the Farads live, luxury, adrenaline and intense emotions await him... But also a backside of violence and cynicism that tests his will.",
            posterPath = "https://image.tmdb.org/t/p/original/t2aNPWte1XmVbFL2HMppoQK3PG.jpg",
            voteAverage = 6.8f,
            releaseDate = "2023-12-12"
        ),
    )
    DetailsTvShowContent(
        title = "Doctor Who",
        description = "The Doctor is a Time Lord: a 900 year old alien with 2 hearts, part of a gifted civilization who mastered time travel. The Doctor saves planets for a living—more of a hobby actually, and the Doctor's very, very good at it.",
        imagePoster = "https://image.tmdb.org/t/p/original/4edFyasCrkH4MKs6H4mHqlrxA6b.jpg",
        imageBackdrop = "https://image.tmdb.org/t/p/w500/vViRXFnSyGJ2fzMbcc5sqTKswcd.jpg",
        genres = listOf(GenreDomain(name = "Action")),
        releaseDate = "2021-12-15",
        voteAverage = "9.5",
        runtime = "7 season",
        onClickBack = {},
        onClickFavorite = {},
        isFavoriteTvShow = false,
        similarTvShowsList = tests
    )
}