package org.kodein.di.generic

import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.bindings.subTypes
import org.kodein.di.direct
import org.kodein.di.jvmType
import org.kodein.di.test.*
import kotlin.test.*

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class GenericJvmTests_00_Factory {

    @Test
    fun test_00_FactoryBindingGetFactory() {

        val kodein = Kodein {
            bind() from factory { name: String -> Person(name) }
        }

        val p1: (String) -> Person by kodein.factory()
        val p2: (String) -> Person by kodein.factory()

        assertNotSame(p1("Salomon"), p2("Salomon"))
    }

    @Test
    fun test_01_WithFactoryGetProvider() {

        val kodein = Kodein { bind<Person>() with factory { name: String -> Person(name) } }

        val p: () -> Person by kodein.provider(arg = "Salomon")
        val dp: () -> Person = kodein.direct.provider(arg = "Salomon")

        assertAllEqual("Salomon", p().name, dp().name)

        val fp: () -> Person by kodein.provider(fArg = { "Salomon" })
        val dfp: () -> Person = kodein.direct.provider(fArg = { "Salomon" })

        assertAllEqual("Salomon", fp().name, dfp().name)
    }

    @Test
    fun test_02_WithFactoryGetInstance() {

        val kodein = Kodein { bind<Person>() with factory { name: String -> Person(name) } }

        val p: Person by kodein.instance(arg = "Salomon")

        assertEquals("Salomon", p.name)

        val fp: Person by kodein.instance(fArg = { "Salomon" })

        assertEquals("Salomon", fp.name)
    }

    @Test
    fun test_03_WithSubFactoryGetInstance() {

        val kodein = Kodein { bind<Person>() with factory { p: Name -> Person(p.firstName) } }

        val p: Person by kodein.instance(arg = FullName("Salomon", "BRYS"))

        assertEquals("Salomon", p.name)
    }

    @Test
    fun test_04_WithGenericFactoryGetInstance() {

        val kodein = Kodein { bind<Person>() with factory { l: List<*> -> Person(l.first().toString()) } }

        val p: Person by kodein.instance(arg = listOf("Salomon", "BRYS"))

        assertEquals("Salomon", p.name)
    }

    @Test
    fun test_05_WithItfFactoryGetInstance() {

        val kodein = Kodein { bind<Person>() with factory { p: IName -> Person(p.firstName) } }

        val p: Person by kodein.instance(arg = FullName("Salomon", "BRYS"))

        assertEquals("Salomon", p.name)
    }

    @Test
    fun test_06_WithTwoItfFactoryGetInstance() {

        val kodein = Kodein {
            bind<Person>() with factory { p: IName -> Person(p.firstName) }
            bind<Person>() with factory { p: IFullName -> Person(p.firstName + " " + p.lastName) }
        }

        val p: Person by kodein.instance(arg = FullInfos("Salomon", "BRYS", 30))

        assertFailsWith<Kodein.NotFoundException> { p.name }
    }

    @Test
    fun test_07_withFactoryLambdaArgument() {
        val kodein = Kodein {
            bind<Runnable>() with factory { f: () -> Unit -> Runnable(f) }
        }

        var passed = false
        val f = { passed = true }

        val run: Runnable by kodein.instance(arg = f)
        run.run()

        assertTrue(passed)
    }

    interface FakeLogger { val cls: Class<*> }

    class FakeLoggerImpl(override val cls: Class<*>) : FakeLogger

    class AwareTest(override val kodein: Kodein) : KodeinAware {
        val logger: FakeLogger by instance(arg = javaClass)
    }

    @Test
    fun test_08_StarFactory() {
        val kodein = Kodein {
            bind<FakeLogger>() with factory { cls: Class<*> -> FakeLoggerImpl(cls) }
        }

        val test = AwareTest(kodein)

        assertEquals(AwareTest::class.java, test.logger.cls)
    }

    @Test
    fun test_09_subTypeFactory() {
        val kodein = Kodein.direct {
            bind<IName>().subTypes() with { type ->
                when (type.jvmType) {
                    FullName::class.java -> singleton { FullName("Salomon", "BRYS") }
                    Name::class.java -> factory { _: Unit -> Name("Salomon") }
                    else -> throw IllegalStateException()
                }
            }
        }

        assertEquals(FullName::class.java, kodein.instance<FullName>().javaClass)
        assertEquals(FullName("Salomon", "BRYS"), kodein.instance())
        assertEquals<FullName>(kodein.instance(), kodein.instance())

        assertEquals(Name::class.java, kodein.instance<Name>().javaClass)
        assertEquals(Name("Salomon"), kodein.instance())
        assertEquals<Name>(kodein.instance(), kodein.instance())
    }

}
