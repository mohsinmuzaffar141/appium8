package Pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import Utilities.DbUtil;
import Utilities.YmlUtil;
import com.sun.javafx.scene.traversal.Direction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import Utilities.Capabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.testng.Assert;

public class LoginPage{

	static Connection dbConnection = null;

	public AndroidDriver driver;
	Utility utility = new Utility();
	DbUtil dbFunc = new DbUtil();
	String fileName = "src/test/resources/TestData/signInData.yaml";
	String dbData = "src/test/resources/TestData/Db.yaml";
	YmlUtil yamlData = new YmlUtil();
	public static String finalDomainName;
	@AndroidFindBy(id ="btnVerifyPasscode")
	public WebElement verifyPasscodeBtn;

	@AndroidFindBy(id="btnLogIn")
	public WebElement signInButton;

	@AndroidFindBy(id="tvSignIn")
	public WebElement signInBtn;

	@AndroidFindBy(id="etCoreInput")
	public WebElement emailInputField;

	@AndroidFindBy(id="button1")
	public WebElement passwordFirst;

	@AndroidFindBy(id="button2")
	public WebElement passwordSecond;

	@AndroidFindBy(id="button3")
	public WebElement passwordThird;

	@AndroidFindBy(id="button4")
	public WebElement passwordFourth;

	@AndroidFindBy(id="button5")
	public WebElement passwordFifth;

	@AndroidFindBy(id="button6")
	public WebElement passwordSix;

	@AndroidFindBy(id="button7")
	public WebElement passwordSeven;

	@AndroidFindBy(id="button8")
	public WebElement passwordEight;

	@AndroidFindBy(id="button9")
	public WebElement passwordNine;

	@AndroidFindBy(id="button0")
	public WebElement passwordZero;

	@AndroidFindBy(id="btnAction")
	public WebElement setPasscodeBtn;

	@AndroidFindBy(id="etCoreInput")
	public WebElement emailInputPlaceholder;

	@AndroidFindBy(id="tvSignIn")
	public WebElement yapText;

	@AndroidFindBy(id="tvSignUp")
	public WebElement signUpNowLink;

	@AndroidFindBy(id="tvForgotPassword")
	public WebElement forgotPassBtn;

	@AndroidFindBy(id="tvVerifyPasscodeTitle")
	public WebElement passcodeTitle;

	@AndroidFindBy(id="ivClose")
	public WebElement closeMailApp;

	@AndroidFindBy(id="tvError")
	public WebElement invalidUNPW;

	@AndroidFindBy(id="textView4")
	public WebElement otpMobileNumber;

	@AndroidFindBy(id="tvError")
	public WebElement setPwdError;

	@AndroidFindBy(id="ivOne")
	public WebElement pwdString;

	@AndroidFindBy(id="buttonRemove")
	public WebElement removePwd;

	@AndroidFindBy(id="tvSubTitle")
	public WebElement otpTitle;

	@AndroidFindBy(id="btnCreatePin")
	public WebElement doneBtnAfterResetPW;

	@AndroidFindBy(id="clSignUp")
	public WebElement newToYAPSignUpNow;

	@AndroidFindBy(id="swRemember")
	public WebElement rememberIDToggleBtn;

	@AndroidFindBy(id="ivMenu")
	public WebElement menuHamburgerIconOnDashboard;

	@AndroidFindBy(id="tvLogOut")
	public WebElement logoutBtn;

	@AndroidFindBy(xpath="//android.widget.Button[@text='LOG OUT']")
	public WebElement finalLogoutBtn;

	@AndroidFindBy(id="tvNoThanks")
	public WebElement noThanksOnTouchIDOption;

	@AndroidFindBy(id="ivLogo")
	public WebElement yapLogo;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Resend OTP']")
	public WebElement resendOTPBtn;

	@AndroidFindBy(id="done")
	public WebElement nextBtnOnOTPScreen;

	@AndroidFindBy(id="cvYapIt")
	public WebElement yapItText;

