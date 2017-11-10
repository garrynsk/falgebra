
package algebra

import algebra.stones.AdditiveGroupComponentF
import annotation.implicitNotFound
// TODO: Add tests
/**

    - Instances are required to satisfy the category laws:

        (associativity of functions) if f : a → b, g : b → c and h : c → d then h <<< (g <<< f) = (h <<< g) <<< f

        (identity of functions) for every object x, there exists a morphism id : x → x, such that 
            for every morphism f : a → x and every morphism g : x → b, we have id <<< f = f and g <<< id = g.

    - The semigroup law:

        (associativity) (x |+| y) |+| z = x |+| (y |+| z)

    - The monoid law:

        (identity) id |+| x = x |+| id = x

    - The groupoid law:

         (inverse) inverse(a) |+| a = zero and a |+| inverse(a) = zero are always defined.

*/

@implicitNotFound("A value of type $AdditiveGroup[{A}] cannot be found")
trait AdditiveGroupF[F[_]] extends AdditiveGroupComponentF[F]

object AdditiveGroupF {

    def apply[F[_]](implicit instance: AdditiveGroupF[F]): AdditiveGroupF[F] = instance

    object Ops {

        implicit class toAdditiveGroupFObject[F[_], A](value: F[A])(implicit tc: AdditiveGroupF[F]) extends AdditiveGroupComponentF.Ops[F, A]  { 

            override val typeClassInstance = tc
            override val target = value
        }
    }

}