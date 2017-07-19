package tactoe.tic.ishwar.com.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

@Suppress("DEPRECATION") class MainActivity : AppCompatActivity() {

    // onCreate method, R is for resource folder access
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun resetUI(view:View){
        bu1.setText("")
        bu2.setText("")
        bu3.setText("")
        bu4.setText("")
        bu5.setText("")
        bu6.setText("")
        bu7.setText("")
        bu8.setText("")
        bu9.setText("")
    }
    //comman Event for all buttons
    fun buClickEvent(view: View){
        //as activity is view change view to button
        val buttonSelect = view as Button

        //default cell id
        var cellId = 0

        //check cellId for selected cell
        when(buttonSelect.id){
            // this -> is set to symobl, check which button pressed from R layout and update cellId
            R.id.bu1 -> cellId = 1
            R.id.bu2 -> cellId = 2
            R.id.bu3 -> cellId = 3
            R.id.bu4 -> cellId = 4
            R.id.bu5 -> cellId = 5
            R.id.bu6 -> cellId = 6
            R.id.bu7 -> cellId = 7
            R.id.bu8 -> cellId = 8
            R.id.bu9 -> cellId = 9
        }

        //call function to start game
        PlayGame(cellId,buttonSelect)

    }

    //create empyt array list of type int for each Player
    var player1=ArrayList<Int>()
    var player2=ArrayList<Int>()

    //set default active player is 1
    var activePlayer =1


    //Play Game function
    fun PlayGame(cellId:Int, buSellected: Button){

        //if player is 1 mark X else O
        if (activePlayer == 1 ){
            buSellected.text="X"
//            buSellected.setBackgroundColor(Color.GREEN)
            //add player 1 selected button id to player 1 arrayList
            player1.add(cellId)
            //select player 2
            activePlayer = 2
            AutoPlay()
        }else{
            buSellected.text="O"
//            buSellected.setBackgroundColor(Color.BLUE)
            //add player 2 selected id to arrayList
            player2.add(cellId)
            activePlayer = 1
        }

        //disable button once pressed
//        if (reset == false){
//        buSellected.isEnabled = false
//        }
        //check winner
        checkWinner()
    }

    fun checkWinner(){

        //default no one is winner
        var winner = -1

        //for first row
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner = 2
        }

        //second row
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner = 2
        }

        //third row

        if (player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner = 2
        }

        //comumn1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner = 2
        }

        //column2
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner=1
        }
        if(player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner=2
        }


        // col 3
        if(player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner=1
        }
        if(player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner=2
        }

        //diagonal1
        if(player1.contains(1) && player1.contains(5) && player1.contains(9)){
            winner=1
        }
        if(player2.contains(1) && player2.contains(5) && player2.contains(9)){
            winner=2
        }

        //diagonal2
        if(player1.contains(3) && player1.contains(5) && player1.contains(7)){
            winner=1
        }
        if(player2.contains(3) && player2.contains(5) && player2.contains(7)){
            winner=2
        }

        if(winner != -1){
            if(winner == 1){
                Toast.makeText(this,"Player 1 win game", Toast.LENGTH_LONG).show()
                player1.clear()
                player2.clear()
            }else{
                Toast.makeText(this,"Player 2 win game", Toast.LENGTH_LONG).show()
                player1.clear()
                player2.clear()
           }
        }
//       when(winner){
//           -1 -> "Draw"
//           1 -> Toast.makeText(this,"Player 1 win game", Toast.LENGTH_SHORT).show()
//           2 -> Toast.makeText(this,"Player 2 win game", Toast.LENGTH_SHORT).show()
//
//       }

    }

    //to create autoplay device will play game with you
    fun AutoPlay(){

        //create array list to check which cells are free
        var emptyArrayList = ArrayList<Int>()

        for (cellId in 1..9){
            //if cellId is not in player1 or player2 , cell is free
            if(!(player1.contains(cellId)) or player2.contains(cellId)){
                emptyArrayList.add(cellId)
            }
        }

        //select next free cell randomly
        var r = Random()
        val randomIndex = r.nextInt(emptyArrayList.size-0)+0
        val cellId = emptyArrayList[randomIndex]

        var buttonSelect : Button?

        when(cellId){
            1-> buttonSelect=bu1
            2-> buttonSelect=bu2
            3-> buttonSelect=bu3
            4-> buttonSelect=bu4
            5-> buttonSelect=bu5
            6-> buttonSelect=bu6
            7-> buttonSelect=bu7
            8-> buttonSelect=bu8
            9-> buttonSelect=bu9
            else -> {
                buttonSelect=bu1
            }
        }

        PlayGame(cellId,buttonSelect)
    }
}
