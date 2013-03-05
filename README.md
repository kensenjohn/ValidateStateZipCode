ValidateStateZipCode
====================

Code to validate zipcodes with the State short code.
Quick version I built to validate zipcodes.
You can get the zip code range by googling it.
I used the following version.
http://directmailmanagerri.com/pdfs/zipcode_list_for_US_states.pdf

Example :
ValidateStatusBean validateStatusBean = ValidateStateZipCode.validate("TX","75209");

// check if valid as follows
if(validateStatusBean.isValid() )
{
  // if it comes here it indicates that the zipcode is present in the state 
}

TODO:
1) Create a property file to store the zip code ranges for each state.


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
