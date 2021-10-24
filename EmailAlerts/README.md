# AzureApps - EmailAlerts - SpringBootAPI

### Steps

Step 1: https://start.spring.io/
 

Step 2: Update pom.xml

  Swagger
  --------
  springfox-swagger2 - 3.0.0
  springfox-swagger-ui - 2.9.2
  SwaggerConfig

  H2DB for JUnit Tests
    spring.jpa.hibernate.ddl-auto=create-drop
    spring.datasource.xxx

  JUnit4 -- @RunWith(SpringJUnit4ClassRunner.class) -- 
  JUnit5 -- @DataJpaTest
            @ExtendWith(SpringExtension.class)

  org.hibernate.dialect.SQLServer2012Dialect

Step 3 : 
 $ mvn clean install

References :
	https://stackoverflow.com/questions/9689793/cant-execute-jar-file-no-main-manifest-attribute

### Running API

$ java -jar target/emailAlerts-0.0.1-SNAPSHOT.jar

http://localhost:8080/actuator/health

http://localhost:8080/swagger-ui.html#/EmailAlert%20operations/findEmailAlertsUsingGET

http://localhost:8080/swagger-ui.html#/EmailAlert_operations/addEmailAlertUsingPOST
Payload:
{
  "emailSent": true,
  "id": 0,
  "mailBody": "Invitation regarding Azure Apps presentation",
  "mailSubject": "Azure App Invite",
  "toAddress": "sathya@snsystems.ai"
}


SQL Database
==================
Create SQL Database in Azure (Compute + storage = Basic = Rs. 350/-)
Set Server Firewall -> Enable Allow Azure services and resources to access this server
Set Server Firewall -> Add Client IP
Save...



CREATE TABLE EMAIL_ALERT
(
  ID int NOT NULL PRIMARY KEY IDENTITY (1, 1),
  TO_ADDRESS varchar(50) NOT NULL,
  MAIL_SUBJECT varchar(50) NOT NULL,
  MAIL_BODY varchar(MAX) NOT NULL,
  IS_EMAIL_SENT bit NOT NULL
)
GO

https://docs.microsoft.com/en-us/sql/connect/jdbc/step-3-proof-of-concept-connecting-to-sql-using-java?view=sql-server-ver15

Resource Group : sathya-demo-func-apps
sathya-emailalert-service

https://docs.microsoft.com/en-us/azure/azure-sql/database/connect-query-java
https://docs.microsoft.com/en-us/azure/azure-functions/functions-scenario-database-table-cleanup