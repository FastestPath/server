package co.fastestpath.api.bootstrap.gtfs.archive.parse

import co.fastestpath.api.bootstrap.gtfs.archive.Archive
import co.fastestpath.api.bootstrap.gtfs.archive.ArchiveEntity
import co.fastestpath.gtfs.GtfsEntity
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import java.io.File
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EntityMapper @Inject constructor(private val mapper: CsvMapper) {

  @Throws(EntityMappingException::class)
  fun map(fileMap: Map<ArchiveEntity, File>): Map<ArchiveEntity, List<GtfsEntity>> {
    val entityMap = HashMap<ArchiveEntity, List<GtfsEntity>>()
    fileMap.forEach { entity, file -> entityMap.put(entity, mapToEntities(file, entity.clazz)) }
    return entityMap
  }

  @Throws(EntityMappingException::class)
  private fun <T : GtfsEntity> mapToEntities(entityFile: File, entityClass: Class<T>): List<T> {
    try {
      return mapper.readerFor(entityClass)
        .with(Archive.SCHEMA)
        .readValues<T>(entityFile)
        .readAll()
    } catch (e: IOException) {
      throw EntityMappingException("Unable to map csv to class.", e)
    }
  }
}
