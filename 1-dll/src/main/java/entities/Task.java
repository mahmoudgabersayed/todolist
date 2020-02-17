package entities;

import roots.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Task extends BaseEntity
{
	private int taskOrder;
	@Lob
	private String text;
	private boolean completed;
	private Date fromTime;
	private Date toTime;
	private boolean expired;
	@ManyToOne
	private TaskUser user;

	public int getTaskOrder()
	{
		return taskOrder;
	}

	public void setTaskOrder(int taskOrder)
	{
		this.taskOrder = taskOrder;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public boolean isCompleted()
	{
		return completed;
	}

	public void setCompleted(boolean completed)
	{
		this.completed = completed;
	}

	public Date getFromTime()
	{
		return fromTime;
	}

	public void setFromTime(Date fromTime)
	{
		this.fromTime = fromTime;
	}

	public Date getToTime()
	{
		return toTime;
	}

	public void setToTime(Date toTime)
	{
		this.toTime = toTime;
	}

	public boolean isExpired()
	{
		return expired;
	}

	public void setExpired(boolean expired)
	{
		this.expired = expired;
	}

	public TaskUser getUser()
	{
		return user;
	}

	public void setUser(TaskUser user)
	{
		this.user = user;
	}
}
