
package algebra
package stones

import algebra.SemicategoryF
import bricks.IdentityF

/**
    The trait contains operations for a Category typeclass. A category is also a semicategory, 
    so an instance of the typeclass can be transformed to it.
*/
trait CategoryComponentF[F[_]] extends SemicategoryComponentF[F] with IdentityF[F] {

    // An instance can be transformed to a semicategory
    def semicategory: SemicategoryF[F] = new SemicategoryF{}

}

object CategoryComponentF {

    trait Ops[F[_], A] extends SemicategoryComponentF.Ops[F, A] with IdentityF.Ops[F, A] {

        def typeClassInstance: CategoryComponentF[F]
        def target: F[A]
        
    }
    
}

