import entities.*;
import persistent.*;

import java.util.*;

public class DLLTest
{
	private static TaskUser user;

	public static void main(String[] args)
	{
		try
		{
			HibernateDBManager.setDbConfigFileName("hibernate.cfg.xml");
			HibernateDBManager.buildSessionFactory();
			addOrFindUserTest();
			addTasksTest();
			editTaskTest();
//			deleteTaskTest();
		}
		catch (Throwable e)
		{
			e.printStackTrace();
			HibernateDBManager.rollbackTransaction();
		}
	}

	private static void addOrFindUserTest() throws Throwable
	{

		TaskUser user = findUserTest(1);
		if (user == null)
		{
			user = new TaskUser();
			user.setCode("1");
			user.setName1("Mahmoud");
			user.setName2("Mahmoud");
			user.setPassword("123");
			CommonRepo.addOrUpdate(user);
			Date startTime = new Date();
			printLapse("adding task user with id " + 1, startTime);
		}
		DLLTest.user = user;
	}

	private static void addTasksTest() throws Throwable
	{
		List<Task> tasks = CommonRepo.listAll(Task.class);
		if (!tasks.isEmpty())
			return;
		Date startTime = new Date();
		for (int i = 0; i < 3; i++)
		{
			Task task = new Task();
			tasks.add(task);
			task.setUser(user);
			task.setTaskOrder(i + 1);
			task.setCompleted(false);
			task.setText("Task " + (i + 1));
			task.setCode("Task_" + (i + 1));
			task.setName1("Task_" + (i + 1));
			task.setName2("Task_" + (i + 1));
		}
		for (Task task : tasks)
		{
			CommonRepo.addOrUpdate(task);
		}
		printLapse("adding task ", startTime);
	}

	private static void editTaskTest() throws Throwable
	{
		long id = 2;
		Task task = CommonRepo.findEntityById(Task.class, id);
		if (task == null)
			return;
		Date startTime = new Date();
		task.setText("Task " + id + " -> Task to Edit");
		CommonRepo.addOrUpdate(task);
		printLapse("editing task ", startTime);
	}

	private static void deleteTaskTest() throws Throwable
	{
		long id = 3;
		Date startTime = new Date();
		CommonRepo.deleteEntityById(Task.class, id);
		printLapse("deleting task with id " + id, startTime);
	}

	private static TaskUser findUserTest(long id) throws Throwable
	{
		Date startTime = new Date();
		TaskUser user = CommonRepo.findEntityById(TaskUser.class, id);
		printLapse("finding task user with id " + id, startTime);
		return user;
	}

	private static Task findTaskTest(long id) throws Throwable
	{
		Date startTime = new Date();
		Task task = CommonRepo.findEntityById(Task.class, id);
		printLapse("finding task with id " + id, startTime);
		return task;
	}

	private static List<Task> findTasksTest() throws Throwable
	{
		Date startTime = new Date();
		List<Task> list = CommonRepo.listAll(Task.class);
		printLapse("finding tasks ", startTime);
		return list;
	}

	private static void printLapse(String id, Date startDate)
	{
		System.out.println(id + " - " + (new Date().getTime() - startDate.getTime()) + " ms");
	}
}
