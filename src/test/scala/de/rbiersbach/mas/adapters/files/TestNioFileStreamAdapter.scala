package de.rbiersbach.mas.adapters.files
import org.junit.Assert.*
import org.junit.Test

import java.time.LocalDateTime

class TestNioFileStreamAdapter:
  @Test def testStreamResourcesDepthSearch(): Unit =
    val currentPath = Path(List("src", "test", "scala", "de", "rbiersbach", "mas", "adapters"))
    val resultList = NioFileStreamAdapter.streamResourcesDepthSearch(currentPath).toList
    assert(resultList.contains(Folder(Path(List()), "adapters",0 )))
    assert(resultList.contains(Folder(Path(List("files")), "files",1 )))
    val testFile = resultList.find(_.name == "TestNioFileStreamAdapter.scala")
    assert(testFile.isDefined)
    testFile match {
      case Some(File(path, name, depth, extension, creationTime)) =>
        assertEquals(path, Path(List("files", "TestNioFileStreamAdapter.scala")))
        assertEquals(depth, 2)
        assertEquals(extension, Some(Extension("scala")))
      case _ => ()
    }
