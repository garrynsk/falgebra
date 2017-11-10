
package bricks

import Binary._

/**
    The trait contains the identity law for multiplicative algebras: 

    Suppose that S is a set and • is some binary operation S × S → S.
    There exists an element e in S such that for every element a in S, the equations e • a = a • e = a hold.

    It contains a renamed identity for distinction of additive instances from neutral ones. 
    It is used in MultiplicativeMonoids and MultiplicativeGroups.

    Useful in case "one" element is needed. For example, if you need functions, which aggeregate/reduce data:
    fold, reduce. It proves, that operations with one don't change data.

*/
trait MultiplicativeIdentityF[F[_]] {
    
    def one[A]: F[A]

    // id |*| x = x
    def oneLeft[A](a: F[A])(mult: Binary[F[A]]): Boolean = 
        mult(one, a) == a

    // x |*| id = x
    def oneRight[A](a: F[A])(mult: Binary[F[A]]): Boolean = 
        mult(a, one) == mult(one, a)

    // id |*| x = x |*| id
    def oneIdentity[A](a: F[A])(mult: Binary[F[A]]): Boolean =
        oneLeft(a)(mult) == oneRight(a)(mult)
            
}

object MultiplicativeIdentityF {

    trait Ops[F[_], A] {
        def typeClassInstance: MultiplicativeIdentityF[F]
        def target: F[A] 
    }
    
}
