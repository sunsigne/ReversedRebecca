package com.sunsigne.reversedrebecca.ressources.lang;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.sunsigne.reversedrebecca.object.piranha.living.LivingOption.LIVING_TYPE;
import com.sunsigne.reversedrebecca.ressources.FilePath;

public class TranslationAnalyzer {

	private static String path = FilePath.RESSOURCES_PATH + "texts";

	public void loadRessources() {
		try {
			checkTranslations();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void checkTranslations() throws IOException {
		String defaultLang = Language.getInstance().getLang();
		Map<String, Long> defaultFiles = getTxtFilesLineCount(Path.of(path, "/" + defaultLang));

		for (String lang : loadLangList(defaultLang)) {
			Map<String, Long> translatedFiles = getTxtFilesLineCount(Path.of(path, "/" + lang));

			for (var entry : defaultFiles.entrySet()) {
				String file = entry.getKey();
				long defaultLines = entry.getValue();
				Long translatedLines = translatedFiles.get(file);

				if (translatedLines != null && translatedLines != defaultLines) {
					System.err.println("Translation inconsistency with following language : " + lang);
					System.err.printf("Missing lines : %s (%d instead of %d)%n", file, defaultLines, translatedLines);
					System.err.println();
				}
			}
		}
	}

	private ArrayList<String> loadLangList(String defaultLang) {
		File file = new File(path);
		var file_list = new ArrayList<String>(Arrays.asList(file.list()));
		var lang_list = new ArrayList<String>();

		file_list.forEach(tempfile -> {
			if (tempfile.contains(".") == false && tempfile.contains(defaultLang) == false) // check if folder or file
				if (lang_list.contains(tempfile) == false)
					lang_list.add(tempfile);
		});

		return lang_list;
	}

	public static Map<String, Long> getTxtFilesLineCount(Path rootDirectory) throws IOException {
		Map<String, Long> result = new HashMap<>();

		Files.walkFileTree(rootDirectory, new SimpleFileVisitor<>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
				String dirName = dir.getFileName() != null ? dir.getFileName().toString() : "";

				// excluding "male" and "female" translations
				for (LIVING_TYPE tempLiving : LIVING_TYPE.values())
					if (tempLiving.getName().equalsIgnoreCase(dirName))
						return FileVisitResult.SKIP_SUBTREE;
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				if (file.toString().toLowerCase().endsWith(".txt")) {
					String relativePath = rootDirectory.relativize(file).toString();
					try (var lines = Files.lines(file)) {
						result.put(relativePath, lines.count());
					}
				}
				return FileVisitResult.CONTINUE;
			}
		});

		return result;
	}

}
