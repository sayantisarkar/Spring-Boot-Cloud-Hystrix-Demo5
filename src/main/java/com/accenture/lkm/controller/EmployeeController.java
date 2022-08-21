package com.accenture.lkm.controller;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.lkm.model.Employee;

@RestController
// extends @Controller
// object are automaticaly convereted to JSON or XML
public class EmployeeController {

	private static Map<Integer, Employee> mapOfEmloyeess = new LinkedHashMap<Integer, Employee>();
	static int count = 10004;
	static {
		mapOfEmloyeess.put(10001, new Employee("Jack", 10001, 12345.6, 1001));
		mapOfEmloyeess.put(10002, new Employee("Justin", 10002, 12355.6, 1002));
		mapOfEmloyeess.put(10003, new Employee("Eric", 10003, 12445.6, 1003));
	}
	boolean flag1 = false;

	@RequestMapping(value = "emp/controller/setFlag/{flag}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> setFlag(@PathVariable("flag") boolean flag) throws Exception {
		flag1 = flag;
		return new ResponseEntity<String>("Flag Set to: " + flag1, HttpStatus.OK);
	}

	@RequestMapping(value = "emp/controller/getDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Employee>> getEmployeeDetails3() throws Exception {
		if (flag1) {
			Thread.sleep(12000);
			throw new Exception("Increased Load..");
		} else {
			Collection<Employee> listEmployee = mapOfEmloyeess.values();
			return new ResponseEntity<Collection<Employee>>(listEmployee, HttpStatus.OK);
		}
	}
}
