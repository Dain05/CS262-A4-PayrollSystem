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

This project is a Payroll Management System built using Java.  
It handles different types of employees along with invoices, and calculates payments using object-oriented programming concepts.

A simple GUI was created using Java Swing so users can view records, add new ones, remove them, and generate pay stubs.

---

## Features

- Supports multiple employee types:
  - Salaried Employee
  - Hourly Employee
  - Commission Employee
  - Base Plus Commission Employee
- Supports Invoice processing (treated as Payable)
- Uses interface-based design (`Payable`)
- Demonstrates inheritance and polymorphism
- GUI interface built using Java Swing
- Add and remove records from the system
- Generates pay stubs
- Saves and loads data from file
- Payment values formatted to 2 decimal places

---

## System Structure

### Core Classes
- Employee (base class)
- SalariedEmployee
- HourlyEmployee
- CommissionEmployee
- BasePlusCommissionEmployee

### Interface
- Payable (implemented by employees and invoice)

### Other Classes
- Invoice
- PayrollSystem (main class)
- PayrollGUI (user interface)
- PayrollDataManager (handles file saving/loading)
- InvalidSalaryException (custom exception)

---

## Key Concepts Used

- Object-Oriented Programming (OOP)
- Inheritance
- Polymorphism
- Interfaces
- Exception Handling
- File Handling (read/write)
- Java Swing GUI
- Event Handling

---

## How to Run

Run one of the following:

- `PayrollGUI.java` → to launch the graphical interface  
- `PayrollSystem.java` → to run the console version  

---

## Output

- Displays employee and invoice payments
- Generates pay stubs
- Saves data to file (`paystub.txt` and data file)
