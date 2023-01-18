
<h1 align="center">
  <br>
  <a href="http://www.amitmerchant.com/electron-markdownify"><img src="https://www.enoca.com/wp-content/uploads/2019/02/enoca.png" alt="Markdownify" width="200"></a>
</h1>

<h4 align="center">QA session by <a href="https://www.enoca.com/" target="_blank">Enoca</a>.</h4>

<p align="center">
  <a href="#qusetion-one">Q1</a> •
  <a href="#how-to-use">How To Use</a> •
  <a href="#download">Download</a> •
  <a href="#credits">Credits</a> •
  <a href="#related">Related</a> •
  <a href="#license">License</a>
</p>

![screenshot](https://raw.githubusercontent.com/amitmerchant1990/electron-markdownify/master/app/img/markdownify.gif)

## Question one

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
## How To Use

To clone and run this application, you'll need [Git](https://git-scm.com) and [Node.js](https://nodejs.org/en/download/) (which comes with [npm](http://npmjs.com)) installed on your computer. From your command line:

```bash
# Clone this repository
$ git clone https://github.com/amitmerchant1990/electron-markdownify

# Go into the repository
$ cd electron-markdownify

# Install dependencies
$ npm install

# Run the app
$ npm start
```

> **Note**
> If you're using Linux Bash for Windows, [see this guide](https://www.howtogeek.com/261575/how-to-run-graphical-linux-desktop-applications-from-windows-10s-bash-shell/) or use `node` from the command prompt.


## Download

You can [download](https://github.com/amitmerchant1990/electron-markdownify/releases/tag/v1.2.0) the latest installable version of Markdownify for Windows, macOS and Linux.

## Emailware

Markdownify is an [emailware](https://en.wiktionary.org/wiki/emailware). Meaning, if you liked using this app or it has helped you in any way, I'd like you send me an email at <bullredeyes@gmail.com> about anything you'd want to say about this software. I'd really appreciate it!

## Credits

This software uses the following open source packages:

- [Electron](http://electron.atom.io/)
- [Node.js](https://nodejs.org/)
- [Marked - a markdown parser](https://github.com/chjj/marked)
- [showdown](http://showdownjs.github.io/showdown/)
- [CodeMirror](http://codemirror.net/)
- Emojis are taken from [here](https://github.com/arvida/emoji-cheat-sheet.com)
- [highlight.js](https://highlightjs.org/)

## Related

[markdownify-web](https://github.com/amitmerchant1990/markdownify-web) - Web version of Markdownify

## Support

<a href="https://www.buymeacoffee.com/5Zn8Xh3l9" target="_blank"><img src="https://www.buymeacoffee.com/assets/img/custom_images/purple_img.png" alt="Buy Me A Coffee" style="height: 41px !important;width: 174px !important;box-shadow: 0px 3px 2px 0px rgba(190, 190, 190, 0.5) !important;-webkit-box-shadow: 0px 3px 2px 0px rgba(190, 190, 190, 0.5) !important;" ></a>

<p>Or</p> 

<a href="https://www.patreon.com/amitmerchant">
	<img src="https://c5.patreon.com/external/logo/become_a_patron_button@2x.png" width="160">
</a>

## You may also like...

- [Pomolectron](https://github.com/amitmerchant1990/pomolectron) - A pomodoro app
- [Correo](https://github.com/amitmerchant1990/correo) - A menubar/taskbar Gmail App for Windows and macOS

## License

MIT

---

> [amitmerchant.com](https://www.amitmerchant.com) &nbsp;&middot;&nbsp;
> GitHub [@amitmerchant1990](https://github.com/amitmerchant1990) &nbsp;&middot;&nbsp;
> Twitter [@amit_merchant](https://twitter.com/amit_merchant)

