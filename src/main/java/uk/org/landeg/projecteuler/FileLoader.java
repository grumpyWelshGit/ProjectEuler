package uk.org.landeg.projecteuler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {
	public static List<String> readLines (final String resourceFilename) {
		Path path = Paths.get(("."));
		Path src = path.resolve("src");
		if (src.toFile().exists()) {
			path = src.resolve("main").resolve("resources").resolve(resourceFilename);
		} else if (path.resolve(resourceFilename).toFile().exists()) {
			path = path.resolve(resourceFilename);
		}
		try {
			final List<String> lines = new ArrayList<>();
			Files.newBufferedReader(path).lines().forEach(line -> lines.add(line));
			return lines;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
