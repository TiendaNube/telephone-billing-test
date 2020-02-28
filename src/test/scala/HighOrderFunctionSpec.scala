import org.scalatest.{Matchers, WordSpecLike}

class HigherOrderFunctionsSpec extends WordSpecLike with Matchers {

  "List" should {
    "create a empty list" in {
      Nilz.size() shouldBe 0
    }

    "create a Lizt with one element" in {
      Conz(1, Nilz).size() shouldBe 1
    }

    "create a Lizt with multiple elements" in {
      Conz(1, Conz(2, Nilz)).size() shouldBe 2
    }

    "create a Lizt in a fancy way" in {
      Lizt(1, 2, 3, 4, 5).size shouldBe 5
    }

    "Append an element to a lizt" in {
      Lizt(1, 2, 3) :+ 4 shouldBe Lizt(1, 2, 3, 4)
    }

    "Reverser a lizt" in {
      Lizt(1, 2, 3, 4).reverse shouldBe Lizt(4, 3, 2, 1)
    }

    "Transform an empty lizt" in {
      Lizt.empty[Int].map(element => element * 2) shouldBe Nilz
    }

    "Transform a lizt with one element" in {
      Lizt(1).map(_ * 2) shouldBe Lizt(2)
    }

    "Transform a Lizt of multiple elements" in {
      Lizt(1, 2, 3, 4).map(_.toString) shouldBe Lizt("1", "2", "3", "4")
    }

    "Create a list with cons operator" in {
      1 :: Nilz shouldBe Lizt(1)
    }

    "Filter a Lizt" in {
      val myLizt = Lizt(1, 2, 3, 4, 5)

      myLizt.filter(ele => ele > 2) shouldBe Lizt(3, 4, 5)

    }
    "Map a List with a crazy function" in {
      val myLizt = Lizt(1, 2, 3)

      def pw2(number: Int): Int = number * number

      val next: Int => Int = number => number + 1


      val squareOfTheNext = next andThen pw2
      val squareOfTheNext2 = pw2 _ compose next

      myLizt.map(squareOfTheNext) shouldBe Lizt(4, 9, 16)
      myLizt.map(squareOfTheNext) shouldBe myLizt.map(squareOfTheNext2)
    }

    "Curried function" in {
      def isNumberGreaterThan(number: Int, than: Int): Boolean = number > than

      def isNumberGreaterThanPA(number: Int)(than: Int): Boolean = number > than

      isNumberGreaterThan(number = 3, than = 2) shouldBe true
      isNumberGreaterThanPA(number = 3)(than = 2) shouldBe true

      val isNumberGreaterThanCurried: Int => Int => Boolean = number => than => number > than
      val isThreeGreaThan = isNumberGreaterThanCurried(3)
      isThreeGreaThan(2) shouldBe true

      def pw2(number: Int): Int = number * number

      val compose: Int => Boolean = pw2 _ andThen isThreeGreaThan

      compose(2) shouldBe false
    }

    "Fold an empty lizt" in {
      Lizt.empty[Int].fold(10)(_ + _) shouldBe 10
    }

    "Fold a lizt of one element" in {
      Lizt(1).fold(10)(_ + _) shouldBe 11
    }

    "Fold a lizt of multiple elements" in {
      Lizt(1,2,3,4,5).fold(0)(_ - _) shouldBe 15
    }

    "concat two lists" in {
      Lizt(1) ::: Lizt(2,3) shouldBe Lizt(1,2,3)
    }


    "Flatmap" in {
      val lizter = (number: Int) => Lizt(number * 2)
      Lizt(1,2,3).map(lizter) shouldBe Lizt(Lizt(2), Lizt(4), Lizt(6))
      Lizt(1,2,3).flatMap(lizter) shouldBe Lizt(2,4,6)
      List(1,2,3,4) should have size 3
    }
  }

}

