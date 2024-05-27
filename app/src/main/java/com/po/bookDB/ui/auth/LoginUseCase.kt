package com.po.bookDB.ui.auth

import com.po.bookDB.ui.common.DataResult
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val dsRepo: AuthRepository
) {
    suspend operator fun invoke(
        userName: String,
        password: String
    ): DataResult<Boolean> {
        return dsRepo.login(email = userName, password = password)
    }
}
