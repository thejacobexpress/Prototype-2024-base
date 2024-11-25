package pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.bumble.appyx.components.backstack.BackStack
import nodes.RootNode

@Composable
expect fun DataEntryMenu(
    modifier: Modifier,
    backStack: BackStack<RootNode.NavTarget>
)