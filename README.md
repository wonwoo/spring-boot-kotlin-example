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
# cd web or webflux
# mvn spring-boot:run

```
or
```
# cd web or webflux
# mvn install
# java -jar target/${name}.jar
```

## use 
1. kotlin 1.3.50
2. Spring Boot 2.2.x 
3. JPA(hibernate) 5.4.8
4. h2
5. thymeleaf 3.0.11
6. Spring Security 5.2.1


## spring boot koilin sample code
### Main sample
```kotlin
@SpringBootApplication
class SpringBootKotlinExampleApplication(private val accountRepository: AccountRepository,
                                         private val messageRepository: MessageRepository) : CommandLineRunner {
  override fun run(vararg p0: String?) {
    //.. some logic  
  }
}

fun main(args: Array<String>) {
    runApplication<SpringBootKotlinExampleApplication>(*args)
}
```
### Service sample
```kotlin
@Service
@Transactional
class MessageService constructor(val messageRepository: MessageRepository){

  @Transactional(readOnly = true)
  fun findAll() : List<Message> {
    return messageRepository.findAll()
  }
  // ... some logic 
}
```


