package co.fastestpath.api.bootstrap.schedule

import java.util.regex.Pattern

class HhMmSs(value: String) : Comparable<HhMmSs> {

  companion object {
    private val PATTERN = Pattern.compile("(?:[012345]\\d):(?:[012345]\\d):(?:[012345]\\d)")
  }

  private val seconds: Int

  init {
    if (!PATTERN.matcher(value).matches()) {
      throw IllegalArgumentException("Invalid HH:MM:SS format, " + value)
    }

    val (hours, minutes, seconds) = value.split(":")
    this.seconds = hours * 3600 + minutes * 60 + seconds
  }

  override fun compareTo(other: HhMmSs): Int {
    return seconds.compareTo(other.seconds)
  }

  override fun hashCode(): Int {
    return seconds
  }

  override fun equals(other: Any?): Boolean {
    if (other == null) {
      return false
    }

    if (!HhMmSs::class.java.isAssignableFrom(other::class.java)) {
      return false
    }

    val other = other as HhMmSs
    if (this.seconds != other.seconds) {
      return false
    }

    return true
  }
}
