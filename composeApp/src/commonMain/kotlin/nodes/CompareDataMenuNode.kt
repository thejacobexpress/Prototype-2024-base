package nodes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import pages.CompareDataMenu
import pages.DataEntryMenu

class CompareDataMenuNode(
    buildContext: BuildContext,
    private val backStack: BackStack<RootNode.NavTarget>,
    private val scoutName: MutableState<String>,
    private val comp: MutableState<String>,
    private val team: MutableIntState
) : Node(buildContext) {
    @Composable
    override fun View(modifier: Modifier) {
        CompareDataMenu(modifier, backStack, scoutName, comp, team)
    }


}