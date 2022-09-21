@LoginModule
Feature: Login Modules

	@LoginUserCredential @regression @daily
	Scenario Outline: C18735 Verify email screen of Sign in process
	                  C19903 Verify that user is successfully able to log in with valid passcode
	                  C18732 Verify pass code screen of sign in process

		Given the application is open
		And user clicks on signIn Button from splash screen
		Then validate the username screen
		Then user inputs the username: "<cred>"
		Then validate the passcode screen
		Then user inputs the password "<cred>"
	    Then validate the OTP screen
		Then user gives otp "<cred>"
		And verify user is on dashboard page

		Examples:
			| cred      |
			| validCred |

	@internationalTransferPreReq   @test
	Scenario Outline: Add beneficiary for internation transfer

		Given the application is open
		And user clicks on signIn Button from splash screen
		Then validate the username screen
		Then user inputs the username: "<cred>"
		Then user inputs the password "<cred>"
		Then validate the OTP screen
		Then user gives otp "<cred>"
		Then i click on YAP it
		Then i click on send money
#		Then i verify that "Send money" should be appear
		Then i click on "International transfer"
		Then Click on add button
		Then Click on Select Country
		And Enter country search "Country" and sheet "internationalTransfer"
		Then Click Next Button
		And Verify "Country" is displayed on Add beneficiary Screen "internationalTransfer"
		And Verify Country flag is displayed on Add beneficiary Screen
		Then Enter beneficiary Nick Name "NickName" and sheet "internationalTransfer"
		Then hide the keyboard
		And Enter beneficiary First Name "FirstName" and sheet "internationalTransfer"
		Then hide the keyboard
		Then Enter beneficiary Last Name "LastName" and sheet "internationalTransfer"
		Then hide the keyboard
#		And Click Country of Residency
#		Then Select Country Of Residence "ResidenceCountry" and sheet "internationalTransfer"
		And scroll to bottom of screen
		Then Click Next Button
		And Enter beneficiary Bank Name "bankName" and sheet "internationalTransfer"
		And Enter beneficiary Bank Branch Name "branch" and sheet "internationalTransfer"
		And Enter beneficiary Bank City "city" and sheet "internationalTransfer"
		And Enter beneficiary Swift Code "swift" and sheet "internationalTransfer"
		Then hide the keyboard
		Then Click Next Button
		And Verify Bank Name "bankName" on Next UI Screen and sheet "internationalTransfer"
		And Verify Bank Branch Name "branch" on Next UI Screen and sheet "internationalTransfer"
		Then Enter beneficiary Bank Account "BankAccount" and sheet "internationalTransfer"
		Then hide the keyboard
		Then Click Next Button
		Then user scroll to desire text "CONFIRM"
		Then user gives otp "<cred>"
		Then i click on link with text "I’LL DO IT LATER"
		Then user delete the beneficiary "internationalTransfer"
		Then user click on yes button
		Then verify beneficiary successfully completed



		Examples:
			| cred      |
			| validCred |