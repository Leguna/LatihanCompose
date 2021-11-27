package site.arksana.latihancompose.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import site.arksana.latihancompose.ui.theme.LatihanComposeTheme
import site.arksana.latihancompose.utils.firstBaselineToTop

@Preview(showBackground = true)
@Composable
fun TextWithPaddingToBaselinePreview() {
    LatihanComposeTheme {
        Text("Hi there!", Modifier.firstBaselineToTop(32.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun TextWithNormalPaddingPreview() {
    LatihanComposeTheme {
        Text("Hi there!", Modifier.padding(top = 32.dp))
    }
}

@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        // Don't constrain child views further, measure them with given constraints
        // List of measured children
        val placeables = measurables.map { measurable ->
            // Measure each child
            measurable.measure(constraints)
        }

        // Track the y co-ord we have placed children up to
        var yPosition = 0
        var xPosition = 0

        // Set the size of the layout as big as it can
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Place children in the parent layout
            placeables.forEach { placeable ->
                // Position item on the screen
                placeable.placeRelative(x = xPosition, y = yPosition)

                // Record the y co-ord placed up to
                yPosition += placeable.height + 4.dp.toPx().toInt()
                xPosition += 4.dp.toPx().toInt()
            }
        }
    }
}

@Composable
fun BodyContent2(modifier: Modifier = Modifier) {
    Column {

        Column(modifier.padding(8.dp)) {
            Text("MyOwnColumn")
            Text("places items")
            Text("vertically.")
            Text("We've done it by hand!")
        }
        MyOwnColumn(modifier.padding(8.dp)) {
            Text("MyOwnColumn")
            Text("places items")
            Text("vertically.")
            Text("We've done it by hand!")
        }
    }
}

@Preview(showBackground = true, heightDp = 400)
@Composable
fun BodyContent2Preview() {
    LatihanComposeTheme {
        LayoutsCodelabWithHeader {
            BodyContent2(it)
        }
    }
}


