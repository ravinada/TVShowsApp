package com.ravinada.sps.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ravinada.sps.usecases.utils.MainCoroutineScopeRule
import com.ravinada.sps.domain.TrendingTvShowsResponse
import com.ravinada.sps.domain.TvShowsEntity
import com.ravinada.sps.domain.toDomainModel
import com.ravinada.sps.domain.usecases.GetTrendingTvShowsUseCase
import com.ravinada.sps.domain.usecases.PopularTvShowsResult
import com.ravinada.sps.domain.usecases.SearchTvShowUseCase
import com.ravinada.sps.usecases.data.FakeRepositorySuccessApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TvShowsViewModelTest {

    private lateinit var fakeRepositorySuccess: FakeRepositorySuccessApi

    lateinit var getTrendingTvShowsUseCase: GetTrendingTvShowsUseCase
    private lateinit var searchTvShowUseCase: SearchTvShowUseCase

    // SUT -> System Under Test
    private lateinit var sut: TvShowsViewModel

    private val listResult = mutableListOf<PopularTvShowsResult>()

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineScopeRule = MainCoroutineScopeRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        fakeRepositorySuccess = FakeRepositorySuccessApi()
        getTrendingTvShowsUseCase = GetTrendingTvShowsUseCase(fakeRepositorySuccess)
        searchTvShowUseCase = SearchTvShowUseCase(fakeRepositorySuccess)
        sut = TvShowsViewModel(getTrendingTvShowsUseCase, searchTvShowUseCase)
    }

    @Test
    fun `get popular tvShows should return different results emitted`() =
        mainCoroutineScopeRule.runBlockingTest {
            // Arrange
            val scope = launch {
                sut.tvShows.collect() {
                    listResult.add(it)
                }
            }

            // Act
            sut.getTrendingTvShows()

            // Assert -> expected = actual
            val expected = listOf(
                PopularTvShowsResult.Loading(true),
                PopularTvShowsResult.Loading(false),
            )

            scope.cancel()
            assertNotEquals(expected, listResult)
        }

    @Test
    fun `get popular test first emit should be loading`() = runTest {
        // Arrange
        sut.tvShows.first() {
            listResult.add(it)
        }

        // Act
        sut.getTrendingTvShows()

        // Assert
        val expected = PopularTvShowsResult.Loading(true)
        assertEquals(expected, listResult.first())
    }

    @Test
    fun `get popular normal test should return success`() = runTest {
        // Arrange
        val list = sut.tvShows.take(2).toList()

        // Act
        sut.getTrendingTvShows()

        // Assert
        val expected = listOf(
            PopularTvShowsResult.Loading(true),
            PopularTvShowsResult.Success(trendingTvShowsFakeResponse().results.toDomainModel()),
        )
        assertEquals(expected, list)
    }


    @After
    fun tearDown() {
        listResult.clear()
        //kill the coroutine
        mainCoroutineScopeRule.coroutineContext.cancelChildren()
    }

    private fun trendingTvShowsFakeResponse() = TrendingTvShowsResponse(
        page = 1,
        results = listOf(
            TvShowsEntity(
                adult = false,
                backdropPath = "",
                firstAirDate = "",
                genreIds = listOf(),
                id = 1,
                mediaType = "",
                name = "",
                originCountry = listOf(),
                originalLanguage = "",
                originalName = "",
                overview = "",
                popularity = 0.0,
                posterPath = "",
                voteAverage = 0.0f,
                voteCount = 0
            )
        ),
        totalPages = 1,
        totalResults = 1
    )
}