import java.util.*;
import java.time.*;
class Age{
public static void main(String... args){
Scanner sc=new Scanner(System.in);
System.out.println("Enter your birth year :");
int byear=sc.nextInt();
int curryear=Year.now().getValue();
System.out.println("Harry's age in "+curryear+" is :"+(curryear-byear));
}}