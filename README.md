# Scala Yo API bindings

## Usage

```scala
import scala.util.{Success, Failure}
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global

import com.rydgel.yo._

implicit val key = ApiToken("YOUR-API-KEY")

val result = YoClient.yoAll // async shit

result.onComplete {
  case Success(resp) => println("request done")
  case Failure(t) => println(t.getMessage)
}

```

The API returns a 20x even for bad requests.
So a successful Future might not mean the call was Ok.

![ohgodwhy](http://i0.kym-cdn.com/photos/images/newsfeed/000/183/923/Whywecanthavenice2.png?1318200747)