
package algebra
package stones

import bricks.Binary._
import bricks.CommutativeF


/**
    The trait contains operations for a Semiring typeclass. A semiring is also a semicategory, 
    a category, a semigroup, an additive monoid and a multiplicative monoid. 
    So an instance of the typeclass can be transformed to any of them.
*/
trait SemiringComponentF[F[_]] extends AdditiveMonoidComponentF[F] with MultiplicativeMonoidComponentF[F] with CommutativeF[F]{
    // TODO: move the law to a separate trait
    def annihilation[A](a: F[A]): Boolean =
        mult(zero, a) == mult(a, zero) && mult(zero, a) == zero

}

object SemiringComponentF {

    trait Ops[F[_], A] extends AdditiveMonoidComponentF.Ops[F, A] with MultiplicativeMonoidComponentF.Ops[F, A] {

        def typeClassInstance: SemiringComponentF[F]
        def target: F[A]
        
    }
    
}
