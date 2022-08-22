package com.sudarshan.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.sudarshan.model.Employee;

public class EmployeeRepository {

    public Employee saveEmployee(Employee employee, DynamoDBMapper dynamoDBMapper) {
        dynamoDBMapper.save(employee);
        return employee;
    }

    public Employee getEmployeeById(String employeeId, DynamoDBMapper dynamoDBMapper) {
        return dynamoDBMapper.load(Employee.class, employeeId);
    }

    public String deleteEmployeeById(String employeeId, DynamoDBMapper dynamoDBMapper) {
        dynamoDBMapper.delete(dynamoDBMapper.load(Employee.class, employeeId));
        return "Employee Id : "+ employeeId +" Deleted!";
    }

    public String updateEmployee(String employeeId, Employee employee, DynamoDBMapper dynamoDBMapper) {
        dynamoDBMapper.save(employee,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("employeeId",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(employeeId)
                                )));
        return employeeId;
    }
}
