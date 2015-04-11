package github.sangmin0602.schedule;

import java.util.List;

public class ScheduleVO {

	private Integer seq;
	private String description;
	private UserVO owner;
	private PlaceVO place;
	private String time;
	
	private String registeredTime;
	
//	private List<ScheduleEventVO> events ;
	
	public ScheduleVO() {}
	
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
	
	

	public PlaceVO getPlace() {
		return place;
	}

	public String getTime() {
		return time;
	}

	public String getRegisteredTime() {
		return registeredTime;
	}

	public void setRegisteredTime(String registeredTime) {
		this.registeredTime = registeredTime;
	}

	@Override
	public String toString() {
		return "ScheduleVO [seq=" + seq + ", description=" + description
				+ ", owner=" + owner + "]";
	}

	public void setPlace(PlaceVO place) {
		this.place = place;
	}

	/**
	 * 약속 시간 - yyyy-MM-dd HH:mm:ss 형식
	 * @param time
	 */
	public void setTime(String time) {
		this.time = time;
		
	}
	
	
	
}
