package com.example.habitmanager

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.RowScopeInstance.weight
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habitmanager.ui.theme.HabitManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitManagerTheme() {
                Column(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    ActivitiesSection()
                    habitsSection()
                }

            }
        }
    }
}

@Composable
fun ActivitiesSection(
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(
            text = "Activities".toUpperCase(),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        Conversation(
            listOf(
                Message("nika", "nika completed task: Special habit 2 unnamed"),
                Message("gio", "nika completed task: Writing"),
                Message("bero", "nika completed task: Running"),
                Message("luka", "nika completed task: Waking up"),
                Message("someone", "nika completed task: Reading")
            )
        )
    }
}

@Composable
fun habitsSection(
    modifier: Modifier = Modifier
){
    Column(modifier) {
        HabitsScreen(
            listOf(
                Habit("wake up", "wake up at 8AM", false),
                Habit("workout", "Work out for 30 minute", false),

                Habit(
                    "reading",
                    "read something for 30 minutes",
                    false
                ),
                Habit("study java", "do leetcode for 1hr", false)
            )
        )
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn() {
        items(messages) {
            MessageCard(it)
        }
    }
}

@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.round_account_circle_24),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .padding(end = 3.dp, bottom = 3.dp, start = 3.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Column(
            modifier = Modifier
                .weight(15f)
                .padding(end = 64.dp)
        ) {
            Text(
                text = msg.at,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(1.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
            ) {
                Text(
                    text = msg.body,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = 7,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Spacer(modifier = Modifier.padding(end = 5.dp))

        Box(modifier = Modifier.padding(end = 15.dp, top = 10.dp)) {
            Text(
                text = "50",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .background(color = colorResource(R.color.darkGreen), shape = CircleShape)
                    .padding(all = 3.dp)
            )
        }
    }
}


@Composable
fun HabitsScreen(
    items: List<Habit>,
) {
    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
    ) {
        item { HeaderComposable(title = "name") }
        items(items) { item ->
            HabitsCard(item)
        }
    }
}

@Composable
fun HeaderComposable(title: String) {
    Text(
        text = title.toUpperCase(),
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier
            .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
            .padding(horizontal = 16.dp)
    )
}

@Composable
fun HabitsCard(
    habit: Habit
) {
    Surface(
        shadowElevation = 1.dp,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp)
            .clip(CircleShape)
    ) {
        Column(
            modifier = Modifier
                .padding(end = 64.dp, start = 8.dp)
        ) {
            Text(
                text = habit.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 7.dp, top = 4.dp, bottom = 4.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = habit.description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 7.dp, top = 4.dp, bottom = 4.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
        Row(modifier = Modifier.padding(start = 300.dp)) {
            Checkbox(
                checked = habit.isCompleted,
                onCheckedChange = {

                },
                enabled = true,

            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun HabitsScreenPreview() {
    HabitManagerTheme() {
        HabitsScreen(
            listOf(
                Habit("wake up", "wake up at 8AM", false),
                Habit("workout", "Work out for 30 minute", false),

                Habit(
                    "reading",
                    "read something for 30 minutes",
                    false
                ),
                Habit("study java", "do leetcode for 1hr", false)
            )
        )
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun ActivitiesSectionPreview() {
    HabitManagerTheme() {
        ActivitiesSection()
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    HabitManagerTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            MessageCard(Message("Lexi", "Take a look at this view its nice testing testing"))
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    HabitManagerTheme() {
        Conversation(
            listOf(
                Message("nika", "nika completed task: Reading"),
                Message("gio", "nika completed task: Writing"),
                Message("bero", "nika completed task: Running"),
                Message("luka", "nika completed task: Waking up"),
                Message("someone", "nika completed task: Reading")
            )
        )
    }
}

data class Message(
    val at: String,
    val body: String
)

data class Habit(
    val name: String,
    val description: String,
    val isCompleted: Boolean
)

sealed class ListItem {
    data class HeaderItem(val title: String) : ListItem()
    data class HabitItem(val message: Habit) : ListItem()
}