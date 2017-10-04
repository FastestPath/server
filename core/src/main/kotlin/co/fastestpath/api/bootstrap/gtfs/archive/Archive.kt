package co.fastestpath.api.bootstrap.gtfs.archive

import com.fasterxml.jackson.dataformat.csv.CsvSchema
import java.io.File

class Archive(val files: Map<ArchiveEntity, File>) {
  companion object {
    val SCHEMA = CsvSchema.emptySchema()
      .withColumnSeparator(',')
  }
}
