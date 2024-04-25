package unit.serverScreen.presentation

import androidx.compose.ui.test.*
import core.di.coreModule
import core.domain.ServerConnectionStatus
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.test.inject
import org.koin.test.junit5.AutoCloseKoinTest
import serverScreen.di.serverScreenModule
import serverScreen.presentation.ServerScreen
import serverScreen.presentation.ServerScreenViewModel
import serverScreen.presentation.components.connectionAdjuster.DISCONNECTED_BUTTON_TAG

class ServerScreenUiTest: AutoCloseKoinTest() {

    private val viewModel by inject<ServerScreenViewModel>()

    @BeforeEach
    fun setup() {
        startKoin {
            modules(
                coreModule,
                serverScreenModule
            )
        }
    }

    @OptIn(ExperimentalTestApi::class, ExperimentalTestApi::class)
    @Test
    fun `connection must not be disconnected after pressing on the connect button`() = runComposeUiTest {
        setContent {
            ServerScreen()
        }

        onNodeWithTag(DISCONNECTED_BUTTON_TAG).performClick()
        assert(viewModel.serverConnection.connectionStatus.value != ServerConnectionStatus.DISCONNECTED)
    }

}
