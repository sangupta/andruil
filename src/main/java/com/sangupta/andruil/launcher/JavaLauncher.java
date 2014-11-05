/**
 *
 * andruil - Java command line shell
 * Copyright (c) 2012-2014, Sandeep Gupta
 * 
 * http://www.sangupta/projects/andruil
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.sangupta.andruil.launcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Helper class that launches a Java program's main class as a
 * command in andruil.
 * 
 * @author sangupta
 *
 */
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
