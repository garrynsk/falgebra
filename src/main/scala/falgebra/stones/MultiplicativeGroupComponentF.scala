
package algebra
package stones

import bricks.MultiplicativeInverseF
import algebra.{ MultiplicativeMonoidF, GroupF, GroupoidF }

/**
    The trait contains operations for a MultiplicativeGroup typeclass. A multiplicative group is also a semicategory, 
    a category, a semigroup, an additive monoid, a groupoid and a group. 
    So an instance of the typeclass can be transformed to any of them.
*/
trait MultiplicativeGroupComponentF[F[_]] extends MultiplicativeInverseF[F] with MultiplicativeMonoidComponentF[F] { self =>

    def divide[A](a: F[A], b: F[A]): F[A] = mult(a, reciprocal(b))
    
    // An instance can be transformed to a groupoid
    def groupoid: GroupoidF[F] = new GroupoidF{

        override def id[A](a: F[A]): F[A] = self.id(a)

        override def inverse[A](a: F[A]): F[A] = self.reciprocal(a)

    }

    // An instance can be transformed to a multiplicative monoid
    def multiplicativeMonoidF: MultiplicativeMonoidF[F] = new MultiplicativeMonoidF{

        override def one[A]: F[A] = self.one

        override def id[A](a: F[A]): F[A] = self.id(a)

        override def mult[A](a: F[A], b: F[A]): F[A] = self.mult(a, b)

    }

    // An instance can be transformed to a group
    def group: GroupF[F] = new GroupF{

        override def id[A]: F[A] = self.one

        override def id[A](a: F[A]): F[A] = self.id(a)

        override def inverse[A](a: F[A]): F[A] = self.reciprocal(a)

        override def combine[A](a: F[A], b: F[A]): F[A] = self.mult(a, b)

    }
}

object MultiplicativeGroupComponentF {

    trait Ops[F[_], A] extends MultiplicativeInverseF.Ops[F, A] with MultiplicativeMonoidComponentF.Ops[F, A] {

        def typeClassInstance: MultiplicativeGroupComponentF[F]
        def target: F[A]
        
        // An alias for the operation 'divide'
        def |/|(b: F[A]): F[A] = typeClassInstance.divide(target,b)

    }
    
}
