package org.learn.resource.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.learn.resource.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

private Map<Integer, Employee> employeeMap = new HashMap<>();
    
	@PostConstruct
    public void init() {
    	employeeMap.put(1, new Employee("mithut", "mithutokder@gmail.com"));
    	employeeMap.put(2, new Employee("mithut1", "mithutokder1@gmail.com"));
    	employeeMap.put(3, new Employee("mithut2", "mithutokder2@gmail.com"));
    	employeeMap.put(4, new Employee("mithut3", "mithutokder3@gmail.com"));
    	employeeMap.put(5, new Employee("mithut4", "mithutokder4@gmail.com"));
    }
	
	public List<Employee> getAll() {
		return new ArrayList<>(employeeMap.values());
	}
	
	public void createEmployee(Employee employee) {
		employeeMap.put(new Random().nextInt(1000), employee);
	}
	
	public Optional<Employee> getEmployee(Integer pk) {
		return employeeMap.get(pk) == null ? Optional.empty() :
			Optional.of(employeeMap.get(pk));
	}
}
