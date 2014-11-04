package com.sangupta.andruil.launcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JavaLauncher {

	/**
	 * Invokes the <code>main</code> method of the specified class in the
	 * current thread.
	 *
	 * @param clazz
	 *            the class to run
	 * @param args
	 *            the arguments to the <code>main</code> method
	 */
	public static void runClass(Class<?> clazz, String[] args) {
		try {
			Method main = clazz.getDeclaredMethod("main", new Class[] { String[].class });
			main.invoke(null, new Object[] { args });
		} catch (NoSuchMethodException e) {
			throw new NoSuchMethodError(e.toString());
		} catch (IllegalAccessException e) {
			throw new IllegalAccessError(e.toString());
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Invokes the <code>main</code> method of the specified class in the
	 * current thread.
	 */
	public static void runClass(String className, String[] args) {
		try {
			Class<?> clazz = Class.forName(className);
			runClass(clazz, args);
		} catch (ClassNotFoundException e) {
			throw new NoClassDefFoundError(e.toString());
		}
	}

}
