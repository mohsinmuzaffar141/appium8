package StepDefinitions;

import Pages.LoginPage;
import Utilities.Capabilities;
import Utilities.DbUtil;
import Utilities.ExcelReader;
import Utilities.YmlUtil;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class LoginSteps extends Capabilities {
	LoginPage loginPage = new LoginPage();
	DbUtil dbFunc = new DbUtil();
	String fileName = "src/test/resources/TestData/signInData.yaml";
	String dbData = "src/test/resources/TestData/Db.yaml";
	String dataExcelFile = System.getProperty("user.dir") + "/src/test/resources/ExcelData/TestData.xlsx";
	YmlUtil yamlData = new YmlUtil();
	String dbCustomers,dbAdmin,dbCards;
	ExcelReader excelReader=new ExcelReader(dataExcelFile);

	@Given("^the application is open$")
	public void applicationIsOpen() {
	}

	@Then("user clicks on signIn Button from splash screen")
	public void clickSignInButtonOnSplashScreen() throws Exception {
		loginPage.clickSignInLink();
	}

	@Then("validate the username screen")
	public void userValidateSignInScreen() {
		softAssertH.assertEquals(loginPage.getYapText(), "Sign in to your account");
		softAssertH.assertFalse(loginPage.isSignInEnabled(), "Sign In Btn on Username screen disabled");
//		softAssertH.assertTrue(loginPage.isSignUpNowDisplayed(), "New to YAP? Sign up now link not appears");
	}

	@Then("validate the passcode screen")
	public void userValidatePasscodeScreen() {
//		softAssertH.assertEquals(loginPage.getPasscodeTitle(), "Enter your passcode to access this account");
//		softAssertH.assertTrue(loginPage.isForgotPWBtnDisplayed(), "Forgot password link appears");
//		softAssertH.assertFalse(loginPage.isSignInOnPWScreenDisabled(), "Sign In Btn on Password screen disabled");
	}

	//	@Then("Verify that {string} exists in database")
//	public void verifyUserInDb(String usernames) throws Exception {
//		String user = yamlData.getDataYML(fileName, usernames,"UserName");
//		String pass = yamlData.getDataYML(fileName, usernames,"Password");
//		String username = yamlData.getDataYML(fileName, usernames, "un");
//		 if(loginPage.finalDomainName.equals("stg")){
//			dbCustomers = yamlData.getDataYML(dbData, "dbStage","dbCustomers");
//		}
//		if(loginPage.finalDomainName.equals("preprod")){
//			dbCustomers = yamlData.getDataYML(dbData, "dbPreProd","dbCustomersPreProd");
//		}
//		String email = "'"+username+"'";
//		String   userExist= "";
//		if(loginPage.validateIfEmail(username)){
//			userExist = dbFunc.getDbValue(dbCustomers, user, pass, "id","customers_"+loginPage.finalDomainName+".customers", "email", email );
//		}
//		else{
//			userExist = dbFunc.getDbValue(dbCustomers, user, pass, "id","customers_"+loginPage.finalDomainName+".customers", "mobile_no", email );
//		}
//		softAssertH.assertNotNull(userExist, "User Name or Phone Number doesn't exist in Db");
//	}
	@Then("user inputs the username: {string}")
	public void userInputUsername(String username) throws Exception {
		loginPage.sendUsername(username);
	}

	@Then("user inputs the password {string}")
	public void userInputsPassword(String passcode) throws Exception {
		loginPage.sendPassword(passcode);
	}

	@Then("validate the OTP screen")
	public void userValidateOTPScreen() {
//		softAssertH.assertTrue(loginPage.isYAPLogoDisplayed(), "YAP logo not displayed on OTP screen");
//		softAssertH.assertFalse(loginPage.isResendBtnEnabled(), "Resend OTP button is not disabled for first 10 seconds");
		softAssertH.assertFalse(loginPage.isNextBtnEnabled(), "Next button is enabled before starting entering OTP");
	}

	@Then("user gives otp {string}")
	public void userInputsOTP(String otp) throws Exception {
		loginPage.sendOTP(otp);
	}

	@Then("user clicks on signIn Button")
	public void userClicksSignInButton() throws Exception {
		loginPage.clickSignIn();
	}

	@Then("verify user is on dashboard page")
	public void verifyYapItOnDashboardScreen() throws Exception {
		Assert.assertTrue(loginPage.isYapItTextAppears(), "User is on Dashboard page after successful login");
	}

//	@Then("i click on home")
//	public void iClickOnHome() {
//		loginPage.clickHomeBtn();
//	}

	@Then("i click on YAP it")
	public void iClickOnYAPIt() throws Exception {
		loginPage.clickYapIt();
	}
	@Then("i click on send money")
	public void iClickOnSendMoney(){
		loginPage.clickSendMoney();
	}

	@Then("i click on {string}")
	public void iClickOn(String text) throws Exception {
		loginPage.clickText(text);
	}

	@And("Click on add button")
	public void ClickOnAddIcon() throws Exception {
		Thread.sleep(2000);
		loginPage.clickAddButton();
	}
	@And("Click on Select Country")
	public void clickOnSelectCountry() throws Exception {
		loginPage.clickSelectCountry();
//        Assert.assertEquals(availableBalance, newBalance, "Balance before Transfer plus transfer amount not equal to updated balance");
	}

	@And("Enter country search {string} and sheet {string}")
	public void searchCountry(String Country, String sheetName) throws Exception {
		loginPage.enterSearchCountryText(excelReader.getCellData(sheetName,Country,2));
	}

	@And("Click Next Button")
	public void clickNextButton() throws InterruptedException {
		loginPage.clickNextButton();
	}
	@And("Verify {string} is displayed on Add beneficiary Screen {string}")
	public void isCountryDisplayed(String Country, String sheetName) throws InterruptedException {
		Assert.assertTrue(loginPage.isCountryDisplayed(excelReader.getCellData(sheetName,Country,2)));
	}
	@And("Verify Country flag is displayed on Add beneficiary Screen")
	public void isCountryFlagDisplayed() throws InterruptedException {
		Assert.assertTrue(loginPage.isCountryFlagDisplayed());
	}
	@And("Enter beneficiary Nick Name {string} and sheet {string}")
	public void enterNickName(String nickName,String sheetName) throws Exception {
		loginPage.enterNickNameText(excelReader.getCellData(sheetName,nickName,2));
	}
	@And("Enter beneficiary First Name {string} and sheet {string}")
	public void enterFirstName(String firstName,String sheetName) throws Exception {
		loginPage.enterFirstNameText(excelReader.getCellData(sheetName,firstName,2));
	}
	@And("Enter beneficiary Last Name {string} and sheet {string}")
	public void enterLastName(String lastName,String sheetName) throws Exception {
		loginPage.enterLastNameText(excelReader.getCellData(sheetName,lastName,2));
	}
	@And("Click Country of Residency")
	public void clickCountryOfResidence() throws Exception {
		loginPage.clickCountryOfResidence();
	}
	@And("Select Country Of Residence {string} and sheet {string}")
	public void selectCountryOfResidence(String residenceCountry,String sheetName) throws Exception {
		loginPage.selectCountryOfResidence(excelReader.getCellData(sheetName,residenceCountry,2));
	}
	@And("scroll to bottom of screen")
	public void scrollBottomOfScreen() throws Exception {
		loginPage.scrollToBottomOfScreen();
	}

	@Then("hide the keyboard")
	public void hideTheKeyboard() {
		loginPage.hideKeyboard();
	}

	@And("Enter beneficiary Bank Name {string} and sheet {string}")
	public void enterBeneficiaryBankName(String bankName,String sheetName) throws Exception {
		loginPage.enterBeneficiaryBankName(excelReader.getCellData(sheetName,bankName,2));
	}
	@And("Enter beneficiary Bank Branch Name {string} and sheet {string}")
	public void enterBeneficiaryBankBranchName(String branchName,String sheetName) throws Exception {
		loginPage.enterBeneficiaryBankBranchName(excelReader.getCellData(sheetName,branchName,2));
	}
	@And("Enter beneficiary Bank City {string} and sheet {string}")
	public void enterBeneficiaryCity(String bankCity,String sheetName) throws Exception {
		loginPage.enterBeneficiaryBankCity(excelReader.getCellData(sheetName,bankCity,2));
	}
	@And("Enter beneficiary Swift Code {string} and sheet {string}")
	public void enterBeneficiarySwiftCode(String swiftCode,String sheetName) throws Exception {
		loginPage.enterBeneficiaryBankSwiftCode(excelReader.getCellData(sheetName,swiftCode,2));
	}
	@And("Verify Bank Name {string} on Next UI Screen and sheet {string}")
	public void verifyBankName(String bankName,String sheetName) throws Exception {
		String bankNameUI = loginPage.getBankName();
		System.out.println("Bank Name Value on UI is: "+bankNameUI);
		Assert.assertEquals(bankNameUI,excelReader.getCellData(sheetName,bankName,2));
	}
	@And("Verify Bank Branch Name {string} on Next UI Screen and sheet {string}")
	public void verifyBankBranchName(String bankBranch,String sheetName) throws Exception {
		String branchNameUI = loginPage.getBankBranch();
		System.out.println("Bank Branch Name Value on UI is: "+branchNameUI);
		Assert.assertEquals(branchNameUI,excelReader.getCellData(sheetName,bankBranch,2));
	}
	@And("Enter beneficiary Bank Account {string} and sheet {string}")
	public void enterBeneficiaryBankAccount(String account,String sheetName) throws Exception {
		loginPage.enterBeneficiaryBankAccount(excelReader.getCellData(sheetName,account,2));
	}

	@And("Confirm beneficiary Bank Account {string} and sheet {string}")
	public void confirmBeneficiaryBankAccount(String account,String sheetName) throws Exception {
		loginPage.confirmBeneficiaryBankAccount(excelReader.getCellData(sheetName,account,2));
	}
	@Then("user verify {string} added successfully")
	public void userVerifyBeneficiaryAddedSuccessfully(String usernames) throws Exception {
		String user = yamlData.getDataYML(fileName, usernames,"UserName");
		String pass = yamlData.getDataYML(fileName, usernames,"Password");
		String username = yamlData.getDataYML(fileName, usernames, "un");
		String beneficiary = yamlData.getDataYML(fileName, usernames, "beneficiary");

		if(loginPage.finalDomainName.equals("stg")){
			dbCustomers = yamlData.getDataYML(dbData, "dbStage","dbCustomers");
		}
		if(loginPage.finalDomainName.equals("preprod")){
			dbCustomers = yamlData.getDataYML(dbData, "dbPreProd","dbCustomersPreProd");
		}
		String value = "'"+beneficiary+"'";

//		if(loginPage.validateIfEmail(username)){
//			userExist = dbFunc.getDbValue(dbCustomers, user, pass, "id","customers_"+loginPage.finalDomainName+".beneficiaries", "title", value );
//		}
//		else{
//			softAssertH.assertNotNull(userExist, "User Name doesn't exist in Db");
//		}
	}

	@Then("user scroll to desire text {string}")
	public void userScrollToDesireText(String text) throws InterruptedException {
		loginPage.scrollToDesireText(text);
	}

	@Then("user delete the beneficiary {string}")
	public void userDeleteTheBeneficiary(String sheetName) {
		loginPage.deleteBeneficiary(excelReader.getCellData(sheetName,"NickName",2));
	}

	@Then("user click on yes button")
	public void userClickOnYesButton() {
		loginPage.clickYesBtn();
	}

	@Then("verify beneficiary successfully completed")
	public void verifyBeneficiarySuccessfullyCompleted() {
		loginPage.verifyBeneficiaryDeleted();
	}

	@Then("i click on link with text {string}")
	public void iClickOnLinkWithText(String text) {
		loginPage.clickLinkText(text);
	}
}
