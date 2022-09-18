package com.example.jogodavelha

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible


class MainActivity: AppCompatActivity() {
    var count = 0
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    private lateinit var title: TextView
    private lateinit var playAgainBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = findViewById<TextView>(R.id.title)
        title.text = "Jogador 1"
        playAgainBtn = findViewById<Button>(R.id.playAgainBtn)
        playAgainBtn.isVisible = false

    }

    fun handlePressButton (view:View){
        val selectedButton = view as Button
        var selectedId = -1
        when(selectedButton.id){
            R.id.btn0 -> selectedId = 0
            R.id.btn1 -> selectedId = 1
            R.id.btn2 -> selectedId = 2
            R.id.btn3 -> selectedId = 3
            R.id.btn4 -> selectedId = 4
            R.id.btn5 -> selectedId = 5
            R.id.btn6 -> selectedId = 6
            R.id.btn7 -> selectedId = 7
            R.id.btn8 -> selectedId = 8
        }

        play(selectedId, selectedButton)
    }


    private fun play (id: Int, btn: Button) {
        if (count == 0) {
            btn.text = "X"
            btn.setBackgroundColor(Color.GREEN)
            player1.add(id)
            count = 1
            title.text = "Jogador 2"
        } else {

            btn.text = "O"
            btn.setBackgroundColor(Color.MAGENTA)
            player2.add(id)
            count = 0
            title.text = "Jogador 1"
        }
        btn.isEnabled = false
        validation()
    }

    private fun validation() {
        var winner = -1

        if (player1.contains(0) && player1.contains(1) && player1.contains(2)) {
            winner = 0
        } else if (player1.contains(3) && player1.contains(4) && player1.contains(5)) {
             winner = 0
        } else if (player1.contains(6) && player1.contains(7) && player1.contains(8)) {
             winner = 0
        } else if (player1.contains(0) && player1.contains(3) && player1.contains(6)) {
             winner = 0
        } else if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
             winner = 0
        } else if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
             winner = 0
        } else if (player1.contains(0) && player1.contains(4) && player1.contains(8)) {
             winner = 0
        } else if (player1.contains(2) && player1.contains(4) && player1.contains(6)) {
            winner = 0
        }

        if (player2.contains(0) && player2.contains(1) && player2.contains(2)) {
            winner = 1
        } else if (player2.contains(3) && player2.contains(4) && player2.contains(5)) {
            winner = 1
        } else if (player2.contains(6) && player2.contains(7) && player2.contains(8)) {
            winner = 1
        } else if (player2.contains(0) && player2.contains(3) && player2.contains(6)) {
            winner = 1
        } else if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 1
        } else if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 1
        } else if (player2.contains(0) && player2.contains(4) && player2.contains(8)) {
            winner = 1
        } else if (player2.contains(2) && player2.contains(4) && player2.contains(6)) {
            winner = 1
        }

        if (winner != -1) {
            val builder = AlertDialog.Builder(this)
            var message = ""

            message = if (winner == 0){
                "Jogador 1 Venceu!"
            } else {
                "Jogador 2 Venceu!"
            }

            playAgainBtn.isVisible = true
            playAgainBtn.setOnClickListener {
                builder
                    .setTitle(message)
                    .setMessage("Deseja jogar novamente?")
                    .setPositiveButton("sim",
                        DialogInterface.OnClickListener { dialog, which ->
                            refresh()
                            playAgainBtn.isVisible = false
                        })
                    .setNegativeButton("cancelar", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }
            builder
                .setTitle(message)
                .setMessage("Deseja jogar novamente?")
                .setPositiveButton("sim",
                    DialogInterface.OnClickListener { dialog, which ->
                        refresh()
                        playAgainBtn.isVisible = false
                    })
                .setNegativeButton("cancelar", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
        } else {
            playAgainBtn.isVisible = true
            val alert = AlertDialog.Builder(this)
            if (player1.size == 5) {
                alert
                    .setTitle("Deu velha!")
                    .setMessage("Deseja jogar novamente?")
                    .setPositiveButton("sim",
                        DialogInterface.OnClickListener { dialog, which ->
                            refresh()
                            playAgainBtn.isVisible = false
                        })
                    .setNegativeButton("cancelar", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }
        }

    }

     private fun refresh() {
         title.text = "Jogador 1"
         count = 0
         player1 = ArrayList<Int>()
         player2 = ArrayList<Int>()

         resetLayout(findViewById<Button>(R.id.btn0))
         resetLayout(findViewById<Button>(R.id.btn1))
         resetLayout(findViewById<Button>(R.id.btn2))
         resetLayout(findViewById<Button>(R.id.btn3))
         resetLayout(findViewById<Button>(R.id.btn4))
         resetLayout(findViewById<Button>(R.id.btn5))
         resetLayout(findViewById<Button>(R.id.btn6))
         resetLayout(findViewById<Button>(R.id.btn7))
         resetLayout(findViewById<Button>(R.id.btn8))
     }

    private fun resetLayout(btn: Button) {
        btn.setBackgroundColor(Color.GRAY)
        btn.isEnabled = true
        btn.text = ""
    }


}