package c11

import java.nio.file.Path

/**
  * Created by Eugene on 4/17/2017.
  */
class PathComponents(val path: Path)

object PathComponents {
  def apply(path: Path) = new PathComponents(path)

  def unapply(pathComponents: PathComponents): Option[(String, String)] = {
    Some(pathComponents.path.getParent.toString, pathComponents.path.getFileName.toString)
  }
}
