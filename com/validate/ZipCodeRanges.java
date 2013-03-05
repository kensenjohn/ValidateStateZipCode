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
 * Time: 11:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class ZipCodeRanges {

    private Integer startZip;
    private Integer endZip;
    private boolean isSingleZip = false;

    public Integer getStartZip() {
        return startZip;
    }

    public void setStartZip(Integer startZip) {
        this.startZip = startZip;
    }

    public Integer getEndZip() {
        return endZip;
    }

    public void setEndZip(Integer endZip) {
        this.endZip = endZip;
    }

    public boolean isSingleZip() {
        return isSingleZip;
    }

    public void setSingleZip(boolean singleZip) {
        isSingleZip = singleZip;
    }

    @Override
    public String toString() {
        return "com.validate.ZipCodeRanges{" +
                "startZip=" + startZip +
                ", endZip=" + endZip +
                ", isSingleZip=" + isSingleZip +
                '}';
    }
}
