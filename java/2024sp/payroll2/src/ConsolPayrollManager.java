import java.util.ArrayList;
import java.util.Iterator;

public class ConsolPayrollManager implements writeEmployee {
    private ArrayList<Employee> employees = new ArrayList<Employee>();

    @Override
    public void writeEmployeePay(){
        Iterator<Employee> iter = employees.iterator();

        while ( (iter.hasNext())){
            Employee curEmp = iter.next();
            System.out.println(curEmp.caclulatePay());
        }
    }
}
