# Parking Slot Manager

A Java console application to manage vehicle parking in colleges and apartments.

---

## Problem Statement

College and apartment parking lots are unorganized. Vehicles park randomly,
there is no record of who parked where or for how long, and overstays go
unnoticed. This leads to disputes, blocked vehicles, and security issues.

This application provides a structured system to register vehicles, assign
parking slots, track entry and exit times, detect overstays, and maintain
a complete daily parking log.

---

## Features

- Register vehicles (Two-Wheeler or Four-Wheeler)
- Auto-assign parking slots on vehicle entry
- Track entry time, exit time, and total duration
- Detect vehicles parked beyond 8 hours
- Calculate overstay fine at Rs.50 per extra hour
- View real-time slot availability
- View all currently parked vehicles
- View complete daily parking log
- Save and reload vehicle data between sessions using File I/O

---

## Project Structure

```
ParkingSlotManager/
│
├── src/
│   ├── Vehicle.java              Stores vehicle details
│   ├── ParkingSlot.java          Represents one physical parking slot
│   ├── ParkingRecord.java        Entry and exit record for each vehicle
│   ├── SlotManager.java          Handles slot assignment and availability
│   ├── EntryExitService.java     Manages vehicle entry and exit logic
│   ├── AlertService.java         Detects overstays and calculates fines
│   ├── FileHandler.java          Saves and loads data from file
│   └── Main.java                 Console menu and user interaction
│
├── data/
│   └── vehicles.txt              Saved vehicle registrations
│
└── README.md
```

---

## Technologies Used

- Java 17
- Object Oriented Programming
- Collections — HashMap, ArrayList
- LocalDateTime API
- File I/O — BufferedReader, BufferedWriter
- Exception Handling

---

## Prerequisites

Java JDK 17 or above must be installed on your system.

To verify:

```
java -version
```

---

## How to Set Up and Run

### Step 1 — Clone the Repository

```
git clone https://github.com/YOUR_USERNAME/ParkingSlotManager.git
```

### Step 2 — Navigate to the src folder

```
cd ParkingSlotManager/src
```

### Step 3 — Compile all files

```
javac Vehicle.java ParkingSlot.java ParkingRecord.java SlotManager.java EntryExitService.java AlertService.java FileHandler.java Main.java
```

### Step 4 — Run the application

```
java Main
```

---

## How to Use

When the application starts, you will see a menu with 8 options.

```
========== MAIN MENU ==========
1. Register Vehicle
2. Vehicle Entry
3. Vehicle Exit
4. View All Slots
5. View Active Parkings
6. Check Overstayed Vehicles
7. View Today's Log
8. Save and Exit
================================
```
---

## Menu Options Explained

| Option | Name                      | What it does                                                        |
|--------|---------------------------|---------------------------------------------------------------------|
| 1      | Register Vehicle          | Add a new vehicle with owner name, number plate, and type           |
| 2      | Vehicle Entry             | Log entry time and auto-assign an available slot                    |
| 3      | Vehicle Exit              | Log exit time, free the slot, show duration and fine if any         |
| 4      | View All Slots            | Display all slots with FREE or OCCUPIED status                      |
| 5      | View Active Parkings      | Show all vehicles currently parked with entry time                  |
| 6      | Check Overstayed Vehicles | Flag vehicles parked beyond 8 hours with fine amount                |
| 7      | View Today's Log          | Show complete entry and exit history for the day                    |
| 8      | Save and Exit             | Save all registered vehicles to file and close the app              |

---

## Data Persistence

Vehicle registrations are automatically saved to data/vehicles.txt when
you exit using option 8. The next time you start the application, all
previously registered vehicles are loaded automatically so you do not need
to re-register them.

---

## Parking Rules Implemented

- Two-wheeler slots are prefixed with T (T001, T002, ...)
- Four-wheeler slots are prefixed with F (F001, F002, ...)
- Default capacity is 20 two-wheeler slots and 10 four-wheeler slots
- Maximum allowed parking duration is 8 hours
- Overstay fine is Rs.50 per extra hour beyond the 8-hour limit

---

## Author

Name       : Ishika Mittal
University : VIT Bhopal University
Course     : Programming in Java
Year       : 2026