	@AndroidFindBy(id="yapHome")
	public WebElement homeBtn;

	@AndroidFindBy(id="etMobileNumber")
	public WebElement SignUpMobileNoInputField;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Send money']/preceding-sibling::android.widget.ImageButton")
	public WebElement sendMoney;

	@AndroidFindBy(id="ivRightIcon")
	public WebElement addIcon;

	@AndroidFindBy(id="tvCountrySelect")
	public WebElement selectCountry;

	@AndroidFindBy(id="etSearch")
	public WebElement searchCountryTextBox;

	@AndroidFindBy(xpath="//android.widget.Button[@text='NEXT']")
	public WebElement nextBtn;

	@AndroidFindBy(id="flag_img")
	public WebElement flagImg;

	@AndroidFindBy(id="etnickName")
	public WebElement nickName;

	@AndroidFindBy(id="etFirstName")
	public WebElement firstName;

	@AndroidFindBy(id="etLastName")
	public WebElement lastName;

	@AndroidFindBy(id="textView")
	public WebElement countryOfResidence;

	@AndroidFindBy(id="etBankName")
	public WebElement beneficiaryBankName;

	@AndroidFindBy(id="etBranch")
	public WebElement beneficiaryBranchName;

	@AndroidFindBy(id="etBankCity")
	public WebElement beneficiaryBankCity;

	@AndroidFindBy(id="etSwiftCode")
	public WebElement beneficiaryBankSwiftCode;

	@AndroidFindBy(id="tvChangeCurrency")
	public WebElement changeCurrency;

	@AndroidFindBy(id="labelCardType")
	public WebElement currencyValue;

	@AndroidFindBy(id="etIban")
	public WebElement beneficiaryBankAccount;

	@AndroidFindBy(id="etConfirmIban")
	public WebElement confirmBeneficiaryBankAccount;

	@AndroidFindBy(id="tvBankName")
	public WebElement bankName;

	@AndroidFindBy(id="tvBankAddress")
	public WebElement bankAddress;

	@AndroidFindBy(id="tvNoThanks")
	public WebElement noThanksBtn;

	@AndroidFindBy(id="btnDelete")
	public WebElement deleteBtn;

	@AndroidFindBy(xpath="//android.widget.Button[@text='YES']")
	public WebElement yesBtn;

	@AndroidFindBy(id="tvButtonTitle")
	public WebElement okBtn;

	@AndroidFindBy(id="tvTitle")
	public WebElement deleteMsg;




	public LoginPage() {
		this.driver = (AndroidDriver) Capabilities.getDriverInstance();
		PageFactory.initElements(new AppiumFieldDecorator(this.driver, Duration.ofSeconds(10)), this);
	}

	public void clickSignIn() throws Exception {
		utility.clickButton(signInButton);
	}

	public void clickSignInLink() throws Exception {
		Thread.sleep(2000);
		utility.clickButton(signInBtn);
	}

	public String getYapText(){
		return utility.getTextFunc(yapText);
	}

	public String emailInputPlaceHolder(){
		return utility.getTextFunc(emailInputPlaceholder);
	}

	public boolean isSignInEnabled(){
		return signInButton.isEnabled();
	}

	public String signUpNowText(){
		return utility.getTextFunc(signUpNowLink);
	}

	public String getPasscodeTitle(){
		return utility.getTextFunc(passcodeTitle);
	}

	public boolean isForgotPWBtnDisplayed(){
		return forgotPassBtn.isDisplayed();
	}

	public boolean isSignUpNowDisplayed(){
		return newToYAPSignUpNow.isDisplayed();
	}

	public boolean isRememberIDBtnDisplayed(){
		return rememberIDToggleBtn.isDisplayed();
	}

	public boolean isYapItTextAppears(){
		return yapItText.isDisplayed();
	}

	public boolean isSignInOnPWScreenDisabled(){
		return verifyPasscodeBtn.isEnabled();
	}

