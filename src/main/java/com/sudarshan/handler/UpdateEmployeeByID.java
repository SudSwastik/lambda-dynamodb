package com.sudarshan.handler;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sudarshan.model.Employee;
import com.sudarshan.repository.EmployeeRepository;

public class UpdateEmployeeByID implements RequestHandler<Employee,String> {

    private EmployeeRepository employeeRepository = new EmployeeRepository();
    @Override
    public String handleRequest(Employee employee, Context context){

        DynamoDBMapper mapper =
                new DynamoDBMapper((AmazonDynamoDB) AmazonDynamoDBClientBuilder.standard().build());
        System.out.println(employee.toString());
        employeeRepository.getEmployeeById(employee.getEmployeeId(), mapper);
        return employeeRepository.getEmployeeById(employee.getEmployeeId(), mapper).toString();
    }
}