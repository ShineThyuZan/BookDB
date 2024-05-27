package com.po.bookDB.ui.books

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.po.bookDB.R
import com.po.bookDB.ui.theme.resources.dimen

@Composable
fun SearchTopBar(
    onNavigation: @Composable () -> Unit = {},
    onActions: @Composable RowScope.() -> Unit = {},
    searchQuery: String = "",
    searchPlaceholder: String = stringResource(id = R.string.search),
    onTextChanged: (String) -> Unit = {},
    onSearchClicked: (String) -> Unit = {},
    onClearSearchQuery: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = MaterialTheme.dimen.base_6x)
                    .padding(end = MaterialTheme.dimen.base_2x)
                    .clip(shape = CircleShape)
                    .background(color = MaterialTheme.colorScheme.surface)
                    .padding(start = MaterialTheme.dimen.base_2x),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_small_search),
                    contentDescription = null
                )

                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(
                            weight = 1f,
                            fill = false
                        ),
                    onValueChange = onTextChanged,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    value = searchQuery,
                    decorationBox = {
                        if (searchQuery.isEmpty()) {
                            Text(
                                text = searchPlaceholder,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        it()
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onSearchClicked(searchQuery)
                        }
                    ),
                    cursorBrush = SolidColor(
                        value = MaterialTheme.colorScheme.primary
                    )
                )

                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = onClearSearchQuery) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_cross),
                            contentDescription = null
                        )
                    }
                }
            }
        },
        navigationIcon = onNavigation,
        actions = onActions,
    )
}
