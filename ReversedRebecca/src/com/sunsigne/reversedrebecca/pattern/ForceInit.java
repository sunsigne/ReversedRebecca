package com.sunsigne.reversedrebecca.pattern;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ForceInit {

	////////// CLASS ////////////

	public <T> void createInstanceOf(Class<T> object) {
		try {
			getClassObject(object).getDeclaredConstructor().newInstance();
		} catch (NoSuchMethodException e) {
			// Not really a problem, still working
		} catch (InstantiationException e) {
			// Can occur when the class is absract
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

	public void loadAllClassesInPackage(String packageName) {
		try {
			getClassesForPackage(packageName);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	private List<Class<?>> getClassesForPackage(final String pkgName) throws IOException, URISyntaxException {
		final String pkgPath = pkgName.replace('.', '/');
		final URI pkg = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(pkgPath)).toURI();
		final ArrayList<Class<?>> allClasses = new ArrayList<Class<?>>();

		Path root;
		if (pkg.toString().startsWith("jar:")) {
			try {
				root = FileSystems.getFileSystem(pkg).getPath(pkgPath);
			} catch (final FileSystemNotFoundException e) {
				root = FileSystems.newFileSystem(pkg, Collections.emptyMap()).getPath(pkgPath);
			}
		} else {
			root = Paths.get(pkg);
		}

		final String extension = ".class";
		try (final Stream<Path> allPaths = Files.walk(root)) {
			allPaths.filter(Files::isRegularFile).forEach(file -> {
				try {
					// "/" occurs on JAR files, "\\" occurs in JVM
					final String path = file.toString().replace('/', '.').replace('\\', '.');
					final String name = path.substring(path.indexOf(pkgName), path.length() - extension.length());
					allClasses.add(Class.forName(name));
				} catch (final ClassNotFoundException | StringIndexOutOfBoundsException ignored) {
				}
			});
		}
		return allClasses;
	}

}