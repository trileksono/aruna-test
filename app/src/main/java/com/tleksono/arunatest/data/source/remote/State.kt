package com.tleksono.arunatest.data.source.remote

/**
 * Created by trileksono on 07/12/20
 */
sealed class State<out DATA> {
    data class Success<DATA>(val data: DATA) : State<DATA>()
    data class Failure<DATA>(val error: String = "") : State<DATA>()
    object Loading : State<Nothing>()
}
