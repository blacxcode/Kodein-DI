package org.kodein.di.generic

import org.kodein.di.*

/**
 * Gets a factory of `T` for the given argument type and return type.
 * The name of the receiving property is used as tag.
 *
 * A & T generics will be preserved.
 *
 * @param A The type of argument the factory takes.
 * @param T The type of object the factory returns.
 * @return A factory.
 * @throws Kodein.NotFoundException if no factory was found.
 * @throws Kodein.DependencyLoopException When calling the factory function, if the instance construction triggered a dependency loop.
 */
inline fun <reified A, reified T : Any> Named.factory() = Factory<A, T>(generic(), generic())

/**
 * Gets a factory of `T` for the given argument type and return type, or nul if none is found.
 * The name of the receiving property is used as tag.
 *
 * A & T generics will be preserved.
 *
 * @param A The type of argument the factory takes.
 * @param T The type of object the factory returns.
 * @return A factory, or null if no factory was found.
 * @throws Kodein.DependencyLoopException When calling the factory function, if the instance construction triggered a dependency loop.
 */
inline fun <reified A, reified T : Any> Named.factoryOrNull() = FactoryOrNull<A, T>(generic(), generic())

/**
 * Gets a provider of `T` for the given type.
 * The name of the receiving property is used as tag.
 *
 * T generics will be preserved.
 *
 * @param T The type of object the provider returns.
 * @return A provider.
 * @throws Kodein.NotFoundException if no provider was found.
 * @throws Kodein.DependencyLoopException When calling the provider function, if the instance construction triggered a dependency loop.
 */
inline fun <reified T : Any> Named.provider() = Provider<T>(generic())

/**
 * Gets a provider of [T] for the given type, curried from a factory that takes an argument [A].
 * The name of the receiving property is used as tag.
 *
 * A & T generics will be preserved.
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve with the returned provider.
 * @param arg The argument that will be given to the factory when curried.
 * @return A provider of [T].
 * @throws Kodein.NotFoundException If no provider was found.
 * @throws Kodein.DependencyLoopException When calling the provider, if the value construction triggered a dependency loop.
 */
inline fun <reified A, reified T : Any> Named.provider(arg: A) = Provider<A, T>(generic(), generic()) { arg }

/**
 * Gets a provider of [T] for the given type, curried from a factory that takes an argument [A].
 * The name of the receiving property is used as tag.
 *
 * A & T generics will be preserved.
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve with the returned provider.
 * @param arg The argument that will be given to the factory when curried.
 * @return A provider of [T].
 * @throws Kodein.NotFoundException If no provider was found.
 * @throws Kodein.DependencyLoopException When calling the provider, if the value construction triggered a dependency loop.
 */
inline fun <A, reified T : Any> Named.provider(arg: Typed<A>) = Provider<A, T>(arg.type, generic()) { arg.value }

/**
 * Gets a provider of [T] for the given type, curried from a factory that takes an argument [A].
 * The name of the receiving property is used as tag.
 *
 * A & T generics will be preserved.
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve with the returned provider.
 * @param fArg A function that returns the argument that will be given to the factory when curried.
 * @return A provider of [T].
 * @throws Kodein.NotFoundException If no provider was found.
 * @throws Kodein.DependencyLoopException When calling the provider, if the value construction triggered a dependency loop.
 */
inline fun <reified A, reified T : Any> Named.provider(noinline fArg: () -> A) = Provider<A, T>(generic(), generic(), fArg)

/**
 * Gets a provider of `T` for the given type, or null if none is found.
 * The name of the receiving property is used as tag.
 *
 * T generics will be preserved.
 *
 * @param T The type of object the provider returns.
 * @return A provider, or null if no provider was found.
 * @throws Kodein.DependencyLoopException When calling the provider function, if the instance construction triggered a dependency loop.
 */
inline fun <reified T : Any> Named.providerOrNull() = ProviderOrNull<T>(generic())

/**
 * Gets a provider of [T] for the given type, curried from a factory that takes an argument [A], or null if none is found.
 * The name of the receiving property is used as tag.
 *
 * A & T generics will be preserved.
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve with the returned provider.
 * @param arg The argument that will be given to the factory when curried.
 * @return A provider of [T], or null if no factory was found.
 * @throws Kodein.DependencyLoopException When calling the provider, if the value construction triggered a dependency loop.
 */
inline fun <reified A, reified T : Any> Named.providerOrNull(arg: A) = ProviderOrNull<A, T>(generic(), generic(), { arg })

/**
 * Gets a provider of [T] for the given type, curried from a factory that takes an argument [A], or null if none is found.
 * The name of the receiving property is used as tag.
 *
 * The argument type is extracted from the `Typed.type` of the argument.
 *
 * A & T generics will be preserved.
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve with the returned provider.
 * @param arg The argument that will be given to the factory when curried.
 * @return A provider of [T], or null if no factory was found.
 * @throws Kodein.DependencyLoopException When calling the provider, if the value construction triggered a dependency loop.
 */
inline fun <A, reified T : Any> Named.providerOrNull(arg: Typed<A>) = ProviderOrNull<A, T>(arg.type, generic(), { arg.value })

