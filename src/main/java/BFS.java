
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BFS {

    class Employee{
        String name;
        List<Employee> children = new ArrayList<Employee>();

        Employee(String name){
            this.name = name;
        }

        void addChildren(Employee emp){
            children.add(emp);
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public void solve() throws IOException{
        BufferedWriter bufferedWriter = new BufferedWriter(new  FileWriter(System.getenv("OUTPUT_PATH")));
        Map<String, Employee> map = new HashMap<String, Employee>();
        Integer.parseInt(scanner.nextLine());
        Employee root = null;
        while(scanner.hasNextLine()){
            String[] line = scanner.nextLine().split(" ");
            Employee emp1 = createEmployee(line[0], map);
            Employee emp2 = createEmployee(line[1], map);
            emp1.addChildren(emp2);
            if(root==null){
                root= emp1;
            }
        }

        bfs(root, bufferedWriter);

        bufferedWriter.close();
    }

    private void bfs(Employee root, BufferedWriter bufferedWriter )throws IOException{
        Queue<Employee> queue = new LinkedList<Employee>();
        queue.add(root);
        queue.add(null);
        while(queue.size()>0){
            Employee emp = queue.poll();
            if(emp == null){
                if(queue.size()==0){
                    break;
                }
                bufferedWriter.write("\n");
                queue.add(null);
            }else{
                bufferedWriter.write(emp.name+" ");
                for(Employee child : emp.children){
                    queue.add(child);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new BFS().solve();
    }

    private Employee createEmployee(String name, Map<String, Employee> map){
        Employee emp = map.getOrDefault(name, new Employee(name));
        map.put(name, emp);
        return emp;
    }

}
