package dimatkachenko.com.SendPayment;

import java.util.Map;

import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public class FormValidator extends AbstractValidator {
    
    public void validate(ValidationContext ctx) {
        //all the bean properties
        Map<String,Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());
         
        validateAccountNumber(ctx, (String)beanProps.get("accountNumber").getValue());
        validateAccountAccessoryMonth(ctx, (String)beanProps.get("accountAccessoryMonth").getValue());
        validateAccountAccessoryYear(ctx, (String)beanProps.get("accountAccessoryYear").getValue());
        validateCSC(ctx, (String)beanProps.get("csc").getValue());
 //       validateAmount(ctx, (Double)beanProps.get("amount").getValue());
    }
     
    private void validateAccountNumber(ValidationContext ctx, String accountNumber) { 
        if(accountNumber == null || !accountNumber.matches("[0-9]{16}")) {
            this.addInvalidMessage(ctx, "accountNumber", "Card number is not correct !");
        }
    }
     
    private void validateAccountAccessoryMonth(ValidationContext ctx, String accountAccessoryMonth) {
        if(accountAccessoryMonth == null || !accountAccessoryMonth.matches("(0[0-9]|1[012])"))  {
            this.addInvalidMessage(ctx, "accountAccessoryMonth", "Month is incorrect!");
        }
    }
     
    private void validateAccountAccessoryYear(ValidationContext ctx, String accountAccessoryYear) {
        if(accountAccessoryYear == null || !accountAccessoryYear.matches("[0-9]{2}"))  {
            this.addInvalidMessage(ctx, "accountAccessoryYEar", "Year is incorrect!");
        }
    }

    private void validateCSC(ValidationContext ctx, String csc) {
        if(csc == null || !csc.matches("[0-9]{3,4}")) {
            this.addInvalidMessage(ctx, "csc", "CSC is incorrect !");            
        }
    }
     
    private void validateAmount(ValidationContext ctx, double amount) {
        if(amount <= 0) {
            this.addInvalidMessage(ctx, "amount", "Amount should be > 0 !");
        }
    }   
     
}