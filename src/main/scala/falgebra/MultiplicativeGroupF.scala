
package algebra

import algebra.stones.MultiplicativeGroupComponentF
import annotation.implicitNotFound
// TODO: Add tests
/**

    - Instances are required to satisfy the category laws:

        (associativity of functions) if f : a → b, g : b → c and h : c → d then h <<< (g <<< f) = (h <<< g) <<< f

        (identity of functions) for every object x, there exists a morphism id : x → x, such that 
            for every morphism f : a → x and every morphism g : x → b, we have id <<< f = f and g <<< id = g.

    - The semigroup law:

        (associativity) (x |*| y) |*| z = x |*| (y |*| z)

    - The monoid law:

        (identity) id |*| x = x |*| id = x

    - The groupoid law:

         (inverse) inverse(a) |*| a = one and a |*| inverse(a) = one are always defined.

*/

@implicitNotFound("A value of type $MultiplicativeGroup[{A}] cannot be found")
trait MultiplicativeGroupF[F[_]] extends MultiplicativeGroupComponentF[F] 

object MultiplicativeGroupF {

    def apply[F[_]](implicit instance: MultiplicativeGroupF[F]): MultiplicativeGroupF[F] = instance

    object Ops {
 
        implicit class toMultiplicativeGroupFObject[F[_], A](value: F[A])(implicit tc: MultiplicativeGroupF[F]) extends MultiplicativeGroupComponentF.Ops[F, A] { 
            
            override val typeClassInstance = tc
            override val target = value
        }
    }   

}
