import java.util.ArrayList;

public class MemberArrayList {
	
	ArrayList<Member> activeMemberStorage; 
	ArrayList<Integer> removedMemberStorage;
	//Default Constructor will be created.
	
	public MemberArrayList () {
		activeMemberStorage = new ArrayList<Member>();
		removedMemberStorage  = new ArrayList<Integer>();
	}
	
	public void addMember (Member newMember) {
//		Member newMember = new Member(ID);
		activeMemberStorage.add(newMember);
	}
	
	public void setNull (int removeIndex) {
	}
	
	public <G> void modifyMember (G modifiedItem) {
		//Use HashMap to detect the variable of Member Class
		//Getting Member's variable as String.
	}
	
	public void removeMember (int index) {
		activeMemberStorage.remove(index);
	}
	
	public void replaceMemberName () {
	}
	
	public void replaceID () {	
	}
	
}

