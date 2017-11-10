
package bricks

/**
    The trait contains the associativity law for functions: 

    Suppose that S is a set and ∘ is some binary operation S × S → S.
    For every three objects a, b and c if f : a → b, g : b → c and h : c → d then 
    h ∘ (g ∘ f) = (h ∘ g) ∘ f.

    The law is applied to every algebra.

*/
trait CompositionF[F[_]] {

    def compose[A, B, C](f: F[B] => F[C])(g: F[A] => F[B]): F[A] => F[C] = f compose g 

    def andThen[A, B, C](f: F[A] => F[B])(g: F[B] => F[C]): F[A] => F[C] = f andThen g 
    
    // h ∘ (g ∘ f) = (h ∘ g) ∘ f
    def composition[A, B, C, D](a: F[D])(f: F[A] => F[B], g: F[B] => F[C], h: F[D] => F[A]): Boolean = 
        compose(compose(g)(f))(h)(a) == compose(g)(compose(f)(h))(a)

}

object CompositionF {
    
    trait Ops[F[_], A]{
        def typeClassInstance: CompositionF[F]
        def target: F[A]
        
    }
}
