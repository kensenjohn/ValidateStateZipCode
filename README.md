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
