package org.kodein.di.generic

import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.test.A
import org.kodein.di.test.B
import org.kodein.di.test.FixMethodOrder
import org.kodein.di.test.MethodSorters
import kotlin.test.*

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class GenericJvmTests_08_Erasure {

    @Test
    fun test_00_TypeErasure() {

        val la = listOf(A(null))
        val lb = listOf(B(null))

        val kodein = Kodein {
            bind<List<A>>() with instance(la)
            bind<List<B>>() with instance(lb)
        }

        assertSame(kodein.direct.instance(), la)
        assertSame(kodein.direct.instance(), lb)
    }

}
