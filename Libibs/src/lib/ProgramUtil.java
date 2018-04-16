package lib;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ProgramUtil {
	public static long factorial(int num) {
		long prod = 1;
		if ((num < 0) || (num > 20)) {
			throw new IllegalArgumentException("It can't be less than 0 or bigger than 20.");
		} else {
			for (int i = 2; i <= num; i++) {
				prod *= i;
			}
			return prod;
		}
	}

	public static int parseInt(String input) throws NumberFormatException {
		final TryParseIntResult t = tryParseInt(input);
		if (t.didParse) {
			return t.result;
		} else {
			throw new NumberFormatException("String was not properly formatted for an int.");
		}
	}

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

	public static TryParseIntResult tryParseInt(String input) {
		boolean negative = false;
		if (input != null) {

			final char[] c = input.trim().toCharArray();

			if (c.length > 0) {
				int i = 0;
				final List<Character> nums = new ArrayList<>();

				if (c[0] == '-') {
					i++;
					negative = true;
				}
				boolean flag = false;
				for (; i < c.length; i++) {

					if (Character.isDigit(c[i])) {
						nums.add(c[i]);
					} else {
						flag = true;
					}

				}

				if (nums.size() == 0 || flag) {
					return new TryParseIntResult(false, null);

				}

				final char[] a = new char[nums.size()];
				for (int j = 0; j < nums.size(); j++) {
					a[j] = nums.get(j);
				}

				final String res = new String(a);
				Integer go = null;
				try {
					go = new Integer(res);
					if (negative) {
						go = new Integer(go * -1);
					}
					return new TryParseIntResult(true, go);
				} catch (final NumberFormatException e) {
					return new TryParseIntResult(false, null);
				}

			}
		}
		return new TryParseIntResult(false, null);
	}

	public static void writeToFile(String filePath, String output) throws IOException {
		if (output == null || filePath == null) {
			throw new IllegalArgumentException("One or more inputs were null.");
		}
		try {
			Files.write(Paths.get(filePath), output.getBytes());
		} catch (final IOException e) {
			throw new FileNotFoundException("No file at specified path.");
		}

	}
}
