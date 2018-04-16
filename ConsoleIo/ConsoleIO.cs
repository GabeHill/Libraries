using System;
using System.Collections.Generic;
using System.Collections.Immutable;
using System.IO;
using System.Linq;

namespace ConsoleIO {
	public class ConsoleIo {

		public static void Print<TKey, TVal>(Dictionary<TKey, TVal> c) {
			var k = c.Keys.ToImmutableArray();
			var v = c.Values.ToImmutableArray();

			for (var i = 0; i < k.Length; i++) Console.Write(k[i] + " : " + v[i] + " // ");
		}

		public static void Print<TKey, TVal>(SortedList<TKey, TVal> c) {
			var k = c.Keys;
			var v = c.Values;

			for (var i = 0; i < k.Count; i++) Console.Write(k[i] + " : " + v[i] + " // ");
		}

		public static void Print<T>(T s) {
			Console.Write(s);
		}

		public static void Print<T>(IEnumerable<T> c) {
			foreach (var t in c) Console.WriteLine(t);
		}

		public static void PrintLn<TKey, TVal>(Dictionary<TKey, TVal> c) {
			var k = c.Keys.ToImmutableArray();
			var v = c.Values.ToImmutableArray();

			for (var i = 0; i < k.Length; i++) Console.WriteLine(k[i] + " : " + v[i]);
		}

		public static void PrintLn<T>(SortedList<T, T> c) {
			var k = c.Keys;
			var v = c.Values;

			for (var i = 0; i < k.Count; i++) Console.WriteLine(k[i] + " : " + v[i]);
		}

		public static void PrintLn<T>(T s) {
			Console.WriteLine(s);
		}

		public static void PrintLn<T>(IEnumerable<T> c) {
			foreach (var t in c) Console.WriteLine(t);
		}

		/**
	 * Generates a Prompt that expects the user to enter one of two responses that
	 * will equate to a bool value. The truestring represents the case
	 * insensitive response that will equate to true. The falsestring acts
	 * similarly, but for a false bool value. Example: Assume this method is
	 * called with a truestring argument of "yes" and a falsestring argument of
	 * "no". If the enters "YES", the method returns true. If the user enters "no",
	 * the method returns false. All other inputs are considered invalid, the user
	 * will be informed, and the Prompt will repeat.
	 *
	 * @param Prompt
	 *            - the Prompt to be displayed to the user
	 * @param truestring
	 *            - the case insensitive value that will evaluate to true
	 * @param falsestring
	 *            - the case insensitive value that will evaluate to false
	 * @return the bool value
	 */
		public static bool PromptForBool(string prompt, string truestring, string falsestring) {
			while (true) {
				var inp = PromptForInput(prompt, false).ToLower();

				if (inp.Equals(truestring)) return true;
				if (inp.Equals(falsestring)) return false;
				Console.WriteLine("Thats not an option. Try again.");
			}
		}

		/**
	 * Generates a Prompt that expects a numeric input representing a byte value.
	 * This method loops until valid input is given.
	 *
	 * @param Prompt
	 *            - the Prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the byte value
	 */
		public static byte PromptForByte(string prompt, byte min, byte max) {
			return (byte) PromptForDouble(prompt, min, max);
		}

		/**
	 * Generates a Prompt that expects a single character input representing a char
	 * value. This method loops until valid input is given.
	 *
	 * @param Prompt
	 *            - the Prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the char value
	 */
		public static char PromptForChar(string prompt, char min, char max) {
			var v = ' ';
			Console.WriteLine(prompt);
			try {
				while (true) {
					var inp = Console.ReadLine();
					if (inp != null) {
						v = inp.First();
						if (v >= min && v <= max) return v;
					}

					Console.WriteLine("Out of bounds. Try again.");
				}
			}
			catch (IOException e) {
				Console.WriteLine("You broke it. Try it again.");
			}

			return v;
		}

		/**
	 * Generates a Prompt that expects a numeric input representing a double value.
	 * This method loops until valid input is given.
	 *
	 * @param Prompt
	 *            - the Prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the double value
	 */
		public static double PromptForDouble(string prompt, double min, double max) {
			Console.WriteLine(prompt);
			while (true)
				try {
					var inp = double.Parse(Console.ReadLine());
					if (inp >= min && inp <= max) return inp;

					Console.WriteLine("Out of bounds. Try again.");
				}
				catch (IOException e) {
					Console.WriteLine("You broke it. Try it again.");
				}
				catch (FormatException e) {
					Console.WriteLine("That wasn't a number.");
				}
		}

		/**
	 * Generates a Prompt that expects a numeric input representing a float value.
	 * This method loops until valid input is given.
	 *
	 * @param Prompt
	 *            - the Prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the float value
	 */
		public static float PromptForFloat(string prompt, float min, float max) {
			return (float) PromptForDouble(prompt, min, max);
		}

		/**
	 * Generates a Prompt that allows the user to enter any response and returns the
	 * string. When allowEmpty is true, empty responses are valid. When false,
	 * responses must contain at least one character (including whitespace).
	 *
	 * @param Prompt
	 *            - the Prompt to be displayed to the user.
	 * @param allowEmpty
	 *            - when true, makes empty responses valid
	 * @return the input from the user as a string
	 */
		public static string PromptForInput(string prompt, bool allowEmpty) {
			Console.WriteLine(prompt);
			try {
				while (true) {
					var kek = Console.ReadLine();
					kek = kek?.Trim();
					if (kek != null && kek.Equals("") && !allowEmpty)
						Console.WriteLine("Can't be empty. Try it again.");
					else
						return kek;
				}
			}
			catch (IOException e) {
				Console.WriteLine("You broke it. Try it again.");
			}
			catch (NullReferenceException e) {
				Console.WriteLine("It cant be null.");
			}

			return null;
		}

		/**
	 * Generates a Prompt that expects a numeric input representing an int value.
	 * This method loops until valid input is given.
	 *
	 * @param Prompt
	 *            - the Prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the int value
	 */
		public static int PromptForInt(string prompt, int min, int max) {
			return (int) PromptForDouble(prompt, min, max);
		}

		/**
	 * Generates a Prompt that expects a numeric input representing a long value.
	 * This method loops until valid input is given.
	 *
	 * @param Prompt
	 *            - the Prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the long value
	 */
		public static long PromptForLong(string prompt, long min, long max) {
			return (long) PromptForDouble(prompt, min, max);
		}

		/**
	 * Generates a console-based menu using the strings in options as the menu
	 * items. Reserves the number 0 for the "quit" option when withQuit is true.
	 *
	 * @param options
	 *            - strings representing the menu options
	 * @param withQuit
	 *            - adds option 0 for "quit" when true
	 * @return the int of the selection made by the user
	 */
		public static int PromptForMenuSelection(string[] options, bool withQuit) {
			var outp = "";
			if (withQuit) outp += "0) Exit\n";
			var i = 0;
			foreach (var s in options) {
				i++;
				outp += i + ") " + s + "\n";
			}

			return PromptForInt(outp + "\nEnter the number for your selection:", withQuit ? 0 : 1, i);
		}

		/**
	 * Generates a Prompt that expects a numeric input representing a short value.
	 * This method loops until valid input is given.
	 *
	 * @param Prompt
	 *            - the Prompt to be displayed to the user
	 * @param min
	 *            - the inclusive minimum boundary
	 * @param max
	 *            - the inclusive maximum boundary
	 * @return the short value
	 */
		public static short PromptForShort(string prompt, short min, short max) {
			return (short) PromptForDouble(prompt, min, max);
		}
	}
}