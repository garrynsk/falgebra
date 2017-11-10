
package algebra

import algebra.stones.SemicategoryComponentF
import annotation.implicitNotFound

// A semigroupoid (also called semicategory or precategory)

/** Formally, a semigroupoid consists of:

    1. a set of things called objects.
    2. for every two objects A and B a set Mor(A,B) of things called morphisms from A to B
    3. for every three objects A, B and C a binary operation Mor(A,B) × Mor(B,C) → Mor(A,C) called composition of morphisms. 

    - Instances must satisfy the following law:

        (associativity of functions) if f : A → B, g : B → C and h : C → D then h compose (g compose f) = (h compose g) compose f.

    As source object A and target object B belong to the same set, we define Hom(C) = Mor(A,B) 
*/

@implicitNotFound("A value of type Semicategory[${A}] cannot be found")
trait SemicategoryF[F[_]] extends SemicategoryComponentF[F]

object SemicategoryF {

    def apply[F[_]](implicit instance: SemicategoryF[F]): SemicategoryF[F] = instance
    
    object Ops {

        implicit class toSemicategoryFObject[F[_], A](value: F[A])(implicit tc: SemicategoryF[F]) extends SemicategoryComponentF.Ops[F, A] { 

            override def typeClassInstance = tc
            override def target = value
        }
    }
    
}

/**

    When we look at the Kleisli category of things that are not quite a Monad, or the static arrow category of things 
    that are not quite Applicative, we wind up with mere a Semigroupoid rather than a Category.
    But where do we find such examples?
    Consider the lowly IntMap. It cannot be made an instance of Applicative or Monad directly.

    https://www.schoolofhaskell.com/user/edwardk/editorial/procrustean-mathematics
*/
