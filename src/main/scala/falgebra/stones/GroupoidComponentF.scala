
package algebra
package stones

import algebra.CategoryF
import bricks.InverseF

/**
    The trait contains operations for a Groupoid typeclass. A groupoid is also a category and 
    a semicategory, so an instance of the typeclass can be transformed to both of them.
*/
trait GroupoidComponentF[F[_]] extends CategoryComponentF[F] with InverseF[F] { self =>

    // An instance can be transformed to a category
    def category: CategoryF[F] = new CategoryF{

        override def id[A](a: F[A]): F[A] = self.id(a)

    }

}

object GroupoidComponentF {

    trait Ops[F[_], A] extends CategoryComponentF.Ops[F, A] with InverseF.Ops[F, A] {

        def typeClassInstance: GroupoidComponentF[F]
        def target: F[A]

    }
    
}


