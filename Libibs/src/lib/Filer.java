package lib;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Filer {
	public static String readFile(String filePath) throws IOException {
		try {
			final String in = new String(Files.readAllBytes(Paths.get(filePath)));
			return in;
		} catch (final FileNotFoundException e) {
			throw new FileNotFoundException("No file specified.");
		}
	}

	public static List<String> readFileToList(String filePath) throws IOException {
		try {
			final List<String> in = (Files.readAllLines(Paths.get(filePath)));
			return in;
		} catch (final FileNotFoundException e) {
			throw new FileNotFoundException("No file specified.");
		}
	}

	public static void writeToFile(String filePath, String output) {
		if (output == null || filePath == null) {
			throw new IllegalArgumentException("One or more inputs were null.");
		}
		try {
			Files.write(Paths.get(filePath), output.getBytes());
		} catch (final IOException | SecurityException e) {
			System.err
					.println("An error has occurred. Please check your file path and write permissions and try again.");
		}

	}
}
