
<h1 align="center">
  <br>
  <a href="http://www.amitmerchant.com/electron-markdownify"><img src="https://www.enoca.com/wp-content/uploads/2019/02/enoca.png" alt="Markdownify" width="200"></a>
</h1>

<h4 align="center">QA session by <a href="https://www.enoca.com/" target="_blank">Enoca</a>.</h4>


<p align="center">
  <a href="#answer-one">A1</a> •
  <a href="#answer-two">A2</a> •
  <a href="#answer-three">A3</a> •
  <a href="#answer-four">A4</a> •
  <a href="#answer-five">A5</a> •
  <a href="#answer-six">A6</a> •
  <a href="#answer-seven">A7</a> •
</p>

## Answer one

The Model View Controller (MVC) design pattern specifies that an application consist of a data model, presentation information, and control information. The pattern requires that each of these be separated into different objects.
MVC is more of an architectural pattern, but not for complete application. MVC mostly relates to the UI / interaction layer of an application. You’re still going to need business logic layer, maybe some service layer and data access layer.
The model designs based on the MVC architecture follow MVC design pattern. The application logic is separated from the user interface while designing the software using model designs.

The MVC pattern architecture consists of three layers:

- Model: It represents the business layer of application. It is an object to carry the data that can also contain the logic to update controller if data is changed.
- View: It represents the presentation layer of application. It is used to visualize the data that the model contains.
- Controller: It works on both the model and view. It is used to manage the flow of application, i.e. data flow in the model object and to update the view whenever data is changed.
### How to works?
A client (browser) sends a request to the controller on the server side, for a page.
The controller then calls the model. It gathers the requested data.
Then the controller transfers the data retrieved to the view layer.
Now the result is sent back to the browser (client) by the view.

