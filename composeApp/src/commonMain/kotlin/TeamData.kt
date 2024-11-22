import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf

var teamDatas : MutableState<List<TeamData>> = mutableStateOf(List<TeamData>(size = 5){
    TeamData(mutableStateOf("BlackRock"), mutableStateOf(false), mutableStateOf(""), mutableStateOf(""), mutableStateOf(""), mutableIntStateOf(0), mutableStateOf(false),
        mutableStateOf(false), mutableStateOf(false), mutableStateOf(false), mutableIntStateOf(0));
    TeamData(mutableStateOf("Vanguard"), mutableStateOf(false), mutableStateOf(""), mutableStateOf(""), mutableStateOf(""), mutableIntStateOf(0), mutableStateOf(false),
        mutableStateOf(false), mutableStateOf(false), mutableStateOf(false), mutableIntStateOf(0));
    TeamData(mutableStateOf("Fidelity"), mutableStateOf(false), mutableStateOf(""), mutableStateOf(""), mutableStateOf(""), mutableIntStateOf(0), mutableStateOf(false),
        mutableStateOf(false), mutableStateOf(false), mutableStateOf(false), mutableIntStateOf(0));
    TeamData(mutableStateOf("JPMorgan"), mutableStateOf(false), mutableStateOf(""), mutableStateOf(""), mutableStateOf(""), mutableIntStateOf(0), mutableStateOf(false),
        mutableStateOf(false), mutableStateOf(false), mutableStateOf(false), mutableIntStateOf(0));
    TeamData(mutableStateOf("State Street"), mutableStateOf(false), mutableStateOf(""), mutableStateOf(""), mutableStateOf(""), mutableIntStateOf(0), mutableStateOf(false),
        mutableStateOf(false), mutableStateOf(false), mutableStateOf(false), mutableIntStateOf(0))})

data class TeamData(
    val teamName : MutableState<String>,

    var hatchPanelCheck : MutableState<Boolean>,
    var lowGoalPoints : MutableState<String>,
    var middleGoalPoints : MutableState<String>,
    var highGoalPoints : MutableState<String>,
    var totalPoints : MutableIntState,

    var mountUnmountCheck : MutableState<Boolean>,
    var fitInBoxCheck : MutableState<Boolean>,
    var weightCheck : MutableState<Boolean>,
    var motorCheck : MutableState<Boolean>,
    var totalBonusPoints : MutableIntState
) {

    fun getName() : String {
        return teamName.value
    }

}