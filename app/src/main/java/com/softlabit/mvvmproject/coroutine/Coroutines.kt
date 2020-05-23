package com.softlabit.mvvmproject.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

object Coroutines {
    fun main(work: suspend (() -> Unit)): Job =
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }
}