	public void sendUsername(String cred) throws Exception {
		System.out.println(cred);
		String username = yamlData.getDataYML(fileName, cred, "mobileNum");
		utility.setText(SignUpMobileNoInputField, username);
		utility.clickButton(signInButton);
	}

	public void enterPassword(String cred) throws Exception {
		for(int i = 0; i < 6; i++) {
			if (driver.findElements(By.id("ivOne")).size()>0) {
				utility.clickButton(removePwd);
			}
			else break;
		}
		String passcode = yamlData.getDataYML(fileName, cred, "pw");
		for (int i = 0; i < passcode.length(); i++) {
			char code = passcode.charAt(i);
			String s = String.valueOf(code);
			switch (s) {
				case "1":
					utility.clickButton(passwordFirst);
					break;
				case "2":
					utility.clickButton(passwordSecond);
					break;
				case "3":
					utility.clickButton(passwordThird);
					break;
				case "4":
					utility.clickButton(passwordFourth);
					break;
				case "5":
					utility.clickButton(passwordFifth);
					break;
				case "6":
					utility.clickButton(passwordSix);
					break;
				case "7":
					utility.clickButton(passwordSeven);
					break;
				case "8":
					utility.clickButton(passwordEight);
					break;
				case "9":
					utility.clickButton(passwordNine);
					break;
				case "0":
					utility.clickButton(passwordZero);
					break;
			}
		}
	}

	public void sendPassword(String passcode) throws Exception {
		enterPassword(passcode);
		utility.clickButton(verifyPasscodeBtn);
	}

	public boolean isYAPLogoDisplayed(){
		return yapLogo.isDisplayed();
	}

	public boolean isResendBtnEnabled(){
		return resendOTPBtn.isEnabled();
	}

	public boolean isNextBtnEnabled(){
		return nextBtnOnOTPScreen.isEnabled();
	}

	public void sendOTP(String cred) throws Exception {
		Thread.sleep(5000);
		String otp = yamlData.getDataYML(fileName, cred, "otp");
		if (otp != null) {
			enterOTP(otp);
		}
		else {
			String mobileNum = yamlData.getDataYML(fileName, cred, "mobileNum");
			String email = yamlData.getDataYML(fileName, cred, "un");
			String emailVal= "'"+email+"'";
			String requiredDB = yamlData.getDataYML(dbData, "dbPreProd", "dbMessage");
			String[] dbDomainName = requiredDB.split("-");
			String domainName = dbDomainName[0];
			String[] domainFinalName = domainName.split("//");
			finalDomainName = domainFinalName[1];
			String user = yamlData.getDataYML(fileName, cred, "UserName");
			String pw = yamlData.getDataYML(fileName, cred, "Password");
//			String mobileNum=dbFunc.getDbValue(requiredDB, user, pw, "mobile_no","admin_"+finalDomainName+".users",emailVal);
			String countryCode=dbFunc.getDbValue(requiredDB, user, pw, "country_code","admin_"+finalDomainName+".users",emailVal);
			if(countryCode==null){
				countryCode="00971";
			}
			String finalOtp = dbFunc.getOTPValueFromDB(requiredDB, user, pw, "sms_text", "messages_" + finalDomainName + ".sms_logs", mobileNum,countryCode);
			enterOTP(finalOtp);
		}
		if (driver.findElements(By.id("tvNoThanks")).size()>0) {
			utility.clickButton(noThanksBtn);
			utility.clickButton(noThanksBtn);
		}
		if (driver.findElements(By.id("ivClose")).size() > 0) {
			utility.clickButton(closeMailApp);
		}
	}

