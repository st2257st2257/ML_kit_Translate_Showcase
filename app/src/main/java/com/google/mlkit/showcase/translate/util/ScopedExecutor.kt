package com.google.mlkit.showcase.translate.util

import android.os.Looper
import com.google.mlkit.showcase.translate.MainActivity
import com.google.mlkit.showcase.translate.get_Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import java.util.concurrent.Executor
import java.util.concurrent.atomic.AtomicBoolean

class ScopedExecutor(private val executor: Executor) : Executor {

    private val isShutdown = AtomicBoolean()

    fun shutdown() {
        isShutdown.set(true)
    }

    override fun execute(command: Runnable) {
        executor.execute {
            if ( !isShutdown.get() ) {
                command.run()
                println("command.run()")
            }
        }
    }
}