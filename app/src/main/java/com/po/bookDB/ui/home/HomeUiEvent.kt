package com.po.bookDB.ui.home
sealed interface HomeUiEvent {
    data class ShowSnackBar(val message: String) : HomeUiEvent
}
