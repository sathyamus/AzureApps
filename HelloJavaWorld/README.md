# AzureApps - HelloJavaWorld

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


----------------------------------------------------------

Docker image

Step 1: $ docker build --tag hellojavaworld .
Step 2: $ docker run hellojavaworld
Step 3: $ docker images
Step 4: $ docker ps
Step 5: $ docker ps -a
Step 6: $ docker rmi -f 02b13c765dbc

