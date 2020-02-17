package entities;

import roots.*;

import javax.persistence.*;

@Entity
public class TaskUser extends BaseEntity
{
	private String password;

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}
