package lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Map;

public class ConsoleIO {

	// PRO TIP: Make sure to create a new BufferedReader in each method
	// where a BufferedReader is required. Do NOT close the reader as that will
	// cause
	// other issues. Also, catch ALL IOExceptions and NumberFormatExceptions.
	// Do not simply mark the method with a "throws" statement

	public static void print(Collection<?> c) {
		c.forEach(v -> System.out.print(v.toString()));
	}

	public static void print(Map<?, ?> c) {
		c.forEach((k, v) -> System.out.print(k.toString() + ": " + v.toString()));
	}

	public static <T> void print(T s) {
		System.out.print(s.toString());
	}

	public static <T> void print(T[] c) {
		for (final T t : c) {
			System.out.println(t.toString());
		}
	}

	public static void printErr(String s) {
		System.err.println(s);
	}

	public static void printLn(Collection<?> c) {
		c.forEach(v -> System.out.println(v.toString()));
	}

	public static void printLn(Map<?, ?> c) {
		c.forEach((k, v) -> System.out.println(k.toString() + ": " + v.toString()));
	}

	public static <T> void printLn(T s) {
		System.out.println(s.toString());
	}

	public static <T> void printLn(T[] c) {
		for (final T t : c) {
			System.out.println(t.toString());
		}
	}

	/**
	 * Generates a prompt that expects the user to enter one of two responses that
	 * will equate to a boolean value. The trueString represents the case
	 * insensitive response that will equate to true. The falseString acts
	 * similarly, but for a false boolean value. Example: Assume this method is
	 * called with a trueString argument of "yes" and a falseString argument of
	 * "no". If the enters "YES", the method returns true. If the user enters "no",
	 * the method returns false. All other inputs are considered invalid, the user
	 * will be informed, and the prompt will repeat.
	 *
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param trueString
	 *            - the case insensitive value that will evaluate to true
	 * @param falseString
	 *            - the case insensitive value that will evaluate to false
	 * @return the boolean value
	 */
	public static boolean promptForBool(String prompt, String trueString, String falseString) {
		while (true) {
			final String in = promptForInput(prompt, false);

			if (in.equalsIgnoreCase(trueString)) {
				return true;
			} else if (in.equalsIgnoreCase(falseString)) {
				return false;
			} else {
				System.out.println("Thats not an option. Try again.");
				continue;
			}
		}
	}

	/**
	 * Generates a prompt that expects a numeric input representing a byte value.
	 * This method loops until valid input is given.
	 *
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the byte value
	 */
	public static byte promptForByte(String prompt, byte min, byte max) {
		return (byte) promptForDouble(prompt, min, max);
	}

	/**
	 * Generates a prompt that expects a single character input representing a char
	 * value. This method loops until valid input is given.
	 *
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the char value
	 */
	public static char promptForChar(String prompt, char min, char max) {
		final BufferedReader eat = new BufferedReader(new InputStreamReader(System.in));
		char v = ' ';
		System.out.println(prompt);
		try {
			while (true) {
				final String in = eat.readLine();
				v = in.charAt(0);
				if (v >= min && v <= max) {
					return v;
				} else {
					System.out.println("Out of bounds. Try again.");
					continue;
				}
			}
		} catch (IOException | IndexOutOfBoundsException e) {
			System.out.println("You broke it. Try it again.");
		}
		return v;
	}

	/**
	 * Generates a prompt that expects a numeric input representing a double value.
	 * This method loops until valid input is given.
	 *
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the double value
	 */
	public static double promptForDouble(String prompt, double min, double max) {
		final BufferedReader eat = new BufferedReader(new InputStreamReader(System.in));
		double in;
		System.out.println(prompt);
		while (true) {
			try {
				in = Double.parseDouble(eat.readLine());
				if (in >= min && in <= max) {
					return in;
				} else {
					System.out.println("Out of bounds. Try again.");
					continue;
				}
			} catch (IOException | NumberFormatException e) {
				System.out.println("You broke it. Try it again.");
			}
		}
	}

	/**
	 * Generates a prompt that expects a numeric input representing a float value.
	 * This method loops until valid input is given.
	 *
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the float value
	 */
	public static float promptForFloat(String prompt, float min, float max) {
		return (float) promptForDouble(prompt, min, max);
	}

	/**
	 * Generates a prompt that allows the user to enter any response and returns the
	 * String. When allowEmpty is true, empty responses are valid. When false,
	 * responses must contain at least one character (including whitespace).
	 *
	 * @param prompt
	 *            - the prompt to be displayed to the user.
	 * @param allowEmpty
	 *            - when true, makes empty responses valid
	 * @return the input from the user as a String
	 */
	public static String promptForInput(String prompt, boolean allowEmpty) {
		final BufferedReader eat = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(prompt);
		String kek;
		try {
			while (true) {
				kek = eat.readLine().trim();
				if (kek != null) {
					if (kek.equals("") && !allowEmpty) {
						System.out.println("Can't be empty. Try it again.");
					} else {
						return kek;
					}
				}
			}
		} catch (final IOException e) {
			System.out.println("You broke it. Try it again.");
		}
		return null;
	}

	/**
	 * Generates a prompt that expects a numeric input representing an int value.
	 * This method loops until valid input is given.
	 *
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the int value
	 */
	public static int promptForInt(String prompt, int min, int max) {
		return (int) promptForDouble(prompt, min, max);
	}

	/**
	 * Generates a prompt that expects a numeric input representing a long value.
	 * This method loops until valid input is given.
	 *
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the long value
	 */
	public static long promptForLong(String prompt, long min, long max) {
		return (long) promptForDouble(prompt, min, max);
	}

	/**
	 * Generates a console-based menu using the Strings in options as the menu
	 * items. Reserves the number 0 for the "quit" option when withQuit is true.
	 *
	 * @param options
	 *            - Strings representing the menu options
	 * @param withQuit
	 *            - adds option 0 for "quit" when true
	 * @return the int of the selection made by the user
	 */
	public static int promptForMenuSelection(String[] options, boolean withQuit) {
		String out = "";
		if (withQuit) {
			out += "0) Exit\n";
		}
		int i = 0;
		for (final String s : options) {
			i++;
			out += i + ") " + s + "\n";
		}
		return promptForInt(out + "\nEnter the number for your selection:", withQuit ? 0 : 1, i);
	}

	/**
	 * Generates a prompt that expects a numeric input representing a short value.
	 * This method loops until valid input is given.
	 *
	 * @param prompt
	 *            - the prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the short value
	 */
	public static short promptForShort(String prompt, short min, short max) {
		return (short) promptForDouble(prompt, min, max);
	}

}
