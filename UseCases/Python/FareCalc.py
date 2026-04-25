rates = {
    'Economy': 10,
    'Premium': 18,
    'SUV': 25
}

def calculate_fare(km, vehicle_type, hour):
    try:
        
        if vehicle_type not in rates:
            return "Service Not Available"

        
        base_amount = km * rates[vehicle_type]

        # Surge pricing logic
        if 17 <= hour <= 20:
            surge_multiplier = 1.5
        else:
            surge_multiplier = 1

        
        final_price = base_amount * surge_multiplier
        return final_price

    except Exception:
        return "Service Not Available"

print("===== Ride Estimate Calculator =====")

try:
    distance = float(input("Enter distance (in km): "))
    vehicle_type = input("Enter vehicle type (Economy/Premium/SUV): ").strip()
    hour_of_day = int(input("Enter hour of day (0-23): "))

    result = calculate_fare(distance, vehicle_type, hour_of_day)

    print("\n===== Price Receipt =====")

    if result == "Service Not Available":
        print(result)
    else:
        print(f"Distance: {distance} km")
        print(f"Vehicle Type: {vehicle_type}")
        print(f"Hour: {hour_of_day}")

        if 17 <= hour_of_day <= 20:
            print("Surge Pricing: Applied (1.5x)")
        else:
            print("Surge Pricing: Not Applied")

        print(f"Final Fare: ₹ {round(result, 2)}")

    print("===========================")

except ValueError:
    print("Invalid input! Please enter correct values.")
