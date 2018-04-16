/*
 * ConsoleIO.h
 *
 *  Created on: Apr 11, 2018
 *      Author: owner
 */

#ifndef CONSOLEIO_H_
#define CONSOLEIO_H_

#include <string>
#include <vector>

using std::vector;
using std::string;


class ConsoleIO {
public:
	double static promptForDouble(string& prompt, double min, double max);

	short static promptForShort(string& prompt, short min, short max);

	int static promptForInt(string& prompt, int min, int max);

	long static promptForLong(string& prompt, long min, long max);

	float static promptForFloat(string& prompt, float min, float max);

	char static promptForChar(string& prompt, char min, char max);

	string static promptForInput(string& prompt, bool allowEmpty);

	bool static promptForBool(string& prompt, string truestring,
			string falsestring);

	int static promptForSelection(vector<string>& options, bool withExit);

};

#endif /* CONSOLEIO_H_ */
