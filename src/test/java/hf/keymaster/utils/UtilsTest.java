package hf.keymaster.utils;

import org.junit.Test;

public class UtilsTest {

	
	@Test
	public void UtilsTest()
	{
		assert(Utils.generateSecureString(30).length() == 30);
	}

}
