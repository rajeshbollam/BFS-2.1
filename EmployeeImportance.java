//In this approach, we use BFS to traverse through the graph.
//The idea here is to first add the object of the given id to a queue and then when we process it, we add it's importance to the result and find the objects of it's subordinates and add them to the queue
//We continue this process until the queue is empty
//Time Complexity: O(n), where n is the length of the employees list
//Space Complexity: O(n), for queue
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};


class Solution {
    public int getImportance(List<Employee> employees, int id) {
        if(employees.size() == 1){
            return employees.get(0).importance;
        }
        HashMap<Integer, Employee> map = new HashMap<>();
        for(Employee e: employees){
            map.put(e.id, e);
        }
        Queue<Employee> q = new LinkedList<>();
        q.add(map.get(id));
        int result = 0;
        while(!q.isEmpty()){
            Employee e = q.poll();
            result += e.importance;
            List<Integer> subIds = e.subordinates;
            for(int subId: subIds){
                Employee subEm = map.get(subId);
                q.add(subEm);
            }
        }
        return result;
    }
}

//In this implementation, we use the same appraoch as above, but we put the id's of employees in the queue instead of the employee objects.
class Solution1 {
    public int getImportance(List<Employee> employees, int id) {
        if(employees.size() == 1){
            return employees.get(0).importance;
        }
        HashMap<Integer, Employee> map = new HashMap<>();
        for(Employee e: employees){
            map.put(e.id, e);
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(id);
        int result = 0;
        while(!q.isEmpty()){
            Integer eid = q.poll();
            Employee e = map.get(eid);
            result += e.importance;
            List<Integer> subIds = e.subordinates;
            for(int subId: subIds){
                q.add(subId);
            }
        }
        return result;
    }
}

//In this approach, we use DFS to traverse the graph. At each node, it traverses through all it's subordinates and adds it's importance to the global result variable
//Time Complexity: O(n)
//Space Complexity: O(h) where h is the maximum depth from given object id to the lowest subordinate.
class Solution2 {
    int result;
    public int getImportance(List<Employee> employees, int id) {
        if(employees.size() == 1){
            return employees.get(0).importance;
        }
        HashMap<Integer, Employee> map = new HashMap<>();
        for(Employee e: employees){
            map.put(e.id, e);
        }
        dfs(map, id);
        return result;
    }

    private void dfs(HashMap<Integer, Employee> map, int id){
        //base

        //logic
        Employee e = map.get(id);
        result += e.importance;
        List<Integer> subIds = e.subordinates;
        for(int subId: subIds){ // the for..each loop returns to the caller if it encounters an empty list. It does nothing. It does not execute.
            dfs(map, subId);    //because there are no further lines in the function, it returns to the previous call in the recursive stack.
        }                       //so recursion stops naturally without any base condition.
    }
}