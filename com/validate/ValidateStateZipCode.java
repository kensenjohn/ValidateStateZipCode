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
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: kensen
 * Date: 1/12/13
 * Time: 10:52 PM
 */
public class ValidateStateZipCode {

    private static HashMap<String,Integer> hmFullState = new HashMap<String, Integer>();
    private static HashMap<String,Integer> hmShortState = new HashMap<String, Integer>();
    private static HashMap<Integer,ArrayList<ZipCodeRanges>> hmArrZipCode = new HashMap<Integer, ArrayList<ZipCodeRanges>>();

    /*private static void main(String[] args)
    {

        //System.out.println(" help = " + iIndex);

        System.out.println(validate("TX","75209"));
        System.out.println(validate("TX","85209"));
        System.out.println(validate("TX","55126"));
    }*/

    public static ValidateStatusBean validate(String state, String zipCode)
    {
        ValidateStatusBean validateStatusBean = new ValidateStatusBean();

        if(state!=null && !"".equalsIgnoreCase(state) && zipCode!=null && !"".equalsIgnoreCase(zipCode))
        {
            loadFullStateMap();
            loadShortStateMap();

            Integer iStateIndex = 0;
            if(state.length() == 2 )
            {
                state = state.toUpperCase();
                iStateIndex = hmShortState.get(state);
            }
            else if(state.length() > 2)
            {
                state = state.toLowerCase();
                iStateIndex = hmFullState.get(state);
            }
            if(iStateIndex == null || iStateIndex <= 0)
            {
                validateStatusBean.setValid(false);
                validateStatusBean.setStatusMessage("Please use a valid state.");
            }
            else
            {
                try
                {

                    loadZipcodeArray(iStateIndex);

                    Integer iZipcode = new Integer(zipCode);
                    ArrayList<ZipCodeRanges> arrZipcodeRanges = hmArrZipCode.get(iStateIndex);
                    for(ZipCodeRanges zipCodeRange : arrZipcodeRanges)
                    {
                        int iStartRange = zipCodeRange.getStartZip();
                        int iEndRange = zipCodeRange.getEndZip();
                        if(iZipcode>(iStartRange-1) && iZipcode<(iEndRange+1))
                        {
                            validateStatusBean.setValid(true);
                            validateStatusBean.setStatusMessage("Success");
                            break;
                        }
                    }

                    if(!validateStatusBean.isValid())
                    {
                        validateStatusBean.setStatusMessage("Please use a valid zipcode");
                    }

                }
                catch(Exception e)
                {
                    validateStatusBean.setValid(false);
                    validateStatusBean.setStatusMessage("Please use a valid zipcode");
                }
            }


        }
        return validateStatusBean;
    }

    private static void loadFullStateMap()
    {

        hmFullState.put("alabama",1);
        hmFullState.put("alaska",2);
        hmFullState.put("arizona",3);
        hmFullState.put("arkansas",4);
        hmFullState.put("california",5);
        hmShortState.put("colorado",6);
        hmShortState.put("connecticut",7);
        hmShortState.put("delaware",8);
        hmShortState.put("district of columbia",9);
        hmShortState.put("florida",10);
        hmShortState.put("georgia",11);
        hmShortState.put("hawaii",12);
        hmShortState.put("idaho",13);
        hmShortState.put("illinois",14);
        hmShortState.put("indiana",15);
        hmShortState.put("iowa",16);
        hmShortState.put("kansas",17);
        hmShortState.put("kentucky",18);
        hmShortState.put("louisiana",19);
        hmShortState.put("maine",20);
        hmShortState.put("maryland",21);
        hmShortState.put("massachusetts",22);
        hmShortState.put("michigan",23);
        hmShortState.put("minnesota",24);
        hmShortState.put("mississippi",25);
        hmShortState.put("missouri",26);
        hmShortState.put("montana",27);
        hmShortState.put("nebraska",28);
        hmShortState.put("nevada",29);
        hmShortState.put("new hampshire",30);
        hmShortState.put("new jersey",31);
        hmShortState.put("new mexico",32);

        hmShortState.put("new york",33);
        hmShortState.put("north carolina",34);
        hmShortState.put("north dakota",35);
        hmShortState.put("Ohio",36);
        hmShortState.put("oklahoma",37);
        hmShortState.put("oregon",38);
        hmShortState.put("pennsylvania",39);
        hmShortState.put("rhode island",40);

        hmShortState.put("south carolina",41);
        hmShortState.put("south dakota",42);
        hmShortState.put("tennessee",43);
        hmShortState.put("texas",44);
        hmShortState.put("utah",45);
        hmShortState.put("vermont",46);
        hmShortState.put("virginia",47);
        hmShortState.put("washington",48);
        hmShortState.put("west virginia",49);
        hmShortState.put("wisconsin",50);
        hmShortState.put("wyoming",51);

        /*
        South Carolina      SC  290nn-299nn
South Dakota      SD  570nn-577nn
Tennessee      TN  370nn-385nn
Texas      TX  733nn, 750nn-770nn, 772nn-799nn
Utah      UT  840nn-847nn
Vermont      VT  050nn-054nn, 056nn-059nn
Virginia      VA  201nn, 220nn-246nn
Washington      WA  980nn-986nn, 988nn-994nn
West Virginia      WV  247nn-268nn
Wisconsin      WI  530nn-532nn, 534nn-535nn, 537nn-549nn
Wyoming      WY  820nn-831nn, 834nn
         */

    }

