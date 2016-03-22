# HA_Exercise
HA Exercise2

		Write a suite of two automated API tests for https://api.data.gov/
		
# Test pre-reqisties:
		- Test requires to generate api-key from https://api.data.gov/. I have removed my key from the code. To run the code please 			generate new key and assign it to 'strAPIKey' attribute present in the 'src.utilities package'.

# Dependancies
		- Test project uses 'Rest-Assured' and 'TestNG' libraries. Please make sure to include following libaries in the project.
		- Rest-Assured
			*	commons-codec-1.9.jar
			*	commons-lang3-3.3.2.jar
			*	commons-logging-1.2.jar
			*	groovy-2.4.4.jar
			*	groovy-json-2.4.4.jar
			*	groovy-xml-2.4.4.jar
			*	hamcrest-core-1.3.jar
			*	hamcrest-library-1.3.jar
			*	httpclient-4.5.1.jar
			*	httpcore-4.4.3.jar
			*	httpmime-4.5.1.jar
			*	tagsoup-1.2.1.jar
		- TestNG
			*	testng.jar
			*	jcommander.ja
			*	bsh-2.0b4.jar
			*	snakeyaml.jar

# Test Cases:
		- TestSuite contains two tests
				*		testFindStationID - 
						This test will query NREL API and will search nearest stations to Austin, TX that are part of 
						the “ChargePoint Network”. It will also verify whether 'HYATT AUSTIN' station is part of 
						“ChargePoint Network”. Finaly it will store 'HYATT AUSTIN' station's id.
				
				* 	testFindStationAddress - 
						This test is dependant on 'testFindStationID'. Once 'HYATT AUSTIN' station's id is
						extracted it will be used in the 'testFindStationAddress' test to fetch street_address. This test
						will also check if extracted address is same as expected.
															
# Issues:
		- testFindStationAddress
			Street address of 'HYATT AUSTIN' fuel station retrieved from API is '208 Barton Springs Rd' which will not 
			match with expected address '208 Barton Springs, Austin, Texas, USA, 78704'.

