numbers = tuple(map(int, input("Enter tuple numbers separated by space: ").split()))

for i in numbers:
    if i % 5 == 0:
        print(i)