    private static  void loadShortStateMap()
    {
        hmShortState.put("AL",1);
        hmShortState.put("AK",2);
        hmShortState.put("AZ",3);
        hmShortState.put("AR",4);
        hmShortState.put("CA",5);
        hmShortState.put("CO",6);
        hmShortState.put("CT",7);
        hmShortState.put("DE",8);
        hmShortState.put("DC",9);
        hmShortState.put("FL",10);
        hmShortState.put("GA",11);
        hmShortState.put("HI",12);
        hmShortState.put("ID",13);
        hmShortState.put("IL",14);
        hmShortState.put("IN",15);
        hmShortState.put("IA",16);
        hmShortState.put("KS",17);
        hmShortState.put("KY",18);
        hmShortState.put("LA",19);
        hmShortState.put("ME",20);
        hmShortState.put("MD",21);
        hmShortState.put("MA",22);
        hmShortState.put("MI",23);
        hmShortState.put("MN",24);
        hmShortState.put("MS",25);
        hmShortState.put("MO",26);
        hmShortState.put("MT",27);
        hmShortState.put("NE",28);
        hmShortState.put("NV",29);
        hmShortState.put("NH",30);
        hmShortState.put("NJ",31);
        hmShortState.put("NM",32);

        hmShortState.put("NY",33);
        hmShortState.put("NC",34);
        hmShortState.put("ND",35);
        hmShortState.put("OH",36);
        hmShortState.put("OK",37);
        hmShortState.put("OR",38);
        hmShortState.put("PA",39);

        hmShortState.put("RI",40);
        hmShortState.put("SC",41);
        hmShortState.put("SD",42);
        hmShortState.put("TN",43);
        hmShortState.put("TX",44);
        hmShortState.put("UT",45);

        hmShortState.put("VT",46);
        hmShortState.put("VA",47);
        hmShortState.put("WA",48);
        hmShortState.put("WV",49);
        hmShortState.put("WI",50);
        hmShortState.put("WY",51);

    }

