package com.validate;
/**
ValidateStateZipCode
Copyright (C) 2013  Kensen John (https://github.com/kensenjohn)

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
*/
/**
 * Created with IntelliJ IDEA.
 * User: kensen
 * Date: 1/12/13
 * Time: 11:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class ValidateStatusBean {
    private boolean isValid = false;
    private String statusMessage = "";

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Override
    public String toString() {
        return "com.validate.ValidateStatusBean{" +
                "isValid=" + isValid +
                ", statusMessage='" + statusMessage + '\'' +
                '}';
    }
}
