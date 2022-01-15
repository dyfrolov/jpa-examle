package example.model.dto.abstracts;

public abstract class PageableDto {
	protected int totalPages;
	protected long totalElements;
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long l) {
		this.totalElements = l;
	}
	protected int number;
	protected int numberOfElements;
	protected int size;
	protected boolean hasNext;
	protected boolean hasPrev;
	protected boolean first;
	protected boolean last;
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getNumberOfElements() {
		return numberOfElements;
	}
	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	public boolean isHasPrev() {
		return hasPrev;
	}
	public void setHasPrev(boolean hasPrev) {
		this.hasPrev = hasPrev;
	}
	public boolean isFirst() {
		return first;
	}
	public void setFirst(boolean first) {
		this.first = first;
	}
	public boolean isLast() {
		return last;
	}
	public void setLast(boolean last) {
		this.last = last;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (first ? 1231 : 1237);
		result = prime * result + (hasNext ? 1231 : 1237);
		result = prime * result + (hasPrev ? 1231 : 1237);
		result = prime * result + (last ? 1231 : 1237);
		result = prime * result + number;
		result = prime * result + numberOfElements;
		result = prime * result + size;
		result = prime * result + (int) (totalElements ^ (totalElements >>> 32));
		result = prime * result + totalPages;
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
		PageableDto other = (PageableDto) obj;
		if (first != other.first)
			return false;
		if (hasNext != other.hasNext)
			return false;
		if (hasPrev != other.hasPrev)
			return false;
		if (last != other.last)
			return false;
		if (number != other.number)
			return false;
		if (numberOfElements != other.numberOfElements)
			return false;
		if (size != other.size)
			return false;
		if (totalElements != other.totalElements)
			return false;
		if (totalPages != other.totalPages)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PageableDto [totalPages=" + totalPages + ", totalElements=" + totalElements + ", number=" + number
				+ ", numberOfElements=" + numberOfElements + ", size=" + size + ", hasNext=" + hasNext + ", hasPrev="
				+ hasPrev + ", first=" + first + ", last=" + last + "]";
	}

	
	
}
