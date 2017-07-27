# spring boot kotlin 

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://spring-boot-kotlin-example.herokuapp.com)

This is spring boot kotlin example.

## Live Demo

https://spring-boot-kotlin-example.herokuapp.com

![demo image1](http://wonwoo.ml/wordpress/wp-content/uploads/2016/10/github1.png)
![demo image1](http://wonwoo.ml/wordpress/wp-content/uploads/2016/10/github2.png)

## Login
`ID : wonwoo`

`Password : 123`

`ID : user`

`Password : 456`


## How to run?
### clone
```
# git clone https://github.com/wonwoo/spring-boot-kotlin-example.git
```
### run
```sh
# mvn spring-boot:run
```
or
```
# mvn install
# java -jar target/spring-boot-kotlin-example-0.0.1-SNAPSHOT.jar
```

## use 
1. kotlin 1.0.3
2. Spring Boot 1.4.1 
3. JPA(hibernate) 5.0.11
4. h2
5. thymeleaf 3.0.0
6. Spring Security 4.1.3


## spring boot koilin sample code
### Main sample
```kotlin
@SpringBootApplication
open class SpringBootKotlinExampleApplication constructor(val accountRepository: AccountRepository, val messageRepository: MessageRepository) : CommandLineRunner{
  override fun run(vararg p0: String?) {
    //.. some logic  
  }
}

fun main(args: Array<String>) {
  SpringApplication.run(SpringBootKotlinExampleApplication::class.java, *args)
}
```
### Service sample
```kotlin
@Service
@Transactional
open class MessageService constructor(val messageRepository: MessageRepository){

  @Transactional(readOnly = true)
  open fun findAll() : List<Message> {
    return messageRepository.findAll()
  }
  // ... some logic 
}
```


