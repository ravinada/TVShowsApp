package com.ravinada.sps.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ravinada.sps.usecases.utils.MainCoroutineScopeRule
import com.ravinada.sps.domain.local.FavoriteTvShowsEntity
import com.ravinada.sps.domain.usecases.GetFavoriteTvShowsUseCase
import com.ravinada.sps.usecases.data.FakeRepositoryErrorApi
import com.ravinada.sps.usecases.data.FakeRepositorySuccessApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetFavoriteTvShowsUseCaseTest {

    @get:Rule
    val testInstantTaskExecutorRule : InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineScopeRule = MainCoroutineScopeRule()

    // SUT -> System Under Test
    private lateinit var sut: GetFavoriteTvShowsUseCase
    private lateinit var sutSuccess: GetFavoriteTvShowsUseCase

    // DOC -> Dependency of Component
    private lateinit var fakeRepositoryErrorApi: FakeRepositoryErrorApi
    private lateinit var fakeRepositorySuccessApi: FakeRepositorySuccessApi

    //Save collect result
    private val listResult = mutableListOf<List<FavoriteTvShowsEntity>>()

    @Before
    fun setUp() {
        fakeRepositoryErrorApi = FakeRepositoryErrorApi()
        sut = GetFavoriteTvShowsUseCase(fakeRepositoryErrorApi)

        fakeRepositorySuccessApi = FakeRepositorySuccessApi()
        sutSuccess = GetFavoriteTvShowsUseCase(fakeRepositorySuccessApi)
    }

    @Test
    fun `should return empty if not exist favorite tvShows`() {
        //Arrange

        //Act
        val result = sut.invoke()

        runBlocking {
            result.collect {
                listResult.add(it)
            }
        }

        //Assert
        assertEquals(listResult[0].size, 0)
    }

    @Test
    fun `should return list of favorite tvShows`() {
        //Arrange

        //Act
        val result = sutSuccess.invoke()

        runBlocking {
            result.collect {
                listResult.add(it)
            }
        }

        //Assert
        assertEquals(listResult[0].size, 1)
    }

    @After
    fun tearDown() {
        listResult.clear()
    }
}