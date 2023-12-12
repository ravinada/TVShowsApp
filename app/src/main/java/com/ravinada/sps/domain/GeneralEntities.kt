package com.ravinada.sps.domain

class InvalidExceptionGeneral(message: String) : Exception(message)
class NoConnectivityException(message: String) : Exception(message)
data class GeneralErrorResponse(
    val code: Int,
    val message: String,
    val error: Error
)
