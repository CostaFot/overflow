package com.feelsokman.androidtemplate.overflow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FlowGuy @Inject constructor() {

    private val humans = mutableListOf<Human>()

    val someFlow: Flow<List<Human>> = flow {
        for (i in 1..10000) {
            delay(1000)
            humans.add(0, Human(i))
            emit(humans)
        }
    }.flowOn(Dispatchers.IO)
}
