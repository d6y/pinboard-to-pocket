class PinboardSpec extends Spec {

  "Read Pinboard HTML into case classes" in {

    val actual = Pinboard.parseHTML(exampleHTML)

    val expected =
      PinboardBookmark("https://reedsy.com/about", 1521303406, Set("book","writing"), Some("We are Reedsy! | Reedsy"), None) ::
      PinboardBookmark("https://adactio-com.cdn.ampproject.org/c/s/adactio.com/journal/13540/amp", 1521290431, Set("web"), Some("Minimal viable service worker"), None) ::
      PinboardBookmark("https://mycroft.ai/", 1521197840, Set("ML","voice"), None, None) ::
      PinboardBookmark("https://developer.movidius.com/buy", 1521120735, Set("ML"), Some("Where To Buy | Movidius NCS | Neural Compute Stick"), Some("USB")) ::
      PinboardBookmark("https://lptk.github.io/programming/2018/03/02/a-dual-to-iterator.html",  1520171546, Set(), Some("Scala"), None) ::
      PinboardBookmark("https://www.lexoo.co.uk/", 1519730354, Set("legal"), None, Some("Recommended by someone")) ::
      PinboardBookmark("https://lexi-lambda.github.io/blog/2018/02/10/an-opinionated-guide-to-haskell-in-2018/", 1519246979, Set("haskell"), None, None) ::
      Nil

    actual shouldBe expected
  }


  val exampleHTML =
    """
    |<!DOCTYPE NETSCAPE-Bookmark-file-1>
    |<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
    |<TITLE>Pinboard Bookmarks</TITLE>
    |<H1>Bookmarks</H1>
    |<DL><p><DT><A HREF="https://reedsy.com/about" ADD_DATE="1521303406" PRIVATE="0" TOREAD="0" TAGS="book,writing">We are Reedsy! | Reedsy</A>
    |
    |<DT><A HREF="https://adactio-com.cdn.ampproject.org/c/s/adactio.com/journal/13540/amp" ADD_DATE="1521290431" PRIVATE="0" TOREAD="0" TAGS="web">Minimal viable service worker</A>
    |
    |<DT><A HREF="https://mycroft.ai/" ADD_DATE="1521197840" PRIVATE="0" TOREAD="0" TAGS="ML,voice"></A>
    |
    |<DT><A HREF="https://developer.movidius.com/buy" ADD_DATE="1521120735" PRIVATE="0" TOREAD="0" TAGS="ML">Where To Buy | Movidius NCS | Neural Compute Stick</A>
    |<DD>USB
    |
    |<DT><A HREF="https://lptk.github.io/programming/2018/03/02/a-dual-to-iterator.html" ADD_DATE="1520171546" PRIVATE="0" TOREAD="0" TAGS="">Scala</A>
    |
    |<DT><A HREF="https://www.lexoo.co.uk/" ADD_DATE="1519730354" PRIVATE="0" TOREAD="0" TAGS="legal"></A>
    |<DD>Recommended by someone
    |
    |<DT><A HREF="https://lexi-lambda.github.io/blog/2018/02/10/an-opinionated-guide-to-haskell-in-2018/" ADD_DATE="1519246979" PRIVATE="0" TOREAD="0" TAGS="haskell"></A>
    |</DL></p>
  """.stripMargin
}
