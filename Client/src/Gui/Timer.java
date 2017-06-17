package Gui;/*
	* Created $YEAR-$MONTH-16
	* Copyright (c) 2017 Pontus Laestadius
	*
	* Permission is hereby granted, free of charge, to any person obtaining
	* a copy of this software and associated documentation files (the
	* "Software"), to deal in the Software without restriction, including
	* without limitation the rights to use, copy, modify, merge, publish,
	* distribute, sublicense, and/or sell copies of the Software, and to
	* permit persons to whom the Software is furnished to do so, subject to
	* the following conditions:
	*
	* The above copyright notice and this permission notice shall be
	* included in all copies or substantial portions of the Software.
	*
	* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
	* EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
	* MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
	* NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
	* LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
	* OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
	* WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

public class Timer {
	long start;
	long end = 0;
	int expected;

	public Timer() {
		this.start(300);
	}

	public Timer(int expected) {
		this.start(expected);
	}

	public void start(int expected) {
		this.expected = expected;
		this.start = System.currentTimeMillis();
	}

	public long stop() {
		return (this.end = System.currentTimeMillis());
	}

	public long elapse() {
		return this.end - this.start;
	}

	/**
	 * @return true if the the start time plus the expected time is less than the end time.
	 */
	public boolean status() {
		return elapse() > this.expected;
	}


	public void status(String str) {
		if (this.status())
			Singleton.debugPrint("TIMER: " + elapse() + "(" + str + ")");
	}
}
