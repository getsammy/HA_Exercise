/**
 * Automated API tests for https://api.data.gov/
 * 
 * @param strLocation - Address of the location (format - city, state)
 * @param strNetwork - Electric charging stations that belong to the given network.
 * @param strStationID - Fuel station id.
 */
package ha.exercise2.tests;

import static com.jayway.restassured.RestAssured.*;

import org.json.JSONArray; 
import org.json.JSONException; 
import org.json.JSONObject;
import org.testng.Assert; 
import org.testng.Reporter;
import org.testng.annotations.Test; 

import com.jayway.restassured.response.Response;

import utilities.Constant;

public class TestAPI_NREL
{ 
	private int intStationID;

	/**
	 * Test to find fuel station id when location and network are provided.
	 * API requires KEY to query required data.
	 * @throws JSONException
	 */
	@Test 
	public void testFindStationID() throws JSONException 
	{ 
		String strLocation = "Austin,TX";
		String strNetwork = "ChargePoint Network";
		intStationID = 0;
		
		//Fetch fuel stations part of 'Chargepoint Network' near Austin, Texas.  
		Response resp =
		given().
			queryParam("location", strLocation).
			queryParam("ev_network", strNetwork).
			queryParam("api_key", Constant.strAPIKey).		 
		when().
			get("https://api.data.gov/nrel/alt-fuel-stations/v1/nearest."+Constant.strOutputFormat).
		then().
				statusCode(200).
				extract().response();
				
		this.extractFuelStationID(resp);			
	} 
	
	/**
	 * Extract fuel station id.
	 * Verify is station id is successfully retrieved.
	 */
	public int extractFuelStationID(Response resp) throws JSONException
	{
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
				intStationID = fuelStation.getInt("id");
				Reporter.log("Station:"+fuelStation.getString("station_name")+" has "+intStationID+" id.", true);
				break;
			}
		}
		
		//Check is station id is retrieved
		Assert.assertTrue((intStationID!=0), "Station ID cannot be retrieved!");
		
		return intStationID;
	}
	
	/**
	 * Test to find fuel station address.
	 * TestFindStationAddress test depends on TestFindStationid to fetch fuel station id.
	 * @throws JSONException
	 */
	@Test(dependsOnMethods = "testFindStationID")
	public void testFindStationAddress() throws JSONException 
	{ 
		//Check if strStationID is available
		if(intStationID != 0)
		{
			Response resp =
					given().
						queryParam("api_key", Constant.strAPIKey).		 
					when().
						get("https://api.data.gov/nrel/alt-fuel-stations/v1/"+intStationID+"."+Constant.strOutputFormat).
					then().
							statusCode(200).
							extract().response();
			
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