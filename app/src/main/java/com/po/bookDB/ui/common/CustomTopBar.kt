package com.po.bookDB.ui.common

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun CustomTopBar(
    title: String = "",
    onNavigation: @Composable () -> Unit = {},
    onActions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = {
            if (title.length > 20) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    ),
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            } else {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        navigationIcon = onNavigation,
        actions = onActions,
        backgroundColor = Color.Transparent
    )
}
