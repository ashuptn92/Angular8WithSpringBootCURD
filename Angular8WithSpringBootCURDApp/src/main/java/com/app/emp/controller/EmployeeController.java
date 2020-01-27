package com.app.emp.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.emp.dao.EmployeeRepository;
import com.app.emp.exception.ResourceNotFoundException;
import com.app.emp.model.Employee;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	@Autowired
	private EmployeeRepository empRepo;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return empRepo.findAll();
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Integer employeeId) throws ResourceNotFoundException {
		Employee emp = empRepo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id : "+employeeId));
		return ResponseEntity.ok().body(emp);
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return empRepo.save(employee);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Integer empId, @Valid @RequestBody Employee empDetails) throws ResourceNotFoundException {
		Employee emp = empRepo.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id : "+empId));
		emp.setEmpId(empDetails.getEmpId());
		emp.setEmpName(empDetails.getEmpName());
		emp.setDesignation(empDetails.getDesignation());
		emp.setEmpSal(empDetails.getEmpSal());
		emp.setGender(empDetails.getGender());
		emp.setMobile(empDetails.getMobile());
		emp.setEmail(empDetails.getEmail());
		emp.setDept(empDetails.getDept());
		
		final Employee updateEmp = empRepo.save(emp);
		return ResponseEntity.ok(updateEmp);
	}
	
	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer empId) throws ResourceNotFoundException {
		Employee delEmp = empRepo.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id : "+empId));
		empRepo.delete(delEmp);
		Map<String, Boolean> respone = new HashMap<String, Boolean>();
		respone.put("deleted", Boolean.TRUE);
		return respone;
	}
}
