package dimatkachenko.com.SendPayment;

import java.util.Set;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class SendingPaymentController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	private final String PAYMENT_APPROVED = "A01";

	@Wire
	private Textbox accountNumber;
	@Wire
	private Listbox monthList;
	@Wire
	private Listbox yearList;

	private String accountAccessoryMonth;
	private String accountAccessoryYear;

	@Wire
	private Textbox csc;
	@Wire
	private Decimalbox amount;
	@Wire
	private Textbox holderName;
	@Wire
	private Textbox street;
	@Wire
	private Textbox city;
	@Wire
	private Textbox state;
	@Wire
	private Textbox zipCode;
	@Wire
	private Window responseModalWindow;
	@Wire
	private Button returnButton;

	String response;

	private ProcessingController processingController = new ProcessingControllerImpl();

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		ListModelList<String> monthModel = new ListModelList<String>(
				new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" });
		monthList.setModel(monthModel);

		ListModelList<String> yearModel = new ListModelList<String>(
				new String[] { "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28" });
		yearList.setModel(yearModel);

	}

	@Listen("onClick=#sendPayment")
	public void doSendPayment() {

		try {
			checkFormFields();
		} catch (CheckFieldException e) {
			Clients.showNotification(e.getMessage(), true);
			return;
		}

		PaymentInfo payment = preparePayment();

		response = processingController.sendPayment(payment.asHttpPostParameters());

		if (!response.equals(PAYMENT_APPROVED)) {
			Clients.showNotification("Payment hasn't been approved ! Error code " + response, true);
		} else {
			responseModalWindow.doModal();
		}
	}

	private void checkFormFields() {

		if (accountNumber.getValue().isEmpty()) {
			throw new CheckFieldException("Fill Card/Account information !");
		}

		if (csc.getValue().isEmpty()) {
			throw new CheckFieldException("Fill CSC !");
		}

		if (amount.doubleValue() <= 0) {
			throw new CheckFieldException("Amount should be > 0 !");
		}

		getSelectedMontAndYear();
	}

	private void getSelectedMontAndYear() {

		Set<String> monthSelection = ((ListModelList) monthList.getModel()).getSelection();

		if (!monthSelection.isEmpty()) {
			accountAccessoryMonth = monthSelection.iterator().next();
		} else {
			throw new CheckFieldException("You should fill Exp Date !");
		}

		Set<String> yearSelection = ((ListModelList) yearList.getModel()).getSelection();

		if (!yearSelection.isEmpty()) {
			accountAccessoryYear = yearSelection.iterator().next();
		} else {
			throw new CheckFieldException("You should fill Exp Date !");
		}
	}

	private PaymentInfo preparePayment() {

		String accountAccessory = accountAccessoryMonth + accountAccessoryYear;
		String stringAmount = Integer.toString((int) (amount.doubleValue() * 100));

		return new PaymentInfo(accountNumber.getValue(), accountAccessory, csc.getValue(), stringAmount,
				holderName.getValue(), street.getValue(), city.getValue(), state.getValue(), zipCode.getValue());
	}

	public Window getResponseModalWindow() {
		return responseModalWindow;
	}

	public void setResponseModalWindow(Window responseModalWindow) {
		this.responseModalWindow = responseModalWindow;
	}

	public Textbox getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Textbox accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Listbox getMonthList() {
		return monthList;
	}

	public void setMonthList(Listbox monthList) {
		this.monthList = monthList;
	}

	public Listbox getYearList() {
		return yearList;
	}

	public void setYearList(Listbox yearList) {
		this.yearList = yearList;
	}

	public String getAccountAccessoryMonth() {
		return accountAccessoryMonth;
	}

	public void setAccountAccessoryMonth(String accountAccessoryMonth) {
		this.accountAccessoryMonth = accountAccessoryMonth;
	}

	public String getAccountAccessoryYear() {
		return accountAccessoryYear;
	}

	public void setAccountAccessoryYear(String accountAccessoryYear) {
		this.accountAccessoryYear = accountAccessoryYear;
	}

	public Textbox getCsc() {
		return csc;
	}

	public void setCsc(Textbox csc) {
		this.csc = csc;
	}

	public Decimalbox getAmount() {
		return amount;
	}

	public void setAmount(Decimalbox amount) {
		this.amount = amount;
	}

	public Textbox getHolderName() {
		return holderName;
	}

	public void setHolderName(Textbox holderName) {
		this.holderName = holderName;
	}

	public Textbox getStreet() {
		return street;
	}

	public void setStreet(Textbox street) {
		this.street = street;
	}

	public Textbox getCity() {
		return city;
	}

	public void setCity(Textbox city) {
		this.city = city;
	}

	public Textbox getState() {
		return state;
	}

	public void setState(Textbox state) {
		this.state = state;
	}

	public Textbox getZipCode() {
		return zipCode;
	}

	public void setZipCode(Textbox zipCode) {
		this.zipCode = zipCode;
	}

	public ProcessingController getProcessingController() {
		return processingController;
	}

	public void setProcessingController(ProcessingController processingController) {
		this.processingController = processingController;
	}

	public Button getReturnButton() {
		return returnButton;
	}

	public void setReturnButton(Button returnButton) {
		this.returnButton = returnButton;
	}
}
