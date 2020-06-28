package znyoo.name.base

import kotlinx.coroutines.*
import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    @Test
    fun addition_isCorrect() {

        runBlocking {
            FuncDefault()
//            FuncLazy()
//            FuncAtomic()
//            FuncUndispatched()
        }

    }

    suspend fun FuncUndispatched() {
        log(1)
        val job = GlobalScope.launch(start = CoroutineStart.UNDISPATCHED) {
            log(2)
            delay(100)
            log(3)
        }
        log(4)
        job.join()
        log(5)
    }

    suspend fun FuncAtomic() {
        log(1)
        val job = GlobalScope.launch(start = CoroutineStart.ATOMIC) {
            log(2)
            delay(100)
            log(3)
        }
        job.cancel()
        log(4)
    }

    suspend fun FuncLazy() {
        log(1)
        val job = GlobalScope.launch(start = CoroutineStart.LAZY) {
            log(2)
        }
        log(3)
        job.join()
        log(4)
    }

    suspend fun FuncDefault() {
        log(1)
        val job = GlobalScope.launch {
            log(2)
            delay(100)
            log(5)
        }
        log(3)
        job.cancel()
        job.join()
        log(4)
    }

    val dateFormat = SimpleDateFormat("HH:mm:ss:SSS")

    val now = {
        dateFormat.format(Date(System.currentTimeMillis()))
    }

    fun log(msg: Any?) = println("${now()} [${Thread.currentThread().name}] $msg")

}
