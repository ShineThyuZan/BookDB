package com.po.bookDB.ui.books

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.po.bookDB.ui.model.BookEntity
import com.po.bookDB.R
import com.po.bookDB.ui.common.CustomVerticalSpacer
import com.po.bookDB.ui.common.Resource

@Composable
fun BookContent(
    modifier: Modifier = Modifier,
    resourceBook: Resource<List<BookEntity>>,
    onItemClicked: (BookEntity) -> Unit,
    onRetry: () -> Unit
) {

    Surface(color = androidx.compose.material3.MaterialTheme.colorScheme.background) {
        Column(
            modifier = modifier
                .wrapContentHeight().padding(top = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            when (resourceBook) {
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    BookListView(
                        book = resourceBook.data ?: listOf(),
                        onFavItemClick = {
                            onItemClicked(it)
                        },)
                }
            }
        }
    }
}


@Composable
fun BookListView(
    modifier: Modifier = Modifier,
    book: List<BookEntity>,
    onFavItemClick: (BookEntity) -> Unit,
) {
    LazyColumn(
        content = {
            items(
                count = book.size
            ) { index ->
                val currentItem = book[index]
                BookItem(
                    modifier = modifier,
                    title = currentItem.bookTitle,
                    subtitle = currentItem.bookSubtitle,
                    imageURl = currentItem.bookImage,
                    bookId = currentItem.bookId,
               //     favOrNotState = favOrNotState,
                    onFavItemClick = { onFavItemClick(currentItem) }
                )
            }
        }
    )
}

@Composable
fun BookItem(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    imageURl: String,
    bookId: String,
    onFavItemClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            model = imageURl,
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(12.dp))
                .padding(all = 12.dp)
                .clickable {
                    onFavItemClick()
                },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp),
                text = title,
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp),
                text = subtitle,
                style = MaterialTheme.typography.overline
            )
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp),
                text = bookId,
                style = MaterialTheme.typography.overline
            )
            Spacer(modifier = modifier.height(8.dp))
        }
    }
}

