import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;

class Test{
    public String testName;
    public float weightagePercent;
    public int duration;
    public int noOfQuestions;
    public ArrayList<Character> answerKey=new ArrayList<>();
    public String testSituation = "Available"; 
    
    public Test(String testName, float weightagePercent , int duration , int noOfQuestions)
    {
    	this.testName = testName; 
    	this.weightagePercent = weightagePercent;
    	this.duration = duration;
    	this.noOfQuestions = noOfQuestions; 
    	
    }
   
    void setKey(ArrayList<Character> ansKey){ 
    	for(int i =0; i<ansKey.size(); i++)
    	{
    		answerKey.add(ansKey.get(i)); 
    	}
    }
    int evaluatingTest(ArrayList<Character> enteredAnskey){ 
    	int marks = 0; 
    	for(int i =0; i<enteredAnskey.size();i++)
    	{
    		if(enteredAnskey.get(i) == answerKey.get(i))
    		{
    			marks += 4; 
    		} 
    		else if(enteredAnskey.get(i) == 'N')
    		{
    			marks += 0; 
    		} 
    		else
    		{
    			marks -= 1; 
    		}
    	}
        return marks;
    }
}

class CourseDetails{
	public String nameCourse; 
	public int unitsCourse; 
	public int Classroom; 
	public List<String> Components = new LinkedList<String>(); 
	public List<String> idNoofStudentsenrolled = new ArrayList<String>(); 
	public List<String> nameofStudents = new ArrayList<String>(); 
	
	CourseDetails(String nameCourse,int unitsCourse,int Classroom)
	{
		this.Classroom = Classroom; 
		this.nameCourse = nameCourse; 
		this.unitsCourse = unitsCourse; 
	}
}
	
class Course extends CourseDetails{
	public int no_of_students;
	public List<Test> allTest = new ArrayList<Test>();  
	public List<String> topics = new LinkedList<String>();
	
	Course(String nameCourse,int unitsCourse,int Classroom, int no_of_students)
	{
		super(nameCourse,unitsCourse,Classroom); 
		this.no_of_students = no_of_students; 
	} 
} 

class Student{
    public String courseName="Object Oriented Programming";
    public String name;
    public String idNumber;
    public String emailAddress;
    public String country;
    public HashMap<String, Integer> marks = new HashMap<>(); 
    public ArrayList<String> attemptStatus = new ArrayList<String>(); 
    Scanner in = new Scanner(System.in);

        
    Student(String name,String idNumber,String emailAddress,String country,Course c1){
        this.name=name;
        this.idNumber=idNumber;
        this.emailAddress=emailAddress + "@pilani.bits-pilani.ac.in";
        this.country=country; 
        c1.nameofStudents.add(this.name); 
        c1.idNoofStudentsenrolled.add(this.idNumber);
        if(!this.name.equals(""))
        {
        	try{
        		FileWriter w= new FileWriter("StudentDetails.txt", true);

        		w.write(this.name + "\n");
        		w.write(this.idNumber + "\n");
        		w.write(this.emailAddress + "\n");
        		w.write(this.country+"\n");
        		w.close();
        	}
        	catch (Exception ae) {
        		System.out.println(ae);
        	} 
        }
    } 
    public static void printWithDelays(String data, TimeUnit unit, long delay) throws InterruptedException {
        for (char ch:data.toCharArray()) {
            System.out.print(ch);
            unit.sleep(delay);
        }
    }
    
