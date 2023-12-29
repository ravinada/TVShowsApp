package com.ravinada.sps.usecases

import com.ravinada.sps.usecases.data.FakeRepositoryErrorApi
import com.ravinada.sps.domain.TvShowsDomain
import com.ravinada.sps.domain.usecases.GetTrendingTvShowsUseCase
import com.ravinada.sps.usecases.data.FakeRepositorySuccessApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

class GetTrendingTvShowsUseCaseTest {

    // SUT -> System Under Test
    private lateinit var sut: GetTrendingTvShowsUseCase
    private lateinit var sutSuccess: GetTrendingTvShowsUseCase

    // DOC -> Dependency of Component
    private lateinit var fakeRepositoryFailureApi: FakeRepositoryErrorApi
    private lateinit var fakeRepositorySuccessApi: FakeRepositorySuccessApi

    //Save collect result
    private val tvShows = mutableListOf<TvShowsDomain>()

    @Before
    fun setUp() {
        fakeRepositoryFailureApi = FakeRepositoryErrorApi()
        sut = GetTrendingTvShowsUseCase(fakeRepositoryFailureApi)

        fakeRepositorySuccessApi = FakeRepositorySuccessApi()
        sutSuccess = GetTrendingTvShowsUseCase(fakeRepositorySuccessApi)
    }

    @Test(expected = HttpException::class)
    fun `should return exception when network request is failed`() = runBlocking{
        //Arrange

        //Act
        val result = sut.invoke()

        result.collect {
            //Assert
            tvShows += it
        }

        //Assert
        assert(tvShows.isEmpty())
    }

    @Test
    fun `should return success with list converted to domain when network request is success`() = runBlocking{
        //Arrange

        //Act
        val result = sutSuccess.invoke()

        result.collect {
            //Assert
            tvShows += it
        }

        //Assert - Verify
        assert(tvShows.isNotEmpty())
    }

    @After
    fun tearDown() {
    }
}