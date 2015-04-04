package github.sangmin0602.schedule;

public class ScheduleVO {

	private Integer seq;
	private String description;
	private UserVO owner;
	
	public ScheduleVO(Integer seq, String description, UserVO owner) {
		super();
		this.seq = seq;
		this.description = description;
		this.owner = owner;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserVO getOwner() {
		return owner;
	}

	public void setOwner(UserVO owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "ScheduleVO [seq=" + seq + ", description=" + description
				+ ", owner=" + owner + "]";
	}
	
	
	
}