	public void enterOTP(String otp){
//		driver.get;
		for (int i = 0; i < otp.length(); i++) {
			char code = otp.charAt(i);
			String s = String.valueOf(code);
			switch (s) {
				case "1":
					driver.pressKey(new KeyEvent(AndroidKey.NUMPAD_1));
					break;
				case "2":
					driver.pressKey(new KeyEvent(AndroidKey.NUMPAD_2));
					break;
				case "3":
					driver.pressKey(new KeyEvent(AndroidKey.NUMPAD_3));
					break;
				case "4":
					driver.pressKey(new KeyEvent(AndroidKey.NUMPAD_4));
					break;
				case "5":
					driver.pressKey(new KeyEvent(AndroidKey.NUMPAD_5));
					break;
				case "6":
					driver.pressKey(new KeyEvent(AndroidKey.NUMPAD_6));
					break;
				case "7":
					driver.pressKey(new KeyEvent(AndroidKey.NUMPAD_7));
					break;
				case "8":
					driver.pressKey(new KeyEvent(AndroidKey.NUMPAD_8));
					break;
				case "9":
					driver.pressKey(new KeyEvent(AndroidKey.NUMPAD_9));
					break;
				case "0":
					driver.pressKey(new KeyEvent(AndroidKey.NUMPAD_0));
					break;
			}

		}
	}

	public void clickForgotPasscode() throws Exception {
		utility.waitForElement(driver, forgotPassBtn);
		utility.clickButton(forgotPassBtn);
	}

	public String extractOTP(String otpMessage) throws Exception {
		String [] s1 = otpMessage.split("is ");
		String [] s2 = s1[1].split(" and");
		System.out.println("loginPage Object is: "+s2[0]);
		return  s2[0];
	}

	public String extractMobileNumber(String mobileOtpSent) throws Exception {
		String [] s1 = mobileOtpSent.split("\\+");
		String mobileNum = "00"+s1[1];
		mobileNum = mobileNum.replaceAll(" ", "");
		return mobileNum;
	}

//	public void enterVerificationCode(String cred) throws Exception {
//		String mobileNum = extractMobileNumber(utility.getTextFunc(otpMobileNumber));
//		String dbCustomers = yamlData.getDataYML(dbData, "db","dbCustomers");
//		String user = yamlData.getDataYML(fileName, cred, "UserName");
//		String pw = yamlData.getDataYML(fileName, cred, "Password");
//		String otp = dbFunc.getOTPValueFromDB(dbCustomers, user, pw, "sms_text", "messages_stg.sms_logs", mobileNum);
//		enterOTP(extractOTP(otp));
//	}

	public void setPassword(String passcode) throws Exception {
		enterPassword(passcode);
		utility.clickButton(setPasscodeBtn);
	}

	public void clickDoneAndUpdatePWinYaml(String oldPW, String newPW) throws Exception {
		utility.clickButton(doneBtnAfterResetPW);
//		yamlData.updateOldPasscode(fileName, oldPW, newPW);
	}

	public String getPrevPWErrorMsg(){
		return utility.getTextFunc(setPwdError);
	}

	public String getInvalidCredMsg(){
		return utility.getTextFunc(invalidUNPW);
	}

	public void clickMenuIconOnDashboardPage() {
		utility.clickButton(menuHamburgerIconOnDashboard);
	}

