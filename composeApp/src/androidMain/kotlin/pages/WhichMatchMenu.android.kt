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

    var mountUnmountCheck by remember { mutableStateOf(false) }
    var fitInBoxCheck by remember { mutableStateOf(false) }
    var weightCheck by remember { mutableStateOf(false) }
    var motorCheck by remember { mutableStateOf(false) }
    var totalBonusPoints by remember { mutableStateOf(0) }

    var bonusDropDownExpanded by remember { mutableStateOf(false) }

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

            // NEEDS A BUTTON TO EXPAND
            DropDownMenu(
                expanded = dropDownExpanded,
                onDismissRequest = {
                    dropDownExpanded = false
                },
                PopupProperties = PopupProperties(focusable = true)
            ) {
                
                DropDownMenuItem(
                    text = { "BlackRock" }
                    onClick = {

                        dropDownExpanded = false
                    }
                )

                DropDownMenuItem(
                    text = { "Vanguard" }
                    onClick = {

                        dropDownExpanded = false
                    }
                )

                DropDownMenuItem(
                    text = { "Fidelity" }
                    onClick = {

                        dropDownExpanded = false
                    }
                )

                DropDownMenuItem(
                    text = { "JPMorgan" }
                    onClick = {

                        dropDownExpanded = false
                    }
                )

                DropDownMenuItem(
                    text = { "State Street" }
                    onClick = {

                        dropDownExpanded = false
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

        // NEEDS A BUTTON TO EXPAND
        DropDownMenu(
            modifier = modifier
                .padding(top = 50.dp),
            expanded = bonusDropDownExpanded,
            onDismissRequest = {
                bonusDropDownExpanded = false
            },
            PopupProperties = PopupProperties(focusable = true)
        ) {
            
            Row(
                modifier = modifier
                    .fillMaxSize()
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Does the team's robot mount and unmount in under 3 minutes?"
                    color = defaultOnPrimary
                )
            
                CheckBox(
                    checked = mountUnmountCheck
                    onCheckedChange = { it ->
                        mountUnmountCheck = it
                        if(mountUnmountCheck) {
                            totalBonusPoints += 5
                        } else {
                            totalBonusPoints -= 5
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
                    text = "Does the team's robot fit in a 16\"x16\"x14\" box?"
                    color = defaultOnPrimary
                )
    
                CheckBox(
                    checked = fitInBoxCheck
                    onCheckedChange = { it ->
                        fitInBoxCheck = it
                        if(fitInBoxCheck) {
                            totalBonusPoints += 5
                        } else {
                            totalBonusPoints -= 5
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
                    text = "Does the team's robot weigh less than 7.5 pounds?"
                    color = defaultOnPrimary
                )
    
                CheckBox(
                    checked = weightCheck
                    onCheckedChange = { it ->
                        weightCheck = it
                        if(weightCheck) {
                            totalBonusPoints += 5
                        } else {
                            totalBonusPoints -= 5
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
                    text = "Does the team's robot use one or fewer motors?"
                    color = defaultOnPrimary
                )
    
                CheckBox(
                    checked = motorCheck
                    onCheckedChange = { it ->
                        motorCheck = it
                        if(motorCheck) {
                            totalBonusPoints += 5
                        } else {
                            totalBonusPoints -= 5
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
                    text = "Total bonus points the team scored: $totalBonusPoints"
                    color = defaultOnPrimary
                )
                
            }
            
        }
        
    }

}
