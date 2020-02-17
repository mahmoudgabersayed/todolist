package validations;

import utils.*;

import java.util.*;

public class TODOResponse
{
	private String errorMessage;
	private List<TODOResponse> responses;

	private TODOResponse()
	{
		this("");
	}

	private TODOResponse(String errorMessage)
	{
		responses = new ArrayList<>();
		this.errorMessage = errorMessage;
	}

	public static TODOResponse createFailureResponse(String message)
	{
		return new TODOResponse(message);
	}

	public static TODOResponse createSuccessResponse()
	{
		return new TODOResponse();
	}

	public void addResponse(TODOResponse response)
	{
		responses.add(response);
	}

	public void addToResponse(TODOResponse response)
	{
		ArrayList<TODOResponse> todoResponses = new ArrayList<>(responses);
		todoResponses.add(new TODOResponse(errorMessage));
		response.responses.addAll(todoResponses);
	}

	public boolean success()
	{
		return !failed();
	}

	public boolean failed()
	{
		for (TODOResponse response : responses)
		{
			if (Checker.isNotEmptyOrNull(response.getErrorMessage()))
				return true;
		}
		return Checker.isNotEmptyOrNull(getErrorMessage());
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}

	public String getMessagesSeparatedInLines()
	{
		if (Checker.isEmptyOrNull(responses))
			return getErrorMessage();
		String message = "";
		for (TODOResponse response : responses)
		{
			if (Checker.isNotEmptyOrNull(message))
				message += "\n";
			message += response.getErrorMessage();
		}
		return message;
	}
}
