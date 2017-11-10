
package algebra
package stones

import bricks.{ OperationF }
import algebra.SemicategoryF

/**
    The trait contains operations for a Semigroup typeclass. A semigroup is also a semicategory, 
    so an instance of the typeclass can be transformed to it.
*/
trait SemigroupComponentF[F[_]] extends SemicategoryComponentF[F] with OperationF[F] {

    def semicategory: SemicategoryF[F] = new SemicategoryF{}

}

object SemigroupComponentF {

    trait Ops[F[_], A] extends SemicategoryComponentF.Ops[F, A] with OperationF.Ops[F, A]{

        def typeClassInstance: SemigroupComponentF[F]
        def target: F[A]

       
    }
    
}

