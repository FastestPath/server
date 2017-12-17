package co.fastestpath.api

enum class Environment {
  PRODUCTION,
  DEVELOPMENT,
  TEST
}

fun environmentOf(value: String) = Environment.valueOf(value.toUpperCase())
