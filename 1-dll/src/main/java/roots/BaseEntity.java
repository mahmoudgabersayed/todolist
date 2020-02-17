package roots;

import javax.persistence.*;
import java.util.*;

@MappedSuperclass
public abstract class BaseEntity extends Persistable
{
	@Column(unique = true)
	private String code;
	@Column(unique = true)
	private String name1;
	@Column(unique = true)
	private String name2;

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getName1()
	{
		return name1;
	}

	public void setName1(String name1)
	{
		this.name1 = name1;
	}

	public String getName2()
	{
		return name2;
	}

	public void setName2(String name2)
	{
		this.name2 = name2;
	}

	@Override
	public String toString()
	{
		return super.toString() + " code " + getCode() + " name1 " + getName1() + " name2 " + getName2();
	}

//	public void addFieldMetaDataTo(List<FieldMetaData> metaData)
//	{
//		if (metaData == null)
//			return;
//		metaData.add(new FieldMetaData("ID", UIFieldType.IntegerNumber, getId()));
//		metaData.add(new FieldMetaData("Code", UIFieldType.Text, getCode()));
//		metaData.add(new FieldMetaData("Name1", UIFieldType.Text, getName1()));
//		metaData.add(new FieldMetaData("Name2", UIFieldType.Text, getName2()));
//	}
//
//	public List<TableHeaderMetaData> tableHeaders()
//	{
//		List<TableHeaderMetaData> metaData = new ArrayList<>();
//		metaData.add(new TableHeaderMetaData("ID", 0));
//		metaData.add(new TableHeaderMetaData("Code", 1));
//		metaData.add(new TableHeaderMetaData("Name1", 2));
//		metaData.add(new TableHeaderMetaData("Name2", 3));
//		return metaData;
//	}
}

