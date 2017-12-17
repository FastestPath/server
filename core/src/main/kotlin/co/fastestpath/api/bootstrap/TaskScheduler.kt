package co.fastestpath.api.bootstrap

import java.time.Duration

interface TaskScheduler {
  fun scheduleAtRate(runnable: Runnable, rate: Duration)
  fun cancel()
}