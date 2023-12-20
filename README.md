
# EmployeeWise

An Employee management application.


## Features

- Add employee
- Get all employees
- Get all employees based on pagenation and sorting
- Delete employee
- Update employees
- Get nth level manager for Employee
- Exception handling
- Send email facility


## API Reference

#### Follow below link for detailed API reference for the application

```http
  https://documenter.getpostman.com/view/24254665/2s9Ykq5zz7
```
## Configurations

Check following configurations Change DB details accordingly

```bash
#DB related
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=demo

#Email related
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=testDemo51193@gmail.com
spring.mail.password=onpwpjyaliznmhlc
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true


```
    
## Run Locally

Clone the project

```bash
  git clone https://link-to-project
```

Go to the project directory

```bash
  cd my-project
```

Install dependencies

```bash
  mvn clean install
```

Start the server

```bash
  mvn spring-boot:run
```


## Deployment

Not Deployed



## Tech Stack

**Client:** Java, SpringBoot


## Authors

- [@Subhajit Saha](https://github.com/subhajit51193)


## Feedback

If you have any feedback, please reach out to us at nnorth87@gmail.com


## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://subhajit51193.github.io/)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/subhajit-saha-103110185/)

**DataBase:** MongoDB

**Server:** Embedded Tomcat



