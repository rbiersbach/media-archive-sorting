package de.rbiersbach.mas.adapters.files

import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.{Files, Path => NioPath}
import java.time.{LocalDateTime, ZoneId}
import scala.collection.JavaConverters._
import scala.language.implicitConversions

extension (path: Path)
  def toNioPath: NioPath = NioPath.of(path.head, path.tail: _*)

extension (nioPath: NioPath)
  def toPath: Path = Path(nioPath.toString.split('/').toList)


object NioFileStreamAdapter extends FileStreamAdapter :
  def streamResourcesDepthSearch(rootPath: Path): Iterator[Resource] =
    val resourceIterator = Files.walk(rootPath.toNioPath).iterator().asScala
      .map(nioPath =>
        nioPath -> Files.readAttributes[BasicFileAttributes](nioPath, classOf[BasicFileAttributes]))
      .map {
        case (nioPath, basicAttributes) if basicAttributes.isDirectory =>
          Folder.fromPath(
            path = nioPath.toPath,
            rootPath = rootPath)
        case (nioPath, basicAttributes) if basicAttributes.isRegularFile =>
          File.fromPath(
            path = nioPath.toPath,
            rootPath = rootPath,
            creationTime = LocalDateTime.ofInstant(basicAttributes.creationTime().toInstant, ZoneId.systemDefault())
          )
      }
    resourceIterator

