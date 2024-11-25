import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf

// NOTE: This file is where the teamDatas list (a list that contains the data for all 5 competing teams in the
// competition) and the TeamData class (the individual scores and points for a team, and the variable that
// teamDatas stores).)

val teamDatas : MutableState<List<TeamData>> = mutableStateOf(listOf(
    TeamData(mutableStateOf("BlackRock"),mutableStateOf(false), mutableStateOf(""), mutableStateOf(""), mutableStateOf(""), mutableIntStateOf(0), mutableIntStateOf(0), mutableStateOf(false),
        mutableStateOf(false), mutableStateOf(false), mutableStateOf(false), mutableIntStateOf(0)),
    TeamData(mutableStateOf("Vanguard"), mutableStateOf(false), mutableStateOf(""), mutableStateOf(""), mutableStateOf(""), mutableIntStateOf(0), mutableIntStateOf(0), mutableStateOf(false),
        mutableStateOf(false), mutableStateOf(false), mutableStateOf(false), mutableIntStateOf(0)),
    TeamData(mutableStateOf("Fidelity"), mutableStateOf(false), mutableStateOf(""), mutableStateOf(""), mutableStateOf(""), mutableIntStateOf(0), mutableIntStateOf(0), mutableStateOf(false),
        mutableStateOf(false), mutableStateOf(false), mutableStateOf(false), mutableIntStateOf(0)),
    TeamData(mutableStateOf("JPMorgan"), mutableStateOf(false), mutableStateOf(""), mutableStateOf(""), mutableStateOf(""), mutableIntStateOf(0), mutableIntStateOf(0), mutableStateOf(false),
        mutableStateOf(false), mutableStateOf(false), mutableStateOf(false), mutableIntStateOf(0)),
    TeamData(mutableStateOf("State Street"), mutableStateOf(false), mutableStateOf(""), mutableStateOf(""), mutableStateOf(""), mutableIntStateOf(0), mutableIntStateOf(0), mutableStateOf(false),
        mutableStateOf(false), mutableStateOf(false), mutableStateOf(false), mutableIntStateOf(0))))

public data class TeamData(
    val teamName : MutableState<String>,

    var hatchPanelCheck : MutableState<Boolean>,
    var lowGoalAssets : MutableState<String>,
    var middleGoalAssets : MutableState<String>,
    var highGoalAssets : MutableState<String>,
    var totalAssets : MutableIntState,
    var totalPoints : MutableIntState,

    var mountUnmountCheck : MutableState<Boolean>,
    var fitInBoxCheck : MutableState<Boolean>,
    var weightCheck : MutableState<Boolean>,
    var motorCheck : MutableState<Boolean>,
    var totalBonusPoints : MutableIntState
) {

    // These 3 functions output the low, middle, and high asset scores of an individual team as integers.
    // Because the text fields for low, middle, and high goal assets in the DataEntryMenu page are strings and can
    // be stored as "", if any of those assets are attempted to be converted to integers using .Int(), it results
    // in an error. These functions detect if an asset is being stored as "", and returns 0 if it is.

    fun getLowGoalAssets() : Int {
        if(lowGoalAssets.value == "") {
            return 0
        } else {
            return lowGoalAssets.value.toInt()
        }
    }

    fun getMiddleGoalAssets() : Int {
        if(middleGoalAssets.value == "") {
            return 0
        } else {
            return middleGoalAssets.value.toInt()
        }
    }

    fun getHighGoalAssets() : Int {
        if(highGoalAssets.value == "") {
            return 0
        } else {
            return highGoalAssets.value.toInt()
        }
    }

}