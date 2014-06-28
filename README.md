# Scala Yo API bindings

## Usage

```scala
import scala.util.{Success, Failure}
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global

import com.rydgel.yo._

// You can use implicit
implicit val key = ApiToken("YOUR-API-KEY")
val result = YoClient.yoAll
// Or you can send explicitly your token
val result = YoClient.yoAll(ApiToken("YOUR-API-KEY"))

result.onComplete {
  case Success(resp) => println("request done")
  case Failure(t) => println(t.getMessage)
}

```
