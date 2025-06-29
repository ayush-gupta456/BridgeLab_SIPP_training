package com.gla;
import java.util.*;

class InvalidMarks extends Exception{
	public InvalidMarks(String message) {
		super(message);
	}
}
 class Student_marks{
      String name;
      String subjects[];
      int marks[];
      

      public Student_marks(String name, String[] subjects, int[] marks) throws InvalidMarks {
        this.name = name;
        this.subjects = subjects;
        this.marks = marks;
        validMarks();
    } 
    void validMarks() throws InvalidMarks{
      for (int mark : marks) {
      if (mark < 0 || mark > 100) {
          throw new InvalidMarks("Marks should be between 0 and 100.");
      }
  }
    }  	
  double calAverage() {
  int total = 0;
  for (int mark : marks) total += mark;
  return (double) total / marks.length;
}
  String Grade() {
  double avg = calAverage();
  if (avg >= 90) return "A+";
  if (avg >= 80) return "A";
  if (avg >= 70) return "B";
  if (avg >= 60) return "C";
  if (avg >= 50) return "D";
  return "F";
}
   void display() {
      System.out.println("Report Card for: " + name);
      for (int i = 0; i < subjects.length; i++) {
           System.out.println(subjects[i]+" : "+marks[i]);
      }
      System.out.println("Average         :"+ calAverage());
      System.out.println("Grade           : " + Grade());
      System.out.println();
    }
}
 public class Student_record{
	 
	    public static void main(String[] args) {
	        List<Student_marks> students = new ArrayList<>();
	        String[] subjects = {"Math", "Science", "English"};
	
	        try {
	            students.add(new Student_marks("Ayush", subjects, new int[]{90, 92, 92}));
	            students.add(new Student_marks("Aman", subjects, new int[]{56, 67, 49}));
	            students.add(new Student_marks("Govind", subjects, new int[]{91, 89, 95}));
	        } catch (InvalidMarks e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	
	        for (Student_marks student : students) {
	            student.display();
	        }
	    }
}













































