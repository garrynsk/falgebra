
package bricks

import Binary._

/**
    The trait contains the commutative law: 

    A binary operation • on a set S is called commutative if:
    x • y = y • x for all x, y in S

    Useful when it is unknown in which order an element will pass into an operation.. 

*/
trait CommutativeF[F[_]] {

    // a • b = b • a
    def commutative[A](a: F[A], b: F[A])(binary: Binary[F[A]]): Boolean = 
        binary(a, b) == binary(b, a)

}

object CommutativeF {
  
    trait Ops[F[_], A] {
        def typeClassInstance: CommutativeF[F]
        def target: F[A] 
    }
    
}