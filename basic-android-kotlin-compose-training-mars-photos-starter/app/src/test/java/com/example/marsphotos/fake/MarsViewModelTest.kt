package com.example.marsphotos.fake

import com.example.marsphotos.fake.fake.FakeDataSource
import com.example.marsphotos.fake.fake.FakeNetworkMarsPhotosRepository
import com.example.marsphotos.fake.rules.TestDispatcherRule
import com.example.marsphotos.ui.screens.MarsUiState
import com.example.marsphotos.ui.screens.MarsViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MarsViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()
    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() = runTest {
        val marsViewModel = MarsViewModel(marsPhotosRepository = FakeNetworkMarsPhotosRepository())
        Assert.assertEquals(
            MarsUiState.Success(
                FakeDataSource.photosList
            ),
            marsViewModel.marsUiState
        )
    }

}