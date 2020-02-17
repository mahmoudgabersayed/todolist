package actions;

public interface IToDoButton
{
	default void setValidationNotPass(boolean validationNotPass)
	{

	}

	default boolean getValidationPass()
	{
		return true;
	}
}
