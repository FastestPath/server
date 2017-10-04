package co.fastestpath.api.bootstrap.schedule

import org.testng.Assert.assertEquals
import org.testng.Assert.assertTrue
import org.testng.annotations.Test

@Test
fun testAgencyIdCache() {
  val value = "abc"
  val a = AgencyId.of(value)
  val b = AgencyId.of(value)
  assertEquals(a, b)
  assertTrue(a == b)
}


