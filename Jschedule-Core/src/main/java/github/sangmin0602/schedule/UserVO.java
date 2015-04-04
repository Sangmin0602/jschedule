package github.sangmin0602.schedule;

public class UserVO {

	private Integer seq;
	private String nickName;
	private String email;
	private String password;
	private String whenJoined;
	private String deleted;
	
	
	public UserVO() {
		super();
	}

	public UserVO(Integer seq, String nickName, String email, String password,
			String whenJoined, String deleted) {
		super();
		this.seq = seq;
		this.nickName = nickName;
		this.email = email;
		this.password = password;
		this.whenJoined = whenJoined;
		this.deleted = deleted;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
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
		result = prime * result + ((deleted == null) ? 0 : deleted.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((nickName == null) ? 0 : nickName.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((seq == null) ? 0 : seq.hashCode());
		result = prime * result
				+ ((whenJoined == null) ? 0 : whenJoined.hashCode());
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
		if (deleted == null) {
			if (other.deleted != null)
				return false;
		} else if (!deleted.equals(other.deleted))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nickName == null) {
			if (other.nickName != null)
				return false;
		} else if (!nickName.equals(other.nickName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (seq == null) {
			if (other.seq != null)
				return false;
		} else if (!seq.equals(other.seq))
			return false;
		if (whenJoined == null) {
			if (other.whenJoined != null)
				return false;
		} else if (!whenJoined.equals(other.whenJoined))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserVO [seq=" + seq + ", nickName=" + nickName + ", email="
				+ email + ", password=" + password + ", whenJoined="
				+ whenJoined + ", deleted=" + deleted + "]";
	}
	
}