![image](https://user-images.githubusercontent.com/48466124/213183675-b5546c0e-0a2c-4876-a841-09edea7f2322.png)

### Model Layer

```aidl
// class that represents model  
public class Employee {  
  
      // declaring the variables  
       private String EmployeeName;  
       private String EmployeeId;  
       private String EmployeeDepartment;  
          
      // defining getter and setter methods  
       public String getId() {  
          return EmployeeId;  
       }  
          
       public void setId(String id) {  
          this.EmployeeId = id;  
       }  
          
       public String getName() {  
          return EmployeeName;  
       }  
          
       public void setName(String name) {  
          this.EmployeeName = name;  
       }  
          
       public String getDepartment() {  
              return EmployeeDepartment;  
           }  
          
       public void setDepartment(String Department) {  
              this.EmployeeDepartment = Department;  
           }  
          
    }  
```

### View Layer

```aidl
// class which represents the view  
public class EmployeeView {  
  
      // method to display the Employee details   
public void printEmployeeDetails (String EmployeeName, String EmployeeId, String EmployeeDepartment){  
          System.out.println("Employee Details: ");  
          System.out.println("Name: " + EmployeeName);  
          System.out.println("Employee ID: " + EmployeeId);  
          System.out.println("Employee Department: " + EmployeeDepartment);  
       }  
    }  

```

### Controller Layer
```aidl
// class which represent the controller  
public class EmployeeController {  
  
      // declaring the variables model and view  
       private Employee model;  
       private EmployeeView view;  
   
      // constructor to initialize  
       public EmployeeController(Employee model, EmployeeView view) {  
          this.model = model;  
          this.view = view;  
       }  
   
      // getter and setter methods   
       public void setEmployeeName(String name){  
          model.setName(name);        
       }  
   
       public String getEmployeeName(){  
          return model.getName();         
       }  
   
       public void setEmployeeId(String id){  
          model.setId(id);        
       }  
   
       public String getEmployeeId(){  
          return model.getId();       
       }  
   
       public void setEmployeeDepartment(String Department){  
              model.setDepartment(Department);        
       }  
   
           public String getEmployeeDepartment(){  
              return model.getDepartment();         
       }  
  
       // method to update view   
       public void updateView() {                  
          view.printEmployeeDetails(model.getName(), model.getId(), model.getDepartment());  
       }      
    }  
```
# Main Class
The MVCMain class fetches the employee data from the method where we have entered the values. Then it pushes those values in the model. After that, it initializes the view (EmployeeView.java). When view is initialized, the Controller (EmployeeController.java) is invoked and bind it to Employee class and EmployeeView class. At last the updateView() method (method of controller) update the employee details to be printed to the console.
```aidl
public class MVCMain {  
       public static void main(String[] args) {  
   
          // fetching the employee record based on the employee_id from the database  
          Employee model = retriveEmployeeFromDatabase();  
   
          // creating a view to write Employee details on console  
          EmployeeView view = new EmployeeView();  
   
          EmployeeController controller = new EmployeeController(model, view);  
   
          controller.updateView();  
   
          //updating the model data  
          controller.setEmployeeName("Nirnay");  
          System.out.println("\n Employee Details after updating: ");  
   
          controller.updateView();  
       }  
   
       private static Employee retriveEmployeeFromDatabase(){  
          Employee Employee = new Employee();  
          Employee.setName("Anu");  
          Employee.setId("11");  
          Employee.setDepartment("Salesforce");  
          return Employee;  
       }  
    }  
```
### Object Oriented Layers
- The reason for the ring of the MVC Framework is that it makes it difficult for the User Interface and Data layers to leave one.
 ``` 
Storage -> DataAccess
```
This is usually handeled by an ORM. An ORM will handle database access.

The ORM plus Custom (Model) Objects that you create allow for abstracted data access. This will look something like:

The ORM translates data to and from the database in order to make it usable. The ORM can handle the CRUD operations for you so that querying, updating, and inserting are not present in other parts of your code.

Business Logic

The business logic works with data by accessing, modifying, and saving it.

The business logic can access your models in order to fulfill the accessing and saving of data.

The modifying part is implemented in the business logic.

Business logic can be small, large, or a combination of other pieces of business runner.

Our graph now look like:
```aidl
Database <- ORM -> Models <- Business Logic
```
Command Processor

The command processor runs pieces of business logic and patches pieces of the overall system together and is sometimes called the controller in MVC
```aidl
Database <- ORM -> Models <- Business Logic <- Command Processor
```

User Interface

The user interface (UI) can be broken into two parts, data representation and user input.

Data representation is called views in MVC and are responsible for taking data and making it readable by the user.

User input triggers pieces of the command processor.

Overall:
```
                                                                -> View
Database <- ORM -> Models <- Business Logic <- Command Processor
                                                                <- User Input
```
## Answer two
Short answer - By the microservice architecture.
So let's think of a structure on an architecture where some of our services run on spring boot, and some of our services run on .net core. In there,we have two options for communication
### Async. communication
In Asynchronous communication, the client sends a request but it doesn’t wait for a response from the service. So the key point here is that, the client should not have blocked a thread while waiting for a response.

The most popular protocol for this Asynchronous communications is AMQP (Advanced Message Queuing Protocol). So with using AMQP protocols, the client sends the message with using message broker systems like Kafka and RabbitMQ queue. The message producer usually does not wait for a response. This message consume from the subscriber systems in async way, and no one waiting for response suddenly.
An asynchronous communication also divided by 2 according to implementation. An asynchronous systems can be implemented in a one-to-one(queue) mode or one-to-many (topic) mode.
![image](https://user-images.githubusercontent.com/48466124/213190685-3f17d18a-37bf-46cc-9e05-8619127cc4c1.png)

In a one-to-one(queue) implementation there is a single producer and single receiver. But in one-to-many (topic) implementation has Multiple receivers. Each request can be processed by zero to multiple receivers. one-to-many (topic) communications must be asynchronous.

So we will see this communication with the publish/subscribe mechanism used in patterns like Event-driven microservices architecture in the upcoming articles. Basically an event-bus or message broker system is publishing events between multiple microservices, and communication provide with subscribing these events in an async way.

Kafka and RabbitMQ is the best tools for this operations.

As you can see that we have understand Microservices Communication types — Sync or Async Communication, And microservice-based application will often use a combination of these 2 communication styles. So we will also design our e-commerce architecture with using both communication types.

But before that, lets elaborate the Synchronous communication and underlying mechanism.
### Another alternative is Sync. communication
Basically, we can say that Synchronous communication is using HTTP or gRPC protocol for returning sync response. The client sends a request and waits for a response from the service. So that means client code block their thread, until the response reach from the server.
, In synchronous communication, the client sends a request with using http protocols and waits for a response from the service. The synchronous communication protocols can be HTTP or HTTPS.When we are using a synchronous request/response-based communication type, HTTP protocols and REST approaches are the most common way to use to design APIs, especially if we’re exposing APIs to the outside of the microservice cluster.If we’re communicating between services internally within our microservices cluster, we might also use binary format communication mechanisms like gRPC. gRPC is one of the best way to communicate for internal microservice communication. gRPC is a modern open source high performance RPC framework that can run in any environment. It can efficiently connect services in and across data centers with pluggable support for load balancing, tracing, health checking and authentication. It is also important to note that gRPC is not a REST framework. It is a different approach to service-to-service communication that is better suited for machines communicating with machines. gRPC is based on the idea of defining a service, specifying the methods that can be called remotely with their parameters and return types. gRPC uses protocol buffers, Google’s data interchange format, as the interface definition language, for describing both the service interface and the structure of the payload messages. You can define your service once in a .proto file and implement clients and servers in any of gRPC’s supported languages, which in turn can be run in environments ranging from servers inside Google to your own tablet - all the complexity of communication between different languages and environments is handled for you by gRPC. You also get all the advantages of working with protocol buffers, including efficient serialization, a simple IDL, and easy interface updating.It is focused on high performance and uses the HTTP/2 protocol to transport binary messages. It is relies on the Protocol Buffers language to define service contracts. Protocol Buffers, also known as Protobuf, allow you to define the interface to be used in service to service communication regardless of the programming language.In GRPC, a client application can directly call a method on a server application on a different machine like it were a local object, making it easy for you to build distributed applications and services.![image](https://user-images.githubusercontent.com/48466124/213191569-281d78c6-8d23-4f9d-9b40-900ea31a2da2.png)
![image](https://user-images.githubusercontent.com/48466124/213190418-170c3439-fe93-433f-a70f-97abb4c40ace.png)

gRPC clients and servers can work and talk to each other in a different of environments, from servers to your own desktop applications, and that can be written in any language that gRPC supports. For example, you can easily create a gRPC server in Java or C# with clients in.

### RPC life cycles
Unary RPCs where the client sends a single request to the server and returns a single response back, just like a normal function call.

Server streaming RPCs where the client sends a request to the server and gets a stream to read a sequence of messages back. The client reads from the returned stream until there are no more messages. gRPC guarantees message ordering within an individual RPC call.

Client streaming RPCs where the client writes a sequence of messages and sends them to the server, again using a provided stream. Once the client has finished writing the messages, it waits for the server to read them and return its response. Again gRPC guarantees message ordering within an individual RPC call.

Bidirectional streaming RPCs where both sides send a sequence of messages using a read-write stream. The two streams operate independently, so clients and servers can read and write in whatever order they like: for example, the server could wait to receive all the client messages before writing its responses, or it could alternately read a message then write a message, or some other combination of reads and writes.
![image](https://user-images.githubusercontent.com/48466124/213192058-cce39918-cd75-4c91-9f31-18f10471093e.png)

## Answer three
### Short answer - By the using Websockets
WebSocket is a bi-directional communication protocol between a browser and a server.
![image](https://user-images.githubusercontent.com/48466124/213195307-febc350a-fbea-404b-bd57-0e706212d7a5.png)

Let’s imagine that we need to check new incoming messages without reloading a page. One way could be sending regular AJAX requests. It’s called polling. Another way could be long polling. In this case, a server holds an AJAX request and returns a response only when new messages appear. In mentioned approaches the client asks a server and gets a response. In case of web sockets, there is an established connection between a client and a server, and the server can send a message to the client without a prior request.
For better understanding of how web sockets work, let’s create a Spring Boot web-application which allows:

to send messages to all connected users
to send notifications from a server to a client without a prior request

To use web sockets in a Spring Boot application, the following dependency should be added:
```aidl
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
```
Web socket configuration

```aidl
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Autowired
    private WebSocketSessionServiceImpl webSocketSessionService;
    
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .withSockJS();
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic", "/queue");
    }
    @Bean
    public PresenceEventListener presenceEventListener() {
        return new PresenceEventListener(webSocketSessionService);
    }
    
```
Here the web socket endpoint “/ws” is registered with two message brokers “/topic” and “/queue”. The flow is the following: a client establishes a connection with an endpoint and subscribes to message brokers to send and process messages.

PresenceEventListener is used to manage web socket sessions. When a new session is created we can map it to a current user and store this mapping for further usage.

```
aidl
public class PresenceEventListener {
    private final WebSocketSessionServiceImpl webSocketSessionService;
    
    public PresenceEventListener(WebSocketSessionServiceImpl webSocketSessionService) {
        this.webSocketSessionService = webSocketSessionService;
    }
    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
        Principal principal = event.getUser();
        webSocketSessionService.setUserSession(principal.getName(), principal);
    }
    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {
        Principal principal = event.getUser();
        webSocketSessionService.removeSession(principal.getName());
    }
}
```

```aidl
@Service
public class WebSocketSessionServiceImpl {
    
    private Map<String, Principal> sessionMap = new ConcurrentHashMap<>();
    
    public void setUserSession(String userName, Principal principal) {
        sessionMap.put(userName, principal);
    }
    
    public void removeSession(String userName) {
        sessionMap.remove(userName);
    }
    
    public boolean sessionExists(String userName) {
        return sessionMap.containsKey(userName);
    }
    
}
```
Security Config
```
@Configuration
@EnableConfigurationProperties
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1")
                    .password(passwordEncoder().encode("password"))
                    .roles("USER")
                .and()
                .withUser("user2")
                    .password(passwordEncoder().encode("password"))
                    .roles("USER");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                    .antMatcher("/**").authorizeRequests()
                    .antMatchers("/login/**", "/logout/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .permitAll()
                .and()
                .logout()
                    .logoutSuccessUrl("/login")
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                    .permitAll();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```
Controller
```aidl
@Controller
public class HomeController {
    private static final String WS_ENDPOINT = "/ws";
    private static final String MESSAGES_TOPIC = "/topic/messages";
    private static final String EVENTS_QUEUE = "/queue/events";
    private static final String NOTIFICATION_MSG = "Today is the last day of your trial version.";
    @Autowired
    private WebSocketSessionServiceImpl webSocketSessionService;
    
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    
    @RequestMapping(value = "/")
    public String home(Principal principal, Model model) {
        model.addAttribute("user", principal);
        
        return "home";
    }
    @MessageMapping(WS_ENDPOINT)
    @SendTo(MESSAGES_TOPIC)
    public Message message(Message message) {
        return message;
    }
    @ResponseBody
    @RequestMapping(value = "/notify/{userName}")
    public void sendNotificationTrigger(@PathVariable String userName) {
        if (webSocketSessionService.sessionExists(userName)) {
            Message message = new Message(null, NOTIFICATION_MSG);
            simpMessagingTemplate.convertAndSendToUser(userName, EVENTS_QUEUE, message);
        }
    }
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }
    @RequestMapping(value = "/logout")
    public String logout() {
        return "login";
    }
}
```

## Answer four
![image](https://user-images.githubusercontent.com/48466124/213201002-160d1bec-2c78-48c4-9b6b-8742aa3dc946.png)
Solution path - ```https://github.com/MeftunH/enocaQA/blob/master/core/StarPattern.java```

## Answer five
### Connectivity Test
Telnet and nc are common tools used to test port connectivity from Linux server. Telnet can be used to test tcp port connections, where as nc can be used to test both tcp/udp ports connectivity. Make sure telnet and nc tools are installed on the Linux server you are trying to test connectivity
```aidl
# yum install nc
# yum install telnet
```
#### Testing TCP port connectivity with telnet
Requesting telnet to connect
```aidl
# telnet [hostname/IP address] [port number]
```
#### Answer for successful from telnet
```aidl
# telnet 192.168.12.10 22
Trying 192.168.12.10...
Connected to 192.168.12.10.
Escape character is '^]'.
SSH-2.0-OpenSSH_6.6.1

Protocol mismatch.
Connection closed by foreign host.
```
#### Answer for unsuccessful from telnet

```aidl
# telnet 192.168.12.10 22
Trying 192.168.12.10...
telnet: connect to address 192.168.12.10: No route to host
```
#### Using nc command to test TCP port connectivity
```aidl
# nc -vz [hostname/IP address] [port number]
```
#### Answer for successful from nc
```aidl
# nc -z -v 192.168.10.12 22
Connection to 192.118.20.95 22 port [tcp/ssh] succeeded!
````
#### Answer for unsuccessful from nc
```aidl
# nc -z -v 192.168.10.12 22
nc: connect to 192.118.20.95 port 22 (tcp) failed: No route to host
````
### How to reach to the server from the outside world
#### 1. Using SSH
```aidl
# ssh [username]@[hostname/IP address]
``` 

#### How do you assign a file to a server
The easiest way to create a new file in Linux is by using the touch command.
```aidl
# touch [filename]
```
This creates a new empty file named test.txt. You can see it by entering:
The ls command lists the contents of the current directory. Since no other directory was specified, the touCreate File with cat Command
Create File with cat Command
The cat command is used to create a new file or to view the contents of an existing file. The cat command is also used to concatenate files and print the output on the standard output.
```aidl
cat > test3.txt
```
Create File with echo Command
```aidl
echo "This is a test file" > test4.txt
```
#### how to extract files from server
If you are using another web host or server provider, the SSH details can typically be found in your dashboard as well.

After you have found the login details, you can log in with the following SSH command.
```aidl
ssh [username]@[hostname/IP address] -p [port]
```
In some Linux distributions, the unzip package is not installed by default. Kinsta users do not have to worry about installing the unzip package because it is automatically installed on all our site containers. If you are managing a server that does not have the unzip package installed, you can use the following command to install it – note that sudo level permissions are required.
The next step is to navigate to the ZIP file and unzip it. To navigate to the correct folder, we can use the cd command, as shown below.
```aidl
cd directory
cd ~/private
//To unzip the file to the current directory, use the command below.
unzip your-file.zip
//To unzip the file to a different directory, use this command instead.
unzip your-file.zip -d directory
//For our backup.zip file, we want to unzip it to our ~/public folder, which can be done with the command below.
unzip backup.zip -d ~/public
```

If you would like to remove the original ZIP file after unzipping it, you can use the rm command like this.

```aidl
rm /path/to/your-file.zip
```

## Answer six
Solution path - ```https://github.com/MeftunH/enocaQA/tree/master/TutorialManagementSystem```

### How to run application

#### 1. Clone the project
```aidl
git clone
```
#### 2. Open the project with your IDE
#### 3. Run the project
#### 4. Open the browser or postman and type ```http://localhost:8081/``` and then use the endpoints
### DB Structure
![image](https://user-images.githubusercontent.com/48466124/213847149-e742e810-b30e-4fad-b4dc-85dc9116b5c9.png)

### Tables
![image](https://user-images.githubusercontent.com/48466124/213846806-17c14f8b-9cb5-45d9-bed9-5c42c6336e91.png)
### Endpoints
```aidl
POST	/api/tutorials	=>create new Tutorial
POST	/api/tutorials/:id/comments	=> create new Comment for a Tutorial
GET	/api/tutorials/:id/comments	=>retrieve all Comments of a Tutorial
GET	/api/comments/:id	=>retrieve a Comment by :id
PUT	/api/comments/:id	=>update a Comment by :id
DELETE	/api/comments/:id	=>delete a Comment by :id
DELETE	/api/tutorials/:id	=>delete a Tutorial (and its Comments) by :id
DELETE	/api/tutorials/:id/comments	=>delete all Comments of a Tutorial
```
Create new tutorial
![image](https://user-images.githubusercontent.com/48466124/213846999-9712eae5-a55a-4cbc-9f44-c42e618099d1.png)
![image](https://user-images.githubusercontent.com/48466124/213847045-8ab213e1-367d-4100-9118-344d04ff93a4.png)

Request body as JSON
```aidl
{
    "description": "Tut 1 description",
    "published": true,
    "title": "Tut 1 title"

}
```

Create new comment for a tutorial Request body as JSON
```aidl
{
    "content": "Comment 1 content",
}
```
## Answer seven
```aidl
updatedAt:[20200101 TO *]
```
You can also use the filter query (fq) parameter to filter your results.
```aidl
*:*&fq=updatedAt:[20200101 TO *]
```
