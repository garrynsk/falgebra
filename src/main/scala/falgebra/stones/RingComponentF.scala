
package algebra
package stones

/**
    The trait contains operations for a Ring typeclass. A ring is also a semicategory, 
    a category, a semigroup, an additive monoid, a multiplicative monoid and a semiring. 
    So an instance of the typeclass can be transformed to any of them.
*/
trait RingComponentF[F[_]] extends SemiringComponentF[F] with AdditiveGroupComponentF[F]

object RingComponentF {

    trait Ops[F[_], A] extends SemiringComponentF.Ops[F, A] with AdditiveGroupComponentF.Ops[F, A]{

        def typeClassInstance: RingComponentF[F]
        def target: F[A]
   
    }
}