    private static void loadZipcodeArray(Integer iStateIndex)
    {
        if(iStateIndex == 1 )
        {
            // Alabama 350nn-352nn, 354nn-369nn
            ZipCodeRanges alabamaZipCode1 = new ZipCodeRanges();
            alabamaZipCode1.setStartZip(35000);
            alabamaZipCode1.setEndZip(35299);

            ZipCodeRanges alabamaZipCode2 = new ZipCodeRanges();
            alabamaZipCode2.setStartZip(35400);
            alabamaZipCode2.setEndZip(36999);

            ArrayList<ZipCodeRanges> arrAlabamaZipCode = new  ArrayList<ZipCodeRanges>();
            arrAlabamaZipCode.add(alabamaZipCode1);
            arrAlabamaZipCode.add(alabamaZipCode2);

            hmArrZipCode.put(1,arrAlabamaZipCode);
        }
        else if( iStateIndex == 2 )
        {
            // Alaska 995nn-999nn
            ZipCodeRanges alaskaZipCode1 = new ZipCodeRanges();
            alaskaZipCode1.setStartZip(99501);
            alaskaZipCode1.setEndZip(99950);

            ArrayList<ZipCodeRanges> arrAlaskaZipCode = new  ArrayList<ZipCodeRanges>();
            arrAlaskaZipCode.add(alaskaZipCode1);

            hmArrZipCode.put(2,arrAlaskaZipCode);
        }
        else if( iStateIndex == 3 )
        {
            //Arizona      AZ  850nn-853nn, 855nn-857nn,  859nn-850nn, 863nn-865nn
            ZipCodeRanges arizonaZipCode1 = new ZipCodeRanges();
            arizonaZipCode1.setStartZip(85000);
            arizonaZipCode1.setEndZip(85399);

            ZipCodeRanges arizonaZipCode2 = new ZipCodeRanges();
            arizonaZipCode2.setStartZip(85500);
            arizonaZipCode2.setEndZip(85799);

            ZipCodeRanges arizonaZipCode3 = new ZipCodeRanges();
            arizonaZipCode3.setStartZip(85900);
            arizonaZipCode3.setEndZip(86099);

            ZipCodeRanges arizonaZipCode4 = new ZipCodeRanges();
            arizonaZipCode4.setStartZip(86300);
            arizonaZipCode4.setEndZip(86599);

            ArrayList<ZipCodeRanges> arrArizonaZipCode = new  ArrayList<ZipCodeRanges>();
            arrArizonaZipCode.add(arizonaZipCode1);
            arrArizonaZipCode.add(arizonaZipCode2);
            arrArizonaZipCode.add(arizonaZipCode3);
            arrArizonaZipCode.add(arizonaZipCode4);

            hmArrZipCode.put(3,arrArizonaZipCode);
        }
        else if( iStateIndex == 4 )
        {
            //Arkansas      AR  716nn-729nn
            ZipCodeRanges arkansasZipCode1 = new ZipCodeRanges();
            arkansasZipCode1.setStartZip(71600);
            arkansasZipCode1.setEndZip(72999);

            ArrayList<ZipCodeRanges> arrArkansasZipCode = new  ArrayList<ZipCodeRanges>();
            arrArkansasZipCode.add(arkansasZipCode1);

            hmArrZipCode.put(4,arrArkansasZipCode);
        }
        else if( iStateIndex == 5 )
        {
            //California      CA  900nn-908nn, 910nn-928nn,  930nn-961nn
            ZipCodeRanges californiaZipCode1 = new ZipCodeRanges();
            californiaZipCode1.setStartZip(90000);
            californiaZipCode1.setEndZip(90899);

            ZipCodeRanges californiaZipCode2 = new ZipCodeRanges();
            californiaZipCode2.setStartZip(91000);
            californiaZipCode2.setEndZip(92899);

            ZipCodeRanges californiaZipCode3 = new ZipCodeRanges();
            californiaZipCode3.setStartZip(93000);
            californiaZipCode3.setEndZip(96199);

            ArrayList<ZipCodeRanges> arrCaliforniaZipCode = new  ArrayList<ZipCodeRanges>();
            arrCaliforniaZipCode.add(californiaZipCode1);
            arrCaliforniaZipCode.add(californiaZipCode2);
            arrCaliforniaZipCode.add(californiaZipCode3);

            hmArrZipCode.put(5,arrCaliforniaZipCode);
        }
        else if( iStateIndex == 6 )
        {
            //Colorado      CO  800nn-816nn
            ZipCodeRanges coloradoZipCode1 = new ZipCodeRanges();
            coloradoZipCode1.setStartZip(80000);
            coloradoZipCode1.setEndZip(81699);

            ArrayList<ZipCodeRanges> arrColoradoZipCode = new  ArrayList<ZipCodeRanges>();
            arrColoradoZipCode.add(coloradoZipCode1);

            hmArrZipCode.put(6,arrColoradoZipCode);
        }
        else if( iStateIndex == 7 )
        {
            //Connecticut      CT  060nn-06389, 06391-069nn
            ZipCodeRanges connecticutZipCode1 = new ZipCodeRanges();
            connecticutZipCode1.setStartZip(6000);
            connecticutZipCode1.setEndZip(6389);

            ZipCodeRanges connecticutZipCode2 = new ZipCodeRanges();
            connecticutZipCode2.setStartZip(6391);
            connecticutZipCode2.setEndZip(6999);

            ArrayList<ZipCodeRanges> arrConnecticutZipCode = new  ArrayList<ZipCodeRanges>();
            arrConnecticutZipCode.add(connecticutZipCode1);
            arrConnecticutZipCode.add(connecticutZipCode2);

            hmArrZipCode.put(7,arrConnecticutZipCode);
        }
        else if( iStateIndex == 8 )
        {
            //Delaware      DE  197nn-199nn
            ZipCodeRanges delawareZipCode1 = new ZipCodeRanges();
            delawareZipCode1.setStartZip(80000);
            delawareZipCode1.setEndZip(81699);

            ArrayList<ZipCodeRanges> arrDelawareZipCode = new  ArrayList<ZipCodeRanges>();
            arrDelawareZipCode.add(delawareZipCode1);

            hmArrZipCode.put(8,arrDelawareZipCode);
        }
        else if( iStateIndex == 9 )
        {
            //District of Columbia      DC  200nn, 202nn-205nn, 569nn
            ZipCodeRanges districtOfColumbiaZipCode1 = new ZipCodeRanges();
            districtOfColumbiaZipCode1.setStartZip(20000);
            districtOfColumbiaZipCode1.setEndZip(20099);

            ZipCodeRanges districtOfColumbiaZipCode2 = new ZipCodeRanges();
            districtOfColumbiaZipCode2.setStartZip(20200);
            districtOfColumbiaZipCode2.setEndZip(20599);

            ZipCodeRanges districtOfColumbiaZipCode3 = new ZipCodeRanges();
            districtOfColumbiaZipCode3.setStartZip(56900);
            districtOfColumbiaZipCode3.setEndZip(56999);

            ArrayList<ZipCodeRanges> arrDistrictOfColumbiaZipCode = new  ArrayList<ZipCodeRanges>();
            arrDistrictOfColumbiaZipCode.add(districtOfColumbiaZipCode1);
            arrDistrictOfColumbiaZipCode.add(districtOfColumbiaZipCode2);
            arrDistrictOfColumbiaZipCode.add(districtOfColumbiaZipCode3);

            hmArrZipCode.put(9,arrDistrictOfColumbiaZipCode);
        }
        else if( iStateIndex == 10 )
        {
            //Florida      FL  320nn-339nn, 341nn, 342nn, 344nn, 346nn, 347nn, 349nn
            ZipCodeRanges floridaZipCode1 = new ZipCodeRanges();
            floridaZipCode1.setStartZip(32000);
            floridaZipCode1.setEndZip(33999);

            ZipCodeRanges floridaZipCode2 = new ZipCodeRanges();
            floridaZipCode2.setStartZip(34100);
            floridaZipCode2.setEndZip(34199);

            ZipCodeRanges floridaZipCode3 = new ZipCodeRanges();
            floridaZipCode3.setStartZip(34200);
            floridaZipCode3.setEndZip(342199);

            ZipCodeRanges floridaZipCode4 = new ZipCodeRanges();
            floridaZipCode4.setStartZip(34400);
            floridaZipCode4.setEndZip(34499);

            ZipCodeRanges floridaZipCode5 = new ZipCodeRanges();
            floridaZipCode5.setStartZip(34600);
            floridaZipCode5.setEndZip(34699);

            ZipCodeRanges floridaZipCode6 = new ZipCodeRanges();
            floridaZipCode6.setStartZip(34700);
            floridaZipCode6.setEndZip(34799);

            ZipCodeRanges floridaZipCode7 = new ZipCodeRanges();
            floridaZipCode7.setStartZip(34900);
            floridaZipCode7.setEndZip(34999);

            ArrayList<ZipCodeRanges> arrFloridaZipCode = new  ArrayList<ZipCodeRanges>();
            arrFloridaZipCode.add(floridaZipCode1);
            arrFloridaZipCode.add(floridaZipCode2);
            arrFloridaZipCode.add(floridaZipCode3);
            arrFloridaZipCode.add(floridaZipCode4);
            arrFloridaZipCode.add(floridaZipCode5);
            arrFloridaZipCode.add(floridaZipCode6);
            arrFloridaZipCode.add(floridaZipCode7);

            hmArrZipCode.put(10,arrFloridaZipCode);
        }
        else if( iStateIndex == 11 )
        {
            // Georgia      GA  300nn-319nn, 398nn-399nn
            ZipCodeRanges georgiaZipCode1 = new ZipCodeRanges();
            georgiaZipCode1.setStartZip(30000);
            georgiaZipCode1.setEndZip(31999);

            ZipCodeRanges georgiaZipCode2 = new ZipCodeRanges();
            georgiaZipCode2.setStartZip(39800);
            georgiaZipCode2.setEndZip(39999);

            ArrayList<ZipCodeRanges> arrGeorgiaZipCode = new  ArrayList<ZipCodeRanges>();
            arrGeorgiaZipCode.add(georgiaZipCode1);
            arrGeorgiaZipCode.add(georgiaZipCode2);

            hmArrZipCode.put(11,arrGeorgiaZipCode);
        }
        else if( iStateIndex == 12 )
        {
            //Hawaii      HI  967nn, 968nn
            ZipCodeRanges hawaiiZipCode1 = new ZipCodeRanges();
            hawaiiZipCode1.setStartZip(96700);
            hawaiiZipCode1.setEndZip(96799);

            ZipCodeRanges hawaiiZipCode2 = new ZipCodeRanges();
            hawaiiZipCode2.setStartZip(96800);
            hawaiiZipCode2.setEndZip(96899);

            ArrayList<ZipCodeRanges> arrHawaiiZipCode = new  ArrayList<ZipCodeRanges>();
            arrHawaiiZipCode.add(hawaiiZipCode1);
            arrHawaiiZipCode.add(hawaiiZipCode2);

            hmArrZipCode.put(12,arrHawaiiZipCode);
        }
        else if( iStateIndex == 13 )
        {
            //Idaho      ID  832nn-838nn
            ZipCodeRanges idahoZipCode1 = new ZipCodeRanges();
            idahoZipCode1.setStartZip(83200);
            idahoZipCode1.setEndZip(83899);

            ArrayList<ZipCodeRanges> arrIdahoZipCode = new  ArrayList<ZipCodeRanges>();
            arrIdahoZipCode.add(idahoZipCode1);

            hmArrZipCode.put(13,arrIdahoZipCode);
        }
        else if( iStateIndex == 14 )
        {
            //Illinois      IL  600nn-620nn, 622nn-629nn
            ZipCodeRanges illinoisZipCode1 = new ZipCodeRanges();
            illinoisZipCode1.setStartZip(60000);
            illinoisZipCode1.setEndZip(62099);

            ZipCodeRanges illinoisZipCode2 = new ZipCodeRanges();
            illinoisZipCode2.setStartZip(62200);
            illinoisZipCode2.setEndZip(62999);

            ArrayList<ZipCodeRanges> arrIllinoisZipCode = new  ArrayList<ZipCodeRanges>();
            arrIllinoisZipCode.add(illinoisZipCode1);
            arrIllinoisZipCode.add(illinoisZipCode2);

            hmArrZipCode.put(14,arrIllinoisZipCode);
        }
        else if( iStateIndex == 15 )
        {
            // Indiana      IN  460nn-479nn
            ZipCodeRanges indianaZipCode1 = new ZipCodeRanges();
            indianaZipCode1.setStartZip(46000);
            indianaZipCode1.setEndZip(47999);

            ArrayList<ZipCodeRanges> arrIndianaZipCode = new  ArrayList<ZipCodeRanges>();
            arrIndianaZipCode.add(indianaZipCode1);

            hmArrZipCode.put(15,arrIndianaZipCode);
        }
        else if( iStateIndex == 16 )
        {
            // Iowa      IA  500nn-516nn, 520nn-528nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(50000);
            zipCode1.setEndZip(51699);

            ZipCodeRanges zipCode2 = new ZipCodeRanges();
            zipCode2.setStartZip(52000);
            zipCode2.setEndZip(52899);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);
            arrZipCode.add(zipCode2);

            hmArrZipCode.put(16,arrZipCode);
        }
        else if( iStateIndex == 17 )
        {
            // Kansas      KS  660nn-662nn, 664nn-679nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(66000);
            zipCode1.setEndZip(66299);

            ZipCodeRanges zipCode2 = new ZipCodeRanges();
            zipCode2.setStartZip(66400);
            zipCode2.setEndZip(67999);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);
            arrZipCode.add(zipCode2);

