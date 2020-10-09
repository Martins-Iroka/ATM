import java.util.*

class ATM {

    private var accountNumber = ""
    private var name = ""
    private var password = ""
    private var fund = 0L

    init {
        println("Hello welcome to your personalize ATM")
        start()
    }

    private fun start() {
        pauseForASecond()
        if (accountNumber.isEmpty()) {
            printStatement("You're obviously new here. Would you want us to set you up")
            printStatement("1: Yes \n2: No")
            response()
        } else {
            printStatement("What transaction will you want to perform")
            printStatement("3: Balance \n4: Withdraw \n5: Payment \n6: Cancel")
            response()
        }
    }

    private fun response() {
        val answer = readLine()
        pauseForASecond()
        when {
            answer.isNullOrEmpty() -> {
                printStatement("You have to pick an option")
                response()
            }
            answer == "1" -> {
                printStatement("Let's get your name")
                userName()
            }
            answer == "2" -> printStatement("Goodbye")

            answer == "3" -> checkBalance()

            answer == "4" -> withDraw()

            answer == "5" -> payment()

            answer == "6" -> printStatement("Goodbye")

            answer == "7" -> payment()

            answer == "8" -> printStatement("Goodbye")

        }
    }

    private fun checkBalance() {
        printStatement("Your balance is $fund")
        start()
    }

    private fun payment() {
        printStatement("Enter amount")
        val amount = readLine()?.toLong() ?: 0L
        pauseForASecond()
        if (amount == 0L) {
            printStatement("That can't be what you want to pay")
            payment()
        } else {
            fund+=amount
            printStatement("You have successfully paid in $amount. \nYour balance is $fund")
            start()
        }
    }

    private fun withDraw() {
        if (fund == 0L) {
            printStatement("Oh my! You have #$fund as your balance.\nWould you want to fund your account?")
            printStatement("7: Yes \n8: No")
            response()
        } else {
            print("How much ")
            val amount = readLine()?.toInt()!!
            pauseForASecond()
            if (fund >= amount) {
                fund-=amount
                printStatement("You just withdrew #$amount. Balance is $fund")
                start()
            } else {
                printStatement("Insufficient balance. Please try and fund your account.")
                start()
            }
        }
    }

    private fun userName() {
        name = readLine() ?: ""
        if (name.isEmpty()) {
            printStatement("We really need your name to set you up")
            userName()
        } else {
            printStatement("Thank you $name")
            accountSetup()
        }
    }

    private fun accountSetup() {
        printStatement("Please wait while we get you an account number")
        accountNumber = UUID.randomUUID().toString()
        pauseForASecond()
        printStatement("Alright your account number is $accountNumber.")
        pauseForASecond()
        passwordSetup()
    }

    private fun passwordSetup() {
        printStatement("Please enter a 4 digit password")
        password = readLine() ?: ""
        pauseForASecond()
        when {
            password.isEmpty() -> {
                printStatement("Please we need your password")
                passwordSetup()
            }
            password.length != 4 -> {
                passwordSetup()
            }
            password.length == 4 -> {
                printStatement("You're all setup $name")
                start()
            }
        }
    }

    private fun printStatement(message: String) = println(message)

    private fun pauseForASecond() {
        printStatement("Loading")
        Thread.sleep(1000)
    }

}