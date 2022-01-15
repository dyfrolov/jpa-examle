package example.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="parent")
public class ParentTagEntity {
	@Id
	private String id;
	private String info;
	@ManyToOne(fetch = FetchType.LAZY)
	private ParentEntity parent;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParentTagEntity other = (ParentTagEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public ParentEntity getParent() {
		return parent;
	}
	public void setParent(ParentEntity parent) {
		this.parent = parent;
	}
	@Override
	public String toString() {
		return "ParentTagEntity [id=" + id + ", info=" + info + ", parent=" + parent + "]";
	}
	
}