    int marksOfTest(String testName){
         if(testName.equals("Quiz"))
         {
             return marks.get("Quiz");
         }
         if(testName.equals("Midsemester_Examination"))
         {
            return marks.get("Midsemester_Examination");
         }
         if(testName.equals("Comprehensive_Examination"))
         {
            return marks.get("Comprehensive_Examination");
         }
         else
        	return 0;
    }
    public ArrayList<String> availableTests(Course c1){ 
    	Test t; 
    	ArrayList<String> avlTest = new ArrayList<String>();
    	for(int i =0; i<c1.allTest.size(); i++)
    	{
    		t =c1.allTest.get(i); 
    		if(t.testSituation.equals("Available"))
    		{
    			if(!this.attemptStatus.contains(t.testName))
    			{
    				avlTest.add(t.testName);
    			}
    		} 
    		else
    		{
    			;
    		}
    	}
    	return avlTest;
    }
    void viewEnrolledCourse(){
        System.out.println(courseName);
    }
    void viewPersonalDetails(){
        System.out.println("Name: "+name);
        System.out.println("BITS ID: "+idNumber);
        System.out.println("Email Address: "+emailAddress);
        System.out.println("Country: "+country);
        System.out.println("Quiz: "+marks.get("Quiz")+" "+"Midsemester_Examination: "+marks.get("Midsemester_Examination")+" "+"Comprehensive_Examination: "+marks.get("Comprehensive_Examination"));
    }
    static void allRegisteredStudents(Course c1){ 
    	for(int i=0; i<c1.nameofStudents.size(); i++)
    	{
    		System.out.println(c1.nameofStudents.get(i)+" "+ c1.idNoofStudentsenrolled.get(i));
    	}
    } 
    public void takeTest(Course c1) throws InterruptedException
    {
    	ArrayList<String> avltest = this.availableTests(c1); 
    	Test t; 
    	ArrayList<Character> answers = new ArrayList<Character>();
    	int index=0; 
    	System.out.print("Showing available tests");
        Student.printWithDelays("...", TimeUnit.MILLISECONDS, 1500); 
		System.out.println("");
    	for(int i=0; i<avltest.size();i++)
    	{
    		System.out.println(avltest.get(i));
    	}
    	System.out.println("From the above List please enter the Test you wish to take or None if you don't wish to take any.");
    	String attemptTest = in.next(); 
    	while(!avltest.contains(attemptTest) && !attemptTest.equals("None"))
    	{
        	System.out.println("You can not take a Test which is not in the list"); 
        	System.out.println("From the above List please enter the Test you wish to take or None if you don't wish to take any.");
        	attemptTest = in.next();
    	}
    	if(avltest.contains(attemptTest))
    	{
    		for(int i =0; i<c1.allTest.size();i++)
    		{
    			if(c1.allTest.get(i).testName.equals(attemptTest))
    			{
    				index = i; 
    			}
    		} 
    		t = c1.allTest.get(index);  
    		System.out.print("The details of the test are");
    		Student.printWithDelays("...", TimeUnit.MILLISECONDS, 500);
			System.out.println("");
    		System.out.println("The test name is " + t.testName );
    		System.out.println("The test weightage is " + t.weightagePercent );
    		System.out.println("The test duration is " + t.duration );
    		System.out.println("The test has " + t.noOfQuestions +" questions.");
    		System.out.println("Please enter the answers for the questions"); 
    		System.out.println("Enter a,b,c,d to answer the question or N is you don't want to attempt");
    		System.out.println("Every quetion carries +4 for correct answer and -1 for wrong answer");
    	
    		for(int i=0; i<t.noOfQuestions;i++)
    		{
    			Character c;
    			c = in.next().charAt(0);
    			answers.add(c);
    		} 
    	
    		int markgot = t.evaluatingTest(answers); 
    		marks.put(attemptTest, markgot); 
    		System.out.print("Evaluating marks"); 
    		Student.printWithDelays("...", TimeUnit.MILLISECONDS, 2500); 
    		System.out.println("");
    		System.out.println("You got "+this.marks.get(t.testName)+" marks in the test you attempted"); 
    		this.attemptStatus.add(attemptTest);
    	
    	} 
    	else
    	{
    		System.out.println("You have decide to not take the test now. You may take it later if its available");
    	}	
    }
}


class Teacher{
    String name;
    String email_id;
    ArrayList<String> courses; 
    Teacher(String name, String email_id,ArrayList<String> courses)
    {
    	this.name = name; 
    	this.email_id = email_id+"@pilani.bits-pilani.ac.in";
		this.courses=courses;
    }
 
    void courseDetails(){
        for(int i=0;i<courses.size();i++){
            System.out.println(courses.get(i));
        }
    } 
    
