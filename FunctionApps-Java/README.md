# AzureApps - FunctionApps-Java

### Steps using Maven archetype

Step 1: 
 $ mvn archetype:generate -DgroupId=com.snsystems -DartifactId=JavaHelloWorld -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

Step 2: Update pom.xml

<build>
  <plugins>
    <plugin>
      <!-- Build an executable JAR -->
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-jar-plugin</artifactId>
      <version>3.1.0</version>
      <configuration>
        <archive>
          <manifest>
            <addClasspath>true</addClasspath>
            <classpathPrefix>lib/</classpathPrefix>
            <mainClass>com.snsystems.App</mainClass>
          </manifest>
        </archive>
      </configuration>
    </plugin>
  </plugins>
</build>

Step 3 : 
 $ mvn clean install

Step 4 : 
 $ java -jar target/HelloJavaWorld-1.0-SNAPSHOT.jar

Step 5 : You will see the output as :
Hello World!

References :
	https://stackoverflow.com/questions/9689793/cant-execute-jar-file-no-main-manifest-attribute


### Steps using Azure Blob

Step 1: Update pom.xml

    <dependency>
        <groupId>com.azure</groupId>
        <artifactId>azure-storage-blob</artifactId>
        <version>12.8.0</version>
    </dependency>

Step 2:

Create a Storage Account using the Azure portal
Step 1 : Create a new general-purpose storage account to use for this tutorial.

Go to the Azure Portal and log in using your Azure account.
Select New > Storage > Storage account.
Select your Subscription.
For Resource group, create a new one and give it a unique name.
Enter a name for your storage account.
Select the Location to use for your Storage Account.
Set Account kind to StorageV2(general purpose v2).
Set Performance to Standard.
Set Replication to Locally-redundant storage (LRS).
Set Secure transfer required to Disabled.
Check Review + create and click Create to create your Storage Account.


$ mvn clean install
$ mvn azure-functions:package  
$ mvn azure-functions:run


References :
  https://docs.microsoft.com/en-us/azure/developer/java/sdk/get-started
  https://github.com/Azure/azure-sdk-for-java/blob/main/sdk/storage/azure-storage-blob/src/samples/java/com/azure/storage/blob/BasicExample.java
  https://docs.microsoft.com/en-us/samples/azure-samples/azure-sdk-for-java-storage-blob-upload-download/upload-download-blobs-java/

  https://github.com/azure/azure-sdk-for-java
  https://docs.microsoft.com/en-us/azure/storage/files/storage-java-how-to-use-file-storage?tabs=java


SELECT * FROM email_alert;
  update email_alert set is_email_sent = 0
