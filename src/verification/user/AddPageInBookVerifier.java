package verification.user;

import java.io.IOException;

import jxl.read.biff.BiffException;
import lib.User.*;
import lib.common.WebPageVerifier;

public class AddPageInBookVerifier extends WebPageVerifier {

	public AddPageInBookVerifier(CreateBookPage createBookPage) throws BiffException, IOException, InterruptedException {

	}

	public boolean verifyCreatedPage(String elementName) throws Exception {
		return verifyLabelText("AddPageInBook","CreatedPageName",elementName);
	}
	
	public boolean verifyValidationErrorMessage() throws Exception {
		return verifyText("");
	}

	public boolean verifyText(String elementName) throws Exception {
		return verifyLabelText("AddPageInBook", elementName);
	}
}