    Map<String , Integer> viewMarks(Student st){
        Map<String, Integer> testMarks = new HashMap<String, Integer>();
        testMarks.put("Quiz",st.marks.get("Quiz"));
        testMarks.put("Midsemester_Examination",st.marks.get("Midsemester_Examination"));
        testMarks.put("Comprehensive_Examination",st.marks.get("Comprehensive_Examination"));
        return testMarks;
    }
    void createTest(String testName, float weightagePercent , int duration , int noOfQuestions, Course c1,ArrayList<Character> anskey) throws InterruptedException{
        Test t = new Test(testName,weightagePercent ,duration ,noOfQuestions); 
        c1.allTest.add(t); 
        System.out.print("Creating Test");
        Student.printWithDelays("...", TimeUnit.MILLISECONDS, 1500); 
		System.out.println(""); 
		System.out.println(t.testName+" Test Created by "+ this.name);
        t.setKey(anskey);
    }
}

class CourseMan {

	public static void main(String[] args) throws InterruptedException { 
		Scanner sc = new Scanner(System.in);
		Course OOP = new Course("Object Oriented Programming",4,5102,3); 
		ArrayList<String> arl=new ArrayList<>();
		arl.add("OOP");
		arl.add("DSA");
		arl.add("DBMS");
		Teacher AD = new Teacher("Amit Dua", "amit.d12",arl); 
		Student stud1 = new Student("Harshit","2020A7PS0057P","f20200057","India",OOP); 
		Student stud2 = new Student("Rachit","2020A7PS0033P","f20200033","India",OOP);
        System.out.println("Details of the students registered in the course are: ");
        System.out.print("\n");
		stud1.viewPersonalDetails();
        System.out.print("\n\n");
        stud2.viewPersonalDetails(); 
        System.out.print("\n\n"); 
        System.out.println("The teacher "+ AD.name +" takes these courses");
        AD.courseDetails();
        System.out.print("\n\n"); 
        System.out.println(stud1.name+ " is registered in the following course"); 
        stud1.viewEnrolledCourse();
		ArrayList<Character> ansq = new ArrayList<Character>();
		ansq.add('a');
		ansq.add('b');
		ansq.add('c');
		ansq.add('c');
		ansq.add('d');
		ansq.add('a');
		ansq.add('a');
		ansq.add('b');
		ansq.add('c');
		ansq.add('a');    
        ArrayList<Character> ansm = new ArrayList<Character>();
        ansm.add('a');
		ansm.add('b');
		ansm.add('c');
		ansm.add('a');
		ansm.add('d');
		ansm.add('a');
		ansm.add('a');
		ansm.add('a');
		ansm.add('c');
		ansm.add('d');
		ansm.add('a');
		ansm.add('b');
		ansm.add('b');
		ansm.add('c');
		ansm.add('d'); 
        ArrayList<Character> ansc = new ArrayList<Character>();
        ansc.add('a');
		ansc.add('b');
		ansc.add('c');
		ansc.add('c');
		ansc.add('c');
		ansc.add('a');
		ansc.add('a');
		ansc.add('b');
		ansc.add('c');
		ansc.add('a');
		ansc.add('a');
		ansc.add('b');
		ansc.add('c');
		ansc.add('c');
		ansc.add('d');
		ansc.add('a');
		ansc.add('a');
		ansc.add('b');
		ansc.add('a');
		ansc.add('d'); 
		AD.createTest("Quiz",30.0f, 1, 10, OOP,ansq); 
		System.out.println(stud1.name+" is taking a test in the "+OOP.nameCourse+" course."); 
		stud1.takeTest(OOP);
		System.out.println(stud2.name+" is taking a test in the "+OOP.nameCourse+" course."); 
		stud2.takeTest(OOP);
		Student stud3;
		String name ="",idno="",email="",country=""; 
		System.out.println("Has any new student joined the course?(Yes/No)");
		String newreg = sc.next();
		while(!newreg.equals("No"))
		{
			if(newreg.equals("Yes"))
			{
				System.out.println("Please enter the name of the student"); 
				name = sc.next(); 
				System.out.println("Please enter the idno of the student"); 
				idno = sc.next(); 
				System.out.println("Please enter the email initials(i.e. till @) of the student"); 
				email = sc.next(); 
				System.out.println("Please enter the country of the student"); 
				country = sc.next(); 
				break;
			} 
			else
			{
				System.out.println("~_~ You had to enter Yes/No"); 
				System.out.println("Has any new student joined the course?(Yes/No)");
				newreg = sc.next();
			}
		}
		stud3 = new Student(name,idno,email,country,OOP); 
		if(!stud3.name.equals(""))
		{
			System.out.println(stud3.name+ " should take the quiz now.");
			stud3.takeTest(OOP);
		}
		AD.createTest("Midsemester_Examination",30.0f, 2, 12, OOP,ansm);
		System.out.println("Well now is the midsem exams are also available."); 
		System.out.println("Students have to take the test"); 
		System.out.println("For "+stud1.name);
		stud1.takeTest(OOP);
		System.out.println("For "+stud2.name);
		stud2.takeTest(OOP);
		if(!stud3.name.equals(""))
		{
			int key = 0;
			while(key!=7)
			{ 
				System.out.println("Please enter what you want to do for "+stud3.name);
				System.out.println("1.View Personal Details!");
				System.out.println("2.See the marks of Test!");
				System.out.println("3.See all availale tests!");
				System.out.println("4. View Enrolled Course.");
				System.out.println("5. See all the students registered in "+ OOP.nameCourse+" course");
				System.out.println("6. Take a test");
				System.out.println("7. Exit"); 
				key = sc.nextInt(); 
				switch(key)
				{
					case 1: stud3.viewPersonalDetails(); 
							System.out.print("\n\n");
							break; 
					case 2: {
								Map<String,Integer> mp3 =new HashMap<>();
								mp3=AD.viewMarks(stud3);
								System.out.println(mp3);
								System.out.print("\n\n");
							}
							break;
					case 3: {
								ArrayList<String> temp = stud3.availableTests(OOP);
								System.out.print("The avaailable test for "+stud3.name+" are");
								Student.printWithDelays("...", TimeUnit.MILLISECONDS, 1000); 
								System.out.println(""); 
								for(int i =0; i<temp.size();i++)
								{
									System.out.println(temp.get(i));
								}
							}
							System.out.print("\n\n");
							break;
					case 4: stud3.viewEnrolledCourse(); 
							System.out.print("\n\n");
							break;
					case 5: Student.allRegisteredStudents(OOP); 
							System.out.print("\n\n");
					        break;
					case 6: stud3.takeTest(OOP); 
							break;
					case 7: ; 
							break;
					default: System.out.println("Please enter a correct option! ~_~"); 
							break;
				}
				
			}
		}  
		Student.allRegisteredStudents(OOP); 
		for(int i =0; i<OOP.allTest.size();i++)
		{
			if(!OOP.allTest.get(i).testName.equals("Comprehensive_Examination"))
			{
				OOP.allTest.get(i).testSituation = "Unavailable";
			}
		} 
		AD.createTest("Comprehensive_Examination",40.0f, 3, 15, OOP,ansc); 
		System.out.println("The semester end is close by. All the students have to take the commprehensive exam"); 
		System.out.println("For "+stud1.name);
		stud1.takeTest(OOP);
		System.out.println("For "+stud2.name);
		stud2.takeTest(OOP);
        System.out.print("\n\n"); 
        Map<String,Integer> mp1 =new HashMap<>();
        mp1=AD.viewMarks(stud1);
        System.out.println(mp1);
        System.out.print("\n\n");
        Map<String,Integer> mp2 =new HashMap<>();
        mp2=AD.viewMarks(stud2);
        System.out.println(mp2);
        System.out.print("\n\n"); 
        if(!stud3.name.equals(""))
		{
        	Map<String,Integer> mp3 =new HashMap<>();
            mp3=AD.viewMarks(stud3);
            System.out.println(mp3);
            System.out.print("\n\n");
		}
		System.out.println("Thankyou and have a nice day :-)");       
	}
}


