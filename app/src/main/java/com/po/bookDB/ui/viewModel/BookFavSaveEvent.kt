package com.po.bookDB.ui.viewModel

import com.po.bookDB.ui.model.BookEntity


sealed class BookEvent {
   data class  SaveFavBook (val bookObj : BookEntity): BookEvent()
    data class ShowSnackBar(val message: String) : BookEvent()
}

sealed class LoginEvent{
    object LoginSuccess: LoginEvent()
}

sealed class SignUpEvent{
    object SignUpSuccess: SignUpEvent()
}