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

    var teamValues : MutableState<List<Any>> = remember { mutableStateOf(listOf()) }

    var dropDownExpanded by remember { mutableStateOf(false) }

    var chosenFilterText by remember { mutableStateOf("Hatch Panel Check") }
    var chosenFilter by remember { mutableStateOf(false) }

    var teamNameText by remember { mutableStateOf("") }

    var firstTimeBooting by remember { mutableStateOf(true) }

    // Need to add each team individually because orderedTeamList just references the location of teamDatas.value if I do orderedTeamList = teamDatas.value!
    var orderedTeamList : MutableState<List<TeamData>> = remember { mutableStateOf(listOf()) }
    if(orderedTeamList.value.size != 5) {
        for(team in teamDatas.value) {
            orderedTeamList.value += team
        }
    }

    // Since "Hatch Panel Check" is the initial value in the dropdown when the user enters the page, the logic for ranking each team in regard to "Hatch Panel Check" must be run.
    if(firstTimeBooting) {
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
        firstTimeBooting = false
    }

    chosenFilter = true

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

                            var teams = teamDatas.value

                            var totalLowAssetOfTeams : List<Int> = listOf(teams[0].getLowGoalAssets(), teams[1].getLowGoalAssets(),
                                teams[2].getLowGoalAssets(), teams[3].getLowGoalAssets(), teams[4].getLowGoalAssets()) as? List<Int> ?: listOf()

                            for(lowAssets in totalLowAssetOfTeams) {
                                teamValues.value += totalLowAssetOfTeams.max()
                                for(team in teams) {
                                    if(team.getLowGoalAssets() == totalLowAssetOfTeams.max()) {
                                        orderedTeamList.value += team
                                        teams -= team
                                        break
                                    }
                                }
                                totalLowAssetOfTeams -= totalLowAssetOfTeams.max()
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

                            var teams = teamDatas.value

                            var totalMiddleAssetOfTeams : List<Int> = listOf(teams[0].getMiddleGoalAssets(), teams[1].getMiddleGoalAssets(),
                                teams[2].getMiddleGoalAssets(), teams[3].getMiddleGoalAssets(), teams[4].getMiddleGoalAssets()) as? List<Int> ?: listOf()

                            for(middleAssets in totalMiddleAssetOfTeams) {
                                teamValues.value += totalMiddleAssetOfTeams.max()
                                for(team in teams) {
                                    if(team.getMiddleGoalAssets() == totalMiddleAssetOfTeams.max()) {
                                        orderedTeamList.value += team
                                        teams -= team
                                        break
                                    }
                                }
                                totalMiddleAssetOfTeams -= totalMiddleAssetOfTeams.max()
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

                            var teams = teamDatas.value

                            var totalHighAssetOfTeams : List<Int> = listOf(teams[0].getHighGoalAssets(), teams[1].getHighGoalAssets(),
                                teams[2].getHighGoalAssets(), teams[3].getHighGoalAssets(), teams[4].getHighGoalAssets()) as? List<Int> ?: listOf()

                            for(highAssets in totalHighAssetOfTeams) {
                                teamValues.value += totalHighAssetOfTeams.max()
                                for(team in teams) {
                                    if(team.getHighGoalAssets() == totalHighAssetOfTeams.max()) {
                                        orderedTeamList.value += team
                                        teams -= team
                                        break
                                    }
                                }
                                totalHighAssetOfTeams -= totalHighAssetOfTeams.max()
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

                            var teams = teamDatas.value

                            var totalAssetOfTeams : List<Int> = listOf(teams[0].totalAssets.intValue, teams[1].totalAssets.intValue,
                                teams[2].totalAssets.intValue, teams[3].totalAssets.intValue, teams[4].totalAssets.intValue) as? List<Int> ?: listOf()

                            for(totalAssets in totalAssetOfTeams) {
                                teamValues.value += totalAssetOfTeams.max()
                                for(team in teams) {
                                    if(team.totalAssets.value == totalAssetOfTeams.max()) {
                                        orderedTeamList.value += team
                                        teams -= team
                                        break
                                    }
                                }
                                totalAssetOfTeams -= totalAssetOfTeams.max()
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

                            for(totalPoints in totalPointOfTeams) {
                                teamValues.value += totalPointOfTeams.max()
                                for(team in teams) {
                                    if(team.totalPoints.value == totalPointOfTeams.max()) {
                                        orderedTeamList.value += team
                                        teams -= team
                                    }
                                }
                                totalPointOfTeams -= totalPointOfTeams.max()
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

                            var teams = teamDatas.value

                            for(team in teams) {
                                if(team.mountUnmountCheck.value) {
                                    orderedTeamList.value += team
                                    teamValues.value += (team.mountUnmountCheck.value)
                                    teams -= (team)
                                }
                            }
                            for(team in teams) {
                                orderedTeamList.value += (team)
                                teamValues.value += (team.mountUnmountCheck.value)
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

                            var teams = teamDatas.value

                            for(team in teams) {
                                if(team.fitInBoxCheck.value) {
                                    orderedTeamList.value += team
                                    teamValues.value += (team.fitInBoxCheck.value)
                                    teams -= (team)
                                }
                            }
                            for(team in teams) {
                                orderedTeamList.value += (team)
                                teamValues.value += (team.fitInBoxCheck.value)
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

                            var teams = teamDatas.value

                            for(team in teams) {
                                if(team.weightCheck.value) {
                                    orderedTeamList.value += team
                                    teamValues.value += (team.weightCheck.value)
                                    teams -= (team)
                                }
                            }
                            for(team in teams) {
                                orderedTeamList.value += (team)
                                teamValues.value += (team.weightCheck.value)
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

                            var teams = teamDatas.value

                            for(team in teams) {
                                if(team.motorCheck.value) {
                                    orderedTeamList.value += team
                                    teamValues.value += (team.motorCheck.value)
                                    teams -= (team)
                                }
                            }
                            for(team in teams) {
                                orderedTeamList.value += (team)
                                teamValues.value += (team.motorCheck.value)
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

                            var teams = teamDatas.value

                            var totalBonusPointOfTeams : List<Int> = listOf(teams[0].totalBonusPoints.intValue, teams[1].totalBonusPoints.intValue,
                                teams[2].totalBonusPoints.intValue, teams[3].totalBonusPoints.intValue, teams[4].totalBonusPoints.intValue) as? List<Int> ?: listOf()

                            for(totalAssets in totalBonusPointOfTeams) {
                                teamValues.value += totalBonusPointOfTeams.max()
                                for(team in teams) {
                                    if(team.totalBonusPoints.value == totalBonusPointOfTeams.max()) {
                                        orderedTeamList.value += team
                                        teams -= team
                                        break
                                    }
                                }
                                totalBonusPointOfTeams -= totalBonusPointOfTeams.max()
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
                    .padding(top = 50.dp)
            ) {

                Column(
                    modifier = modifier
                        .padding(end = 25.dp),
                    horizontalAlignment = Alignment.End
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
                        .padding(start = 25.dp),
                    horizontalAlignment = Alignment.Start
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