import entities.*;
import modules.*;

import java.util.*;

public class BLLTest
{
	public static void main(String[] args)
	{
		try
		{
			CoreLogic core = CoreLogic.getInstance();
			TaskUser user = core.login("1", "123");
			if (user == null)
				return;
			List<Task> tasks = core.findUserTasks(user);
			for (Task task : tasks)
			{
				System.out.println(task.getCode() + " for " + task.getUser());
			}
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}

}
