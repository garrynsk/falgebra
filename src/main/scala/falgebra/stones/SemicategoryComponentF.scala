
package algebra
package stones

import bricks.{ CompositionF, AssociativeF }

/**
    The trait contains operations for a Semicategory typeclass. 
*/
trait SemicategoryComponentF[F[_]] extends CompositionF[F] with AssociativeF[F] 

object SemicategoryComponentF {

    trait Ops[F[_], A] extends CompositionF.Ops[F, A] with AssociativeF.Ops[F, A] {

        def typeClassInstance: SemicategoryComponentF[F]
        def target: F[A]
        
    }
    
}

