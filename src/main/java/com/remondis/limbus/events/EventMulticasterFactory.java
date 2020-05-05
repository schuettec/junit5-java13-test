package com.remondis.limbus.events;

import static java.util.Objects.requireNonNull;

/**
 * Factory to create {@link EventMulticaster} instances.
 *
 * @author schuettec
 *
 */
public class EventMulticasterFactory {

	/**
	 * Creates an {@link EventMulticaster} for the specified subscriber type. The
	 * {@link EventMulticaster} created will forward thrown exceptions from
	 * subscribers. <b>Note: An exception aborts the notifaction of further
	 * subscribers.</b>
	 *
	 * @param subscriberType The subscriber interface.
	 *
	 * @return Returns the {@link EventMulticaster}.
	 */
	public static <I> EventMulticaster<I> create(Class<I> subscriberType) throws Exception {
		requireNonNull(subscriberType, "subscriber type must not be null!");
		ReflectionUtil.denyNotInterface(subscriberType);

		MulticastHandler<I> handler = new MulticastHandler<I>(subscriberType);
		return handler;
	}

	/**
	 * Check if the specified type is an interface and if not throws an
	 * {@link IllegalTypeException}.
	 *
	 * @param type The type.
	 * @throws IllegalTypeException Thrown if the type is not an interface.
	 */
	public static void denyNotInterface(Class<?> type) throws RuntimeException {
		if (!ReflectionUtil.isInterface(type)) {
			throw new RuntimeException(String.format("The specified type must be an interface: %s", type.getName()));
		}
	}

}
