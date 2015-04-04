package github.sangmin0602.schedule;

public class PlaceVO {
	private Integer seq;
	private String placeName;
	private String description;
	private Integer ownerSeq;
	
	public PlaceVO() {
		super();
	}
	public PlaceVO(Integer seq, String placeName, String description) {
		super();
		this.seq = seq;
		this.placeName = placeName;
		this.description = description;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getOwnerSeq() {
		return ownerSeq;
	}
	public void setOwnerSeq(Integer ownerSeq) {
		this.ownerSeq = ownerSeq;
	}
	@Override
	public String toString() {
		return "PlaceVO [seq=" + seq + ", placeName=" + placeName
				+ ", description=" + description + ", ownerSeq=" + ownerSeq
				+ "]";
	}
	public PlaceVO(Integer seq, String placeName, String description,
			Integer ownerSeq) {
		super();
		this.seq = seq;
		this.placeName = placeName;
		this.description = description;
		this.ownerSeq = ownerSeq;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((ownerSeq == null) ? 0 : ownerSeq.hashCode());
		result = prime * result
				+ ((placeName == null) ? 0 : placeName.hashCode());
		result = prime * result + ((seq == null) ? 0 : seq.hashCode());
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
		PlaceVO other = (PlaceVO) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (ownerSeq == null) {
			if (other.ownerSeq != null)
				return false;
		} else if (!ownerSeq.equals(other.ownerSeq))
			return false;
		if (placeName == null) {
			if (other.placeName != null)
				return false;
		} else if (!placeName.equals(other.placeName))
			return false;
		if (seq == null) {
			if (other.seq != null)
				return false;
		} else if (!seq.equals(other.seq))
			return false;
		return true;
	}

	
}
