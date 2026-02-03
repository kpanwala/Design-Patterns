import java.util.ArrayList;
import java.util.List;

// Step 1: Implement Cloneable
class Employee implements Cloneable {
    private List<String> empList;

    public Employee() {
        empList = new ArrayList<String>();
    }

    public Employee(List<String> list) {
        this.empList = list;
    }

    // Simulate a heavy database call
    public void loadData() {
        empList.add("Virat");
        empList.add("Rohit");
        empList.add("Shikhar");
        empList.add("Dhoni");
    }

    public List<String> getEmpList() {
        return empList;
    }

    // Step 2: Override clone() to provide a Deep Copy
    @Override
    public Object clone() throws CloneNotSupportedException {
        List<String> temp = new ArrayList<String>();
        for (String s : this.getEmpList()) {
            temp.add(s);
        }
        return new Employee(temp);
    }
}


public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        /*
        The prototype design pattern is a creational pattern that creates new objects by cloning an existing instance (a prototype) rather than creating them from scratch. 
         */
        
        // Load the original once (The "Expensive" part)
        Employee emps = new Employee();
        emps.loadData();

        // Use the Prototype to create new objects (The "Fast" part)
        Employee empsNew = (Employee) emps.clone();
        Employee empsNew1 = (Employee) emps.clone();

        // Customize the clones independently
        List<String> list = empsNew.getEmpList();
        list.add("John");

        List<String> list1 = empsNew1.getEmpList();
        list1.remove("Dhoni");

        System.out.println("Original List: " + emps.getEmpList());
        System.out.println("Modified Clone 1: " + list);
        System.out.println("Modified Clone 2: " + list1);

        /* OUTPUT:

        Original List: [Virat, Rohit, Shikhar, Dhoni]
        Modified Clone 1: [Virat, Rohit, Shikhar, Dhoni, John]
        Modified Clone 2: [Virat, Rohit, Shikhar]
         */
    }
}