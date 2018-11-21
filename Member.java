//import java.lang.*;

	public class Member {
		private String ID;
		private String firstName;
		private String lastName;
		private String major;
		private Boolean VIP;
		
		public Member () {}
		
		public Member (String ID) {
			this.ID = ID;
		}
		
		public void setID (String ID) {
			this.ID = ID;
		}
		
		public void setfName (String fName) {
			this.firstName = fName;
		}
		
		public void setlName (String lName) {
			this.lastName = lName;
		}
		
		public void setMajor (String major) {
			this.major = major;
		}
		
		public void setVIP (String VIP) {
			if (VIP.equals("true")) {
				this.VIP = true;
			} else if (VIP.equals("false"))   {
				this.VIP = false;
			} else {
				return;
			}
		}
		
		public String getFirstName () {
			return this.firstName;
		}
		
		public String getLastName () {
			return this.lastName;
		}
		
		public String major () {
			return this.major;
		}
		
		public String getName () {
			return this.firstName + " " + this.lastName;
		}
		
		public String getCunyID () {
			return this.ID;
		}
		
		public String getMemberInfo () {
			String vip = "";
			if (this.VIP == null) {
				vip = "(Not Set)";
;			}
			return this.ID + " <> " + this.firstName + " " + this.lastName + " <> " + this.major + " <> " + this.VIP + vip;
		}
		
		
	}

