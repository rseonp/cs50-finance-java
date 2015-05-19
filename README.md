# C$50 Finance (Java Edition)
A Java (Spring Boot) implementation of the C$50 Finance problem set from cs50x


## Introduction
You've mastered C and learned the basics of Java. Now it's time to apply that knowledge in a modern application environment. We'll learn Spring MVC (via Spring Boot) and Hibernate annotations (for storing objects in a database), while re-implementing our old friend, C$50 Finance.

## Getting ready
We need to prep our environment before diving into the code.
* Install [MAMP](https://www.mamp.info), if you haven't already. We'll use MAMP as a convenient way to fire up and inspect our MySQL database (via either phpMyAdmin or Sequel Pro, which is bundled with MAMP). We won't use the Apache or PHP portions of the app for this project, but having them around isn't a bad thing.
* Fire up MAMP and open either phpMyAdmin or Sequel Pro (default user:pass is typically root:root or root:(blank)). Create a database called `cs50_finance_java`, a user `jharvard` with password `crimson`, and grant `jharvard` all privileges on your new database (for `localhost` only).
* Fork this repository.
* Fire up IntelliJ, and create a new project via File > New > Project From Version Control. Select your new fork from the popup, and proceed with project creation.

## Getting started
There's a lot that's new here, including a Java web app using the MVC pattern, object persistence via Hibernate, Spring Boot, Maven, Thymeleaf templates, and more. Fear not. You'll get used to it all with a bit of guidance, by reading the code base, and referencing documentation online.

Once you have your new IntelliJ project, you can start up your web app by opening the Maven panel and expanding `Plugins > spring-boot`. There you'll see `spring-boot:run` as an option. Right-click on it and select `Run finance[spring-boot:...]`. The app will spark to life and you'll see a flurry of log message in the Run panel. If you see an exception stack trace, something went wrong. The most common issue here is that your database isn't running. Make sure it's actually running. If that's not it, try to get some hints from the stack.

Once the app starts up cleanly, point your browser at http://localhost:8080 and you should see the C$50 Finance login page.

Have a look around at the code. You'll see that it's all in the `net.cs50.finance` package (`src/main/java/net/cs50/finance/`), with sub-packages for `models` and `controllers`, and a couple more below that. Our templates are stored in `src/main/resources/templates/` with the Tymeleaf extension of `.html` since they're "natural templates" (that is, templates that can still be displayed in a browser without any additional rendering engine).

## What to do
* First, register a user or two for testing via the register link on the login page. (Notice how we've already implemented that one for you!)
* You have a few TODOs left in the comments (open your TODO panel in IntelliJ for a handy guide, and to clear them off as you go). The first is to implement quote lookup. You'll find the request handler for this action in `StockController.java`, and `quote_form.html` and  `quote_display.html`. Take it from there, using the parameters already put in place for you in the template.
* We failed to make the `symbol` field of `StockHolding.java` case-insensitive, which will cause unintended results when buying and selling. Fix this by updating the appropriate constructor for the `StockHolding` class to make the symbol either always be upper or lowercase (your choice). You'll also have to update the static `buyShares` and `sellShares` methods in the same class.
* Implement buy and sell. You'll find request handlers in `StockController.java`, relevant model methods in `StockHolding.java`, and a shared buy/sell template in `transaction_form.html` and `transaction_confirm.html`. (Read the controller code in detail to see how we manage this shared template, which would have been messy in PHP.) Note that you're making changes that should be persisting data in the database, so be sure that's happening correctly before checking this one off your list. You'll have to deal with a `StockLookupException` in some cases. Your controller should `try/catch` the affected calls and respond appropriately (hint: see `AbstractFinanceController.displayError` and it's usage in other locations within the project).
* Now that you can buy and sell, let's make sure our users can't buy indefinitely (we forgot to give them a cash limit). Add a `cash` field to the `User` model class, making sure to include the proper persistence annotations. Then, update the user's cash on buy/sell requests, and handle a request to buy that exceeds available funds appropriately.
* Display the user's stocks in a table. You'll find some code already in place in `PortfolioController.java` and the `portfolio.html` template. You should display the following fields for each stock in the template: display name (use `Stock.toString()`), number of shares owned, current price, and total value of shares owned. Format the currency values appropriately, with 2 decimal places. You may find the `th:each` Thymleaf tag useful here.

## resources

### Spring Boot/General/Other
* [Spring Boot Reference Guide](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Maven (Wikipedia)](http://en.wikipedia.org/wiki/Apache_Maven)
* [Thymeleaf User Guide](http://www.thymeleaf.org/doc/tutorials/2.1/usingthymeleaf.html)

### Data/ORM/Hibernate
* [What is ORM (Object-Relational Mapping)? (Wikipedia)](http://en.wikipedia.org/wiki/Object-relational_mapping)
* [The JPA (Java Persistence API) (Wikipedia)](http://en.wikipedia.org/wiki/Java_Persistence_API#Related_technologies)
* [Spring Data JPA Reference](http://docs.spring.io/spring-data/jpa/docs/1.8.0.RELEASE/reference/html/) (See section 2.4 for info on DAO query methods)
* [ORM in Spring](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/orm.html) (Note that the XML configuration sections here aren't relevant to Spring Boot, but there's some useful info on DAOs in Spring)
