/*
 * ConsoleIO.cpp
 *
 *  Created on: Apr 11, 2018
 *      Author: owner
 */

#include "ConsoleIO.h"
#include <sstream>
#include <exception>
#include <iostream>

using std::cout;
using std::endl;
using std::cin;
using std::string;
using std::stringstream;
using std::exception;
using std::vector;

double ConsoleIO::promptForDouble(string& prompt, double min, double max) {
	double input;
	while (true) {
		cout << prompt << endl;
		try {
			string s;
			getline(cin, s);
			if (!s.empty()) {
				input = std::stod(s);
				if (input >= min && input <= max) {
					return input;
				} else {
					cout << "Number out of bounds. Try again." << endl;
				}
			}
		} catch (exception& e) {
			cout << "Something went wrong. Try again." << endl;
		}
	}
}

short ConsoleIO::promptForShort(string& prompt, short min, short max) {
	return (short) promptForDouble(prompt, min, max);
}

int ConsoleIO::promptForInt(string& prompt, int min, int max) {
	return (int) promptForDouble(prompt, min, max);
}

long ConsoleIO::promptForLong(string& prompt, long min, long max) {
	return (long) promptForDouble(prompt, min, max);
}

float ConsoleIO::promptForFloat(string& prompt, float min, float max) {
	return (float) promptForDouble(prompt, min, max);
}

char ConsoleIO::promptForChar(string& prompt, char min, char max) {
	char input;
	while (true) {
		cout << prompt << endl;
		try {
			string s;
			getline(cin, s);
			if (!s.empty()) {
				input = s.at(0);
				if (input >= min && input <= max) {
					return input;
				} else {
					cout << "Character out of bounds. Try again." << endl;
				}
			}
		} catch (exception& e) {
			cout << "Something went wrong. Try again." << endl;
		}
	}
}

string ConsoleIO::promptForInput(string& prompt, bool allowEmpty) {
	while (true) {
		cout << prompt << endl;
		try {
			string s;
			getline(cin, s);
			if (s.empty() && !allowEmpty) {
				cout << "Input can not be empty. Try again." << endl;
			} else {
				return s;
			}
		} catch (exception& e) {
			cout << "Something went wrong. Try again." << endl;
		}
	}
}

bool ConsoleIO::promptForBool(string& prompt, string truestring,
		string falsestring) {
	while (true) {
		string input = promptForInput(prompt, false);
		if (input.compare(truestring)) {
			return true;
		} else if (input.compare(falsestring)) {
			return false;
		} else {
			cout << "That didn't match any options. Try again." << endl;
		}
	}
}

int ConsoleIO::promptForSelection(vector<string>& options, bool withExit) {
	string out = "";

	if (withExit) {
		out += "0) Exit\n";
	}

	for (int i = 0; i < options.size(); i++) {
		out += std::to_string(i) + ") " + options[i] + "\n";
	}

	out = "Enter your selection.";
	int in = promptForInt(out, withExit ? 0 : 1, options.size());
	return in;
}


