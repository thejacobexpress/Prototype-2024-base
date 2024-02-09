package pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node

expect class AutoMenu(
    buildContext: BuildContext,
    backStack: BackStack<AutoTeleSelectorMenu.NavTarget>,
    match: MutableState<String>,
    team: MutableIntState,
    robotStartPosition: MutableIntState,
    autoSpeakerNum: MutableIntState,
    autoAmpNum: MutableIntState,
    quanNotes: MutableState<String>
) : Node {

    @Composable
    override fun View(modifier: Modifier)
}