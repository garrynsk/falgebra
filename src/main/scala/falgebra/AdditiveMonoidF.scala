
package algebra

import algebra.stones.AdditiveMonoidComponentF
import annotation.implicitNotFound

/**
    An additive monoid is like a usual monoid, but its binary operation is addition. 
    We need it to construct a ring.  

    - Instances are required to satisfy the category laws:

        (associativity of functions) if f : a → b, g : b → c and h : c → d then h <<< (g <<< f) = (h <<< g) <<< f

        (identity of functions) for every object x, there exists a morphism id : x → x, such that 
            for every morphism f : a → x and every morphism g : x → b, we have id <<< f = f and g <<< id = g.

    - The semigroup law:

        (associativity) (x |+| y) |+| z = x |+| (y |+| z)

    - And the additional law:

        (identity) id |+| x = x |+| id = x
*/

@implicitNotFound("A value of type AdditiveMonoid[${A}] cannot be found")
trait AdditiveMonoidF[F[_]]  extends AdditiveMonoidComponentF[F] 

object AdditiveMonoidF {

    def apply[F[_]](implicit instance: AdditiveMonoidF[F]): AdditiveMonoidF[F] = instance

    object Ops {

        implicit class toAdditiveMonoidFObject[F[_], A](value: F[A])(implicit tc: AdditiveMonoidF[F]) extends AdditiveMonoidComponentF.Ops[F, A] { 

            override val typeClassInstance = tc
            override val target = value
            
        }

    }
}