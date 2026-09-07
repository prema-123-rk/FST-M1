player1 = input("Enter the your name Player 1 : ")
player2 = input("Enter the your name Player 2 : ")
player1_choice = input(player1 + " Enter your choice - rock, paper or scissors? : ").lower()
player2_choice = input(player2 + " Enter your choice - rock, paper or scissors? : ").lower()
print("Player 1 choose : " + player1_choice)
print("Player 2 choose : " + player2_choice)
if player1_choice == "rock" and player2_choice == "scissors" :
     print("Player 1 wins")
elif player1_choice == "rock" and player2_choice == "paper" :
     print("Player 2  wins")
elif player1_choice == "paper" and player2_choice == "rock" :
     print("Player 1  wins")
elif player1_choice == "paper" and player2_choice == "scissors" :
     print("Player 2  wins")
elif player1_choice == "scissors" and player2_choice == "rock" :
     print("Player 2  wins")
elif player1_choice == "scissors" and player2_choice == "paper" :
     print("Player 1  wins")
else : print("It's a draw")
