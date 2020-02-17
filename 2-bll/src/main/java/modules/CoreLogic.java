package modules;

import entities.*;
import persistent.*;
import validations.*;

import java.util.*;

public class CoreLogic
{
	private static CoreLogic instance;
	private TaskModule taskModule;
	private UserModule userModule;

	public static CoreLogic getInstance()
	{
		if (instance == null)
			instance = new CoreLogic();
		return instance;
	}

	public static void loadConfiguration()
	{
		try
		{
			HibernateDBManager.setDbConfigFileName("hibernate.cfg.xml");
			HibernateDBManager.buildSessionFactory();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private CoreLogic()
	{
		try
		{
			HibernateDBManager.setDbConfigFileName("hibernate.cfg.xml");
			HibernateDBManager.buildSessionFactory();
			taskModule = new TaskModule();
			userModule = new UserModule();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public TODOResponse addOrUpdateTask(Task task)
	{
		return taskModule.addOrUpdate(task);
	}

	public TODOResponse deleteTask(Task task)
	{
		return taskModule.deleteTask(task);
	}

	public List<Task> findUserTasks(TaskUser user) throws Throwable
	{
		return taskModule.findUserTasks(user);
	}

	public TODOResponse register(TaskUser user)
	{
		return userModule.register(user);
	}

	public void deleteUser(TaskUser user)
	{
		userModule.deleteUser(user);
	}

	public TaskUser findFirstUser()
	{
		return userModule.findFirstUser();
	}

	public TaskUser login(String userName, String password)
	{
		return userModule.login(userName, password);
	}
}
