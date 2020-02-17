package persistent;

import org.hibernate.*;
import org.hibernate.criterion.*;
import roots.*;

import java.util.*;

public class CommonRepo
{
	public static <T extends BaseEntity> void addOrUpdate(T entity) throws Throwable
	{
		try
		{
			Session repo = HibernateDBManager.getCurrentSessionAndBeginTransaction();
			repo.saveOrUpdate(entity);
			HibernateDBManager.commitTransaction();
		}
		catch (Throwable e)
		{
			HibernateDBManager.rollbackTransaction();
			throw e;
		}

	}

	public static <T extends BaseEntity> void deleteEntity(T entity) throws Throwable
	{
		try
		{
			Session repo = HibernateDBManager.getCurrentSessionAndBeginTransaction();
			repo.delete(entity);
			HibernateDBManager.commitTransaction();
		}
		catch (Throwable e)
		{
			HibernateDBManager.rollbackTransaction();
			throw e;
		}
	}

	public static <T extends BaseEntity> void deleteEntityById(Class<? extends BaseEntity> klass, long id) throws Throwable
	{
		try
		{
			T entity = findEntityById(klass, id);
			Session repo = HibernateDBManager.getCurrentSessionAndBeginTransaction();
			if (entity == null)
				throw new Exception("No Row With id " + id + " in Table " + klass.getSimpleName());
			repo.delete(entity);
			HibernateDBManager.commitTransaction();
		}
		catch (Throwable e)
		{
			HibernateDBManager.rollbackTransaction();
			throw e;
		}
	}

	public static <T extends BaseEntity> T findEntityById(Class<? extends BaseEntity> klass, long id) throws Throwable
	{
		try
		{
			Session repo = HibernateDBManager.getCurrentSessionAndBeginTransaction();
			Criteria criteria = repo.createCriteria(klass);
			criteria.add(Restrictions.eq("id", id));
			List list = criteria.list();
			HibernateDBManager.commitTransaction();
			if (list.isEmpty())
				return null;
			return (T) list.get(0);
		}
		catch (Throwable e)
		{
			HibernateDBManager.rollbackTransaction();
			throw e;
		}
	}
	public static <T extends BaseEntity> List<T> listAll(Class<? extends BaseEntity> klass) throws Throwable
	{
		try
		{
			Session repo = HibernateDBManager.getCurrentSessionAndBeginTransaction();
			Criteria criteria = repo.createCriteria(klass);
			List list = criteria.list();
			HibernateDBManager.commitTransaction();
			return list;
		}
		catch (Throwable e)
		{
			HibernateDBManager.rollbackTransaction();
			throw e;
		}
	}
}