/**
 * Gets a provider of [T] for the given type, curried from a factory that takes an argument [A], or null if none is found.
 * The name of the receiving property is used as tag.
 *
 * A & T generics will be preserved.
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve with the returned provider.
 * @param fArg A function that returns the argument that will be given to the factory when curried.
 * @return A provider of [T], or null if no factory was found.
 * @throws Kodein.DependencyLoopException When calling the provider, if the value construction triggered a dependency loop.
 */
inline fun <reified A, reified T : Any> Named.providerOrNull(noinline fArg: () -> A) = ProviderOrNull<A, T>(generic(), generic(), fArg)

/**
 * Gets an instance of `T` for the given type.
 * The name of the receiving property is used as tag.
 *
 * T generics will be preserved.
 *
 * @param T The type of object to retrieve.
 * @return An instance.
 * @throws Kodein.NotFoundException if no provider was found.
 * @throws Kodein.DependencyLoopException If the instance construction triggered a dependency loop.
 */
inline fun <reified T : Any> Named.instance() = Instance<T>(generic())

/**
 * Gets an instance of [T] for the given type, curried from a factory that takes an argument [A].
 * The name of the receiving property is used as tag.
 *
 * A & T generics will be preserved.
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve.
 * @param arg The argument that will be given to the factory when curried.
 * @return An instance of [T].
 * @throws Kodein.NotFoundException If no provider was found.
 * @throws Kodein.DependencyLoopException If the value construction triggered a dependency loop.
 */
inline fun <reified A, reified T : Any> Named.instance(arg: A) = Instance<A, T>(generic(), generic()) { arg }

/**
 * Gets an instance of [T] for the given type, curried from a factory that takes an argument [A].
 * The name of the receiving property is used as tag.
 *
 * The argument type is extracted from the `Typed.type` of the argument.
 *
 * A & T generics will be preserved.
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve.
 * @param arg The argument that will be given to the factory when curried.
 * @return An instance of [T].
 * @throws Kodein.NotFoundException If no provider was found.
 * @throws Kodein.DependencyLoopException If the value construction triggered a dependency loop.
 */
inline fun <A, reified T : Any> Named.instance(arg: Typed<A>) = Instance<A, T>(arg.type, generic()) { arg.value }

/**
 * Gets an instance of [T] for the given type, curried from a factory that takes an argument [A].
 * The name of the receiving property is used as tag.
 *
 * A & T generics will be preserved.
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve.
 * @param fArg A function that returns the argument that will be given to the factory when curried.
 * @return An instance of [T].
 * @throws Kodein.NotFoundException If no provider was found.
 * @throws Kodein.DependencyLoopException If the value construction triggered a dependency loop.
 */
inline fun <reified A, reified T : Any> Named.instance(noinline fArg: () -> A) = Instance<A, T>(generic(), generic(), fArg)

/**
 * Gets an instance of `T` for the given type, or null if none is found.
 * The name of the receiving property is used as tag.
 *
 * T generics will be preserved.
 *
 * @param T The type of object to retrieve.
 * @return An instance, or null if no provider was found.
 * @throws Kodein.DependencyLoopException If the instance construction triggered a dependency loop.
 */
inline fun <reified T : Any> Named.instanceOrNull() = InstanceOrNull<T>(generic())

/**
 * Gets an instance of [T] for the given type, curried from a factory that takes an argument [A], or null if none is found.
 * The name of the receiving property is used as tag.
 *
 * A & T generics will be preserved.
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve.
 * @param arg The argument that will be given to the factory when curried.
 * @return An instance of [T], or null if no factory was found.
 * @throws Kodein.DependencyLoopException If the value construction triggered a dependency loop.
 */
inline fun <reified A, reified T : Any> Named.instanceOrNull(arg: A) = InstanceOrNull<A, T>(generic(), generic()) { arg }

/**
 * Gets an instance of [T] for the given type, curried from a factory that takes an argument [A], or null if none is found.
 * The name of the receiving property is used as tag.
 *
 * The argument type is extracted from the `Typed.type` of the argument.
 *
 * A & T generics will be preserved.
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve.
 * @param arg The argument that will be given to the factory when curried.
 * @return An instance of [T], or null if no factory was found.
 * @throws Kodein.DependencyLoopException If the value construction triggered a dependency loop.
 */
inline fun <A, reified T : Any> Named.instanceOrNull(arg: Typed<A>) = InstanceOrNull<A, T>(arg.type, generic()) { arg.value }

/**
 * Gets an instance of [T] for the given type, curried from a factory that takes an argument [A], or null if none is found.
 * The name of the receiving property is used as tag.
 *
 * A & T generics will be preserved.
 *
 * @param A The type of argument the curried factory takes.
 * @param T The type of object to retrieve.
 * @param fArg A function that returns the argument that will be given to the factory when curried.
 * @return An instance of [T], or null if no factory was found.
 * @throws Kodein.DependencyLoopException If the value construction triggered a dependency loop.
 */
inline fun <reified A, reified T : Any> Named.instanceOrNull(noinline fArg: () -> A) = InstanceOrNull<A, T>(generic(), generic(), fArg)

/**
 * Gets a constant of type [T] and tag whose tag is the name of the receiving property.
 *
 * T generics will be preserved.
 *
 * @param T The type of object to retrieve.
 * @return An instance of [T].
 * @throws Kodein.NotFoundException If no provider was found.
 * @throws Kodein.DependencyLoopException If the value construction triggered a dependency loop.
 */
inline fun <reified T : Any> KodeinAware.constant() = Constant<T>(generic())