            hmArrZipCode.put(17,arrZipCode);
        }
        else if( iStateIndex == 18 )
        {
            //Kentucky      KY  400nn-427nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(40000);
            zipCode1.setEndZip(42799);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);

            hmArrZipCode.put(18,arrZipCode);
        }
        else if( iStateIndex == 19 )
        {
            // Louisiana      LA  700nn-701nn, 703nn-708nn, 710nn-714nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(70000);
            zipCode1.setEndZip(70199);

            ZipCodeRanges zipCode2 = new ZipCodeRanges();
            zipCode2.setStartZip(70300);
            zipCode2.setEndZip(70899);

            ZipCodeRanges zipCode3 = new ZipCodeRanges();
            zipCode3.setStartZip(71000);
            zipCode3.setEndZip(71499);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);
            arrZipCode.add(zipCode2);
            arrZipCode.add(zipCode3);

            hmArrZipCode.put(19,arrZipCode);
        }
        else if( iStateIndex == 20 )
        {
            //Maine      ME  039nn-049nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(3900);
            zipCode1.setEndZip(4999);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);

            hmArrZipCode.put(20,arrZipCode);
        }
        else if( iStateIndex == 21 )
        {
            // Maryland      MD  206nn-212nn, 214nn-219nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(20600);
            zipCode1.setEndZip(21299);

            ZipCodeRanges zipCode2 = new ZipCodeRanges();
            zipCode2.setStartZip(21400);
            zipCode2.setEndZip(21999);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);
            arrZipCode.add(zipCode2);

            hmArrZipCode.put(21,arrZipCode);
        }
        else if( iStateIndex == 22 )
        {
            // Massachusetts      MA  010nn-027nn, 055nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(1000);
            zipCode1.setEndZip(2799);

            ZipCodeRanges zipCode2 = new ZipCodeRanges();
            zipCode2.setStartZip(5500);
            zipCode2.setEndZip(5599);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);
            arrZipCode.add(zipCode2);

            hmArrZipCode.put(22,arrZipCode);
        }
        else if( iStateIndex == 23 )
        {
            // Michigan      MI  480nn-499nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(48000);
            zipCode1.setEndZip(49999);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);

            hmArrZipCode.put(23,arrZipCode);
        }
        else if( iStateIndex == 24 )
        {
            // Minnesota      MN  550nn-551nn, 553nn-567nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(55000);
            zipCode1.setEndZip(55199);

            ZipCodeRanges zipCode2 = new ZipCodeRanges();
            zipCode2.setStartZip(55300);
            zipCode2.setEndZip(56799);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);
            arrZipCode.add(zipCode2);

            hmArrZipCode.put(24,arrZipCode);
        }
        else if( iStateIndex == 25 )
        {
            // Mississippi      MS  386nn-397nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(38600);
            zipCode1.setEndZip(39799);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);

            hmArrZipCode.put(25,arrZipCode);
        }
        else if( iStateIndex == 26 )
        {
            // Missouri      MO  630nn-631nn, 633nn-641nn, 644nn-658nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(63000);
            zipCode1.setEndZip(63199);

            ZipCodeRanges zipCode2 = new ZipCodeRanges();
            zipCode2.setStartZip(63300);
            zipCode2.setEndZip(64199);

            ZipCodeRanges zipCode3 = new ZipCodeRanges();
            zipCode3.setStartZip(64400);
            zipCode3.setEndZip(65899);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);
            arrZipCode.add(zipCode2);
            arrZipCode.add(zipCode3);

            hmArrZipCode.put(26,arrZipCode);
        }
        else if( iStateIndex == 27 )
        {
            // Montana      MT  590nn-599nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(59000);
            zipCode1.setEndZip(59999);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);

            hmArrZipCode.put(27,arrZipCode);
        }
        else if( iStateIndex == 28 )
        {
            // Nebraska      NE  680nn-681nn, 683nn-693nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(68000);
            zipCode1.setEndZip(68199);

            ZipCodeRanges zipCode2 = new ZipCodeRanges();
            zipCode2.setStartZip(68300);
            zipCode2.setEndZip(69399);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);
            arrZipCode.add(zipCode2);

            hmArrZipCode.put(28,arrZipCode);
        }
        else if( iStateIndex == 29 )
        {
            //Nevada      NV  889nn-891nn, 893nn-895nn, 897nn-898nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(88900);
            zipCode1.setEndZip(89199);

            ZipCodeRanges zipCode2 = new ZipCodeRanges();
            zipCode2.setStartZip(89300);
            zipCode2.setEndZip(89599);

            ZipCodeRanges zipCode3 = new ZipCodeRanges();
            zipCode3.setStartZip(89700);
            zipCode3.setEndZip(89899);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);
            arrZipCode.add(zipCode2);
            arrZipCode.add(zipCode3);

            hmArrZipCode.put(29,arrZipCode);
        }
        else if( iStateIndex == 30 )
        {
            // New Hampshire      NH  030nn-038nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(3000);
            zipCode1.setEndZip(3899);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);

            hmArrZipCode.put(30,arrZipCode);
        }
        else if( iStateIndex == 31 )
        {
            // New Jersey      NJ  070nn-089nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(7000);
            zipCode1.setEndZip(8999);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);

            hmArrZipCode.put(31,arrZipCode);
        }
        else if( iStateIndex == 32 )
        {
            // New Mexico      NM  870nn-871nn, 873nn-875nn, 877nn-884nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(87000);
            zipCode1.setEndZip(87199);

            ZipCodeRanges zipCode2 = new ZipCodeRanges();
            zipCode2.setStartZip(87300);
            zipCode2.setEndZip(87599);

            ZipCodeRanges zipCode3 = new ZipCodeRanges();
            zipCode3.setStartZip(87700);
            zipCode3.setEndZip(88499);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);
            arrZipCode.add(zipCode2);
            arrZipCode.add(zipCode3);

            hmArrZipCode.put(32,arrZipCode);
        }
        else if( iStateIndex == 33 )
        {
            // New York      NY  005nn, 06390, 100nn-149nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(500);
            zipCode1.setEndZip(599);

            ZipCodeRanges zipCode2 = new ZipCodeRanges();
            zipCode2.setStartZip(10000);
            zipCode2.setEndZip(14999);

            ZipCodeRanges zipCode3 = new ZipCodeRanges();
            zipCode3.setStartZip(6390);
            zipCode3.setEndZip(6390);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);
            arrZipCode.add(zipCode2);
            arrZipCode.add(zipCode3);

            hmArrZipCode.put(33,arrZipCode);
        }
        else if( iStateIndex == 34 )
        {
            // North Carolina      NC  270nn-289nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(27000);
            zipCode1.setEndZip(28999);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);

            hmArrZipCode.put(34,arrZipCode);
        }
        else if( iStateIndex == 35 )
        {
            // North Dakota      ND  580nn-588nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(58000);
            zipCode1.setEndZip(58899);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);

            hmArrZipCode.put(35,arrZipCode);
        }
        else if( iStateIndex == 36 )
        {
            // Ohio      OH  430nn-459nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(43000);
            zipCode1.setEndZip(45999);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);

            hmArrZipCode.put(36,arrZipCode);
        }
        else if( iStateIndex == 37 )
        {
            // Oklahoma      OK  730nn-731nn, 734nn-741nn, 743nn-749nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(73000);
            zipCode1.setEndZip(73199);

            ZipCodeRanges zipCode2 = new ZipCodeRanges();
            zipCode2.setStartZip(73400);
            zipCode2.setEndZip(74199);

            ZipCodeRanges zipCode3 = new ZipCodeRanges();
            zipCode3.setStartZip(74300);
            zipCode3.setEndZip(74999);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);
            arrZipCode.add(zipCode2);
            arrZipCode.add(zipCode3);

            hmArrZipCode.put(37,arrZipCode);
        }
        else if( iStateIndex == 38 )
        {
            // Oregon      OR  970nn-979nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(97000);
            zipCode1.setEndZip(97999);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);

            hmArrZipCode.put(38,arrZipCode);
        }
        else if( iStateIndex == 39 )
        {
            // Pennsylvania      PA  150nn-196nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(15000);
            zipCode1.setEndZip(19699);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);

            hmArrZipCode.put(39,arrZipCode);
        }
        else if( iStateIndex == 40 )
        {
            // Rhode Island      RI  028nn, 029nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(2800);
            zipCode1.setEndZip(2999);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);

            hmArrZipCode.put(40,arrZipCode);
        }
        else if( iStateIndex == 41 )
        {
            // South Carolina      SC  290nn-299nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(29000);
            zipCode1.setEndZip(29999);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);

            hmArrZipCode.put(41,arrZipCode);
        }
        else if( iStateIndex == 42 )
        {
            // South Dakota      SD  570nn-577nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(57000);
            zipCode1.setEndZip(57799);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);

            hmArrZipCode.put(42,arrZipCode);
        }
        else if( iStateIndex == 43 )
        {
            // Tennessee      TN  370nn-385nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(37000);
            zipCode1.setEndZip(38599);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);

            hmArrZipCode.put(43,arrZipCode);
        }
        else if( iStateIndex == 44 )
        {
            // Texas      TX  733nn, 750nn-770nn, 772nn-799nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(73300);
            zipCode1.setEndZip(73399);

            ZipCodeRanges zipCode2 = new ZipCodeRanges();
            zipCode2.setStartZip(75000);
            zipCode2.setEndZip(77099);

            ZipCodeRanges zipCode3 = new ZipCodeRanges();
            zipCode3.setStartZip(77200);
            zipCode3.setEndZip(79999);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);
            arrZipCode.add(zipCode2);
            arrZipCode.add(zipCode3);

            hmArrZipCode.put(44,arrZipCode);
        }
        else if( iStateIndex == 45 )
        {
            // Utah      UT  840nn-847nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(84000);
            zipCode1.setEndZip(84799);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);

            hmArrZipCode.put(45,arrZipCode);
        }
        else if( iStateIndex == 46 )
        {
            // Vermont      VT  050nn-054nn, 056nn-059nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(5000);
            zipCode1.setEndZip(5499);

            ZipCodeRanges zipCode2 = new ZipCodeRanges();
            zipCode2.setStartZip(5600);
            zipCode2.setEndZip(5999);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);
            arrZipCode.add(zipCode2);

            hmArrZipCode.put(46,arrZipCode);
        }
        else if( iStateIndex == 47 )
        {
            // Virginia      VA  201nn, 220nn-246nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(20100);
            zipCode1.setEndZip(20199);

            ZipCodeRanges zipCode2 = new ZipCodeRanges();
            zipCode2.setStartZip(22000);
            zipCode2.setEndZip(24699);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);
            arrZipCode.add(zipCode2);

            hmArrZipCode.put(47,arrZipCode);
        }
        else if( iStateIndex == 48 )
        {
            //Washington      WA  980nn-986nn, 988nn-994nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(98000);
            zipCode1.setEndZip(98699);

            ZipCodeRanges zipCode2 = new ZipCodeRanges();
            zipCode2.setStartZip(98800);
            zipCode2.setEndZip(99499);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);
            arrZipCode.add(zipCode2);

            hmArrZipCode.put(48,arrZipCode);
        }
        else if( iStateIndex == 49 )
        {
            // West Virginia      WV  247nn-268nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(24700);
            zipCode1.setEndZip(26899);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);

            hmArrZipCode.put(49,arrZipCode);
        }
        else if( iStateIndex == 50 )
        {
            //  Wisconsin      WI  530nn-532nn, 534nn-535nn, 537nn-549nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(53000);
            zipCode1.setEndZip(53299);

            ZipCodeRanges zipCode2 = new ZipCodeRanges();
            zipCode2.setStartZip(53400);
            zipCode2.setEndZip(53599);

            ZipCodeRanges zipCode3 = new ZipCodeRanges();
            zipCode3.setStartZip(53700);
            zipCode3.setEndZip(54999);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);
            arrZipCode.add(zipCode2);
            arrZipCode.add(zipCode3);

            hmArrZipCode.put(50,arrZipCode);
        }
        else if( iStateIndex == 51 )
        {
            // Wyoming      WY  820nn-831nn, 834nn
            ZipCodeRanges zipCode1 = new ZipCodeRanges();
            zipCode1.setStartZip(82000);
            zipCode1.setEndZip(83199);

            ZipCodeRanges zipCode2 = new ZipCodeRanges();
            zipCode2.setStartZip(83400);
            zipCode2.setEndZip(83499);

            ArrayList<ZipCodeRanges> arrZipCode = new  ArrayList<ZipCodeRanges>();
            arrZipCode.add(zipCode1);
            arrZipCode.add(zipCode2);

            hmArrZipCode.put(51,arrZipCode);
        }

    }

}
