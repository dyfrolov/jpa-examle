package example.model.dto;

import java.util.List;

import example.model.dto.abstracts.PageableDto;

public class ParentsPageDto extends PageableDto{
	private List<ParentDto> parents;

	public List<ParentDto> getParents() {
		return parents;
	}

	public void setParents(List<ParentDto> parents) {
		this.parents = parents;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((parents == null) ? 0 : parents.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParentsPageDto other = (ParentsPageDto) obj;
		if (parents == null) {
			if (other.parents != null)
				return false;
		} else if (!parents.equals(other.parents))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ParentsPageDto [parents=" + parents + ", totalPages=" + totalPages + ", number=" + number
				+ ", numberOfElements=" + numberOfElements + ", size=" + size + ", hasNext=" + hasNext + ", hasPrev="
				+ hasPrev + ", first=" + first + ", last=" + last + "]";
	}
	
}
