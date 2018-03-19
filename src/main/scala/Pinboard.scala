import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import cats.syntax.option._
import scala.collection.JavaConverters._

case class PinboardBookmark(
  url         : String,
  date        : Long,
  tags        : Set[String],
  description : Option[String],
  comment     : Option[String]
)

object Pinboard {

  def parseHTML(html: String): Seq[PinboardBookmark] = {

    // Bookmarks are in DT elements, which might be followed by a comment in a DD element
    val elements = Jsoup.parse(html).select("DT,DD").asScala.toList

    // Pair up the elements:
    val pairs: List[(Element,Element)] = elements zip elements.drop(1)

    def pairUp(pairs: List[(Element,Element)]): List[PinboardBookmark] =
      pairs match {
        case Nil                                    => Nil
        case (DT(bookmark1), DT(bookmark2)) :: Nil  => bookmark1 :: bookmark2 :: Nil
        case (DD(_), DT(bookmark))          :: Nil  => bookmark :: Nil
        case (DT(bookmark), DD(comment))    :: more => bookmark.copy(comment = comment.some) :: pairUp(more)
        case (DT(bookmark), DT(_))          :: more => bookmark :: pairUp(more)
        case _                              :: more => pairUp(more)
      }

    pairUp(pairs)
  }

  private object Tags {
    def from(csv: String): Set[String] =
      csv.split(",").toList.filter(_.nonEmpty).toSet
  }

  private object Text {
    def from(text: String): Option[String] =
      if (text.isEmpty) none else text.some
  }

  private object DT {
    def unapply(e: Element): Option[PinboardBookmark] =
      e.nodeName() match {
        case "dt" =>
          val a = e.childNode(0)
          PinboardBookmark(
            url         = a.attr("href"),
            date        = a.attr("add_date").toLong,
            tags        = Tags.from(a.attr("tags")),
            description = Text.from(e.text()),
            comment     = None
          ).some
        case _ => none
      }
  }

  private object DD {
    def unapply(e: Element): Option[String] =
      e.nodeName() match {
        case "dd" => e.text().some
        case _ => none
      }
  }

}
