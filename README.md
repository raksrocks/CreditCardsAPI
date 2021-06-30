# CreditCardsAPI
API for processing credit cards | 

Available as Docker image: Refer Dockerfile or run /mvnw.cmd spring-boot:build-image <br />
Available on AWS at: http://creditcardsapi-env.eba-pvyjfhpb.eu-west-2.elasticbeanstalk.com/creditcards/

Two REST Endpoints are implemented

* **URL**

  _/creditcards/_

"GetAll" returns all cards in the system


* **Method:**
  
  _getAll_

  `GET` 
  
*  **URL Params**

   _No URL Parameters required_
   
* **Success Response:**
  
  _HTTP 200 with list of credit cards in the system_

  * **Code:** 200 <br />
    **Content:** `[ 
        { 
          name : "Sample Name", 
          number:"4716550166817131", 
          limit:100.0, 
          balance:0.0 
        }, 
        { 
          name : "Sample Name2", 
          number:"4716550166817131", 
          limit:100.0, 
          balance:0.0 
        }
        ]`<br />
    
"Add" will create a new credit card for a given name, card number, and limit

  	Card numbers should be validated using Luhn 10
    New cards start with a £0 balance
    for cards not compatible with Luhn 10, returns an error


* **Method:**
  
  _add_

  `POST` 
  
*  **URL Params**

   _No URL Parameters required_
   
* **Data Params**
 **Content:**  `
        { 
          name : "Sample Name", 
          number:"4716550166817131", 
          limit:100.0,
          balance:0.0 
        } `

* **Success Response:**
  
  _HTTP 201 with credit card response_

  * **Code:** 201 <br />
    **Content:** 
       ` { `<br />
         ` id : 101, `<br />
         ` name : "Sample Name", `<br />
         ` number:"4716550166817131", `<br />
         ` limit:100.0, `<br />
         ` balance:0.0 `<br />
       ` } `<br />
* **Error Response:**

  _In case of failed input validations, 400 is returned. In case of server errors 500 is returned_

  * **Code:** 400 BAD REQUEST <br />
    **Content:** `{
    "status": "BAD_REQUEST",
    "message": "invalid input",
    "errors": [
        "Input value for 'name' is not valid"
    ]
}`

  OR

  * **Code:** 500 INTERNAL SERVER ERROR <br />
    **Content:** `{
    "status": "BAD_REQUEST",
    "message": "invalid input",
    "errors": [
        "Unexpected error has occurred"
    ]
}`

* **Dependancies used:**
    * spring-boot-starter-test 2.4
    * spring-boot-devtools
    * spring-boot-starter-data-jpa
    * spring-boot-starter-web
    * H2
    * cucumber-java
    * cucumber-junit
    * commons-csv
    * rest-assured
    * gson
    * rest-assured-common
    * Java - 11
    * JUnit - 5
