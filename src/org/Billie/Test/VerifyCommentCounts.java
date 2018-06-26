package org.Billie.Test;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.HashMap;
import java.util.Map;
import javax.json.JsonArray;
import org.Billie.Core.MainClass;

/**
 * TO verify the comment counts - Generic Code
 * @author shivakanth
 */
public class VerifyCommentCounts extends MainClass {

	@Test()
	public void verifyCommentCountForID() {
		int value = 0;

		JsonArray array;
		Map<String, Integer> postIdFrequency = new HashMap<String, Integer>();

		array = getArray();

		if (!array.isEmpty()) {
			System.out.println("=> Finding postID frequency for response Data\n");
			array.forEach(arrayElement -> {
				String postId = arrayElement.asJsonObject().get(postIdKey).toString();
				Integer count = postIdFrequency.get(postId);

				if (count == null)
					postIdFrequency.put(postId, 1);
				else 
					postIdFrequency.put(postId, count + 1);

			});

			if (postIdFrequency.containsKey(postIDValueToFetch))
				value = postIdFrequency.get(postIDValueToFetch);

			if (value == countToValidate)
				System.out.println("=> Number of comments found for id - " + postIDValueToFetch + " is : " + value + "\n");
			else
				Assert.fail("--> Comment count is not as expected\n");
		} else
			Assert.fail("--> Comment count is not as expected as response array is empty or not valid\n");

	}
}
