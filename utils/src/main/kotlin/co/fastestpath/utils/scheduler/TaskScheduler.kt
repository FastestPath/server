package co.fastestpath.utils.scheduler

import java.time.Duration

interface TaskScheduler {
  fun scheduleAtRate(runnable: Runnable, rate: Duration)
  fun cancel()
}
