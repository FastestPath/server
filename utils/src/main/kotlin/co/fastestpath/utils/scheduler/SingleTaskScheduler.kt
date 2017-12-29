package co.fastestpath.utils.scheduler

import java.time.Duration
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

class SingleTaskScheduler : TaskScheduler {

  companion object {
    val THREAD_POOL_SIZE = 1
  }

  private val scheduledPool = Executors.newScheduledThreadPool(THREAD_POOL_SIZE)

  private var task: ScheduledFuture<*>? = null

  override fun scheduleAtRate(runnable: Runnable, rate: Duration) {
    task = scheduledPool.scheduleAtFixedRate(runnable, 0, rate.toNanos(), TimeUnit.NANOSECONDS)
  }

  override fun cancel() {
    task?.cancel(true)
  }
}
