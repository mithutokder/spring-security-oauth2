package org.learn.resource.controller;

import java.util.List;
import java.util.Optional;

import org.learn.resource.model.Employee;
import org.learn.resource.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
    private EmployeeService employeeService; 

    @GetMapping("/employee/fetch/all/")
    @ResponseBody
    public ResponseEntity<List<Employee>> getEmployee() {
    	List<Employee> users = employeeService.getAll();
        if(users.isEmpty()){
            return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Employee>>(users, HttpStatus.OK);
    }

    @PostMapping(value = "/employee/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@RequestBody Employee employee) {
       employeeService.createEmployee(employee);
    }
    
    @RequestMapping(value = "/employee/fetch/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Employee> getUser(@PathVariable("id") int id) {
        Optional<Employee> user = employeeService.getEmployee(id);
        if (!user.isPresent()) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(user.get(), HttpStatus.OK);
    }

}
