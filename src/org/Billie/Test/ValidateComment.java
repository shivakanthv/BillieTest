package org.Billie.Test;

import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.HashMap;
import javax.json.JsonArray;
import org.Billie.Core.MainClass;
import junit.framework.Assert;

/*************************************
 * @author shivakanth
 * @Desc : To validate comment provided
 ************************************/
public class ValidateComment extends MainClass {

	boolean isFound = true;

	@Test()
	public void verifyPresenceOfComment() {

		System.out.println("****Verifying Presence of Comment in Response******\n");
		JsonArray array;
		array = getArray();

		HashMap<String, String> userComment = new HashMap<String, String>();

		// For splitting the data in given format in ReadMe
		String commentToVerify[] = commentKey.split("\r\n");
		Arrays.asList(commentToVerify).forEach(comment -> {
			String commentArray[] = comment.split(": ");
			userComment.put(commentArray[0], commentArray[1]);
		});

		array.forEach(arrayElement -> {
			int postId = Integer.valueOf(arrayElement.asJsonObject().get(postIdKey).toString());
			if (postId == Integer.valueOf(postIDValueToFetch)) {

				if (arrayElement.asJsonObject().getInt(idKey) == Integer.valueOf(userComment.get(idKey))) {
					String name = arrayElement.asJsonObject().getString(nameKey);
					String email = arrayElement.asJsonObject().getString(emailKey);
					String body = arrayElement.asJsonObject().getString(bodyKey);

					compare(name, userComment.get(nameKey));
					compare(email, userComment.get(emailKey));
					compare(body, userComment.get(bodyKey).toString());
				}
			}
		});

		if (isFound == false)
			Assert.assertTrue("--> Failed to find the comment\n", isFound);
		else
			System.out.println("=> Success : Comment found in the response\n");

	}

	/**************************
	 * @Desc : To compare data
	 * @param actual
	 * @param expected
	 *************************/
	public void compare(String actual, String expected) {
		if (!actual.equalsIgnoreCase(expected))
			isFound = false;

	}
}