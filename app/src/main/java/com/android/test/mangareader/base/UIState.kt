package com.android.test.mangareader.base

sealed class UIState<out T> {
    object Loading: UIState<Nothing>()
    data class Error(val message: String) : UIState<Nothing>()
    data class Success<T>(val data: T): UIState<T>()
    data class Info(val message: String): UIState<Nothing>()
}