# 🍽️ Restaurant Order System

A **production-ready console-based restaurant management system** that combines inventory management, shopping cart functionality, undo/redo operations, and menu management. Built entirely in Java with custom data structures.

## 📌 Project Overview

This system demonstrates core Computer Science concepts through a real-world restaurant scenario:

- **Inventory Management** – Track product stock with `ArrayList` for O(1) random access
- **Shopping Cart** – Custom `Singly Linked List` for sequential cart operations
- **Undo Feature** – Stack implementation (LIFO) for reverting last cart addition
- **Menu Management** – Generic class with bounded type parameter for different menu items
- **Polymorphism** – Different item types (Appetizer, MainCourse, Dessert, Beverage) with unique preparation time calculations
- **File Persistence** – Inventory saved to/loaded from text file

## 🎯 Concepts from Both Assignments

| Concept | Source Assignment | Implementation |
|---------|------------------|----------------|
| `ArrayList<Product>` | Grocery Store Inventory | ✅ Complete |
| Custom Singly Linked List | Grocery Store Cart | ✅ Complete |
| Stack for Undo | Grocery Store Undo | ✅ Complete |
| File I/O (load/save) | Grocery Store | ✅ Complete |
| Interface as ADT | University Course | ✅ `MenuItem` interface |
| Multiple implementations | University Lecture/Lab | ✅ Appetizer, MainCourse, etc. |
| Generic `Manager<T extends Interface>` | University CourseManager | ✅ `MenuManager<T extends MenuItem>` |
| Sort by calculation | University (workload) | ✅ Sort by preparation time |
| Find max by calculation | University (highest workload) | ✅ Find longest preparation time |

## 🚀 Features

### Inventory Management (From Grocery Assignment)
- ✅ Load inventory from `inventory.txt`
- ✅ Display all products in formatted table
- ✅ Search product by ID (exact match)
- ✅ Search product by name (case-insensitive, partial)
- ✅ Add new product (duplicate ID prevention)
- ✅ Remove product by ID
- ✅ Update stock quantity
- ✅ Save inventory back to file

### Shopping Cart (From Grocery Assignment)
- ✅ Add item to cart (checks inventory availability)
- ✅ View cart with itemized subtotals
- ✅ Remove item from cart (restores inventory)
- ✅ Update item quantity (adjusts inventory)
- ✅ Clear entire cart (restores all inventory)
- ✅ Undo last addition (stack-based)

### Menu Management (From University Assignment)
- ✅ Interface `MenuItem` as ADT
- ✅ Four implementations: `Appetizer`, `MainCourse`, `Dessert`, `Beverage`
- ✅ Generic `MenuManager<T extends MenuItem>`
- ✅ Sort menu items by preparation time
- ✅ Find item with longest preparation time
- ✅ Display all menu items with dietary info

### Billing & Checkout
- ✅ Generate detailed bill with tax calculation (8%)
- ✅ Display subtotal, tax, and total
- ✅ Clear cart and undo stack after checkout
- ✅ Save inventory changes permanently

## 🛠️ Tech Stack

- **Language**: Java (JDK 8+)
- **Data Structures**:
  - `java.util.ArrayList` – Inventory storage
  - Custom Singly Linked List – Shopping cart
  - Custom Stack (linked list based) – Undo feature
- **File I/O**: `java.io` (BufferedReader/BufferedWriter)
- **No external dependencies** – Pure Java

## 📁 Project Structure
```
RestaurantOrderSystem/
│
├── src/
│ ├── model/
│ │ ├── MenuItem.java (Interface ADT)
│ │ ├── Appetizer.java (MenuItem implementation)
│ │ ├── MainCourse.java (MenuItem implementation)
│ │ ├── Dessert.java (MenuItem implementation)
│ │ ├── Beverage.java (MenuItem implementation)
│ │ └── Product.java (Inventory item)
│ │
│ ├── datastructures/
│ │ ├── CartNode.java (Node for linked list)
│ │ ├── CartList.java (Custom Singly Linked List)
│ │ └── UndoStack.java (Custom Stack implementation)
│ │
│ └── manager/
│ ├── InventoryManager.java (ArrayList-based inventory)
│ └── MenuManager.java (Generic menu manager)
│
├── database/
│ └── inventory.txt (Auto-created on first run)
│
├── Main.java (Entry point)
└── README.md
```


## 🏃 How to Run

### Prerequisites
- Java JDK 8 or higher
- Command line / Terminal

### Steps

1. **Clone or download** the project
```bash
git clone https://github.com/yourusername/RestaurantOrderSystem.git
cd RestaurantOrderSystem
```
2. **Compile** all Java files
```
javac -d bin src/**/*.java Main.java
```
3. **Run** the application
```
java -cp bin Main
```

---
## VS Code Users
1. Open the project folder in VS Code
2. Ensure all files are saved
3. Right-click Main.java → "Run Java"

---
## 📖 Usage Guide
Main Menu Options
```
==================================================
     RESTAURANT ORDER SYSTEM
==================================================
1.  View Inventory
2.  View Menu (with prep times)
3.  Add Item to Cart
4.  View Cart
5.  Remove Item from Cart
6.  Update Item Quantity
7.  Undo Last Addition
8.  Clear Cart
9.  Checkout & Generate Bill
10. Search Products
11. Add New Product (Staff)
12. Save & Exit
--------------------------------------------------
```

## Sample Walkthrough
1. View available products (Option 1)
- Shows all inventory items with ID, Name, Price, Stock

2. Add items to cart (Option 3)
- Enter product ID (e.g., 101 for Margherita Pizza)
- Enter quantity (e.g., 2)
- Stock is temporarily reduced

3. View cart (Option 4)
Shows items, quantities, unit prices, subtotals
Displays cart total

4. Undo last addition (Option 7)
- Removes the most recently added item
- Restores stock to inventory

5. Checkout (Option 9)
- Generates detailed bill
- Applies 8% tax
- - Clears cart permanently

---
## 📊 Sample Output
Inventory Display
```
============================================================
ID    Name                      Price      Stock
------------------------------------------------------------
101   Margherita Pizza          $12.99     10
102   Pepperoni Pizza           $14.99     8
103   Caesar Salad              $8.99      15
============================================================
```

Cart Display
```
======================================================================
Product                   Quantity   Unit Price Subtotal
----------------------------------------------------------------------
Margherita Pizza          2          $12.99     $25.98
Pepperoni Pizza           1          $14.99     $14.99
----------------------------------------------------------------------
Total: $40.97
======================================================================
```

Bill Generation
```
============================================================
                  BILL
============================================================
[Cart items displayed here]

Subtotal: $40.97
Tax (8%): $3.28
TOTAL: $44.25
============================================================
Thank you for your order!
```
