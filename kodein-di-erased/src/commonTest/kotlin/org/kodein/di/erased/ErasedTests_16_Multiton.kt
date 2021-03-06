package org.kodein.di.erased

import org.kodein.di.Kodein
import org.kodein.di.bindings.ScopeCloseable
import org.kodein.di.bindings.SingleItemScopeRegistry
import org.kodein.di.bindings.UnboundedScope
import org.kodein.di.test.CloseableData
import org.kodein.di.test.FixMethodOrder
import org.kodein.di.test.MethodSorters
import org.kodein.di.test.Person
import kotlin.test.*

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ErasedTests_16_Multiton {

    @Test
    fun test_00_Multiton() {
        val kodein = Kodein { bind() from multiton { name: String -> Person(name) } }

        val p1: Person by kodein.instance(arg = "Salomon")
        val p2: Person by kodein.instance(fArg = { "Salomon" })
        val p3: Person by kodein.instance(arg = "Laila")
        val p4: Person by kodein.instance(fArg = { "Laila" })

        assertSame(p1, p2)
        assertSame(p3, p4)

        assertNotSame(p1, p3)

        assertEquals("Salomon", p1.name)
        assertEquals("Laila", p3.name)
    }

    @Test
    fun test_01_MultitonWithSingleItemScope() {
        val myScope = UnboundedScope(SingleItemScopeRegistry())

        val kodein = Kodein {
            bind<CloseableData>() with scoped(myScope).multiton { name: String -> CloseableData(name) }
        }

        val a: CloseableData by kodein.instance(arg = "one")
        val b: CloseableData by kodein.instance(arg = "one")
        assertSame(a, b)
        val c: CloseableData by kodein.instance(arg = "two")

        assertNotSame(a, c)
        assertTrue(a.closed)
        assertFalse(c.closed)

        val d: CloseableData by kodein.instance(arg = "one")
        assertNotSame(c, d)
        assertNotSame(a, d)
        assertTrue(c.closed)
        assertFalse(d.closed)
    }

    @Test
    fun test_02_NonSyncedMultiton() {
        val kodein = Kodein { bind() from multiton(sync = false) { name: String -> Person(name) } }

        val p1: Person by kodein.instance(arg = "Salomon")
        val p2: Person by kodein.instance(fArg = { "Salomon" })
        val p3: Person by kodein.instance(arg = "Laila")
        val p4: Person by kodein.instance(fArg = { "Laila" })

        assertSame(p1, p2)
        assertSame(p3, p4)

        assertNotSame(p1, p3)

        assertEquals("Salomon", p1.name)
        assertEquals("Laila", p3.name)
    }

}
