package nodes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import pages.DataEntryMenu

class DataEntryMenuNode(
    buildContext: BuildContext,
    private val backStack: BackStack<RootNode.NavTarget>
    ) : Node(buildContext) {
        @Composable
        override fun View(modifier: Modifier) {
            DataEntryMenu(modifier, backStack)
        }


    }