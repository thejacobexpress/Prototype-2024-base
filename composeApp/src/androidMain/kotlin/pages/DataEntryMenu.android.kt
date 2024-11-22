package pages

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.operation.pop
import nodes.RootNode
import defaultOnPrimary
import defaultPrimaryVariant
import getCurrentTheme
import teamDatas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun DataEntryMenu(
    modifier: Modifier,
    backStack: BackStack<RootNode.NavTarget>,
    scoutName: MutableState<String>,
    comp: MutableState<String>,
    team: MutableIntState
) {

    val context = LocalContext.current

    var chosenTeamIndex by remember { mutableStateOf(0) }
    var chosenTeamName by remember { mutableStateOf(teamDatas.value[chosenTeamIndex].getName()) }

    var dropDownExpanded by remember { mutableStateOf(false) }
    var bonusDropDownExpanded by remember { mutableStateOf(false) }

    var refreshTotalPoints by remember { mutableStateOf(false) }

    Column(

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

                TextField(
                    modifier = modifier
                        .menuAnchor(),
                    value = chosenTeamName,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropDownExpanded)
                    }
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
                                color = getCurrentTheme().onPrimary
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
                                color = getCurrentTheme().onPrimary
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
                                color = getCurrentTheme().onPrimary
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
                                color = getCurrentTheme().onPrimary
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
                                color = getCurrentTheme().onPrimary
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
                modifier = modifier/*
                .width((LocalConfiguration.current.screenWidthDp * 0.8f).dp)*/,
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
                modifier = modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Amount of points in low goal: ",
                    color = getCurrentTheme().onPrimary
                )

                TextField(
                    modifier = modifier
                        .height(55.dp)
                        .width(70.dp),
                    value = teamDatas.value[chosenTeamIndex].lowGoalPoints.value,
                    onValueChange = { it ->
                        if(it.length <= 3) {
                            teamDatas.value[chosenTeamIndex].lowGoalPoints.value = it
                            refreshTotalPoints = true
                        }
                    }
                )

            }

            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Amount of points in middle goal: ",
                    color = getCurrentTheme().onPrimary
                )

                TextField(
                    modifier = modifier
                        .height(55.dp)
                        .width(70.dp),
                    value = teamDatas.value[chosenTeamIndex].middleGoalPoints.value,
                    onValueChange = { it ->
                        if(it.length <= 3) {
                            teamDatas.value[chosenTeamIndex].middleGoalPoints.value = it
                            refreshTotalPoints = true
                        }
                    }
                )

            }

            if (teamDatas.value[chosenTeamIndex].hatchPanelCheck.value) {

                Row(
                    modifier = modifier,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Text(
                        text = "Amount of points in high goal: ",
                        color = getCurrentTheme().onPrimary
                    )

                    TextField(
                        modifier = modifier
                            .height(55.dp)
                            .width(70.dp),
                        value = teamDatas.value[chosenTeamIndex].highGoalPoints.value,
                        onValueChange = { it ->
                            if(it.length <= 3) {
                                teamDatas.value[chosenTeamIndex].highGoalPoints.value = it
                                refreshTotalPoints = true
                            }
                        }
                    )

                }

            }

            if(refreshTotalPoints) {
                try {
                    teamDatas.value[chosenTeamIndex].totalPoints.value = 0
                    if(teamDatas.value[chosenTeamIndex].lowGoalPoints.value != "") {
                        teamDatas.value[chosenTeamIndex].totalPoints.value += teamDatas.value[chosenTeamIndex].lowGoalPoints.value.toInt()
                    }
                    if(teamDatas.value[chosenTeamIndex].middleGoalPoints.value != "") {
                        teamDatas.value[chosenTeamIndex].totalPoints.value += teamDatas.value[chosenTeamIndex].middleGoalPoints.value.toInt()
                    }
                    if(teamDatas.value[chosenTeamIndex].highGoalPoints.value != "") {
                        teamDatas.value[chosenTeamIndex].totalPoints.value += teamDatas.value[chosenTeamIndex].highGoalPoints.value.toInt()
                    }
                } catch (e : NumberFormatException) {
                    print(e.stackTraceToString())
                    Toast.makeText(context, "Please enter a number.", Toast.LENGTH_LONG).show()
                }
                refreshTotalPoints = false
            }

            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.Start
            ) {

                Text(
                    text = "Total points the team scored: ${teamDatas.value[chosenTeamIndex].totalPoints.value}",
                    color = getCurrentTheme().onPrimary
                )

            }

            Column(
                modifier = modifier
                    .padding(top = 50.dp),
            ) {
                Button(
                    onClick = {
                        bonusDropDownExpanded = !bonusDropDownExpanded
                    }
                ) {
                    Text(
                        text = "Input bonus point data (OPTIONAL)",
                        color = getCurrentTheme().onPrimary
                    )
                }
                DropdownMenu(
                    modifier = modifier,
                    expanded = bonusDropDownExpanded,
                    onDismissRequest = {
                        bonusDropDownExpanded = false
                    },
                ) {

                    Row(
                        modifier = modifier,
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            modifier = modifier
                                .width((LocalConfiguration.current.screenWidthDp * 0.8f).dp),
                            text = "Does the team's robot mount and unmount in under 3 minutes?",
                            color = getCurrentTheme().onPrimary
                        )

                        Checkbox(
                            checked = teamDatas.value[chosenTeamIndex].mountUnmountCheck.value,
                            onCheckedChange = { it ->
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
                            text = "Does the team's robot fit in a 16\"x16\"x14\" box?",
                            color = getCurrentTheme().onPrimary
                        )

                        Checkbox(
                            checked = teamDatas.value[chosenTeamIndex].fitInBoxCheck.value,
                            onCheckedChange = { it ->
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
                            text = "Does the team's robot weigh less than 7.5 pounds?",
                            color = getCurrentTheme().onPrimary
                        )

                        Checkbox(
                            checked = teamDatas.value[chosenTeamIndex].weightCheck.value,
                            onCheckedChange = { it ->
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
                            text = "Does the team's robot use one or fewer motors?",
                            color = getCurrentTheme().onPrimary
                        )

                        Checkbox(
                            checked = teamDatas.value[chosenTeamIndex].motorCheck.value,
                            onCheckedChange = { it ->
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
                                .width((LocalConfiguration.current.screenWidthDp * 0.8f).dp),
                            text = "Total bonus points the team scored: ${teamDatas.value[chosenTeamIndex].totalBonusPoints.value}",
                            color = getCurrentTheme().onPrimary
                        )

                    }

                }
            }

        }

    }


}
