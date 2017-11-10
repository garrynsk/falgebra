
package bricks

import Binary._

/**
    The trait contains a neutrally named binary operation, which is necessary for any algebra implementation. 
    In most cases it is requered to be associative.

*/
trait OperationF[F[_]] {
    
    def combine[A](a: F[A], b: F[A]): F[A]
     
}

object OperationF {

    trait Ops[F[_], A] {

        def typeClassInstance: OperationF[F]
        def target: F[A]

            // An alias for the operation 'combine'
            def <:>(b: F[A]): F[A] = typeClassInstance.combine(target, b)

    }
}
