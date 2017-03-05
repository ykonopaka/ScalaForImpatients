import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}
import java.nio.file.Paths

/**
  * Created by Eugene on 3/5/2017.
  */
class Friend(val id : Long) extends Serializable {
  private var fr : Friend = null;

  def friend_=(newFriend: Friend) {
    this.fr = newFriend;
  }

  def friend = this.fr
}

object Main extends App {
  val currDir = Paths.get("c:\\Users\\Eugene\\IdeaProjects\\ScalaForImpatients\\src")
  val file = currDir.resolve("friends.obj").toString;

  val fr1 = new Friend(1L)
  val fr2 = new Friend(2L)
  val fr3 = new Friend(3L)

  fr1.friend = fr2
  fr3.friend = fr1

  val friends = Array(fr1, fr2, fr3)

  val outObj = new ObjectOutputStream(
    new FileOutputStream(file))

  outObj.writeObject(friends)
  outObj.close()

  val in = new ObjectInputStream(new FileInputStream(file))
  val deserialized = in.readObject.asInstanceOf[Array[Friend]]

  println(deserialized)
}