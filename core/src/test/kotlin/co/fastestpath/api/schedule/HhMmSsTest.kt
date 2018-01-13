package co.fastestpath.api.schedule

import org.testng.Assert.assertEquals
import org.testng.Assert.assertNotEquals
import org.testng.annotations.Test
import java.lang.IllegalArgumentException

class HhMmSsTest {

  @Test
  fun testCompareTo() {
    val greater = HhMmSs("00:00:01")
    val lesser = HhMmSs("00:00:00")
    assertEquals(greater.compareTo(lesser), 1)
    assertEquals(lesser.compareTo(greater), -1)
    assertEquals(greater.compareTo(greater), 0)
  }

  @Test
  fun testHashCode() {
    val value = "01:01:01"
    val left = HhMmSs(value)
    val right = HhMmSs(value)
    val other = HhMmSs("01:01:02")
    assertEquals(left.hashCode(), right.hashCode())
    assertNotEquals(left.hashCode(), other.hashCode())
  }

  @Test
  fun testEquals() {
    val value = "01:01:01"
    val left = HhMmSs(value)
    val right = HhMmSs(value)
    val other = HhMmSs("01:01:02")
    assertEquals(left, right)
    assertNotEquals(left, other)
  }

  @Test(expectedExceptions = [IllegalArgumentException::class])
  fun testInvalidFormat() {
    HhMmSs("01-01-01")
  }

  @Test(expectedExceptions = [IllegalArgumentException::class])
  fun testBlank() {
    HhMmSs("")
  }
}
