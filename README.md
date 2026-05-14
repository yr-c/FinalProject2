# Final Project 2 - Library system
___
### The final project for the Winter semester of 2026
#### By Yoonchan Rhie

## Introduction
This is a mock system of a library, simulating backend behaviour when dealing with different 
types of users and items. It incorporates data loading/saving using CSV files and is organized in packages.

## Core functionalities
- Admins may add and remove books to and from the library. 
- Admins may register new users to the library. 
- Users may borrow available items and return them to the library. Doing so will set the item as "borrowed".
- Admin users or a system admin may save and load data to CSV files.
- Admin users or a system admin may generate a report of all users and items in the library.

## Project structure
*Check resources directory for UML class diagram.*
```
src/main/java/org.yoonchan
    entities/ Book, DVD, Item, Magazine
    roles/ Admin, CSVPersister, InvalidItemException, ItemRegistrar, 
        MaximumBorrowsReachedException, Reporter, Student, Teacher, User, UserRegistrar
    util/ Constants, ItemUtil, UserUtil
    Library, Main
    
src/main/recources
    FinalProject2_UML.png, items.csv, users.csv
    
src/test/java
    ItemComparatorTest, ItemUtilTest, UserComparatorTest, UserUtilTest
```

## Unit testing 
Use of JUnit 6 for unit tests. 

## Final report
You can find the document of the full user guide and report 
[here](https://docs.google.com/document/d/1UQWHKPYBgKEEdD7Qb8X5phi4_tnfHNPCC4CegD8RnBE/edit?usp=sharing).