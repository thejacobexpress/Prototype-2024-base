package pages

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.operation.push
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import composables.InternetErrorAlert
import defaultSecondary
import getCurrentTheme
import getLastSynced
import matchData
import nodes.RootNode
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import sync
import teamData

@OptIn(ExperimentalResourceApi::class)
@Composable
actual fun MainMenu(
    modifier: Modifier,
    backStack: BackStack<RootNode.NavTarget>,
    robotStartPosition: MutableIntState,
    scoutName: MutableState<String>,
    comp: MutableState<String>,
    team: MutableIntState
) {
    var selectedPlacement by remember { mutableStateOf(false) }
    val openError = remember { mutableStateOf(false) }
    var matchSyncedResource by remember { mutableStateOf(if (matchData == null) "crossmark.png" else "checkmark.png") }
    var teamSyncedResource by remember { mutableStateOf(if (teamData == null) "crossmark.png" else "checkmark.png") }

    when {
        openError.value -> {
            InternetErrorAlert { openError.value = false }
        }
    }
    Column(modifier = Modifier.verticalScroll(ScrollState(0))) {
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { backStack.push(RootNode.NavTarget.LoginPage) },
                modifier = Modifier.scale(0.75f).align(Alignment.CenterStart)
            ) {
                Text(text = "Login", color = getCurrentTheme().onPrimary)
            }

            Text(
                text = "Bear Metal Scout App",
                fontSize = 25.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Divider(color = getCurrentTheme().onSurface, thickness = 2.dp)
        OutlinedButton(
            border = BorderStroke(3.dp, Color.Yellow),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = defaultSecondary),
            contentPadding = PaddingValues(horizontal = 60.dp, vertical = 5.dp),
            onClick = {
                openError.value = !sync(false)
                if (!openError.value)
                    selectedPlacement = true
                else
                    matchSyncedResource = "checkmark.png"
            },
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(horizontal = 50.dp, vertical = 50.dp),
        ) {
            Text(
                text = "Match",
                color = getCurrentTheme().onPrimary,
                fontSize = 35.sp
            )
        }




        OutlinedButton(
            border = BorderStroke(3.dp, Color.Yellow),
            shape = RoundedCornerShape(25.dp),
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 15.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = defaultSecondary),
            onClick = {
                openError.value = !sync(true)
                if (teamData != null) teamSyncedResource = "checkmark.png"
                if (matchData != null) matchSyncedResource = "checkmark.png"
            },
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(horizontal = 50.dp, vertical = 50.dp),
        ) {
            Column {
                Text(
                    text = "Sync",
                    color = getCurrentTheme().onPrimary,
                    fontSize = 35.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Text(
                    text = "Last synced ${getLastSynced()}",
                    fontSize = 12.sp,
                )

                Spacer(modifier = androidx.compose.ui.Modifier.height(10.dp))

                Row {
                    Text("Robot List")

                    Image(
                        painterResource(res = teamSyncedResource),
                        contentDescription = "status",
                        modifier = androidx.compose.ui.Modifier.size(30.dp).offset(x = 100.dp, y = (-5).dp)
                    )
                }

                Spacer(modifier = androidx.compose.ui.Modifier.height(10.dp))

                Row {
                    Text("Match List")

                    Image(
                        painterResource(res = matchSyncedResource),
                        contentDescription = "status",
                        modifier = androidx.compose.ui.Modifier.size(30.dp).offset(x = (98.5).dp),
                    )
                }
            }
        }
    }

}