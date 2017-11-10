
package bricks

/**
    The trait contains the identity law: 

    For every object x, there exists a morphism id : x → x called the identity morphism for x, 
    such that for every morphism f : a → b, we have id ∘ f = f = f ∘ id.

    It is used to prove that an algebra is actually a category. 

*/
trait IdentityF[F[_]] extends CompositionF[F] {

    def id[A](a: F[A]): F[A] = a

    // id ∘ f = f
    def unitLeft[A](a: F[A])(f: F[A] => F[A]): Boolean = 
        compose(f)(id[A])(a) == f(a)

    // f ∘ id = f
    def unitRight[A](a: F[A])(f: F[A] => F[A]): Boolean = 
        andThen(f)(id[A])(a) == f(a)
    
    // id ∘ f = f ∘ id
    def identity[A](a: F[A])(f: F[A] => F[A]): Boolean =
        unitLeft(a)(f) == unitRight(a)(f)
        
}

object IdentityF {
     trait Ops[F[_], A] {

        def typeClassInstance: IdentityF[F]
        def target: F[A]
    }
}
