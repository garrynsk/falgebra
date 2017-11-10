
package algebra
package stones

import algebra.{ CategoryF, SemicategoryF, SemigroupF }

/**
    The trait contains operations for a Monoid typeclass. A monoid is also a semicategory, 
    a semigroup and a category. So an instance of the typeclass can be transformed to any of them.
*/
trait MonoidComponentF[F[_]] extends SemigroupComponentF[F] with CategoryComponentF[F] { self =>
    
    def id[A]: F[A]

    // An instance can be transformed to a semicategory
    override def semicategory: SemicategoryF[F] = new SemicategoryF{}

    // An instance can be transformed to a semigroup
    def semigroup: SemigroupF[F] = new SemigroupF{

        override def combine[A](a: F[A], b: F[A]): F[A] = self.combine(a, b)

    }

    // An instance can be transformed to a category
    def category: CategoryF[F] = new CategoryF{

        override def id[A](a: F[A]): F[A] = self.id(a)

    }
}

object MonoidComponentF {

    trait Ops[F[_], A] extends SemigroupComponentF.Ops[F, A] with CategoryComponentF.Ops[F, A] {

        def typeClassInstance: MonoidComponentF[F]
        def target: F[A]
        
    }
    
}