	public void clickOnLogoutButton() {
		utility.clickButton(logoutBtn);
		utility.clickButton(finalLogoutBtn);
	}
//
//	public boolean validateIfEmail(String email) {
//		Pattern pattern;
//		Matcher matcher;
//		String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//		pattern = Pattern.compile(EMAIL_PATTERN);
//		matcher = pattern.matcher(email);
//		return matcher.matches();
//	}
//
//
//	public String deleteUserAdmin(String url, String user, String pass, String value) throws Exception {
////			Connection dbConnection = null;
//		String valueIs = null;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Properties info = new Properties();
//			info.put("user", user );
//			info.put("password", pass);
//			dbConnection = DriverManager.getConnection(url, info);
//			if (dbConnection != null) {
//				System.out.println("Successfully connected to MySQL database test");
//				Statement s = dbConnection.createStatement();
//				s.execute("set sql_safe_updates=0;");
//				s.execute("DELETE FROM admin_"+finalDomainName+".users WHERE user_id IN (select email from customers_"+finalDomainName+".customers where customer_id IN ('" + value + "'));");
//			}
//		} catch (SQLException ex)
//		{ System.out.println("An error occurred while connecting MySQL databse");
//			ex.printStackTrace();
//		}finally{
//			dbConnection.close();
//		}
//		return valueIs;
//	}
//
//	public String deleteUserCards(String url, String user, String pass, String value) throws Exception {
//		String valueIs = null;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Properties info = new Properties();
//			info.put("user", user );
//			info.put("password", pass);
//			dbConnection = DriverManager.getConnection(url, info);
//			if (dbConnection != null) {
//				System.out.println("Successfully connected to MySQL database test");
//				Statement s= dbConnection.createStatement();
//				s.execute("DELETE FROM cards_"+finalDomainName+".users_meetings WHERE id_user_address IN (select id from cards_"+finalDomainName+".user_addresses where card_serial_number in (SELECT card_serial_number FROM cards_"+finalDomainName+".user_cards where customer_id in ('"+value+"')));");
//				s.execute(	"DELETE from cards_"+finalDomainName+".user_addresses where card_serial_number in (SELECT card_serial_number FROM cards_"+finalDomainName+".user_cards where customer_id in ('"+value+"'));");
//				s.execute("DELETE from cards_"+finalDomainName+".user_cards where customer_id in ('"+value+"');");
//			}
//		} catch (SQLException ex)
//		{ System.out.println("An error occurred while connecting MySQL databse");
//			ex.printStackTrace();
//		}finally{
//			dbConnection.close();
//		}
//		return valueIs;
//	}
//
//	public String deleteUserCustomer(String url, String user, String pass, String value,String uuid) throws Exception {
//		String valueIs = null;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Properties info = new Properties();
//			info.put("user", user );
//			info.put("password", pass);
//			dbConnection = DriverManager.getConnection(url, info);
//			if (dbConnection != null) {
//				System.out.println("Successfully connected to MySQL database test");
//				Statement s= dbConnection.createStatement();
//				s.execute("DELETE from customers_"+finalDomainName+".customer_documents WHERE customer_id IN ('"+value+"');");
//				s.execute(	"DELETE from customers_"+finalDomainName+".customer_info WHERE customer_id IN ('"+value+"');");
//				s.execute("DELETE from customers_"+finalDomainName+".document_information where customer_uuid in (SELECT UUID from customers_"+finalDomainName+".customers WHERE customer_id IN ('"+value+"'));");
//				s.execute("DELETE from customers_"+finalDomainName+".accounts where parent_uuid in ('"+uuid+"');");
//				s.execute("DELETE from customers_"+finalDomainName+".accounts where customer_id in (SELECT id from customers_"+finalDomainName+".customers WHERE customer_id IN ('"+value+"'));");
//				s.execute("DELETE from customers_"+finalDomainName+".customers where customer_id in ('"+value+"');");
//				s.execute("DELETE from customers_"+finalDomainName+".customer_seq where id_customer in ('"+value+"');");
//				s.execute("DELETE from customers_"+finalDomainName+".kyc_amendments where customer_id in ('"+value+"');");
//				s.execute("DELETE from customers_"+finalDomainName+".kyc_amendment_history where customer_id in ('"+value+"');");
//			}
//		} catch (SQLException ex)
//		{ System.out.println("An error occurred while connecting MySQL databse");
//			ex.printStackTrace();
//		}finally{
//			dbConnection.close();
//		}
//		return valueIs;
//	}
//
//	public void clickHomeBtn() {
//		utility.clickButton(homeBtn);
//	}

	public void clickYapIt() throws Exception {
		yapItText.click();
	}

	public void clickSendMoney(){
		utility.clickButton(sendMoney);
	}
	public void clickText(String text) throws Exception {
		WebElement buttonText=driver.findElement(By.xpath("//android.widget.TextView[@text='"+text+"']"));
		utility.clickButton(buttonText);
	}

