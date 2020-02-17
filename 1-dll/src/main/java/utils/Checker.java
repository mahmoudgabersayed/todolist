package utils;

import java.util.*;

public class Checker
{
	public static <T> boolean areEqual(T o1, T o2)
	{
		if (o1 == null || o2 == null)
			return false;
		return o1.equals(o2);
	}

	public static <T> boolean areNotEqual(T o1, T o2)
	{
		return !areEqual(o1, o2);
	}

	public static <T> boolean isEmptyOrNull(T o)
	{
		if (o == null)
			return true;
		if (o instanceof String)
			return ((String) o).isEmpty();
		if (o instanceof List)
			return ((List) o).isEmpty();
		return false;
	}

	public static <T> boolean isNotEmptyOrNull(T o)
	{
		return !isEmptyOrNull(o);
	}

	public static <T> T getFirstNotEmptyOrNull(T o1, T o2)
	{
		if (isNotEmptyOrNull(o1))
			return o1;
		return o2;
	}

	public static <T> T getListFirstElement(List<T> list)
	{
		if (isEmptyOrNull(list))
			return null;
		return list.get(0);
	}
}
