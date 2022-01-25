package com.sunsigne.reversedrebecca.pattern;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ForceInit {

	////////// CLASS ////////////

	public <T> void createInstanceOf(Class<T> object) {
		try {
			getClassObject(object).getDeclaredConstructor().newInstance();
		} catch (NoSuchMethodException e) {
			// Not really a problem, still working
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	private <T> Class<T> getClassObject(Class<T> object) {
		try {
			Class.forName(object.getName(), true, object.getClassLoader());
		} catch (ClassNotFoundException e) {
			throw new AssertionError(e); // Can't happen
		}
		return object;
	}

	////////// PACKAGE ////////////
	
	// WARNING !!! Each class constructor must ask for NO argument to work properly
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void createInstanceOf(Class[] classes) {
		int size = classes.length;
		for(int index = 0; index < size; index++ ) {
			createInstanceOf(classes[index]);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Class[] loadAllClassesInPackage(String packageName) {
		try {
			return getClasses(packageName);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	private Class[] getClasses(String packageName) throws ClassNotFoundException, IOException {

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes.toArray(new Class[classes.size()]);
	}

	@SuppressWarnings("rawtypes")
	private List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(
						Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}
	
	
}
