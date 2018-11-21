
**_Custom Json Parsing Program (Data Structure Project 1 - Fall 2018)_**

# PROJECT 1 ___________ PARSING STRING 
###### (Some code templates are provided by Prof) 

- Array Stack Implement Stack and Perform Push,Pop and adjust Size.

- HashIndex Class perform Hashing, finding Hashed Value and Removing Hashed KV pairs.

- IO Class perform Write file to ouput.txt and log.txt.

- Input.json has KV pairs of Strings

- Member Class contain Variable for Keys of Json Strings and functions for setting and mutating variables.

- MemberArrayList contain Two ArrayList Variable "ActiveMemberStorage" and "RemovedMemberStorage". RemovedMemberStorage will contains indexs value of ActiveMemberStorage which are removed.

  Ex: ActiveMemberStorage index -> 4 is removed First According to Json String. RemovedMemberStorage will contains index value of 4 at first element of its array. This is for time-space trade off for removing array and reshifting all data and hashing keys.

- Ouput.txt _(Sample file above has written data)_ will be written with Active members information.

- Stack Interface

- log.txt _(Sample File above has written data)_ contain every actions performed on Json Strings' KV pairs.

- Main.java contains a very large "findData" function which performs on every strings which are cut off from "{"  to "}". This function will separate every word between two quotes "" and check every variable and perform necessary actions such as success of each string will be written on output and every error or success of getting data from Json KV pairs will be writted on log.txt. Please refer comment lines for code implementations and log file for some possible errors. 
