package com.kzqzd.composeapplication

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testStateFlow(): Unit = runBlocking {
//        val mutableStateFlow = MutableStateFlow(0)
//        val sharedFlow: StateFlow<Int> = mutableStateFlow
        val sharedFlow = MutableSharedFlow<Int>()
        launch {
            sharedFlow.collect { value ->
                println("Collector 1 received: $value time = ${System.currentTimeMillis()}")
            }
        }
        launch {
            sharedFlow.collect { value ->
                println("Collector 2 received: $value time = ${System.currentTimeMillis()}")
            }
        }
        launch {
            repeat(3){ i->
//                mutableStateFlow.value = i
                sharedFlow.emit(i)
            }
        }
        callbackFlow<> {  }
    }
}
