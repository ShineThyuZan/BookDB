package com.po.bookDB.ui.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.po.bookDB.ui.auth.FavBookInsertUseCase
import com.po.bookDB.ui.books.BookUiState
import com.po.bookDB.ui.books.FavListUseCase
import com.po.bookDB.ui.common.Resource
import com.po.bookDB.ui.paging.GetBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val useCaseFavInsert : FavBookInsertUseCase,
    private val useCase: GetBookUseCase,
    private val favListUseCase: FavListUseCase
) : ViewModel() {

    private val _bookApiData: MutableState<BookApiData> = mutableStateOf(BookApiData())
    val bookApiData: State<BookApiData> get() = _bookApiData

    private val _bookEvent = MutableSharedFlow<BookEvent>()
    val bookEvent: SharedFlow<BookEvent> get() = _bookEvent

    private val _bookUiState = mutableStateOf(BookUiState())
    val bookUiState: State<BookUiState> get() = _bookUiState

    fun onBookAction(action: BookFavAction) {
        when (action) {
            is BookFavAction.ClickFav -> {
                viewModelScope.launch {
                        _bookEvent.emit(
                            BookEvent.ShowSnackBar(
                                message = "${action.bookObj.bookTitle} favorite"
                            )
                        )
                    useCaseFavInsert.invoke(action.bookObj)
                }
            }
        }
    }
    fun favList(){
        viewModelScope.launch {
            favListUseCase().collect { favList ->
                _bookApiData.value = bookApiData.value.copy(
                    favList = favList
                )
            }
        }
    }


    fun getBookApi() {
        viewModelScope.launch {
            _bookApiData.value = bookApiData.value.copy(
                books = Resource.Loading()
            )
            useCase(query = "of").collect { bookData ->
                bookData.let {
                    _bookApiData.value = bookApiData.value.copy(
                        books = bookData
                    )
                }
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _bookUiState.value = bookUiState.value.copy(
            query =  query
        )
    }

    private var searchJob: Job? = null
     fun search(){
        searchJob?.cancel()
        searchJob =viewModelScope.launch {
            useCase(query = bookUiState.value.query).collect {
                _bookApiData.value = bookApiData.value.copy(
                    searchData = it
                )
            }
        }
    }
}