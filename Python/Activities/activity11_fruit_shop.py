fruits = {
    "apple": 100,
    "banana": 40,
    "orange": 80,
    "mango": 120,
    "grapes": 90
}

fruit = input("Enter fruit name: ").lower()

if fruit in fruits:
    print(fruit, "is available")
    print("Price:", fruits[fruit])
else:
    print(fruit, "is not available")
