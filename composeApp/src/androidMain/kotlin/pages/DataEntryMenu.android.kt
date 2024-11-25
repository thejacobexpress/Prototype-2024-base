package pages

import android.graphics.Paint.Align
import android.provider.DocumentsContract.Root
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Colors
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.operation.pop
import com.bumble.appyx.components.backstack.operation.push
import nodes.RootNode
import defaultPrimaryVariant
import defaultSecondary
import getCurrentTheme
import teamDatas
import kotlin.math.floor
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun DataEntryMenu(
    modifier: Modifier,
    backStack: BackStack<RootNode.NavTarget>
) {

    val context = LocalContext.current

    var chosenTeamIndex by remember { mutableStateOf(0) }
    var chosenTeamName by remember { mutableStateOf(teamDatas.value[chosenTeamIndex].teamName.value) }

    var dropDownExpanded by remember { mutableStateOf(false) }
    var bonusDropDownExpanded by remember { mutableStateOf(false) }

    var refreshtotalPoints by remember { mutableStateOf(false) } // If true, a function in this file recalculates the total amount of points AND total amount of assets the team that the player currently selected has.

    var bonusPointButtonBorderColor by remember { mutableStateOf(Color.White) }
    var bonusPointButtonText by remember { mutableStateOf("Reveal bonus point data (OPTIONAL)") }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            OutlinedButton(
                modifier = modifier
                    .align(Alignment.CenterStart)
                    .scale(0.75f),
                onClick = {
                    backStack.pop()
                }
            ) {
                Text(
                    text = "Back",
                    color = getCurrentTheme().onPrimary
                )
            }

            Text(
                modifier = modifier
                    .align(Alignment.Center),
                text = "Input Robot Data",
                fontSize = 25.sp,
                color = getCurrentTheme().onPrimary
            )

        }

        HorizontalDivider(
            color = defaultPrimaryVariant,
            thickness = 3.dp
        )

        Column(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
        ) {

            ExposedDropdownMenuBox(
                modifier = modifier
                    .width(200.dp),
                expanded = dropDownExpanded,
                onExpandedChange = { it ->
                    dropDownExpanded = it
                }
            ) {

                // This TextField is where the user's team that they are currently adding data for is displayed.
                TextField(
                    modifier = modifier
                        .menuAnchor(),
                    value = chosenTeamName,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropDownExpanded)
                    },
                    textStyle = TextStyle(color = Color.White)
                )

                ExposedDropdownMenu(
                    expanded = dropDownExpanded,
                    onDismissRequest = {
                        dropDownExpanded = false
                    }
                ) {
                    HorizontalDivider(
                        color = getCurrentTheme().onSurface,
                        thickness = 3.dp
                    )

                    DropdownMenuItem(
                        {
                            Text(
                                text = "BlackRock",
                                color = Color.White
                            )
                        },
                        onClick = {
                            chosenTeamName = "BlackRock"
                            chosenTeamIndex = 0
                            dropDownExpanded = false
                        }
                    )

                    HorizontalDivider(
                        color = getCurrentTheme().onSurface,
                        thickness = 3.dp
                    )

                    DropdownMenuItem(
                        {
                            Text(
                                text = "Vanguard",
                                color = Color.White
                            )
                        },
                        onClick = {
                            chosenTeamName = "Vanguard"
                            chosenTeamIndex = 1
                            dropDownExpanded = false
                        }
                    )

                    HorizontalDivider(
                        color = getCurrentTheme().onSurface,
                        thickness = 3.dp
                    )

                    DropdownMenuItem(
                        {
                            Text(
                                text = "Fidelity",
                                color = Color.White
                            )
                        },
                        onClick = {
                            chosenTeamName = "Fidelity"
                            chosenTeamIndex = 2
                            dropDownExpanded = false
                        }
                    )

                    HorizontalDivider(
                        color = getCurrentTheme().onSurface,
                        thickness = 3.dp
                    )

                    DropdownMenuItem(
                        {
                            Text(
                                text = "JPMorgan",
                                color = Color.White
                            )
                        },
                        onClick = {
                            chosenTeamName = "JPMorgan"
                            chosenTeamIndex = 3
                            dropDownExpanded = false
                        }
                    )

                    HorizontalDivider(
                        color = getCurrentTheme().onSurface,
                        thickness = 3.dp
                    )

                    DropdownMenuItem(
                        {
                            Text(
                                text = "State Street",
                                color = Color.White
                            )
                        },
                        onClick = {
                            chosenTeamName = "State Street"
                            chosenTeamIndex = 4
                            dropDownExpanded = false
                        }
                    )

                    HorizontalDivider(
                        color = getCurrentTheme().onSurface,
                        thickness = 3.dp
                    )
                }

            }

        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 30.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Row(
                modifier = modifier
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Able to pull off hatch panel?"
                )

                Checkbox(
                    checked = teamDatas.value[chosenTeamIndex].hatchPanelCheck.value,
                    onCheckedChange = { it ->
                        teamDatas.value[chosenTeamIndex].hatchPanelCheck.value = it
                    }
                )

            }

            Row(
                modifier = modifier
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Amount of assets in low goal: ",
                    color = getCurrentTheme().onPrimary
                )

                TextField(
                    modifier = modifier
                        .height(55.dp)
                        .width(70.dp),
                    value = teamDatas.value[chosenTeamIndex].lowGoalAssets.value,
                    onValueChange = { it ->
                        if(it.length <= 3) { // Prevents the user from storing more than 3 digit numbers!
                            teamDatas.value[chosenTeamIndex].lowGoalAssets.value = it
                            refreshtotalPoints = true
                        }
                    },
                    textStyle = TextStyle(color = Color.White),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )

            }

            Row(
                modifier = modifier
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Amount of assets in middle goal: ",
                    color = getCurrentTheme().onPrimary
                )

                TextField(
                    modifier = modifier
                        .height(55.dp)
                        .width(70.dp),
                    value = teamDatas.value[chosenTeamIndex].middleGoalAssets.value,
                    onValueChange = { it ->
                        if(it.length <= 3) { // Prevents the user from storing more than 3 digit numbers!
                            teamDatas.value[chosenTeamIndex].middleGoalAssets.value = it
                            refreshtotalPoints = true
                        }
                    },
                    textStyle = TextStyle(color = Color.White),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )

            }

            if (teamDatas.value[chosenTeamIndex].hatchPanelCheck.value) {

                Row(
                    modifier = modifier
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Text(
                        text = "Amount of assets in high goal: ",
                        color = getCurrentTheme().onPrimary
                    )

                    TextField(
                        modifier = modifier
                            .height(55.dp)
                            .width(70.dp),
                        value = teamDatas.value[chosenTeamIndex].highGoalAssets.value,
                        onValueChange = { it ->
                            if(it.length <= 3) { // Prevents the user from storing more than 3 digit numbers!
                                teamDatas.value[chosenTeamIndex].highGoalAssets.value = it
                                refreshtotalPoints = true
                            }
                        },
                        textStyle = TextStyle(color = Color.White),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )

                }

            }

            // Calculates the amount of assets and total points the team the player has selected (at the top of
            // the screen in the dropdown menu) scored.
            if(refreshtotalPoints) {

                try {
                    // Sets total points and assets of the team the user currently has selected to 0 in order to add the low, middle,
                    // and high goal assets together individually.
                    teamDatas.value[chosenTeamIndex].totalAssets.value = 0
                    teamDatas.value[chosenTeamIndex].totalPoints.value = 0
                    if(teamDatas.value[chosenTeamIndex].lowGoalAssets.value != "") {
                        teamDatas.value[chosenTeamIndex].totalAssets.value += teamDatas.value[chosenTeamIndex].lowGoalAssets.value.toInt()
                        teamDatas.value[chosenTeamIndex].totalPoints.value += teamDatas.value[chosenTeamIndex].lowGoalAssets.value.toInt()*2
                    }
                    if(teamDatas.value[chosenTeamIndex].middleGoalAssets.value != "") {
                        teamDatas.value[chosenTeamIndex].totalAssets.value += teamDatas.value[chosenTeamIndex].middleGoalAssets.value.toInt()
                        teamDatas.value[chosenTeamIndex].totalPoints.value += teamDatas.value[chosenTeamIndex].middleGoalAssets.value.toInt()*3
                    }
                    if(teamDatas.value[chosenTeamIndex].highGoalAssets.value != "") {
                        teamDatas.value[chosenTeamIndex].totalAssets.value += teamDatas.value[chosenTeamIndex].highGoalAssets.value.toInt()
                        teamDatas.value[chosenTeamIndex].totalPoints.value += teamDatas.value[chosenTeamIndex].highGoalAssets.value.toInt()*5
                    }

                    // Finds the amount of times the yearly interest and quarterly interest are added to the total amount of points.
                    val twelveAssets : Int = floor(teamDatas.value[chosenTeamIndex].totalAssets.value / 12.0).roundToInt()
                    val threeAssets : Int = floor(teamDatas.value[chosenTeamIndex].totalAssets.value / 3.0).roundToInt()

                    // Adds the yearly and quarterly interest to the total amount of points the team has scored.
                    teamDatas.value[chosenTeamIndex].totalPoints.value += (twelveAssets*100) + (threeAssets*20)

                } catch (e : NumberFormatException) {
                    print(e.stackTraceToString())
                    Toast.makeText(context, "Please enter a number.", Toast.LENGTH_LONG).show()
                }

                refreshtotalPoints = false // Sets to false so the total amount of points the team has can
            // be recalculated once the user changes the amount of assets the team has scored.
            }

            Row(
                modifier = modifier
                    .padding(start = 10.dp, top = 20.dp),
                horizontalArrangement = Arrangement.Start
            ) {

                Text(
                    text = "Total assets the team scored: ${teamDatas.value[chosenTeamIndex].totalAssets.value}",
                    color = getCurrentTheme().onPrimary,
                    fontWeight = FontWeight.Bold
                )

            }

            Row(
                modifier = modifier
                    .padding(start = 10.dp),
                horizontalArrangement = Arrangement.Start
            ) {

                Text(
                    text = "Total points the team scored: ${teamDatas.value[chosenTeamIndex].totalPoints.value}",
                    color = getCurrentTheme().onPrimary,
                    fontWeight = FontWeight.Bold
                )

            }

            Column(
                modifier = modifier
                    .padding(top = 50.dp),
            ) {
                OutlinedButton (
                    modifier = modifier
                        .scale(1f)
                        .padding(start = 10.dp),
                    border = BorderStroke(1f.dp, bonusPointButtonBorderColor),
                    onClick = {
                        bonusDropDownExpanded = !bonusDropDownExpanded
                        if(bonusDropDownExpanded) {
                            bonusPointButtonBorderColor = Color.Yellow
                            bonusPointButtonText = "Hide bonus point data (OPTIONAL)"
                        } else {
                            bonusPointButtonBorderColor = Color.White
                            bonusPointButtonText = "Reveal bonus point data (OPTIONAL)"
                        }
                    }
                ) {
                    Text(
                        text = bonusPointButtonText,
                        color = getCurrentTheme().onPrimary
                    )
                }

                if(bonusDropDownExpanded) {

                    Column(
                        modifier = modifier
//                            .border(BorderStroke(3.dp, getCurrentTheme().onSurface))
                    ) {

                        Column(
                            modifier = modifier
                                .padding(10.dp)
                                .border(BorderStroke(3.dp, getCurrentTheme().onSurface))
                        ) {

                            Column(
                                modifier = modifier
                                    .padding(10.dp)
                            ) {
                                Row(
                                    modifier = modifier,
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Text(
                                        modifier = modifier
                                            .width((LocalConfiguration.current.screenWidthDp * 0.8f).dp),
                                        text = "Does the robot mount and unmount in under 3 minutes?",
                                        color = getCurrentTheme().onPrimary
                                    )

                                    Checkbox(
                                        checked = teamDatas.value[chosenTeamIndex].mountUnmountCheck.value,
                                        onCheckedChange = { it ->
                                            // Adds 5 points if the box is checked, and subtracts 5 points if it isn't.
                                            teamDatas.value[chosenTeamIndex].mountUnmountCheck.value = it
                                            if (teamDatas.value[chosenTeamIndex].mountUnmountCheck.value) {
                                                teamDatas.value[chosenTeamIndex].totalBonusPoints.value += 5
                                            } else {
                                                teamDatas.value[chosenTeamIndex].totalBonusPoints.value -= 5
                                            }
                                        }
                                    )

                                }

                                Row(
                                    modifier = modifier,
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Text(
                                        modifier = modifier
                                            .width((LocalConfiguration.current.screenWidthDp * 0.8f).dp),
                                        text = "Does the robot fit in a 16\"x16\"x14\" box?",
                                        color = getCurrentTheme().onPrimary
                                    )

                                    Checkbox(
                                        checked = teamDatas.value[chosenTeamIndex].fitInBoxCheck.value,
                                        onCheckedChange = { it ->
                                            // Adds 5 points if the box is checked, and subtracts 5 points if it isn't.
                                            teamDatas.value[chosenTeamIndex].fitInBoxCheck.value = it
                                            if(teamDatas.value[chosenTeamIndex].fitInBoxCheck.value) {
                                                teamDatas.value[chosenTeamIndex].totalBonusPoints.value += 5
                                            } else {
                                                teamDatas.value[chosenTeamIndex].totalBonusPoints.value -= 5
                                            }
                                        }
                                    )

                                }

                                Row(
                                    modifier = modifier,
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Text(
                                        modifier = modifier
                                            .width((LocalConfiguration.current.screenWidthDp * 0.8f).dp),
                                        text = "Does the robot weigh less than 7.5 pounds?",
                                        color = getCurrentTheme().onPrimary
                                    )

                                    Checkbox(
                                        checked = teamDatas.value[chosenTeamIndex].weightCheck.value,
                                        onCheckedChange = { it ->
                                            // Adds 5 points if the box is checked, and subtracts 5 points if it isn't.
                                            teamDatas.value[chosenTeamIndex].weightCheck.value = it
                                            if(teamDatas.value[chosenTeamIndex].weightCheck.value) {
                                                teamDatas.value[chosenTeamIndex].totalBonusPoints.value += 5
                                            } else {
                                                teamDatas.value[chosenTeamIndex].totalBonusPoints.value -= 5
                                            }
                                        }
                                    )

                                }

                                Row(
                                    modifier = modifier,
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Text(
                                        modifier = modifier
                                            .width((LocalConfiguration.current.screenWidthDp * 0.8f).dp),
                                        text = "Does the robot use one or fewer motors?",
                                        color = getCurrentTheme().onPrimary
                                    )

                                    Checkbox(
                                        checked = teamDatas.value[chosenTeamIndex].motorCheck.value,
                                        onCheckedChange = { it ->
                                            // Adds 5 points if the box is checked, and subtracts 5 points if it isn't.
                                            teamDatas.value[chosenTeamIndex].motorCheck.value = it
                                            if(teamDatas.value[chosenTeamIndex].motorCheck.value) {
                                                teamDatas.value[chosenTeamIndex].totalBonusPoints.value += 5
                                            } else {
                                                teamDatas.value[chosenTeamIndex].totalBonusPoints.value -= 5
                                            }
                                        }
                                    )

                                }

                                Row(
                                    modifier = modifier,
                                    horizontalArrangement = Arrangement.Center
                                ) {

                                    Text(
                                        modifier = modifier
                                            .padding(top = 10.dp)
                                            .width((LocalConfiguration.current.screenWidthDp * 0.8f).dp),
                                        text = "Total bonus points the team scored: ${teamDatas.value[chosenTeamIndex].totalBonusPoints.value}",
                                        color = getCurrentTheme().onPrimary,
                                        fontWeight = FontWeight.Bold
                                    )

                                }
                            }

                        }

                    }

                }

            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = 15.dp, bottom = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedButton(
                    border = BorderStroke(3.dp, Color.Yellow),
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = defaultSecondary),
                    onClick = {
                        backStack.push(RootNode.NavTarget.CompareDataMenu)
                    }
                ) {

                    Text(
                        text = "Compare data",
                        color = getCurrentTheme().onPrimary
                    )

                }

            }

        }

    }


}
