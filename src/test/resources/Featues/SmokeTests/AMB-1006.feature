Feature: Verify a PDF document can be downloaded and opened in Briefcase 

Background: 

	Given  User Navigates to Sever 
	When  User enters Credentials to Login 
	#Login as Chambers Courtney // Test2020!
	And User clicks on Send Key to Device 
	Then User navigates to MobileBrifcase App 
	And After user navigates to Appellate DC Development - CMKA - dev 
	
	
@AMB_1006 
Scenario: 
	Verify if PDF document can be downloaded from the server to Briefcase and viewed within Briefcase
     
	Given  User clicks on Motions/Pettitions 
	Then User selects case "18-12418" 
	And User taps on pdf doc in cmecf and verifies that it is downloaded from the server and opens in Briefcase 
	
	
	#For testing purposes, we need to delete all docs from the device 
	#to verify documents are being downloaded from the server.
	#To do this, go to the settings page and tap Delete all documents on device.
	