case class PocketEntry(
  url: String,
  time: Long,
  title: Option[String],
  tags: Set[String],
)

case class Credentials(
  consumer_key: String,
  access_token: String
)

case class Pocket(credentials: Credentials) {

  import com.softwaremill.sttp._
  import io.circe.syntax._
  import io.circe.generic.auto._

  def add(entry: PocketEntry): Request[String, Nothing] =
    sttp.post(uri"https://getpocket.com/v3/add").body(entry)

  private lazy val creds = credentials.asJson

  private implicit val entrySerializer:  BodySerializer[PocketEntry] = p => {
    val json: String = (p.asJson deepMerge creds).spaces2
    StringBody(json, "UTF-8", Some("application/json"))
  }

}
