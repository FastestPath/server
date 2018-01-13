package co.fastestpath.utils

import org.testng.Assert.*
import org.testng.annotations.Test
import java.text.ParseException
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

class SimpleDateTest {

  companion object {
    private val EPOCH: Date = Date.from(Instant.EPOCH)
    private val EPOCH_STRING: String = SimpleDate.FORMAT.format(EPOCH)

    private fun simpleDateOf(instant: Instant): SimpleDate {
      val instantAsString = SimpleDate.FORMAT.format(Date.from(instant))
      return SimpleDate(instantAsString)
    }
  }

  @Test
  fun testValidDate() {
    SimpleDate(EPOCH_STRING)
  }

  @Test(expectedExceptions = [ParseException::class])
  fun testInvalidFormat() {
    SimpleDate("2000")
  }

  @Test
  fun testEquals() {
    assertEquals(SimpleDate(EPOCH_STRING), SimpleDate(EPOCH_STRING))
  }

  @Test
  fun testNotEqual() {
    assertNotEquals(SimpleDate("20000101"), SimpleDate(EPOCH_STRING))
  }

  @Test
  fun testIsWithinRangeExclusive() {
    val now = Instant.now()
    val date = simpleDateOf(now)
    val start = simpleDateOf(now.minus(1, ChronoUnit.DAYS))
    val end = simpleDateOf(now.plus(1, ChronoUnit.DAYS))
    assertTrue(date.isWithinRange(start, end))
  }

  @Test
  fun testIsWithinRangeInclusive() {
    val now = Instant.now()
    val date = simpleDateOf(now)
    val start = simpleDateOf(now)
    val end = simpleDateOf(now.plus(1, ChronoUnit.DAYS))
    assertTrue(date.isWithinRange(start, end))
  }

  @Test
  fun testIsBeforeRange() {
    val now = Instant.now()
    val date = simpleDateOf(now)
    val start = simpleDateOf(now.plus(1, ChronoUnit.DAYS))
    val end = simpleDateOf(now.plus(2, ChronoUnit.DAYS))
    assertFalse(date.isWithinRange(start, end))
  }

  @Test
  fun testIsAfterRange() {
    val now = Instant.now()
    val date = simpleDateOf(now)
    val start = simpleDateOf(now.minus(1, ChronoUnit.DAYS))
    val end = simpleDateOf(now.minus(2, ChronoUnit.DAYS))
    assertFalse(date.isWithinRange(start, end))
  }
}
