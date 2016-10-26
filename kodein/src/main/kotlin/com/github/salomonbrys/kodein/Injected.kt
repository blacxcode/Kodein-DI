package com.github.salomonbrys.kodein

/**
 * Gets a lazy factory for the given type, tag and argument type.
 *
 * [A] & [T] generics will be kept.
 *
 * The returned property should not be accessed before calling [KodeinInjectedBase.inject].
 *
 * @param A The type of argument the factory held by this property takes.
 * @param T The type of object to retrieve with the factory held by this property.
 * @receiver Either a [KodeinInjector] instance or a [KodeinInjected] class.
 * @param tag The bound tag, if any.
 * @return A lazy property that yields a factory of `T`.
 * @throws KodeinInjector.UninjectedException When accessing the property, if it was accessed before calling [KodeinInjectedBase.inject].
 * @throws Kodein.DependencyLoopException When calling the factory, if the value construction triggered a dependency loop.
 */
inline fun <reified A, reified T : Any> KodeinInjectedBase.factory(tag: Any? = null) = genericFactory<A, T>(tag)

/**
 * Gets a lazy factory for the given type, tag and argument type, or null if none is found
 *
 * The returned property should not be accessed before calling [KodeinInjectedBase.inject].
 *
 * [A] & [T] generics will be kept.
 *
 * @param A The type of argument the factory held by this property takes.
 * @param T The type of object to retrieve with the factory held by this property.
 * @receiver Either a [KodeinInjector] instance or a [KodeinInjected] class.
 * @param tag The bound tag, if any.
 * @return A lazy property that yields either a factory of `T` or null if no factory was found.
 * @throws KodeinInjector.UninjectedException When accessing the property, if it was accessed before calling [KodeinInjectedBase.inject].
 * @throws Kodein.DependencyLoopException When calling the factory, if the value construction triggered a dependency loop.
 */
inline fun <reified A, reified T : Any> KodeinInjectedBase.factoryOrNull(tag: Any? = null) = genericFactoryOrNull<A, T>(tag)

/**
 * Gets a lazy provider for the given type and tag.
 *
 * The returned property should not be accessed before calling [KodeinInjectedBase.inject].
 *
 * [T] generics will be kept.
 *
 * @param T The type of object to retrieve with the provider held by this property.
 * @receiver Either a [KodeinInjector] instance or a [KodeinInjected] class.
 * @param tag The bound tag, if any.
 * @return A lazy property that yields a provider of `T`.
 * @throws KodeinInjector.UninjectedException When accessing the property, if it was accessed before calling [KodeinInjectedBase.inject].
 * @throws Kodein.DependencyLoopException When calling the provider, if the value construction triggered a dependency loop.
 */
inline fun <reified T : Any> KodeinInjectedBase.provider(tag: Any? = null) = genericProvider<T>(tag)

/**
 * Gets a lazy provider for the given type and tag, or null if none is found.
 *
 * The returned property should not be accessed before calling [KodeinInjectedBase.inject].
 *
 * @param T The type of object to retrieve with the provider held by this property.
 * @receiver Either a [KodeinInjector] instance or a [KodeinInjected] class.
 * @param tag The bound tag, if any.
 * @return A lazy property that yields a provider of `T` or null if no provider was found.
 * @throws KodeinInjector.UninjectedException When accessing the property, if it was accessed before calling [KodeinInjectedBase.inject].
 * @throws Kodein.DependencyLoopException When calling the provider, if the value construction triggered a dependency loop.
 */
inline fun <reified T : Any> KodeinInjectedBase.providerOrNull(tag: Any? = null) = genericProviderOrNull<T>(tag)

/**
 * Gets a lazy instance for the given type and tag.
 *
 * The returned property should not be accessed before calling [KodeinInjectedBase.inject].
 *
 * [T] generics will be kept.
 *
 * @param T The type of object to retrieve.
 * @receiver Either a [KodeinInjector] instance or a [KodeinInjected] class.
 * @param tag The bound tag, if any.
 * @return A lazy property that yields a `T`.
 * @throws KodeinInjector.UninjectedException When accessing the property, if it was accessed before calling [KodeinInjectedBase.inject].
 */
inline fun <reified T : Any> KodeinInjectedBase.instance(tag: Any? = null) = genericInstance<T>(tag)

