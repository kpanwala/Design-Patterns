// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.io.*;

/*
Questions

Phase 1: The Fundamentals (Filtering, Mapping, Slicing)
Filter & Transform: Given a list of integers, find all even numbers, square them, and store them in a list.

Unique & Sorted: Take a list of strings, convert them to uppercase, remove duplicates, and sort them alphabetically.

First N Elements: From a list of strings, find the first 3 strings that have a length greater than 5.

Skipping Data: Skip the first 2 elements of a list and print the rest.

Flattening: Given a list of lists of integers, flatten them into a single list of integers.

Phase 2: Terminal Operations & Aggregations
Sum & Average: Calculate the sum and average of a list of doubles.

Max & Min: Find the maximum and minimum value in a list of integers.

Counting: Count how many strings in a list start with the letter 'J'.

String Joining: Take a list of names and join them into a single string separated by commas (e.g., "Alice, Bob, Charlie").

Any/All Match: Check if any number in a list is negative, and if all numbers are greater than zero.

Phase 3: Advanced Data Grouping (Collectors)
Grouping By: Given a list of Employee objects (name, department, salary), group them by department.

Counting by Group: Count the number of employees in each department.

Partitioning: Partition a list of integers into two groups: even and odd.

Mapping within Collectors: Group employees by department but only store their names in the resulting map.

Averaging by Group: Find the average salary for each department.

Max by Group: Find the highest-paid employee in each department.

Summarizing: Generate statistics (sum, average, count, max, min) for a list of integers in one go.

Phase 4: Complex Reductions & Performance
Custom Reduction: Find the product of all numbers in a list using reduce().

Longest String: Find the longest string in a list using reduce().

Word Frequency: Given a sentence, count the frequency of each word (case-insensitive).

Parallel Streams: Demonstrate the use of a parallel stream to sum a massive list of numbers.

Infinite Streams: Generate a list of the first 10 Fibonacci numbers using Stream.iterate.

Random Numbers: Generate 5 random integers between 1 and 100 and sort them.

Phase 5: Real-World Scenarios (Competitive & Core Dev)
Top N Performers: From a list of Students, find the top 3 students based on their GPA.

Filtering Map Entries: Given a Map<String, Integer>, filter out entries where the value is less than 50.

Distinct by Property: Filter a list of User objects to get only unique users based on their Email ID.

Finding Non-Repeating Character: Find the first non-repeating character in a string using Streams.

Anagram Check: Use streams to determine if two strings are anagrams of each other.

Merge & Sort: Merge two sorted lists into one sorted list using streams.

Flatten & Filter Map: Given a list of Orders, where each order has a list of Items, find the total price of all items across all orders that are categorized as "Electronics".
*/
class Main {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1,2,3,4,5);
        List<String> strList = Arrays.asList("Kalp", "Panwala", "KALP", "Abc", "XYZ", "verstappen", "HamilTon","aakash");
        List<List<Integer>> nestIntList = Arrays.asList(Arrays.asList(1,2,3), Arrays.asList(7,8,9));
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "IT", 70000),
            new Employee("Bob", "HR", 50000),
            new Employee("Charlie", "IT", 80000),
            new Employee("David", "Finance", 65000),
            new Employee("Alice", "HR", 45000)
        );
        
        List<Double> doubleList = Arrays.asList(10.5, 20.0, 30.5, 40.0);
        String sentence = "Java is fun and java is powerful";
        
        List<Integer> q1 = intList.stream()
                       .filter(n -> n % 2 == 0)
                       .map(n -> n * n)
                       .toList();
        List<String> q2 = strList.stream()
                            .map(String::toUpperCase)
                            .distinct()
                            .sorted()
                            .toList();
                            
        List<String> q3 = strList.stream() 
                            .filter(e->e.length()>=5)
                            .limit(3)
                            .toList();
                            
        List<Integer> q5 = nestIntList.stream() 
                            .flatMap(List::stream)
                            .toList();
        
        double avg = doubleList.stream() 
                            .mapToDouble(Double::doubleValue)
                            .average()
                            .orElse(0.0);
                            
        double sum = doubleList.stream() 
                            .mapToDouble(Double::doubleValue)
                            .sum();
        String q9 = String.join(",", strList);
        
        Map<String, List<Employee>> groupedEmp = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDept));
    
        Map<String, Long> countByDept = employees.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.counting()));
        
        Map<Boolean, List<Integer>> evenOdd = intList.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));
        
        IntSummaryStatistics stats = intList.stream()
                                            .mapToInt(Integer::intValue)
                                            .summaryStatistics();
                                            
        List<Employee> top3BySalary = employees.stream().sorted((a,b)->(int)(b.getSalary()-a.getSalary())).limit(3).toList();                                    
        List<Employee> filterBySalary = employees.stream().filter((e)->e.getSalary()>60000).toList();
        
        //System.out.println(q1.toString());
        //System.out.println(q2.toString());
        //System.out.println(q3.toString());
        //intList.stream().skip(2).forEach(System.out::println);
        // System.out.println(q5.toString());
        //System.out.println(avg+" : "+sum);
        /*
        System.out.println(intList.stream().mapToInt(Integer::intValue)
                    .max().orElse(0));
        System.out.println(intList.stream().mapToInt(Integer::intValue)
                    .min().orElse(0));
        */
        //System.out.println(q9);
        //System.out.println(groupedEmp);
        //System.out.println(countByDept);
        //System.out.println(evenOdd);
        
        /*
        System.out.println("Count:   " + stats.getCount());
        System.out.println("Sum:     " + stats.getSum());
        System.out.println("Min:     " + stats.getMin());
        System.out.println("Max:     " + stats.getMax());
        System.out.println("Average: " + stats.getAverage());
        */
        
        //System.out.println(intList.stream().reduce(0,(e,s)->s+e));
        //OR
        //System.out.println(intList.stream().reduce((e,s)->s+e).orElse(0));
        
        // System.out.println(intList.stream().reduce(1,(e,s)->s*e));
        
        // System.out.println(strList.stream().reduce("",(e,s)->{
        //     if(s==null || e.length()>s.length()){ s=e;}
        //     return s;
        //     }));
        
        // System.out.println(Arrays.stream(sentence.split(" "))
        // .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()))); //ignoring case
        
        // System.out.println(Arrays.stream(sentence.split(" "))
        // .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))); // case sensitive
        
        //System.out.println(top3BySalary);
        //System.out.println(filterBySalary);
        //System.out.println(distinctNameList);
        
        String s1 = "listen", s2 = "silent";
        System.out.println(
            Stream.of(s1.split("")).sorted().collect(Collectors.joining()).equals(Stream.of(s2.split("")).sorted().collect(Collectors.joining())));
    }
    
    static class Employee {
        String name, dept; double salary;
        Employee(String n, String d, double s) { name = n; dept = d; salary = s; }
        String getDept() { return dept; }
        double getSalary() { return salary; }
        
        public String toString(){
            return this.name+" "+this.getDept()+" "+this.getSalary();
        }
    }
}