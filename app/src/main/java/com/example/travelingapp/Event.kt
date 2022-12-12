package com.example.travelingapp


open class Event<T> {
    var handlers = listOf<(T) -> Unit>()

    infix fun on(handler: (T) -> Unit) {
        handlers += handler
    }

    fun emit(event: T) {
        for (subscriber in handlers) {
            subscriber(event)
        }
    }
}

data class UpdateEventData(val currentPath: Pair<List<Int>,Double>,val progress:Int, val best:Pair<List<Int>,Double>)

class UpdateEvent: Event<UpdateEventData>()