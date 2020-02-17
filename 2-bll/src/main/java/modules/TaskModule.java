package modules;

import entities.*;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.exception.*;
import persistent.*;
import validations.*;

import java.util.*;

public class TaskModule
{
	public TODOResponse addOrUpdate(Task task)
	{
		try
		{
			CommonRepo.addOrUpdate(task);
		}
		catch (Throwable e)
		{
			String error = e.getMessage();
			if(e instanceof GenericJDBCException)
				error = ((GenericJDBCException) e).getSQLException().getMessage();
			e.printStackTrace();
			return TODOResponse.createFailureResponse(error);
		}
		return TODOResponse.createSuccessResponse();
	}

	public TODOResponse deleteTask(Task task)
	{
		try
		{
			CommonRepo.deleteEntity(task);
		}
		catch (Throwable e)
		{
			String error = e.getMessage();
			if(e instanceof GenericJDBCException)
				error = ((GenericJDBCException) e).getSQLException().getMessage();
			e.printStackTrace();
			return TODOResponse.createFailureResponse(error);
		}
		return TODOResponse.createSuccessResponse();
	}

	public List<Task> findUserTasks(TaskUser user) throws Throwable
	{
		List<Task> tasks;
		Session session;
		try
		{
			session = HibernateDBManager.getCurrentSessionAndBeginTransaction();
			Criteria criteria = session.createCriteria(Task.class);
			criteria.add(Restrictions.eq("user", user));
			tasks = criteria.list();
			HibernateDBManager.commitTransaction();
		}
		catch (Throwable e)
		{
			e.printStackTrace();
			String cause = e.getMessage();
			if (e instanceof JDBCException)
				cause = ((JDBCException) e).getSQLException().getMessage();
			throw new Exception(cause);
		}
		return tasks;
	}
}