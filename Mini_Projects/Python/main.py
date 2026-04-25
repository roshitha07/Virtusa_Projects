import json
from datetime import datetime
import matplotlib.pyplot as plt

FILE_NAME = "expenses.json"

def load_expenses():
    try:
        with open(FILE_NAME, "r") as file:
            return json.load(file)
    except:
        return []

def save_expenses(expenses):
    with open(FILE_NAME, "w") as file:
        json.dump(expenses, file, indent=4)

def add_expense():
    amount = float(input("Enter amount: "))
    category = input("Enter category (Food/Travel/Bills/etc): ")
    description = input("Enter description: ")
    date = input("Enter date (YYYY-MM-DD) or press Enter for today: ")

    if date == "":
        date = datetime.today().strftime("%Y-%m-%d")

    expense = {
        "date": date,
        "category": category,
        "amount": amount,
        "description": description
    }

    expenses = load_expenses()
    expenses.append(expense)
    save_expenses(expenses)

    print("Expense recorded successfully.")

def monthly_summary():
    expenses = load_expenses()
    month = input("Enter month (YYYY-MM): ")

    total = 0
    for exp in expenses:
        if exp["date"].startswith(month):
            total += exp["amount"]

    print(f"Total expenses for {month}: ₹{total}")

def category_breakdown():
    expenses = load_expenses()
    summary = {}

    for exp in expenses:
        category = exp["category"]
        summary[category] = summary.get(category, 0) + exp["amount"]

    if not summary:
        print("No expenses found.")
        return

    print("\nCategory-wise Spending:")
    for cat, amt in summary.items():
        print(f"{cat}: ₹{amt}")

    labels = list(summary.keys())
    values = list(summary.values())

    plt.pie(values, labels=labels, autopct='%1.1f%%')
    plt.title("Category-wise Expense Distribution")
    plt.show()

def highest_spending_category():
    expenses = load_expenses()
    summary = {}

    for exp in expenses:
        category = exp["category"]
        summary[category] = summary.get(category, 0) + exp["amount"]

    if not summary:
        print("No expenses found.")
        return

    highest = max(summary, key=summary.get)
    print(f"Highest spending category: {highest} (₹{summary[highest]})")

def main():
    while True:
        print("\n--- Smart Expense Tracker ---")
        print("1. Add Expense")
        print("2. Monthly Summary")
        print("3. Category Breakdown")
        print("4. Highest Spending Category")
        print("5. Exit")

        choice = input("Enter your choice: ")

        if choice == "1":
            add_expense()
        elif choice == "2":
            monthly_summary()
        elif choice == "3":
            category_breakdown()
        elif choice == "4":
            highest_spending_category()
        elif choice == "5":
            break
        else:
            print("Invalid choice.")

if __name__ == "__main__":
    main()
