
package algebra

import algebra.stones.SemiringComponentF
import annotation.implicitNotFound

/**
    A semiring is a set R equipped with two binary operations + and ⋅, called addition and multiplication.
    
    - Instances are required to satisfy the category laws:

        (associativity of functions) if f : a → b, g : b → c and h : c → d then h <<< (g <<< f) = (h <<< g) <<< f

        (identity of functions) for every object x, there exists a morphism id : x → x, such that 
            for every morphism f : a → x and every morphism g : x → b, we have id <<< f = f and g <<< id = g.

    - Commutative monoid under addition:

        (associativity) (a |+| b) |+| c = a |+| (b |+| c)

        (identity) zero |+| a = a |+| zero = a

        (commutativity) a |+| b = b |+| a

    - Monoid under multiplication:

        (associativity) (a |*| b) |*| c = a |*| (b |*| c)

        (identity) one |*| a = a |*| one = a

    - Multiplication distributes over addition:

        Left distributivity: a |*| (b |+| c) = (a |*| b) |+| (a |*| c)

        Right distributivity: (a |+| b) |*| c = (a |*| c) |+| (b |*| c)
        
    - Annihilation: zero |*| a = a |*| zero = zero

*/

@implicitNotFound("A value of type Semiring[${A}] cannot be found")
trait SemiringF[F[_]] extends SemiringComponentF[F] 

object SemiringF {

    def apply[F[_]](implicit instance: SemiringF[F]): SemiringF[F] = instance 

    object Ops {
        
        implicit class toSemiringFObject[F[_], A](self: F[A])(implicit tc: SemiringF[F]) extends SemiringComponentF.Ops[F, A]  { 

            override def typeClassInstance = tc
            override def target = self
        }
    }
}
