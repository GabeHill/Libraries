package lib;

import java.util.ArrayList;
import java.util.List;

public class SimpleMath {

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

	public static Integer parseInt(String input) {
		final TryParseIntResult t = tryParseInt(input);
		if (t.didParse) {
			return t.result;
		}
		return null;
	}

	public static TryParseIntResult tryParseInt(String input) {
		boolean negative = false;
		if (input != null) {

			final char[] c = input.trim().toCharArray();

			if (c.length > 0) {
				final char[] check = { '0', '1', '2', '3', '4', '5', '6', '7', '8', 9 };
				int i = 0;
				final List<Character> nums = new ArrayList<>();

				if (c[0] == '-') {
					i++;
					negative = true;
				}

				for (; i < c.length; i++) {
					for (final char element : check) {
						if (element == c[i]) {
							nums.add(c[i]);
						}
					}
				}

				final char[] a = new char[nums.size()];
				for (int j = 0; j < nums.size(); j++) {
					a[j] = nums.get(j);
				}

				final String res = new String(a);
				Integer go = null;
				try {
					go = new Integer(res);
				} catch (final NumberFormatException e) {
					System.err.println("The input is not properly formatted for an integer.");
					return new TryParseIntResult(false, null);
				}
				if (negative) {
					go *= -1;
				}
				return new TryParseIntResult(true, go);
			}
		}
		return new TryParseIntResult(false, null);
	}
}
