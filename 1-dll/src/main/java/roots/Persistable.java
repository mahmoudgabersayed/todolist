package roots;

import javax.persistence.*;
import java.io.*;

@MappedSuperclass
public abstract class Persistable implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		return " id " + getId();
	}
}
