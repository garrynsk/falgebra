
package algebra
package stones

import algebra.{ CategoryF, GroupoidF, MonoidF }

/**
    The trait contains operations for a Group typeclass. A group is also a semicategory, a groupoid, 
    a monoid, a semigroup and a category. So an instance of the typeclass can be transformed to any of them.
*/
trait GroupComponentF[F[_]] extends GroupoidComponentF[F] with MonoidComponentF[F] { self =>

    def remove[A](a: F[A], b: F[A]): F[A] = combine(a, inverse(b))
    
    // An instance can be transformed to a category
    override def category: CategoryF[F] = new CategoryF{

        override def id[A](a: F[A]): F[A] = self.id(a)

    }

    // An instance can be transformed to a groupoid
    def groupoid: GroupoidF[F] = new GroupoidF{

        override def id[A](a: F[A]): F[A] = self.id(a)

        override def inverse[A](a: F[A]): F[A] = self.inverse(a)

    }

    // An instance can be transformed to a monoid
    def monoid: MonoidF[F] = new MonoidF{

        override def id[A]: F[A] = self.id

        override def id[A](a: F[A]): F[A] = self.id(a)

        override def combine[A](a: F[A], b: F[A]): F[A] = self.combine(a, b)

    }
}

object GroupComponentF {

    trait Ops[F[_], A] extends GroupoidComponentF.Ops[F, A] with MonoidComponentF.Ops[F, A] {

        def typeClassInstance: GroupComponentF[F]
        def target: F[A]

    }
    
}


