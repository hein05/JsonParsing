import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class Main {
	
	static MemberArrayList memberList = new MemberArrayList();
	static HashIndex hashing = new HashIndex ();
	static ArrayList <String> logMessages = new ArrayList();
	
	public final static String outputFile = "Output.txt";
	public final static String logFile = "log.txt";
	
	public static IO out = new IO("log.txt");
	
	public static void main(String[] args) {
		Main main = new Main();
		ArrayStack stack = new ArrayStack();
		CMD readWrite = new CMD("Input.json");
		int count = 0;
		String pop;
	    while (true) {
	        String nextCMD = readWrite.getNextCommand();   
	        if (stack.size() > 0) {
	        	pop = (String) stack.pop();
	        	if (pop.matches(".*\\btype\\b.*")) { //Check if String has "type" key
	        		main.findData(pop);
	        	} else {
	        		//String is not Valid. Update on Log.txt
	        		logMessages.add("String, < " + pop + " >is not Valid. There is no \"Type\" Key");
	        	}
	        }
	        if (nextCMD != null) {
	        	if (stack.size() < 100) {
	        		stack.push(nextCMD);
	        	}
	        } else {
	        	if (stack.size() > 0) { //Last POP
		        	stack.pop();
		        	pop = (String) stack.pop();
		        }
	          break;
	        }
	      }
	    main.writeToFile(outputFile);
	    main.writeToLog(logFile);
	}
	
	enum cType {ADD, DEL, MOD, DEFAULT};
	enum kType {ID, FIRSTNAME, LASTNAME, MAJOR, VIP, DEFAULT};
	
	public void findData (String popStr) {
		
		cType commandType;
		kType keyType;
		commandType = cType.DEFAULT;
		keyType = kType.DEFAULT;
		
		Pattern patternEach = Pattern.compile("\"(.*?)\""); //Find each Data for Key and Value
		Matcher matchEach = patternEach.matcher(popStr);
		
		int indexofKV = 0;
		
		boolean idExists = false;
		
		Member newMember = new Member();
		
		Integer modifiedIndex = null;
		
		while (matchEach.find()) {
			String matchedStr = matchEach.group(1);
			
			if (indexofKV == 1) {
				//Setting Enum
				if (matchedStr.equals("ADD")) {
					commandType = cType.ADD;
				} else if (matchedStr.equals("MOD")) {
					commandType = cType.MOD;
				} else if (matchedStr.equals("DEL")) {
					commandType = cType.DEL;
				} else {
					//Write to log.txt
					logMessages.add("Command, <"  + matchedStr + ">not recognized for <" + popStr + ">");
					break;
				}
			}
			
			if (commandType == cType.ADD) {
				if (indexofKV > 1 && indexofKV % 2 == 0) { //Detecting if it is key
					if (matchedStr.equals("ID")) {
						keyType = kType.ID;
						idExists = true;
					} else if (matchedStr.equals("firstName")) {
						keyType = kType.FIRSTNAME;
					} else if (matchedStr.equals("lastName")) {
						keyType = kType.LASTNAME;
					} else if (matchedStr.equals("major")) {
						keyType = kType.MAJOR;
					} else if (matchedStr.equals("VIP")) {
						keyType = kType.VIP;
					}					
				} else { //Detecting if it is Value
					if (keyType == kType.ID) {
						try { //Check if ID is integer
							Integer.parseInt(matchedStr);
						} catch (NumberFormatException e) { //Catching if String is not in Number Format
							//Write to log.txt
							logMessages.add("ID not in Number Format  ->" + "For String < " + popStr);
							break;
						}
						if (hashing.containsKey(matchedStr)) { // Check if key Already Exits
							//Write to log.txt that duplicate key. Fail to write
							logMessages.add("MemberID already exists. (Suggested Command Key \\\"MOD\\\")\"  ->" + "For String < " + popStr);
							break;
						} else {
							//Implement Success of Whole String and add member to Hashmap.
							newMember.setID(matchedStr);
							if (memberList.removedMemberStorage.size() > 0) {
								hashing.put(matchedStr,memberList.removedMemberStorage.get(0));
								System.out.println(" Hashed to removedIndex" + memberList.removedMemberStorage.get(0));
							} else {
								hashing.put(matchedStr, memberList.activeMemberStorage.size());

							}
							logMessages.add("Hashing Done ->" + "For String < " + popStr);

						}
					} else if (keyType == kType.FIRSTNAME) {
							newMember.setfName(matchedStr);

					} else if (keyType == kType.LASTNAME) {
							newMember.setlName(matchedStr);

					} else if (keyType == kType.MAJOR) {
							newMember.setMajor(matchedStr);

					} else if (keyType == kType.VIP) {
							newMember.setVIP(matchedStr);
					}
				}
				
			} else if (commandType == cType.MOD) {
				if (indexofKV > 1 && indexofKV % 2 == 0) { //Detecting if it is key
					if (matchedStr.equals("ID")) {
						keyType = kType.ID;
						idExists = true;
					} else if (matchedStr.equals("firstName")) {
						keyType = kType.FIRSTNAME;
					} else if (matchedStr.equals("lastName")) {
						keyType = kType.LASTNAME;
					} else if (matchedStr.equals("major")) {
						keyType = kType.MAJOR;
					} else if (matchedStr.equals("VIP")) {
						keyType = kType.VIP;
					}
					
				} else { //Detecting if it is Value
					if (keyType == kType.ID) {
						try { //Check if ID is integer
							Integer.parseInt(matchedStr);
						} catch (NumberFormatException e) { //Catching if String is not in Number Format
							//Write to log.txt
							logMessages.add("ID not in Number Format  ->" + "For String < " + popStr);
							break;
						}
						if (hashing.containsKey(matchedStr)) { // Check if key Must Already Exits
							modifiedIndex = hashing.find(matchedStr);
							System.out.println(" >>>>>> " + modifiedIndex);
						} else {
							//Write to log New KV pairs. Wrong Command
							logMessages.add("MemberID already exists. (Suggested Command Key \\\"ADD\\\")\"  ->" + "For String < " + popStr);
							break;
						}
					} else if (keyType == kType.FIRSTNAME) {
						if (modifiedIndex != null) {
							String fName = memberList.activeMemberStorage.get(modifiedIndex).getFirstName();
							memberList.activeMemberStorage.get(modifiedIndex).setfName(matchedStr);
							logMessages.add("Change Member First Name From:  ->" + fName + " To " + matchedStr);
						}
					} else if (keyType == kType.LASTNAME) {
						if (modifiedIndex != null) {
							String lName = memberList.activeMemberStorage.get(modifiedIndex).getLastName();
							memberList.activeMemberStorage.get(modifiedIndex).setlName(matchedStr);
							logMessages.add("Change Member Last Name From:  ->" + lName + " To " + matchedStr);
						}

					} else if (keyType == kType.MAJOR) {
						if (modifiedIndex != null) {
							String getMajor = memberList.activeMemberStorage.get(modifiedIndex).major();
							memberList.activeMemberStorage.get(modifiedIndex).setMajor(matchedStr);
							logMessages.add("Change Member's Major From:  ->" + getMajor + " To " + matchedStr);
						}

					} else if (keyType == kType.VIP) {
						if (modifiedIndex != null) {
							memberList.activeMemberStorage.get(modifiedIndex).setVIP(matchedStr);
							logMessages.add("Change Member's VIP status");
						}
					}
				}
				
			} else if (commandType == cType.DEL) {
				if (indexofKV == 2) {
					if (matchedStr.equals("ID")) {
						keyType = kType.ID;
					} else {
						break;
					}
				} else if (keyType == kType.ID) { //Do getting HashIndex Map from here.
					try { //Check if ID is integer
						Integer.parseInt(matchedStr);
					} catch (NumberFormatException e) { //Catching if String is not in Number Format
						//Write to log.txt
						logMessages.add("ID not in Number Format  ->" + "For String < " + popStr);
						break;
					}
					if (hashing.containsKey(matchedStr)) { // Check if key Must Already Exits
						modifiedIndex = hashing.find(matchedStr);
						memberList.activeMemberStorage.set(modifiedIndex, null);
						logMessages.add("REMOVED Hashing Index  ->" + "For ID < " + matchedStr);
						hashing.remove(matchedStr);
						memberList.removedMemberStorage.add(modifiedIndex);
						logMessages.add("REMOVED Member from ArrayList  ->" + "For Index < " + modifiedIndex);
					} else {
						//Write to log New KV pairs. Wrong Command
						logMessages.add("MemberID already Not exists. (Suggested Command Key \\\"ADD\\\")\"  ->" + "For String < " + popStr);
						break;
					}
				}
			}
			//To get success KVIndex must be even. and ID mustn't have duplicate or valid.
			indexofKV += 1;
		}
		//Add Member to ArrayList , Modify Member, Delete Member
		if (indexofKV % 2 == 0 && idExists) { // CHECKING IF STRING IS CORRUPTED BY CHECKING EVEN PAIRS
			if (commandType == cType.ADD) {
				if (memberList.removedMemberStorage.size() > 0) {
					memberList.activeMemberStorage.set(memberList.removedMemberStorage.get(0), newMember);
					memberList.removedMemberStorage.remove(0);
				} else {
					memberList.activeMemberStorage.add(newMember);
				}
				logMessages.add("ADDED new Member to ArrayList  ->" + "Index of KV = " + indexofKV + ">>>"+ newMember.getMemberInfo() + " Hashed Value >>>> " + hashing.find(newMember.getCunyID()) );
			} else if (commandType == cType.DEL) {
				
			}
		} else {
			if (!idExists) {
				//Write to Log.txt
				logMessages.add("NO MEMBER-ID found for String" + popStr);
			} else {
				// KV PAIRS STRING CORRUPTED. WRITE TO LOG.TXT
				logMessages.add("KV PAIRS STRING CORRUPTED.");
			}
		}
					
	}
	
	
	public void writeToFile (String fileName) {
		//WRITING TO OUTPUT FILE Line by Line
    	IO out = new IO(fileName);
        try {
          out.OutputTitle("_________ List of Active Members ___________\n\n");
          for (int i = 0; i < memberList.activeMemberStorage.size(); i++) {
        	  if (memberList.activeMemberStorage.get(i) != null) {
        		  String str = memberList.activeMemberStorage.get(i).getMemberInfo();
        		  System.out.println("Output NOT Complete." + str);
        		  String num = Integer.toString(i + 1);
        		  out.append(num + " -> ");
        		  out.append(str);
        		  out.append("\n");
        	  } else {
        		  String str = "Removed Member";
        		  String num = Integer.toString(i + 1);
        		  out.append(num + " -> ");
        		  out.append(str);
        		  out.append("\n");
        	  }
          }
          out.close();
          System.out.println("Output Complete.");
        } catch (IOException e) {
          System.out.println("File IO Error");
          e.printStackTrace();
        }
      }
	
	public void writeToLog (String fileName) {
		//WRITING TO OUTPUT FILE Line by Line
    	IO out = new IO(fileName);
        try {
          out.OutputTitle("__________ Lists of Parsing Actions__________\n\n");
          for (int i = 0; i < logMessages.size(); i++) {
        	String str = logMessages.get(i);
        	String num = Integer.toString(i + 1);
        	out.append(num + " -> ");
            out.append(str);
            out.append("\n");
          }
          out.close();
          System.out.println("Output Complete.");
        } catch (IOException e) {
          System.out.println("File IO Error");
          e.printStackTrace();
        }
      }
	
	

}
