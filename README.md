# Premium-Calculator
Premium Calculator for insurance company

Assignment

To run Application Java 8 is required. Everything tested on Ubuntu Linux/macOS/Windows.

There is two options how to run Application:

build and run as Java self executable container or using gradle.

# Used technologies and frameworks :
JAVA 8
Spring Boot 2.3.3 (Spring boot WEB)
Build tool gradle
Jackson data bind

# API reference :

GET http://localhost:8080/api/v1/policies/calculate-premium
Content-Type: application/json

Calculates Premium, example JSON object in body request below 

{
  "number": "123",
  "status": "REGISTERED",
  "policyObjects": [
    {
      "name" : "House",
      "policySubObject" : [
        {
          "name" : "TV",
          "insuredSum" : 450,
          "riskType" : "FIRE"
        },
        {
          "name" : "Laptop",
          "insuredSum" : 50,
          "riskType" : "FIRE"
        },
        {
          "name" : "Mobile",
          "insuredSum" : 102.51,
          "riskType" : "THEFT"
        }
      ]

    }
  ]

}

