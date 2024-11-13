package pages

import android.content.Context
import android.hardware.usb.UsbManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.operation.push
import compKey
import defaultSecondary
import getCurrentTheme
import getLastSynced
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import matchData
import nodes.RootNode
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.json.JSONException
import sendData
import sendDataUSB
import setTeam
import sync
import teamData


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
actual fun MainMenu(
    modifier: Modifier,
    backStack: BackStack<RootNode.NavTarget>,
    robotStartPosition: MutableIntState,
    scoutName: MutableState<String>,
    comp: MutableState<String>,
    team: MutableIntState
) {

    Column(modifier = Modifier.verticalScroll(ScrollState(0))) {
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { backStack.push(RootNode.NavTarget.LoginPage) }, modifier = Modifier
                    .scale(0.75f)
                    .align(Alignment.CenterStart)
            ) {
                Text(text = "Login", color = getCurrentTheme().onPrimary)
            }

            Text(
                text = "Bear Metal Scout App",
                fontSize = 25.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        HorizontalDivider(color = getCurrentTheme().onSurface, thickness = 2.dp)
        Text(
            text = "Hello ${scoutName.value}",
            color = getCurrentTheme().onPrimary,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        OutlinedButton(
            border = BorderStroke(3.dp, Color.Yellow),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = defaultSecondary),
            contentPadding = PaddingValues(horizontal = 60.dp, vertical = 5.dp),
            onClick = {
                //TODO switch to your new page here
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 50.dp, vertical = 50.dp),
        ) {
            Text(
                text = "Match",
                color = getCurrentTheme().onPrimary,
                fontSize = 35.sp
            )
        }
    }
}