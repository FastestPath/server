package co.fastestpath.api.utils

import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvParser
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

fun configuredCsvMapper(): CsvMapper {
  val mapper = com.fasterxml.jackson.dataformat.csv.CsvMapper()
  mapper.registerModule(JavaTimeModule())
  mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY)
 return mapper
}
