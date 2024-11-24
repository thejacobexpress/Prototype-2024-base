import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf

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