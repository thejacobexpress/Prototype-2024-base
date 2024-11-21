package pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bumble.appyx.components.backstack.BackStack
import nodes.RootNode

@Composable
actual fun WhichMatchMenu(
    modifier: Modifier,
    backStack: BackStack<RootNode.NavTarget>,
    scoutName: MutableState<String>,
    comp: MutableState<String>,
    team: MutableIntState
) {

    var hatchPanelCheck by remember { mutableStateOf(false) }
    var lowGoalPoints by remember { mutableStateOf(0) }
    var middleGoalPoints by remember { mutableStateOf(0) }
    var highGoalPoints by remember { mutableStateOf(0) }
    var totalPoints by remember { mutableStateOf(lowGoalPoints+middleGoalPoints+highGoalPoints) }
    
    var dropDownExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Select a team",
                color = getCurrentTheme().onPrimary
            )

            DropDownMenu(
                expanded = dropDownExpanded,
                onDismissRequest = {
                    dropDownExpanded = !dropDownExpanded
                }
            ) {
                
                DropDownMenuItem(
                    text = { "BlackRock" }
                    onClick = {

                    }
                )

                DropDownMenuItem(
                    text = { "Vanguard" }
                    onClick = {

                    }
                )

                DropDownMenuItem(
                    text = { "Fidelity" }
                    onClick = {

                    }
                )

                DropDownMenuItem(
                    text = { "JPMorgan" }
                    onClick = {

                    }
                )

                DropDownMenuItem(
                    text = { "State Street" }
                    onClick = {

                    }
                )
                
            }
            
        }

        HorizontalDivider(
            color = defaultPrimaryVariant,
            thickness = 3.dp
        )

        Row(
            modifier = modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Did the team pull off the hatch panel?"
                color = defaultOnPrimary
            )

            CheckBox(
                checked = hatchPanelCheck
                onCheckedChange = { it ->
                    hatchPanelCheck = it
                }
            )
            
        }

        Row(
            modifier = modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Did many points did the team score in the low goal?"
                color = defaultOnPrimary
            )

            TextField(
                modifier = modifier
                    .height(55.dp),
                value = lowGoalPoints.toString(),
                onValueChange = { it ->
                    try {
                        lowGoalPoints = it.toInt()
                    } catch (e : Exception) {
                        Toast.makeText(context, "Please enter a number.", Toast.LENGTH_LONG).show()
                    }
                }
            )
            
        }

        Row(
            modifier = modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Did many points did the team score in the middle goal?"
                color = defaultOnPrimary
            )

            TextField(
                modifier = modifier
                    .height(55.dp),
                value = middleGoalPoints.toString(),
                onValueChange = { it ->
                    try {
                        middleGoalPoints = it.toInt()
                    } catch (e : Exception) {
                        Toast.makeText(context, "Please enter a number.", Toast.LENGTH_LONG).show()
                    }
                }
            )
            
        }

        Row(
            modifier = modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Did many points did the team score in the high goal?"
                color = defaultOnPrimary
            )

            TextField(
                modifier = modifier
                    .height(55.dp),
                value = highGoalPoints.toString(),
                onValueChange = { it ->
                    try {
                        highGoalPoints = it.toInt()
                    } catch (e : Exception) {
                        Toast.makeText(context, "Please enter a number.", Toast.LENGTH_LONG).show()
                    }
                }
            )
            
        }

        Row(
            modifier = modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Total points the team scored: $totalPoints"
                color = defaultOnPrimary
            )
            
        }
        
    }

}