/**
 * Gets a lazy instance for the given type and tag.
 *
 * The returned property should not be accessed before calling [KodeinInjectedBase.inject].
 *
 * [T] generics will be kept.
 *
 * @param T The type of object to retrieve.
 * @receiver Either a [KodeinInjector] instance or a [KodeinInjected] class.
 * @param tag The bound tag, if any.
 * @return A lazy property that yields a `T`.
 * @throws KodeinInjector.UninjectedException When accessing the property, if it was accessed before calling [KodeinInjectedBase.inject].
 */
inline fun <reified T : Any> KodeinInjectedBase.instanceOrNull(tag: Any? = null) = genericInstanceOrNull<T>(tag)



/**
 * Gets a lazy curried provider of `T` for the given tag from a factory with an `A` argument.
 *
 * The returned property should not be accessed before calling [KodeinInjectedBase.inject].
 *
 * [T] generics will be kept.
 *
 * @param T The type of object to retrieve with the provider.
 * @param tag The bound tag, if any.
 * @return A lazy property that yields a provider of `T`.
 * @throws KodeinInjector.UninjectedException When accessing the property, if it was accessed before calling [KodeinInjectedBase.inject].
 * @throws Kodein.DependencyLoopException When calling the provider, if the value construction triggered a dependency loop.
 */
inline fun <A, reified T : Any> CurriedInjectorFactory<A>.provider(tag: Any? = null) = genericProvider<A, T>(tag)

/**
 * Gets a lazy curried provider of `T` for the given tag from a factory with an `A` argument, or null if none is found.
 *
 * The returned property should not be accessed before calling [KodeinInjectedBase.inject].
 *
 * [T] generics will be kept.
 *
 * @param T The type of object to retrieve with the provider.
 * @param tag The bound tag, if any.
 * @return A lazy property that yields a provider of `T` or null if no factory was found.
 * @throws KodeinInjector.UninjectedException When accessing the property, if it was accessed before calling [KodeinInjectedBase.inject].
 * @throws Kodein.DependencyLoopException When calling the provider, if the value construction triggered a dependency loop.
 */
inline fun <A, reified T : Any> CurriedInjectorFactory<A>.providerOrNull(tag: Any? = null) = genericProviderOrNull<A, T>(tag)

/**
 * Gets a lazy instance of `T` for the given tag from a factory with an `A` argument.
 *
 * The returned property should not be accessed before calling [KodeinInjectedBase.inject].
 *
 * [T] generics will be kept.
 *
 * @param T The type of object to retrieve.
 * @param tag The bound tag, if any.
 * @return A lazy property that yields a `T`.
 * @throws KodeinInjector.UninjectedException When accessing the property, if it was accessed before calling [KodeinInjectedBase.inject].
 */
inline fun <A, reified T : Any> CurriedInjectorFactory<A>.instance(tag: Any? = null) = genericInstance<A, T>(tag)

/**
 * Gets a lazy instance of `T` for the given tag from a factory with an `A` argument, or null if none is found.
 *
 * The returned property should not be accessed before calling [KodeinInjectedBase.inject].
 *
 * [T] generics will be kept.
 *
 * @param T The type of object to retrieve.
 * @param tag The bound tag, if any.
 * @return A lazy property that yields a `T` or null if no factory was found.
 * @throws KodeinInjector.UninjectedException When accessing the property, if it was accessed before calling [KodeinInjectedBase.inject].
 */
inline fun <A, reified T : Any> CurriedInjectorFactory<A>.instanceOrNull(tag: Any? = null) = genericInstanceOrNull<A, T>(tag)

/**
 * Allows to inject a provider or an instance from a curried factory with an `A` argument.
 *
 * [A] generics will be kept.
 *
 * @param A The type of argument the factory takes.
 * @receiver Either a [KodeinInjector] instance or a [KodeinInjected] class.
 * @param arg A function that provides the argument that will be passed to the factory.
 * @return An object from which you can inject an instance or a provider.
 */
inline fun <reified A> KodeinInjectedBase.with(noinline arg: () -> A) = withGeneric(arg)

/**
 * Allows to inject a provider or an instance from a curried factory with an `A` argument.
 *
 * [A] generics will be kept.
 *
 * @param A The type of argument the factory takes.
 * @receiver Either a [KodeinInjector] instance or a [KodeinInjected] class.
 * @param arg The argument that will be passed to the factory.
 * @return An object from which you can inject an instance or a provider.
 */
inline fun <reified A> KodeinInjectedBase.with(arg: A) = withGeneric(arg)
