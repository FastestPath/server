package co.fastestpath.api.bootstrap.schedule

import org.testng.Assert.*
import org.testng.annotations.Test

class AgencyIdTest {
  @Test
  fun testCache() {
    val value = "abc"
    val a = AgencyId.of(value)
    val b = AgencyId.of(value)
    val c = AgencyId.of("other")
    assertEquals(a, b)
    assertTrue(a == b)
    assertFalse(a == c)
  }
}
