
package bricks

import Binary._

/**
    The trait contains the associative law: 

    Suppose that S is a set and • is some binary operation S × S → S
    For all a, b and c in S, the equation (a • b) • c = a • (b • c) holds.

    It proves that it doesn't matter in which order to calculate the binary operation.
    The property is useful in parallel and distributed systems.

*/
trait AssociativeF[F[_]] {

    // (a • b) • c = a • (b • c)
    def associative[A](a: F[A], b: F[A], c: F[A])(binary: Binary[F[A]]): Boolean = 
        binary(binary(a, b), c) == binary(a, binary(b, c))

}

object AssociativeF {

    trait Ops[F[_], A]{
        def typeClassInstance: AssociativeF[F]
        def target: F[A] 
    }
    
}
