package de.rbiersbach.mas.adapters.files

import java.time.LocalDateTime

opaque type Extension = String

object Extension:
  def apply(string: String): Extension = string

opaque type Path = List[String]

object Path:
  def apply(stringList: List[String]): Path = stringList

  def apply(string: String): Path = Path(string.split('/').toList)

  extension (path: Path)
    def head: String = path.head

    def tail: List[String] = path.tail

    def name: String = path.last

    def depth: Int = path.length

    def -(otherPath: Path): Path = otherPath.diff(path)

    def extension: Option[Extension] =
      name.split('.').toList match {
        case "" :: tail if tail.length < 2 => None
        case name :: List() => None
        case list => list.lastOption
      }

end Path

trait Resource:
  def path: Path

  def name: String

  def depth: Int


case class File(path: Path, name: String, depth: Int, extension: Option[Extension], creationTime: LocalDateTime) extends Resource

object File:
  def fromPath(path: Path, rootPath: Path, creationTime: LocalDateTime): File =
    File(
      path = rootPath - path,
      name = path.name,
      depth = path.depth - rootPath.depth,
      extension = path.extension,
      creationTime = creationTime
    )


case class Folder(path: Path, name: String, depth: Int) extends Resource

object Folder:
  def fromPath(path: Path, rootPath: Path): Folder =
    Folder(
      path = rootPath - path,
      name = path.name,
      depth = path.depth - rootPath.depth,
    )
   
