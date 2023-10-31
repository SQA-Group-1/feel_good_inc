# Coding,Commenting and Committing conventions


## IDE Settings
Android API: 34<br>
JDK: 17

<!-- ### How to set JDK -->

<!-- Download JDK 17 if needed (from <https://jdk.java.net/archive/>). Download version 17.0.2.

1. Select File -> Settings (Windows) or Android Studio -> Preferences (Mac)
2. In the sidebar select Build, Execution, Deployment -> Build Tools -> Gradle
3. Under Gradle Projects, ensure w3w is selected
4. Change the Gradle JDK to 17. -->

## Branches and Pull Requests

> :warning: Never merge/commit into `main` without making a pull request

When creating a branch, make sure to use the corresponding issue your branch is addressing.

Below is an example of how to link a branch to an issue:

![Alt text](<assets/pull_request.png>)

If you already created a branch, when you are ready to merge into `main`, add the pull request to the issue.

## Committing
In general, make commit messages clear and concise. Commit messages shouldn't be vague. Keep them below 72 characters wherever possible.

Whenever possible, make sure to reference issues in your commits. For example:

```fixes bug with user table not updating correctly (#18)```

## Spaces & Indentation
* Spaces rather than tabs
* 4 spaces for each indent
* Spaces around operators such as + (so `a + b`)
* But no space for incrementing a variable with `i++` and the like
* Space after `if`, `else`, `switch` before the brackets (e.g. `if (i > 5)`)
* No space after method name and the brackets (e.g. `getX()`)
* No space after method name for declarations (e.g. `public void setX(int x)`)

## Curly Braces {}
* On the same line with a space before them

### Wrapping
* Try to long lines (longer than ~90 characters) in a sensible place
* (e.g. put different parameters to a method call on separate lines if the line would be really long otherwise)

**Sample:**
```
public class ExampleMainClass {
    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            System.out.println("Hello world " + i);
        }
    }
}
```


## JavaDoc Methods
* Add a @Description tag to each non-trivial methods (so you don't have to document getters and setters, like `changeLength` for instance)
* Add @author so it's clear who worked on each function, dont need to @date - `git blame` exists
* Add @param for each parameter with a description of where its from and how its used (unless it's really obvious)
* Add @return if it isn't obvious from the method name
* Add @throws if you throw can an exception

## Classes
* Add @description tags to each class explaining what it does
* Make sure to document all non-trivial functions, including arguments, return values, and exceptions
    * (notably this excludes trivial functions like getters, setters and constructors, unless these methods have some unexpected side effects/are not trivial)



**JavaDoc Sample**
```
/**
 * @description lets the user select between
 * single player and multiplyaer
* Usage:
* <pre> 
*   
* </pre>
 */
public class MainMenu extends Application {

    Button singlePlayer, multiPlayer;
    
    public static void main (String[] args) {
        launch(args);
    }


    /**
     * @description Overrides the existing start function in the JavaFx Application
     * @param primaryStage is where the ui is Drawn onto the screen
     * @author Isaac Destura
     * @date 21/11/22
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Main Menu");

        // code does stuff
    }
}
```
