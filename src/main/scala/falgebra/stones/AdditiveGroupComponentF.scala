
package algebra
package stones

import bricks.AdditiveInverseF
import algebra.{ AdditiveMonoidF, GroupF, GroupoidF }

/**
    The trait contains operations for an AdditiveGroup typeclass. An additive group is also a semicategory, 
    a category, a semigroup, an additive monoid, a groupoid and a group. 
    So an instance of the typeclass can be transformed to any of them.
*/
trait AdditiveGroupComponentF[F[_]] extends AdditiveInverseF[F] with AdditiveMonoidComponentF[F] { self =>

    def substract[A](a: F[A], b: F[A]): F[A] = add(a, negation(b))

    // An instance can be transformed to a groupoid
    def groupoid: GroupoidF[F] = new GroupoidF {

        override def id[A](a: F[A]): F[A] = self.id(a)

        override def inverse[A](a: F[A]): F[A] = self.negation(a)

    }

    // An instance can be transformed to an additive monoid
    def additiveMonoid: AdditiveMonoidF[F] = new AdditiveMonoidF{

        override def zero[A]: F[A] = self.zero

        override def id[A](a: F[A]): F[A] = self.id(a)

        override def add[A](a: F[A], b: F[A]): F[A] = self.add(a, b)

    }

    // An instance can be transformed to a group
    def group: GroupF[F] = new GroupF{

        override def id[A]: F[A] = self.zero

        override def id[A](a: F[A]): F[A] = self.id(a)

        override def inverse[A](a: F[A]): F[A] = self.negation(a)

        override def combine[A](a: F[A], b: F[A]): F[A] = self.add(a, b)

    }
    
}

object AdditiveGroupComponentF {

    trait Ops[F[_], A] extends AdditiveInverseF.Ops[F, A] with AdditiveMonoidComponentF.Ops[F, A] {

        def typeClassInstance: AdditiveGroupComponentF[F]
        def target: F[A]
        
        // An alias for the operation 'substract'
        def |-|(b: F[A]): F[A] = typeClassInstance.substract(target,b)
        
    }
    
}
