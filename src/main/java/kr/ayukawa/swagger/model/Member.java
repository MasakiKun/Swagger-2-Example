package kr.ayukawa.swagger.model;

public class Member {
	private int memberId;
	private String name;
	private String gender;
	private int age;
	private String job;
	
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	@Override
	public String toString() {
		return
				String.format(
						"Member [memberId=%d, name=%s, gender=%s, age=%d, job=%s",
						this.memberId,
						this.name,
						this.gender,
						this.age,
						this.job);
	}
	
	public Member() {}
	@java.beans.ConstructorProperties({"memberId", "name", "gender", "age", "job"})
	public Member(int memberId, String name, String gender, int age, String job) {
		this.memberId = memberId;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.job = job;
	}
}
