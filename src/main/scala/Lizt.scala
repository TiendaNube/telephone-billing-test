sealed trait Lizt[+A] { self: Lizt[A] =>

  def :: [B >: A](element: B): Lizt[B] = Conz(element, self)

  def ::: [B >: A] (otherList: Lizt[B]): Lizt[B] = otherList match {
    case Nilz             => self
    case Conz(head, tail) => Conz(head, tail ::: self)
  }

  def :+ [B >: A](element: B): Lizt[B] = self match {
    case Nilz             => Conz(element, Nilz)
    case Conz(head, Nilz) => Lizt(head, element)
    case Conz(head, tail) => Conz(head, tail :+ element)
  }

  def size(): Int = self match {
    case Nilz             => 0
    case Conz(_, Nilz)    => 1
    case Conz(_, tail)    => 1 + tail.size
  }

  def reverse: Lizt[A] = self match {
    case Nilz              => Nilz
    case x@Conz(_,Nilz)    => x
    case Conz(head,tail)   => tail.reverse :+ head
  }

  def filter(f: A => Boolean): Lizt[A] = self match {
    case Nilz                          => Nilz
    case x@Conz(head, Nilz) if f(head) => x
    case Conz(_, Nilz)                 => Nilz
    case Conz(head, tail) if f(head)   => head :: tail.filter(f)
    case Conz(_, tail)                 => tail.filter(f)
  }

  def fold[B](z: B)(op: (B,A) => B): B = self match {
    case Nilz => z
    case Conz(head, Nilz) => op(z, head)
    case Conz(head, tail) => op(tail.fold(z)(op), head)
  }

  def flatMap[B](f: A => Lizt[B]): Lizt[B] = self match {
    case Nilz             => Nilz
    case Conz(head, tail) => f(head) ::: tail.flatMap(f)
  }


  def map[B](f: A => B): Lizt[B] =  self match {
    case Nilz => Nilz
    case Conz(head, Nilz) => Lizt(f(head))
    case Conz(head, tail) => Conz(f(head), tail.map(f))
  }
}

case class Conz[A](head: A, tail: Lizt[A]) extends Lizt[A]
case object Nilz extends Lizt[Nothing]

object Lizt {

  def empty[A](): Lizt[A] = Nilz

  def apply[A](elems: List[A]): Lizt[A] = elems match {
    case Nil => Nilz
    case head :: Nil => Conz(head, Nilz)
    case head :: tail => Conz(head, Lizt.apply(tail))
  }

  def apply[A](elems: A*): Lizt[A] = elems.toList match {
    case Nil => Nilz
    case head :: Nil => Conz(head, Nilz)
    case head :: tail => Conz(head, Lizt.apply(tail))
  }
}