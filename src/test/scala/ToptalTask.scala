import ToptalTask.parseInput

import java.util.Date
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import scala.io.Source.fromFile


case class UserDTO(id: Int,
                   user_id: String,
                   age: Int,
                   user_weight: Double,
                   name: String,
                   price: Int,
                   weight: Int,
                   calories: Int,
                   fat: Double,
                   carbs: Double,
                   protein: Double,
                   time_consumed: String,
                   date_consumed: Date,
                   lunch_type: String,
                   favorite: Boolean,
                   procedence: String)

object ToptalTask {

  def parseInput(path: String): String = {
    fromFile(path).mkString
  }




}

class ToptalTaskSpec extends AnyFlatSpec {
  "readLines" should "parse the input properly" in {
    val expected = """[
                     |  {
                     |    "id": 1,
                     |    "user_id": "18",
                     |    "age": "33",
                     |    "user_weight": "91.88",
                     |    "name": "Pasta Carbonara",
                     |    "price": 10.39,
                     |    "weight": 630,
                     |    "calories": 383,
                     |    "fat": 10.3,
                     |    "carbs": 5.95,
                     |    "protein": 12.67,
                     |    "time_consumed": "11:58",
                     |    "date_consumed": "2022-09-25",
                     |    "type": "lunch",
                     |    "favorite": "false",
                     |    "procedence": "purchased"
                     |  }
                     |,
                     |  {
                     |    "id": 2,
                     |    "user_id": "12",
                     |    "age": "47",
                     |    "user_weight": "59.98",
                     |    "name": "Peking Duck",
                     |    "price": 25.16,
                     |    "weight": 180,
                     |    "calories": 538,
                     |    "fat": 11.75,
                     |    "carbs": 14.96,
                     |    "protein": 9.2,
                     |    "time_consumed": "09:27",
                     |    "date_consumed": "2022-10-10",
                     |    "type": "breakfast",
                     |    "favorite": "false",
                     |    "procedence": "purchased"
                     |  }
                     |]""".stripMargin

    parseInput("/home/ramcel/Dev/LeetCodeProblems/src/test/resources/testJson.json") shouldBe expected
  }
}