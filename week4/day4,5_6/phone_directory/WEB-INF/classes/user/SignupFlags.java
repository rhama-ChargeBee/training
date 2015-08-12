package user;

public class SignupFlags{
	private boolean passFlag1;
	private boolean emailFlag;
	private boolean dbFlagNot;

	public SignupFlags(boolean passFlag1, boolean emailFlag, boolean dbFlagNot){
		this.passFlag1=passFlag1;
		this.emailFlag=emailFlag;
		this.dbFlagNot=dbFlagNot;
	}
	public boolean getPassFlag1(){
		return passFlag1;
	}
	public boolean getEmailFlag(){
		return emailFlag;
	}
	public boolean getDbFlagNot(){
		return dbFlagNot;
	}
}