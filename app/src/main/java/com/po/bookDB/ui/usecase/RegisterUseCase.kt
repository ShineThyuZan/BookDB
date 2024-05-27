package com.po.bookDB.ui.usecase

import com.po.bookDB.ui.auth.AuthRepository
import com.po.bookDB.ui.common.DataResult
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val dsRepo: AuthRepository
) {
    suspend operator fun invoke(
        userName: String,
        password: String
    ): DataResult<Boolean> {
        return dsRepo.createUser(email = userName,password = password)
    }
}