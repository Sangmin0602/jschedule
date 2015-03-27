package github.sangmin0602.schedule;

public class UserVO {

	private Integer seq;
	private String nickName;
	private String email;
	private String password;
	private String whenJoined;
	
	
	public UserVO(){}
	
	public UserVO(Integer seq, String nickName, String email, String password,
			String whenJoined) {
		super();
		this.seq = seq;
		this.nickName = nickName;
		this.email = email;
		this.password = password;
		this.whenJoined = whenJoined;
	}
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getWhenJoined() {
		return whenJoined;
	}
	public void setWhenJoined(String whenJoined) {
		this.whenJoined = whenJoined;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		UserVO other = (UserVO) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (seq == null) {
			if (other.seq != null)
				return false;
		} else if (!seq.equals(other.seq))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserVO [" + seq + ", nick=" + nickName + ", email="
				+ email + ", joined="
				+ whenJoined + "]";
	}
	
	
	
}