	public void clickAddButton() throws Exception {
		utility.clickButton(addIcon);
	}
	public void clickSelectCountry() throws Exception {
		utility.clickButton(selectCountry);
	}
	public void enterSearchCountryText(String country) throws Exception {
		utility.setText(searchCountryTextBox, country);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+country+"']")).click();
	}
	public void hideKeyboard(){
		if(driver.isKeyboardShown()){
			driver.hideKeyboard();
		}
	}

	public void clickNextButton(){
//        while(driver.findElements(By.xpath("//android.widget.Button[@text='NEXT']")).size()>0){
		utility.clickButton(nextBtn);

	}

	public boolean isCountryDisplayed(String country) throws InterruptedException {
		return  driver.findElement(By.xpath("//android.widget.TextView[@text='"+country+"']")).isDisplayed();
	}
	public boolean isCountryFlagDisplayed(){
		return flagImg.isDisplayed();
	}
	public void enterNickNameText(String nickNames) throws Exception {
		utility.setText(nickName, nickNames);
	}
	public void enterFirstNameText(String firstNames) throws Exception {
		utility.setText(firstName, firstNames);
	}
	public void enterLastNameText(String lastNames) throws Exception {
		utility.setText(lastName, lastNames);
	}
	public void clickCountryOfResidence() throws Exception {
		utility.clickButton(countryOfResidence);
	}
	public void selectCountryOfResidence(String CountryOfResidence){
		utility.scrollAndClick(CountryOfResidence);
	}
	public void scrollToBottomOfScreen() throws Exception {
		utility.swipe(driver,"DOWN"); Thread.sleep(1000);
	}

	public void enterBeneficiaryBankName(String bankName) throws Exception {
		utility.setText(beneficiaryBankName, bankName);
	}
	public void enterBeneficiaryBankBranchName(String branchName) throws Exception {
		utility.setText(beneficiaryBranchName, branchName);
	}
	public void enterBeneficiaryBankCity(String bankCity) throws Exception {
		utility.setText(beneficiaryBankCity, bankCity);
	}
	public void enterBeneficiaryBankSwiftCode(String swiftCode) throws Exception {
		utility.setText(beneficiaryBankSwiftCode, swiftCode);
	}
	public void clickChangeCurrency(){
		utility.clickButton(changeCurrency);
	}
	public String getCurrencyOnUI(){
		return utility.getTextFunc(currencyValue);
	}
	public String getBankName(){
		return utility.getTextFunc(bankName);
	}
	public String getBankBranch(){
		return utility.getTextFunc(bankAddress);
	}
	public void enterBeneficiaryBankAccount(String BankAccount) throws Exception {
		utility.setText(beneficiaryBankAccount, BankAccount);
	}

	public void confirmBeneficiaryBankAccount(String BankAccount) throws Exception {
		utility.setText(confirmBeneficiaryBankAccount, BankAccount);
	}

	public void scrollToDesireText(String text) throws InterruptedException {
		utility.scroll(text);
	}

	public void deleteBeneficiary(String nickName) {
		WebElement elem=driver.findElement(By.xpath("//android.widget.TextView[@text = '"+nickName+"']"));
		utility.swipeElementAndroid(elem, Direction.LEFT);
		utility.clickButton(driver.findElement(By.xpath("//android.widget.TextView[@text='"+nickName+"']/following::android.widget.TextView[@text = 'Delete']")));
	}

	public void clickYesBtn() {
		utility.clickButton(yesBtn);
	}

	public void verifyBeneficiaryDeleted() {
		Assert.assertTrue(deleteMsg.isDisplayed());
		utility.clickButton(okBtn);
	}

	public void clickLinkText(String text) {
		System.out.println(text);
		WebElement buttonText=driver.findElement(By.xpath("//android.widget.Button[@text=""\"+text+"\"]"));
		utility.clickButton(buttonText);
	}
}