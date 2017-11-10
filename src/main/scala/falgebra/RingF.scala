
package algebra

import algebra.stones.RingComponentF
import annotation.implicitNotFound

/**

The Ring trait is for types that support addition, multiplication, and subtraction operations.

Instances must satisfy the following laws:

- The category laws:

    (associativity of functions) if f : a → b, g : b → c and h : c → d then h <<< (g <<< f) = (h <<< g) <<< f

    (identity of functions) for every object x, there exists a morphism id : x → x, such that 
        for every morphism f : a → x and every morphism g : x → b, we have id <<< f = f and g <<< id = g.

- Ring is an abelian group under addition

    (associativity) (a |+| b) |+| c = a |+| (b |+| c)

    (identity) zero |+| a = a |+| zero = a

    (commutativity) a |+| b = b |+| a

    (inverse) g |-| g = id = g |-| g 

- Ring is a monoid under multiplication

    (associativity) (a |*| b) |*| c = a |*| (b |*| c)

    (identity) one |*| a = a |*| one = a

- Multiplication distributes over addition:

    (left distributivity) a |*| (b |+| c) = (a |*| b) |+| (a |*| c)

    (right distributivity) (a |+| b) |*| c = (a |*| c) |+| (b |*| c)

*/
trait newRing
@implicitNotFound("A value of type $Ring[{A}] cannot be found")
trait RingF[F[_]] extends RingComponentF[F] 
object RingF {

    def apply[F[_]](implicit instance: RingF[F]): RingF[F] = instance

    object Ops {
        
        implicit class toRingFObject[F[_], A](value: F[A])(implicit tc: RingF[F]) extends RingComponentF.Ops[F, A]  { 

            override def typeClassInstance = tc
            override def target = value
        }
    }

}
