
package algebra
package stones

import bricks.Binary._
import bricks.{ MultiplicativeIdentityF, MultiplicativeOperationF }
import algebra.{ SemigroupF, MonoidF }

/**
    The trait contains operations for a MultiplicativeMonoid typeclass. A multiplicative monoid is also a monoid,
    a semigroup, a category and a semicategory. So an instance of the typeclass can be transformed to any of them.
*/
trait MultiplicativeMonoidComponentF[F[_]] extends CategoryComponentF[F] with MultiplicativeIdentityF[F] with MultiplicativeOperationF[F]{ self =>

    // An instance can be transformed to a semigroup
    /** A trait SemiringComponent inherits this trait and a AdditiveMonoid trait.
        Both of them can be transformed to a semigroup. So we have to give them different names, 
        otherwise SemiringComponent will contain two conflicting methods. 
    */
    def multSemigroup: SemigroupF[F] = new SemigroupF{

        override def combine[A](a: F[A], b: F[A]): F[A] = self.mult(a, b)

    }

    // An instance can be transformed to a monoid
    // Same thing is with the monoid. 
    def addMonoid: MonoidF[F] = new MonoidF {

        override def id[A]: F[A] = self.one

        override def id[A](a: F[A]): F[A] = self.id(a)

        override def combine[A](a: F[A], b: F[A]): F[A] = self.mult(a, b)
        
    }

}

object MultiplicativeMonoidComponentF {

    trait Ops[F[_], A] extends CategoryComponentF.Ops[F, A] with MultiplicativeIdentityF.Ops[F, A] with MultiplicativeOperationF.Ops[F, A] {

        def typeClassInstance: MultiplicativeMonoidComponentF[F]
        def target: F[A]

    }
    
}
