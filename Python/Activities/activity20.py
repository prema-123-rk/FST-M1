import pandas as pd

df = pd.read_excel("contacts.xlsx")

rows, columns = df.shape
print("Rows:", rows)
print("Columns:", columns)

print("\nEmail Column:")
print(df["Email"])

print("\nSorted Data:")
print(df.sort_values(by="FirstName"))
