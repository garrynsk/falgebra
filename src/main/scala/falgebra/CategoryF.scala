
package algebra

import algebra.stones.CategoryComponentF
import annotation.implicitNotFound

/**
    A category C consists of
        - a class Obj(C) of objects
        - a class Hom(C) of morphisms
        - for every three objects a, b and c, a binary operation hom(a, b) × hom(b, c) → hom(a, c) called composition of morphisms
    
    - Instances must satisfy the following laws:

        (associativity of functions) if f : a → b, g : b → c and h : c → d then h <<< (g <<< f) = (h <<< g) <<< f
        (identity of functions) for every object x, there exists a morphism id : x → x, such that 
            for every morphism f : a → x and every morphism g : x → b, we have id <<< f = f and g <<< id = g.
*/
@implicitNotFound("A value of type Category[${A}] cannot be found")
trait CategoryF[F[_]] extends CategoryComponentF[F]

object CategoryF {

    def apply[F[_]](implicit instance: CategoryF[F]): CategoryF[F] = instance

    object Ops {

        implicit class toCategoryFObject[F[_], A](value: F[A])(implicit tc: CategoryF[F]) extends CategoryComponentF.Ops[F, A] { 

            override def typeClassInstance = tc
            override def target = value
        }

    }
}
