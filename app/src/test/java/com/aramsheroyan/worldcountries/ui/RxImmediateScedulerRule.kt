/*
 * Created by Aram Sheroyan
 * https://github.com/aramsheroyan
 */

package com.aramsheroyan.worldcountries.ui

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class RxImmediateSchedulerRule(val immediate: Scheduler) : TestRule {
    override fun apply(base: Statement?, description: Description?): Statement {

        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxJavaPlugins.setIoSchedulerHandler { immediate }
                RxJavaPlugins.setComputationSchedulerHandler { immediate }
                RxJavaPlugins.setNewThreadSchedulerHandler { immediate }
                RxJavaPlugins.setSingleSchedulerHandler { immediate }
                RxAndroidPlugins.setMainThreadSchedulerHandler { immediate }

                try {
                    base?.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxAndroidPlugins.reset()
                }
            }
        }
    }
}