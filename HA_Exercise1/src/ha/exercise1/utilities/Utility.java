package ha.exercise1.utilities;

import java.util.Random;

public class Utility 
{
	public static String strLetters = "abcdefghijklmnopqrstuvwxyz";
	public static String strNumbers = "0123456789";

	public static String randomString(String strCharSet, int length)
	{
	    char[] strRandomString = new char[length];
	    Random rand = new Random();
	    
	    for (int i = 0; i < length; i++)
	    {
	        strRandomString[i] = strCharSet.charAt(rand.nextInt(strCharSet.length()));
	    }
	    return new String(strRandomString);
	}
}