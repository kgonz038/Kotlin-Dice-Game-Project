import java.util.*

val randGen = Random()
val scanner= Scanner(System.`in`)
var playerName: String? = null
/*
*  Kimberly Gonzalez  |  Tuesday 11/16/21
*
*  Kotlin Project - Dice Game
*
* Rules:
* After entering your name and how many rounds you wish to play, there will be 3 beginning dice rolls for
* the computer's and player's turn. After displaying the score of those starting dice rolls, the player can
* choose to either roll 1 die or 2 dice. The goal is to roll to 21 or as close to 21, WITHOUT going over
* 21. If the player or the computer go over 21, the turn for that player will be a 0, and the next turn is automatically
* initiated.
* Whoever is the closest to 21 will win for that round, and will tally up. The best of rounds chosen to play
* will decide the winner of the dice game. */

fun main(args: Array<String>)
{
    println("")
    println("Please enter your name: ")
    playerName = scanner.nextLine()

    println("How many total rounds would you like to play?: ")
    val rounds = scanner.nextInt()
    scanner.nextLine()
    println()

    var roundsWonPlayer = 0
    var roundsWonComp = 0
    var roundsPlayed = 0


    while (roundsPlayed < rounds && roundsWonPlayer <= rounds / 2 && roundsWonComp <= rounds / 2)
    {
        println("")
        System.out.format("|      ROUND # %d      |", roundsPlayed + 1)
        println("")

        val playerTurn = roundsPlayed and 1 == 1
        val score1 = goTurn(playerTurn)
        val score2 = goTurn(!playerTurn)

        val playerScore = if (playerTurn) score1 else score2
        val compScore = if (playerTurn) score2 else score1
        var playerWins: Boolean

        playerWins = if (playerScore <= 21 && compScore <= 21)
        {
            playerScore > compScore
        }
        else if (playerScore > 21 && compScore <= 21)
        {

            false
        }
        else if (playerScore <= 21 && compScore > 21)
        {
            true
        }

        else
        {
            playerScore < compScore
        }



        if (playerWins)
        {
            System.out.format("%s wins this round!%n", playerName)
            roundsWonPlayer++
        }
        else if (playerScore == compScore)
        {
            println("It is a tie. No winner for this round.")
            roundsWonPlayer + 0
            roundsWonComp + 0
        }
        else
        {
            System.out.format("Computer wins this round!%n")
            roundsWonComp++
        }

        System.out.format("%n___________%n" + "Total Wins%n" +
                "___________%n" + "%s: %d%n" + "Computer: %d%n%n",
            playerName, roundsWonPlayer, roundsWonComp)

        roundsPlayed++
    }

    if (roundsWonPlayer > roundsWonComp)

    System.out.format("Congrats %s, you have won this dice game!%n", playerName)

    else if (roundsWonPlayer < roundsWonComp) println("Sorry, Computer has won this dice game! Thanks for playing!")

    else println("The dice game has ended in a tie! No winners! Thanks for playing!")
}

fun goTurn(playerTurn: Boolean): Int
{
    val roundName = getRoundPlayerName(playerTurn)
    val rolls = rolls

    System.out.format("It is %s's turn! ", roundName)
    System.out.format("The starting dice rolls for %s are: ", roundName)

    for (i in 0 until rolls.size - 1)
    {
        System.out.format("%d, ", rolls[i])
    }

    System.out.format("and %d.%n", rolls[rolls.size - 1])

    var sum = getRollScore(rolls)

    System.out.format("Starting rolls for %s: %d%n", roundName, sum)


    for (hitCounter in 0..3)
    {
        if (playerTurn && sum == 21 || !playerTurn && sum == 21)
        {
            println("Score hit 21, thus will automatically hold for this turn with the current score of 21.")
            break
        }

        if (playerTurn && sum > 21 || !playerTurn && sum > 21)
        {
            println("Score went over 21! Score for this round is 0.")
            break
        }

        var rollOne: Boolean

        if (playerTurn)
        {
            println("Do you want to roll or hold? Enter 'r' to roll or 'h' to hold.")
            if (!scanner.nextLine().equals("r", ignoreCase = true))
            {
                break
            }
            println("Do you want to roll one die (1) or two (2) dice?")

            rollOne = scanner.nextInt() == 1

            scanner.nextLine()
        }


        else
        {
            rollOne = sum >= 15

            if (sum >= 17)
            {
                break
            }
        }

        val roll2 = newRoll()
        sum += roll2

        if (rollOne)
        {
            System.out.format("%s rolls one more die. The roll is: %d.%n", roundName, roll2)
        }
        else
        {
            val roll3 = newRoll()
            sum += roll3
            System.out.format("%s rolls two more dice. The rolls are: %d and %d.%n", roundName, roll2, roll3)
        }
        System.out.format("%s's total score is now %d.%n", roundName, sum)
    }
    println()
    return sum
}

fun getRoundPlayerName(playerTurn: Boolean): String?
{
    return if (playerTurn) playerName else "Computer"
}

fun getRollScore(roll: IntArray): Int
{
    return  roll[0] + roll[1] + roll[2]
}

fun newRoll(): Int
{
    return randGen.nextInt(6) + 1
}

val rolls: IntArray
    get() = intArrayOf(newRoll(), newRoll(), newRoll())



