package com.po.bookDB.ui.common

object Constants {
    //for testing paging
    const val INITIAL_PAGE = 0
    const val ITEM_REQUEST = 10 //request items per page ( from RemoteServer )
    const val LOAD_SIZE = 30 //load data per page ( from PagingSource )

    const val ITEM_PER_PAGE = 20
    const val DB_NAME = "offlinedb"
    const val BOOK_TABLE = "booktable"
    const val REMOTE_KEY_TABLE = "remotekeytable"

    const val RESPONSE_SUCCESS = "SUCCESS"
    const val RESPONSE_FAIL = "FAIL"
}