package actions;

import javafx.scene.control.*;
import screens.*;

public abstract class BaseToDoButton extends Button implements IToDoButton
{
	private BaseToDoGuiScreen owner;

	public BaseToDoButton(BaseToDoGuiScreen owner)
	{
		this.owner = owner;
	}

	public BaseToDoButton(String text, BaseToDoGuiScreen owner)
	{
		super(text);
		this.owner = owner;
		setUpActions();
	}

	public BaseToDoGuiScreen getOwner()
	{
		return owner;
	}

	public abstract void setUpActions();
}
