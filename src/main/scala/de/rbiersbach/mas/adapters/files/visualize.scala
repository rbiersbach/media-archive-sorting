package de.rbiersbach.mas.adapters.files

import java.time.format.DateTimeFormatter

extension (resource: Resource)
  def visualize: String =
    resource match {
      case File(_, name, depth, _, creationTime) =>
        val indendation = "  ".repeat(depth)
        val time = creationTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        f"$indendation|$name $time"
      case Folder(_, name, depth) =>
        val indendation = "==".repeat(depth)
        f"$indendation $name"
    }


