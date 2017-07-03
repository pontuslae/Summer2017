package User; /*
	* Created on 02/07/2017.
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

import Exceptions.UserPrivilegesViolated;

public class PrivateUser extends User {

	private int privateKey = 0;                      // This key authenticates the PrivateUser and is used as a signature.

	public PrivateUser(String username) {
		this.username = username;
	}

	/**
	 * Sets the private key for the PrivateUser to communicate with the server with.
	 * @param privateKey a String private key which should be received from a database.
	 * @throws UserPrivilegesViolated when trying to set an already set key.
	 */
	public void setPrivateKey(int privateKey) throws UserPrivilegesViolated {
		if (this.privateKey == 0) {
			this.privateKey = privateKey;
		} else {
			throw new UserPrivilegesViolated("A private key can only be set once");
		}
	}

	// TODO: 02/07/2017 This does not seem secure.
	public int getPrivateKey() {
		return this.privateKey;
	}
}
