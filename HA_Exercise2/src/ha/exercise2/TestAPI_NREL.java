/**
 * Automated API tests for https://api.data.gov/
 * 
 * @param strLocation - Address of the location (format - city, state)
 * @param strNetwork - Electric charging stations that belong to the given network.
 * @param strStationID - Fuel station id.
 */
package ha.exercise2;

import static com.jayway.restassured.RestAssured.get; 

import org.json.JSONArray; 
import org.json.JSONException; 
import org.json.JSONObject;
import org.testng.Assert; 
import org.testng.annotations.Test; 

import com.jayway.restassured.response.Response;

import utilities.Constant;

public class TestAPI_NREL
{ 
	private int strStationID;

	/**
	 * Test to find fuel station id when location and network are provided.
	 * API requires KEY to query required data.
	 * @throws JSONException
	 */
	@Test 
	public void TestFindStationID() throws JSONException 
	{ 
		String strLocation = "Austin,TX";
		String strNetwork = "ChargePoint Network";
		strStationID = 0;		

		//Fetch fuel stations part of 'Chargepoint Network' near Austin, Texas.  
		Response resp = get("https://api.data.gov/nrel/alt-fuel-stations/v1/nearest."+Constant.strOutputFormat+"?location="+strLocation+"&ev_network="+strNetwork+"&api_key="+Constant.strAPIKey);
				
		String strResponse = "[" +resp.asString() + "]";
		
		//Convert response in JSON 
		JSONArray jsonResponse = new JSONArray(strResponse); 
		
		//Fetch fuel station names
		JSONArray jsonFuelStations = jsonResponse.getJSONObject(0).getJSONArray("fuel_stations");
		
		int intArraySize = jsonFuelStations.length(); 
		
		for(int i = 0;i<intArraySize;i++)
		{
			JSONObject fuelStation = jsonFuelStations.getJSONObject(i);
			
			if(fuelStation.getString("station_name").equals("HYATT AUSTIN"))
			{
				strStationID = fuelStation.getInt("id");
				System.out.println("Station:"+fuelStation.getString("station_name")+" has "+strStationID+" id.");
				break;
			}
		}
		
		//Check is station id is retrieved
		Assert.assertTrue((strStationID!=0));
		
			
	} 
	
	/**
	 * Test to find fuel station address.
	 * TestFindStationAddress test depends on TestFindStationid to fetch fuel station id.
	 * @throws JSONException
	 */
	@Test(dependsOnMethods = "TestFindStationID")
	public void TestFindStationAddress() throws JSONException 
	{ 
		//Check if strStationID is available
		if(strStationID != 0)
		{
			Response resp = get("https://api.data.gov/nrel/alt-fuel-stations/v1/"+strStationID+"."+Constant.strOutputFormat+"?api_key="+Constant.strAPIKey);
			
			
			String strResponse = "[" +resp.asString() + "]";
			
			//Convert response in JSON 
			JSONArray jsonResponse = new JSONArray(strResponse);
			
			//Get street address
			String strStreetAddress = jsonResponse.getJSONObject(0).getJSONObject("alt_fuel_station").getString("street_address");
			
			//verify if address is as expected.
			Assert.assertEquals(strStreetAddress, "208 Barton Springs, Austin, Texas, USA, 78704");
			
			/*
			 * Street address of 'HYATT AUSTIN' fuel station retrieved from API is '208 Barton Springs Rd' which will not 
			 * match with expected street address '208 Barton Springs, Austin, Texas, USA, 78704'.
			 */
		}
		else
		{
			System.out.println("Station ID is not retrieved from 'TestFindStationID' test.");
		}
	}
}