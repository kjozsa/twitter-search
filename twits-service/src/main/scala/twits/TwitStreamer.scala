package twits

import twitter4j._

object TwitStreamer extends App {
  var stream: TwitterStream = _

  def bootstrap() {
    System.setProperty("twitter4j.loggerFactory", "twitter4j.NullLoggerFactory")

    val twitter = TwitterFactory.getSingleton()
    stream = new TwitterStreamFactory(twitter.getConfiguration).getInstance()
    stream.addListener(new StatusAdapter {
      override def onStatus(status: Status): Unit = {
        if (status.getText.toLowerCase.contains("android"))
          println(s"${status.getLang} # ${status.getUser.getScreenName}: ${status.getText}")
      }
    })

    stream.sample()
  }


  def shutdown() {
    println("Shutting down streamer")
    stream.shutdown()
  }

//  bootstrap()
}