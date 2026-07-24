import pandas as pd

df = pd.read_csv("users.csv")

print(df["Username"])
print(df.loc[1, ["Username", "Password"]])
print(df.sort_values("Username"))
print(df.sort_values("Password", ascending=False))
