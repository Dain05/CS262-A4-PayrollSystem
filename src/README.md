# Assignment #4 – Payroll Management System

## Course Information
Course: Object-Oriented Programming II (CS262)  
Lecturer: Doron Williams  
Type: Group Assignment  

---

## Group Members
- Dain Thorpe – 2301011605  
- Joan Johnson-Brown – 2401010520  
- Shanique Wisdom – 2401010649  
- Pasha Pinnock – 2401011323  
- Dante Graham – 2401010192  

---

## GitHub Repository
https://github.com/Dain05/CS262-A4-PayrollSystem

---

## Project Overview

This project implements a Payroll Management System using Java and Swing GUI.

### Features
- Supports multiple employee types:
  - Salaried Employee
  - Hourly Employee
  - Commission Employee
  - Base Plus Commission Employee
- Supports Invoice processing (treated as Payable)
- Uses interface-based design (`Payable`)
- Demonstrates polymorphism and inheritance
- GUI interface built with Java Swing
- Add and remove records dynamically
- Generates pay stubs
- Saves and loads data using file handling
- Formats payment output to 2 decimal places

---

## System Components

### Core Classes
- Employee (Base class)
- SalariedEmployee
- HourlyEmployee
- CommissionEmployee
- BasePlusCommissionEmployee

### Interface
- Payable (implemented by employees and invoices)

### Other Classes
- Invoice (implements Payable)
- PayrollSystem (main execution)
- PayrollGUI (user interface)
- PayrollDataManager (file handling)
- InvalidSalaryException (custom exception)

---

## Key Concepts Demonstrated
- Object-Oriented Programming (OOP)
- Inheritance and Polymorphism
- Interfaces
- Exception Handling
- File Handling (Read/Write)
- Java Swing GUI
- Event Handling

---

## How to Run
Run:
- `PayrollGUI.java` for GUI interface  
OR  
- `PayrollSystem.java` for console execution  

---

## Output
- Displays employee payments
- Generates pay stubs
- Saves data to file
