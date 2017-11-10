
package bricks

import Binary._

/**
    The trait contains a binary operation, which is necessary for any algebra implementation. 
    In most cases it is requered to be associative.
    
    This particulat trait can be used for additive operations, such as List concatenation or 
    integer addition.

*/
trait AdditiveOperationF[F[_]] {
    
    def add[A](a: F[A], b: F[A]): F[A]
     
}

object AdditiveOperationF {

    trait Ops[F[_], A] {

        def typeClassInstance: AdditiveOperationF[F]
        def target: F[A] 

            // An alias for the operation 'add'
            def |+|(b: F[A]): F[A] = typeClassInstance.add(target, b)
    }
}
