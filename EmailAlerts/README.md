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



-------------------------------
### Docker building image, run container steps

docker build --tag sathyamus/email-alerts-api .
docker run -p8080:8080 sathyamus/email-alerts-api


### Tagging images, and pushing them to local / remote Registry

docker tag local-image:tagname new-repo:tagname
docker push new-repo:tagname

### Removing / untagging from local / remote Registry
docker rmi -f e26263695ec1
docker rmi sathyamus/email-alerts-api

docker tag sathyamus/email-alerts-api sathyaimageshub.azurecr.io/email-alerts-api:1.0.0

### Logging into azure and Container Registry
az login --tenant d8071284-1122-471c-1122-2df44d5b2d1f
az acr login --name sathyaimageshub

docker push sathyaimageshub.azurecr.io/email-alerts-api:2.0.0


-------------------------------

az login --tenant d8071284-1234
kubectl get pods --output=wide
kubectl get all
kubectl apply -f deployment-new.yaml


https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/static/provider/cloud/deploy.yaml

In Windows, Edit -> C:\Windows\System32\drivers\etc\hosts

kubectl run tmp-shell --rm -i --tty --image nicolaka/netshoot -- /bin/bash

bash-5.1# curl http://10.1.0.102:80/
    <html><body><h1>It works!</h1></body></html>

bash-5.1# curl http://10.1.0.104:80/
    <html><body><h1>It works!</h1></body></html>

bash-5.1# curl http://10.1.0.99:80/
    <!DOCTYPE html>
    <html>
    <head>
    <title>Welcome to nginx!</title>
    <style>
        body {
            width: 35em;
            margin: 0 auto;
            font-family: Tahoma, Verdana, Arial, sans-serif;
        }
    </style>
    </head>
    <body>
    <h1>Welcome to nginx!</h1>
    <p>If you see this page, the nginx web server is successfully installed and
    working. Further configuration is required.</p>

    <p>For online documentation and support please refer to
    <a href="http://nginx.org/">nginx.org</a>.<br/>
    Commercial support is available at
    <a href="http://nginx.com/">nginx.com</a>.</p>

    <p><em>Thank you for using nginx.</em></p>
    </body>
    </html>


bash-5.1# curl http://10.1.0.107:80/
    <!DOCTYPE html>
    <html>
    <head>
    <title>Welcome to nginx!</title>
    <style>
        body {
            width: 35em;
            margin: 0 auto;
            font-family: Tahoma, Verdana, Arial, sans-serif;
        }
    </style>
    </head>
    <body>
    <h1>Welcome to nginx!</h1>
    <p>If you see this page, the nginx web server is successfully installed and
    working. Further configuration is required.</p>

    <p>For online documentation and support please refer to
    <a href="http://nginx.org/">nginx.org</a>.<br/>
    Commercial support is available at
    <a href="http://nginx.com/">nginx.com</a>.</p>

    <p><em>Thank you for using nginx.</em></p>
    </body>
    </html>