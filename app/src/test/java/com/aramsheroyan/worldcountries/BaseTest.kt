/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries

import androidx.annotation.NonNull
import com.aramsheroyan.worldcountries.ui.RxImmediateSchedulerRule
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

abstract class BaseTest {

    var scheduler: Scheduler = defaultTestScheduler()
        set(value) {
            schedulers = RxImmediateSchedulerRule(value)
        }

    @Rule
    @JvmField
    var schedulers = RxImmediateSchedulerRule(scheduler)

    @Before
    fun testSetup() {
        MockitoAnnotations.initMocks(this)
    }

}

fun defaultTestScheduler() = object : Scheduler() {
    override fun scheduleDirect(@NonNull run: Runnable, delay: Long, @NonNull unit: TimeUnit): Disposable {
        // this prevents StackOverflowErrors when scheduling with a delay
        return super.scheduleDirect(run, 0, unit)
    }

    override fun createWorker(): Worker {
        return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, false)
    }
}