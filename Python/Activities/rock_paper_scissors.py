# Rock-Paper-Scissors Game (Two Players)

print("=== Rock Paper Scissors Game ===")

player1 = input("Player 1 (rock/paper/scissors): ").lower()
player2 = input("Player 2 (rock/paper/scissors): ").lower()

valid_choices = ["rock", "paper", "scissors"]

if player1 not in valid_choices or player2 not in valid_choices:
    print("Invalid input! Please enter rock, paper, or scissors.")
elif player1 == player2:
    print("It's a Tie!")
elif (player1 == "rock" and player2 == "scissors") or      (player1 == "scissors" and player2 == "paper") or      (player1 == "paper" and player2 == "rock"):
    print("Player 1 Wins!")
else:
    print("Player 2 Wins!")
