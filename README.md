
# My Personal Project

## what will my application?
my personal project will be revolved around a **sneaker botting profit-loss tracker**. This programme is
specifically made for sneaker-botters who like to buy sneakers on websites for retail price and re-selling them at a
higher price to make profits. This application will log  buys and sells and calculate  net profit. 
Every time the user makes a buy or sale, he/she can log entries into this application. The application will also
provide total revenue and net profits. There will be two main types of **Investment**, one is support investment, 
and the other is sneaker investment

the programme will have: 
- 4 entry options of **investments**, which will include:
- purchases of sneakers with sneaker names, quantities of each sneaker, and price of each sneaker
- purchases of proxy plans and their corresponding price (proxies are IP addresses that hinder the site from 
detecting 
multiple purchases from a single address).
- purchases of third party solvers and their corresponding price (Third party solvers solve captcha for the user).
- purchases of notification cook groups and their corresponding price

## who will use my application and why am I intrigued in creating it?
This application is for people who like to bot sneakers and like to have a place to organize their profits and loses. 
sneaker-botting is a very popular idea among teens and even adults, they make up a considerable community in Canada.
Me and some of my friends have been sneaker-botting for a long time. For many people, especially beginners,
it is a pain trying to keep track of profits, loses and investments. I usually keep track profits and loses on a 
Google Doc which is hard to follow sometimes. Some people like using spread-sheets however I find them really 
complicated. This gave rise to an idea to make an easy to operate and helpful profit-loss tracker. 

## User stories
- as a user I want to be able to add sneakers I just bought to the sneakers purchase list 
- as a user I want to be able to add newly purchased proxy plans to the existing purchased proxy plan list
- as a user I want to be able to add newly purchased third party captcha solver to the existing purchased captcha 
solver list
- as a user I want to be able to add sales in form of revenues to the revenue list
- as a user I want to be able to view my current total revenue made.
- as a user I want to be able to view net profits
- as a user I want to be able to view my total amount of money spent on each support investment everytime I add a new 
entry
- as a user, I want to be able to keep the list size of purchases list the same if an entry with existing name is added.
- as a user I want to be able to view my total money spent on Sneaker investments everytime I add a new entry.
- as a user, I want to be able to save my purchased proxy list to file 
- as a user, I want to be able to save my purchased cook group list to file
- as a user, I want to be able to save my purchased captcha solver list to file 
- as a user, I want to be able to save my sneaker entry list to file 
- as a user, I want to be able to load my purchased proxy list from file
- as a user, I want to be able to load my purchased cook group list from file
- as a user, I want to be able to load my purchased captcha solver from file
- as a user, I want to be able to load my sneaker entry list from file
- as a user, I want to be able to save my revenue list to file
- as a user, I want to be able to load my revenue list from file
- as a user, I want to be able to remove an entry from a support entry list
- as a user, I want to be able to remove a sneaker entry from the sneaker entry list 
- as a user, I want to be able to remove a revenue from a revenue list


## phase 4 : task 2
- a sample of events logged in my system:

Sat Nov 20 12:44:36 PST 2021\
a new support entry has been added, its name is Oculus proxies

Sat Nov 20 12:44:46 PST 2021\
a new support entry has been added, its name is Forbidden Cook

Sat Nov 20 12:44:57 PST 2021\
a new support entry has been added, its name is Cap Monster Solver

Sat Nov 20 12:45:15 PST 2021\
a new support entry has been added, its name is solv

Sat Nov 20 12:45:16 PST 2021\
a support entry of name solv has been removed from its list

Sat Nov 20 12:45:28 PST 2021\
a new support entry has been added, its name is 2Captcha

Sat Nov 20 12:45:39 PST 2021\
a new sneaker entry named Yeezy 350 White ColorWay has been added to an empty sneaker entry list

Sat Nov 20 12:45:48 PST 2021\
a new entry named Nike Dunk Low has been added to the sneaker entry list

Sat Nov 20 12:45:51 PST 2021\
a new revenue of 1900.0 has been added to the existing revenues

Sat Nov 20 12:45:54 PST 2021\
a new revenue of 13000.0 has been added to the existing revenues

Sat Nov 20 12:45:57 PST 2021\
an existing revenue of 13000.0 has been removed from the existing revenues

Sat Nov 20 12:46:00 PST 2021\
a new revenue of 2100.0 has been added to the existing revenues


## phase 4 trask 3: 
After reflecting on my design and UML, I realized that the structure of my model classes were fine. However, I realized
a huge mistake made on my design of GUI. As shown in the UML diagram I have many helper classes for listening to button events
when a specific button is pressed. Instead of making these classes nested classes which can have access to private fields in my main
gui class, I created a new Java class outside of GUI. By making these supposedly nested classes a new java class, it presented with trouble
referring to private fields in my GUI class. In fact, I realized this error while working on phase but at the time did not know how to solve
this problem. For every other event listener class I created outside the GUI class, I had to pass in the fields I want in the GUI class into the 
constructor of the newly created listener class. For example, my **SaveActionListener** needed all of my RevenueList, SneakerPurchaseList, ThirdPartySolverPurchaseList, 
ProxyPurchase and CookGroupPurchaseList instances passed into its constructor. By creating these classes outside of my GUI class, I have created 
more than one path to a class. If starting from GUI, I want to access sneakerPurchaseList class, I have many paths. One path goes
straight from GUI to SneakerPurchaseList class, another path can be taken from GUI to addSneakerListener, and then to SneakerPurchaseListClass. This 
increases coupling which is not desired.\











