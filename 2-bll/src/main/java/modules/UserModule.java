package modules;

import entities.*;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.exception.*;
import persistent.*;
import validations.*;

import java.util.*;

public class UserModule
{
	public TODOResponse register(TaskUser user)
	{
		try
		{
			Session session = HibernateDBManager.getCurrentSessionAndBeginTransaction();
			session.saveOrUpdate(user);
			HibernateDBManager.commitTransaction();
		}
		catch (Throwable e)
		{
			e.printStackTrace();
			HibernateDBManager.rollbackTransaction();
			String cause = e.getMessage();
			if(e instanceof JDBCException)
				cause = ((JDBCException) e).getSQLException().getMessage();
			return TODOResponse.createFailureResponse(cause);
		}
		return TODOResponse.createSuccessResponse();
	}

	public void deleteUser(TaskUser user)
	{

	}

	public List<TaskUser> listAllUsers()
	{
		try
		{
			return CommonRepo.listAll(TaskUser.class);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public TaskUser findUserById(long id)
	{
		try
		{
			return CommonRepo.findEntityById(TaskUser.class, id);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public TaskUser findFirstUser()
	{
		List<TaskUser> users = listAllUsers();
		if (users.isEmpty())
			return null;
		return users.get(0);
	}

	public TaskUser login(String userName, String password)
	{
		try
		{
			Session session = HibernateDBManager.getCurrentSessionAndBeginTransaction();
			Criteria criteria = session.createCriteria(TaskUser.class);
			criteria.add(Restrictions.and(Restrictions.eq("code", userName)).add(Restrictions.eq("password", password)));
			List list = criteria.list();
			HibernateDBManager.commitTransaction();
			if (list.isEmpty())
			{
				throw new Exception("username and password do not match");
			}
			return (TaskUser) list.get(0);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
