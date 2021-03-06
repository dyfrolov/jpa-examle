package example.model.dto;

import java.util.List;


public class ParentDto {
private String id;
private String name;
private boolean enabled;
public boolean isEnabled() {
	return enabled;
}
public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}
private List<ChildDto> childs;
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
	ParentDto other = (ParentDto) obj;
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
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public List<ChildDto> getChilds() {
	return childs;
}
public void setChilds(List<ChildDto> childs) {
	this.childs = childs;
}
@Override
public String toString() {
	return "ParentDto [id=" + id + ", name=" + name + ", enabled=" + enabled + ", childs=" + childs + "]";
}




}
