package dimatkachenko.com.SendPayment;

public class PaymentInfo {

	private String requestType = "sale";
	private String userName = "829000";
	private String password = "TestNt62400~";
	private String accountId = "829001";
	private String accountType = "R";
	private String transactionIndustryType = "RE";
	
	private String accountNumber;  // validate
	private String accountAccessory;  // validate
	private String csc;  // validate
	private String amount;   // validate
	private String holderName;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	
	// for test
	public PaymentInfo() {
		
	}
	
	public PaymentInfo(String accountNumber, String accountAccessory, String csc, String amount, String holderName,
			String street, String city, String state, String zipCode) {
		super();
		this.accountNumber = accountNumber;
		this.accountAccessory = accountAccessory;
		this.csc = csc;
		this.amount = amount;
		this.holderName = holderName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	public String asHttpPostParameters() {
		return "&requestType=" + requestType + "&userName=" + userName + "&password=" + password
				+ "&accountId=" + accountId + "&amount=" + amount + "&accountType=" + accountType
				+ "&transactionIndustryType=" + transactionIndustryType	+ "&holderName=" + holderName 
				+ "&accountNumber=" + accountNumber + "&accountAccessory=" + accountAccessory 
				+ "&csc=" + csc + "&street=" + street + "&city=" + city + "&state=" 
				+ state + "&zipCode=" + zipCode;
	}
	
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getTransactionIndustryType() {
		return transactionIndustryType;
	}
	public void setTransactionIndustryType(String transactionIndustryType) {
		this.transactionIndustryType = transactionIndustryType;
	}
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountAccessory() {
		return accountAccessory;
	}
	public void setAccountAccessory(String accountAccessory) {
		this.accountAccessory = accountAccessory;
	}
	public String getCsc() {
		return csc;
	}
	public void setCsc(String csc) {
		this.csc = csc;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
