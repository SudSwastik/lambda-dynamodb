package com.sudarshan.handler;

import com.amazonaws.Response;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sudarshan.model.Employee;
import com.sudarshan.repository.EmployeeRepository;

public class CreateEmployeeHandler implements RequestHandler<Employee,String> {

        private EmployeeRepository employeeRepository = new EmployeeRepository();
        @Override
        public String handleRequest(Employee employee, Context context){

            DynamoDBMapper mapper =
                    new DynamoDBMapper((AmazonDynamoDB) AmazonDynamoDBClientBuilder.standard().build());
            System.out.println(employee.toString());
            employeeRepository.saveEmployee(employee, mapper);
            return "Message Saved Successfully";
        }
}