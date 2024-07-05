# < NYX >
a simple Dependency Injection Container, which uses runtime reflexion, written in Java  

note: This is a Project used to understand the concept of DI and is not Production ready

## How to use

Start the DI Container in your Main function
```java
import dev.aronba.nyx.NyxApplication;

public class Main {
    public static void main(String[] args) {
        NyxApplication.run(args);
    }
}
```

Mark your Components with the `@Candidate` Annotation and add the Dependency in you Constructor

```java
import dev.aronba.nyx.di.annotations.Candidate;

@Candidate
class MyApplication {

    public MyApplication(MyRestService service){
        service.getUser();
        // implement some smart stuff here
    }
}
```
Mark your Services with the `@Candidate` Annotation
```java
import dev.aronba.nyx.di.annotations.Candidate;

@Candidate
class MyRestService {
    public RestReponse<User> getUser(){
        // implement some smart stuff here
    return new User();
    }
}
```

##  Feature Ideas
- Startup/Shutdown hooks for the Application
- Lifecycle Management -> @Postconstruct stuff etc.
- Make the Classpath Scanner good (I stole it)
- Make the whole thing fast
- Support for setter Injection
- More configuration options
- Proxying Objects
