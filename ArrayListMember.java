import java.util.ArrayList;

public class ArrayListMember extends ArrayList<Member> {
	
	void addNewMember (String ID) {
		Member newMember = new Member (ID);
		this.add(newMember);
	}
}


