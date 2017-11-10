
package bricks

import Binary._

/**
    The trait contains the inverse law for additive algebras: 

    Suppose that S is a set and • is some binary operation S × S → S.
    For each a in S, there exists an element b in S, commonly denoted a^(−1) (or −a, if the operation is denoted "+"), 
    such that a • b = b • a = e, where e is the identity element.

    It contains a renamed inverse for distinction of additive instances from neutral ones. 
    It is used in AdditiveGroups.

    Useful in case when data can be removed from a structure. 

*/
trait AdditiveInverseF[F[_]] {

    def negation[A](a: F[A]): F[A] 

    // -a |+| a = id
    def leftNegation[A](a: F[A])(id: F[A])(binary: Binary[F[A]]): Boolean =
        binary(negation(a), a) == id

    // a |+| -a = id
    def rightNegation[A](a: F[A])(id: F[A])(binary: Binary[F[A]]): Boolean =
        binary(a, negation(a)) == id
}

object AdditiveInverseF {

    trait Ops[F[_], A] {

        def typeClassInstance: AdditiveInverseF[F]
        def target: F[A] 

    }
    
}