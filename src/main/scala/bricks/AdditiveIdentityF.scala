
package bricks

import Binary._

/**
    The trait contains the identity law for additive algebras: 

    Suppose that S is a set and • is some binary operation S × S → S.
    There exists an element e in S such that for every element a in S, the equations e • a = a • e = a hold.

    It contains a renamed identity for distinction of additive instances from neutral ones. 
    It is used in AdditiveMonoids and AdditiveGroups.

    Useful in case zero element is needed. For example, if you need functions, which aggeregate/reduce data:
    fold, reduce. It proves, that operations with zero don't change data.

*/
trait AdditiveIdentityF[F[_]] {
    
    def zero[A]: F[A]

    // id |+| x = x
    def zeroLeft[A](a: F[A])(add: Binary[F[A]]): Boolean = 
        add(zero, a) == a

    // x |+| id = x
    def zeroRight[A](a: F[A])(add: Binary[F[A]]): Boolean = 
        add(a, zero) == add(zero, a)

    // id |+| x = x |+| id
    def zeroIdentity[A](a: F[A])(add: Binary[F[A]]): Boolean =
        zeroLeft(a)(add) == zeroRight(a)(add)
        
}

object AdditiveIdentityF {

    trait Ops[F[_], A] {
        def typeClassInstance: AdditiveIdentityF[F]
        def target: F[A] 
    }
    
}
