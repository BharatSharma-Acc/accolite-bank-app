Accolite Bank Application performs basic banking operations including Create Account, Fetch Account, Withdraw/Deposit Balance etc using  [SpringBoot](http://projects.spring.io/spring-boot/) RESTful web service application with [Docker](https://www.docker.com/)

#### Prerequisite 

Installed:   
[Docker](https://www.docker.com/)   
[git](https://www.digitalocean.com/community/tutorials/how-to-contribute-to-open-source-getting-started-with-git)   

Optional:   
[Docker-Compose](https://docs.docker.com/compose/install/)   
[Java 1.8 or 11.1](https://www.oracle.com/technetwork/java/javase/overview/index.html)   
[Maven 3.x](https://maven.apache.org/install.html)


#### Steps

##### Clone source code from git
```
git clone https://github.com/BharatSharma-Acc/accolite-bank-app.git .
```

##### Run using Docker Compose
```
docker-compose up --build
```

##### Test application

```
Hit URL http://localhost:8080/bankapp/swagger-ui.html#!/ in any browser
```

response should be:
```
UI should open with multiple operations like account-operations and user-operations
```

Refer UserManual(Radancy-AssigmentUserManual.pdf) for complete Request/Response and other required details. Please contact me at bharat.sharma@accolitedigital.com for any issue
