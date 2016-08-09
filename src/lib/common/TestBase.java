package lib.common;

import java.io.IOException;
import jxl.read.biff.BiffException;

public class TestBase {
	protected static TestDriver driver;

	public TestBase() throws NullPointerException, BiffException, IOException {
		initializeDriver();
	}

	public void initializeDriver() throws BiffException, IOException, NullPointerException {
		driver = TestDriver.getInstance();
	}

	public void closebrowser() throws IOException {
		TestDriver.closebrowser();
	}

	public TestDriver getDriver() {
		return driver;
	}

	public void setDriver(TestDriver driver) {
		TestBase.driver = driver;
	}
}