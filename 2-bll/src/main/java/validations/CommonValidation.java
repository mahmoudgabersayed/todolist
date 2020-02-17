package validations;

import utils.*;

public class CommonValidation
{
	public static void checkRequired(String fieldName, Object value, TODOResponse response)
	{
		if (Checker.isNotEmptyOrNull(value))
			return;
		TODOResponse.createFailureResponse(fieldName + " field is required").addToResponse(response);
	}
}
