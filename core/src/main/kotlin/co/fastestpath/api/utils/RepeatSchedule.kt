package co.fastestpath.api.utils

import java.time.Duration
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

class RepeatSchedule {

  private val scheduledPool = Executors.newScheduledThreadPool(1)

  var task: ScheduledFuture<*>? = null

  fun scheduleAtRate(runnable: Runnable, rate: Duration) {
    task = scheduledPool.scheduleAtFixedRate(runnable, 0, rate.toSeconds(), TimeUnit.SECONDS)
  }

  fun cancel() {
    task?.cancel(true)
  }

  fun finalize() {
    cancel()
  }
}

