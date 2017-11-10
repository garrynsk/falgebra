
package algebra

import algebra.stones.MultiplicativeMonoidComponentF
import annotation.implicitNotFound

/**
    A multiplicative monoid is like a usual monoid, but its binary operation is multiplication. 
    We need it to construct a ring.  

    - Instances are required to satisfy the category laws:

        (associativity of functions) if f : a → b, g : b → c and h : c → d then h <<< (g <<< f) = (h <<< g) <<< f

        (identity of functions) for every object x, there exists a morphism id : x → x, such that 
            for every morphism f : a → x and every morphism g : x → b, we have id <<< f = f and g <<< id = g.

    - The semigroup law:

        (associativity) (x |*| y) |*| z = x |*| (y |*| z)

    - And the additional law:

        (identity) id |*| x = x |*| id = x
*/

@implicitNotFound("A value of type MultiplicativeMonoid[${A}] cannot be found")
trait MultiplicativeMonoidF[F[_]] extends MultiplicativeMonoidComponentF[F]

object MultiplicativeMonoidF {

    def apply[F[_]](implicit instance: MultiplicativeMonoidF[F]): MultiplicativeMonoidF[F] = instance

    object Ops {

        implicit class toMultiplicativeMonoidFObject[F[_], A](value: F[A])(implicit tc: MultiplicativeMonoidF[F]) extends  MultiplicativeMonoidComponentF.Ops[F, A]{ 

            override def typeClassInstance = tc
            override def target = value
        }
    }

}