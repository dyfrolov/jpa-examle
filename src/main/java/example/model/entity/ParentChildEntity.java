package example.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="parent_child")
public class ParentChildEntity {
	@Id
	private String id;
	@ManyToOne(fetch = FetchType.LAZY)
	private ParentEntity parent;
	@ManyToOne(fetch = FetchType.LAZY)
	private ChildEntity child;
	private String info;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ParentEntity getParent() {
		return parent;
	}
	public void setParent(ParentEntity parent) {
		this.parent = parent;
	}
	public ChildEntity getChild() {
		return child;
	}
	public void setChild(ChildEntity child) {
		this.child = child;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "ParentChildEntity [id=" + id + ", parent=" + parent + ", child=" + child + ", info=" + info + "]";
	}
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
		ParentChildEntity other = (ParentChildEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
