
package bricks

import Binary._

/**
    The trait contains a binary operation, which is necessary for any algebra implementation. 
    In most cases it is requered to be associative.
    
    This particulat trait can be used for multiplicative operations, such as integer multiplication.

*/
trait MultiplicativeOperationF[F[_]] {
    
    def mult[A](a: F[A], b: F[A]): F[A]
     
}

object MultiplicativeOperationF {
 
    trait Ops[F[_], A] {

        def typeClassInstance: MultiplicativeOperationF[F]
        def target: F[A]

            // An alias for the operation 'mult'
            def |*|(b: F[A]): F[A] = typeClassInstance.mult(target, b)

    }
    
}
