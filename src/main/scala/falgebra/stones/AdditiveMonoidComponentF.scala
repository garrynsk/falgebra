
package algebra
package stones

import bricks.Binary._
import bricks.{ AdditiveOperationF, AdditiveIdentityF }
import algebra.{ MonoidF, SemigroupF }

/**
    The trait contains operations for an AdditiveMonoid typeclass. An additive monoid is also a monoid,
    a semigroup, a category and a semicategory. So an instance of the typeclass can be transformed to any of them.
*/
trait AdditiveMonoidComponentF[F[_]] extends CategoryComponentF[F] with AdditiveIdentityF[F] with AdditiveOperationF[F] { self =>
    
    // An instance can be transformed to a semigroup
    /** A trait SemiringComponent inherits this trait and a MultiplicativeMonoid trait.
        Both of them can be transformed to a semigroup. So we have to give them different names, 
        otherwise SemiringComponent will contain two conflicting methods. 
    */
    def addSemigroup: SemigroupF[F] = new SemigroupF{

        override def combine[A](a: F[A], b: F[A]): F[A] = add(a, b)

    }

    // An instance can be transformed to a monoid
    // Same thing is with the monoid. 
    def monoid: MonoidF[F] = new MonoidF {

        override def id[A]: F[A] = self.zero

        override def id[A](a: F[A]): F[A] = self.id(a)

        override def combine[A](a: F[A], b: F[A]): F[A] = self.add(a, b)
        
    }

}

object AdditiveMonoidComponentF {

    trait Ops[F[_], A] extends CategoryComponentF.Ops[F, A] with AdditiveIdentityF.Ops[F, A] with AdditiveOperationF.Ops[F, A] {

        def typeClassInstance: AdditiveMonoidComponentF[F]
        def target: F[A]

    }
    
}
