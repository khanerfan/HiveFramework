package verification.user;

import java.io.IOException;

import jxl.read.biff.BiffException;
import lib.User.*;
import lib.common.WebPageVerifier;

public class CreateBookVerifier extends WebPageVerifier {

	public CreateBookVerifier(CreateBookPage createBookPage) throws BiffException, IOException, InterruptedException {

	}

	public boolean verifyCreatedBook(String elementName) throws Exception {
		return verifyLabelText("CreateBook","CreatedBookTitle",elementName);
	}
	
	public boolean verifyValidationErrorMessage() throws Exception {
		return verifyText("BookTitleError");
	}

	public boolean verifyText(String elementName) throws Exception {
		return verifyLabelText("CreateBook", elementName);
	}
	
	
	
}
