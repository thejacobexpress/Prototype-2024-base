package pages

import TeamData
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.operation.pop
import defaultPrimaryVariant
import getCurrentTheme
import nodes.RootNode
import teamDatas

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun CompareDataMenu(
    modifier: Modifier,
    backStack: BackStack<RootNode.NavTarget>,
    scoutName: MutableState<String>,
    comp: MutableState<String>,
    team: MutableIntState
) {

    val context = LocalContext.current

    var orderedTeamList : MutableState<List<TeamData>> = remember { mutableStateOf(listOf()) }
    if(orderedTeamList.value.size != 5) {
        for(team in teamDatas.value) {
            orderedTeamList.value += team
        }
    }

    var teamValues : MutableState<List<Any>> = remember { mutableStateOf(listOf()) }

    var dropDownExpanded by remember { mutableStateOf(false) }

    var chosenFilterText by remember { mutableStateOf("Hatch Panel Check") }
    var chosenFilter by remember { mutableStateOf(false) }

    var teamNameText by remember { mutableStateOf("") }

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
                text = "Compare Robot Data",
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
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
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
                    value = chosenFilterText,
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
                                text = "Hatch Panel Check",
                                color = getCurrentTheme().onPrimary
                            )
                        },
                        onClick = {
                            chosenFilterText = "Hatch Panel Check"

                            for(team in orderedTeamList.value) {
                                orderedTeamList.value -= team
                            }
                            for(value in teamValues.value) {
                                teamValues.value -= value
                            }

                            var teams = teamDatas.value

                            for(team in teams) {
                                if(team.hatchPanelCheck.value) {
                                    orderedTeamList.value += team
                                    teamValues.value += (team.hatchPanelCheck.value)
                                    teams -= (team)
                                }
                            }
                            for(team in teams) {
                                orderedTeamList.value += (team)
                                teamValues.value += (team.hatchPanelCheck.value)
                            }

                            chosenFilter = true
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
                                text = "Low Goal Assets",
                                color = getCurrentTheme().onPrimary
                            )
                        },
                        onClick = {
                            chosenFilterText = "Low Goal Assets"

                            for(team in orderedTeamList.value) {
                                orderedTeamList.value -= team
                            }
                            for(value in teamValues.value) {
                                teamValues.value -= value
                            }


                            dropDownExpanded = false

                            chosenFilter = true
                        }
                    )

                    HorizontalDivider(
                        color = getCurrentTheme().onSurface,
                        thickness = 3.dp
                    )

                    DropdownMenuItem(
                        {
                            Text(
                                text = "Middle Goal Assets",
                                color = getCurrentTheme().onPrimary
                            )
                        },
                        onClick = {
                            chosenFilterText = "Middle Goal Assets"

                            for(team in orderedTeamList.value) {
                                orderedTeamList.value -= team
                            }
                            for(value in teamValues.value) {
                                teamValues.value -= value
                            }


                            dropDownExpanded = false

                            chosenFilter = true
                        }
                    )

                    HorizontalDivider(
                        color = getCurrentTheme().onSurface,
                        thickness = 3.dp
                    )

                    DropdownMenuItem(
                        {
                            Text(
                                text = "High Goal Assets",
                                color = getCurrentTheme().onPrimary
                            )
                        },
                        onClick = {
                            chosenFilterText = "High Goal Assets"

                            for(team in orderedTeamList.value) {
                                orderedTeamList.value -= team
                            }
                            for(value in teamValues.value) {
                                teamValues.value -= value
                            }


                            dropDownExpanded = false

                            chosenFilter = true
                        }
                    )

                    HorizontalDivider(
                        color = getCurrentTheme().onSurface,
                        thickness = 3.dp
                    )

                    DropdownMenuItem(
                        {
                            Text(
                                text = "Total Assets",
                                color = getCurrentTheme().onPrimary
                            )
                        },
                        // Ranks teams' number of total points from greatest to lowest
                        onClick = {
                            chosenFilterText = "Total Assets"

                            for(team in orderedTeamList.value) {
                                orderedTeamList.value -= team
                            }
                            for(value in teamValues.value) {
                                teamValues.value -= value
                            }


                            dropDownExpanded = false
                            chosenFilter = true
                        }
                    )

                    HorizontalDivider(
                        color = getCurrentTheme().onSurface,
                        thickness = 3.dp
                    )

                    DropdownMenuItem(
                        {
                            Text(
                                text = "Total Points",
                                color = getCurrentTheme().onPrimary
                            )
                        },
                        onClick = {

                            chosenFilterText = "Total Points"

                            for(team in orderedTeamList.value) {
                                orderedTeamList.value -= team
                            }
                            for(value in teamValues.value) {
                                teamValues.value -= value
                            }

                            var teams = teamDatas.value

                            var totalPointOfTeams : List<Int> = listOf(teams[0].totalPoints.intValue, teams[1].totalPoints.intValue,
                                teams[2].totalPoints.intValue, teams[3].totalPoints.intValue, teams[4].totalPoints.intValue) as? List<Int> ?: listOf()

                            for(teamPoints in totalPointOfTeams.sortedDescending()) {
                                teamValues.value += teamPoints
                                orderedTeamList.value += teams[0]
                                teams -= teams[0]
                                totalPointOfTeams -= teamPoints
                            }

                            dropDownExpanded = false

                            chosenFilter = true
                        }
                    )

                    HorizontalDivider(
                        color = getCurrentTheme().onSurface,
                        thickness = 3.dp
                    )

                    DropdownMenuItem(
                        {
                            Text(
                                text = "Mount in 3 Minutes",
                                color = getCurrentTheme().onPrimary
                            )
                        },
                        onClick = {
                            chosenFilterText = "Mount in 3 Minutes"

                            for(team in orderedTeamList.value) {
                                orderedTeamList.value -= team
                            }
                            for(value in teamValues.value) {
                                teamValues.value -= value
                            }


                            dropDownExpanded = false

                            chosenFilter = true
                        }
                    )

                    HorizontalDivider(
                        color = getCurrentTheme().onSurface,
                        thickness = 3.dp
                    )

                    DropdownMenuItem(
                        {
                            Text(
                                text = "Fits in 16\"x16\"x14\" Box",
                                color = getCurrentTheme().onPrimary
                            )
                        },
                        onClick = {
                            chosenFilterText = "Fits in 16\"x16\"x14\" Box"

                            for(team in orderedTeamList.value) {
                                orderedTeamList.value -= team
                            }
                            for(value in teamValues.value) {
                                teamValues.value -= value
                            }


                            dropDownExpanded = false

                            chosenFilter = true
                        }
                    )

                    HorizontalDivider(
                        color = getCurrentTheme().onSurface,
                        thickness = 3.dp
                    )

                    DropdownMenuItem(
                        {
                            Text(
                                text = "Under 7.5 Pounds",
                                color = getCurrentTheme().onPrimary
                            )
                        },
                        onClick = {
                            chosenFilterText = "Under 7.5 pounds"

                            for(team in orderedTeamList.value) {
                                orderedTeamList.value -= team
                            }
                            for(value in teamValues.value) {
                                teamValues.value -= value
                            }


                            dropDownExpanded = false

                            chosenFilter = true
                        }
                    )

                    HorizontalDivider(
                        color = getCurrentTheme().onSurface,
                        thickness = 3.dp
                    )

                    DropdownMenuItem(
                        {
                            Text(
                                text = "Has 1 or less Motors",
                                color = getCurrentTheme().onPrimary
                            )
                        },
                        onClick = {
                            chosenFilterText = "Has 1 or less Motors"

                            for(team in orderedTeamList.value) {
                                orderedTeamList.value -= team
                            }
                            for(value in teamValues.value) {
                                teamValues.value -= value
                            }

                            dropDownExpanded = false

                            chosenFilter = true
                        }
                    )

                    HorizontalDivider(
                        color = getCurrentTheme().onSurface,
                        thickness = 3.dp
                    )

                    DropdownMenuItem(
                        {
                            Text(
                                text = "Total Bonus Points",
                                color = getCurrentTheme().onPrimary
                            )
                        },
                        onClick = {
                            chosenFilterText = "Total Bonus Points"

                            for(team in orderedTeamList.value) {
                                orderedTeamList.value -= team
                            }
                            for(value in teamValues.value) {
                                teamValues.value -= value
                            }


                            dropDownExpanded = false

                            chosenFilter = true
                        }
                    )

                    HorizontalDivider(
                        color = getCurrentTheme().onSurface,
                        thickness = 3.dp
                    )

                }

            }

            Row(
                modifier = modifier
                    .padding(50.dp)
            ) {

                Column(
                    modifier = modifier
                        .width((LocalConfiguration.current.screenWidthDp * 0.5f).dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    for(team in orderedTeamList.value) {

                        Row(
                            horizontalArrangement = Arrangement.Center
                        ) {

                            Text(
                                text = team.teamName.value,
                                color = getCurrentTheme().onPrimary
                            )

                        }

                    }

                }

                Column(
                    modifier = modifier
                        .width((LocalConfiguration.current.screenWidthDp * 0.5f).dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    if(chosenFilter) {
                        for(teamIndex in teamDatas.value.indices) {

                            Row(
                                horizontalArrangement = Arrangement.Center
                            ) {

                                Text(
                                    text = "${teamValues.value[teamIndex]}",
                                    color = getCurrentTheme().onPrimary
                                )

                            }

                        }
                    }

                }

            }

        }

    }

}