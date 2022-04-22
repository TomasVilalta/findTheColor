package com.example.findthecolor

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.ColorUtils
import androidx.databinding.DataBindingUtil
import com.example.findthecolor.databinding.ActivityMainBinding
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Logger.addLogAdapter(AndroidLogAdapter())


        startGame()


    }

    private fun setListeners(buttonList: List<Button>, randomColor: Int) {
        val ratio = 0.4f
        val offsetColor = ColorUtils.blendARGB(randomColor, Color.BLACK, ratio)
        Logger.d("$offsetColor")
        val chosen = buttonList.random()
        chosen.setBackgroundColor(offsetColor)


        for (item in buttonList)
            item.setOnClickListener { buttonPressed(item, chosen) }
    }

    private fun buttonPressed(item: Button, chosen: Button) {
        if (item != chosen){
            Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show()
            startGame()}
        else {
            Toast.makeText(this, "Good Job!", Toast.LENGTH_SHORT).show()
            startGame()
        }
    }

    private fun startGame() {
        val buttonList = listOf<Button>(
            binding.button1,
            binding.button2,
            binding.button3,
            binding.button4,
            binding.button5,
            binding.button6,
            binding.button7,
            binding.button8,
            binding.button9
        )
        val colorList = listOf<Int>(
            Color.RED,
            Color.BLACK,
            Color.BLUE,
            Color.CYAN,
            Color.GREEN,
            Color.MAGENTA,
            Color.GRAY
        )

        val randomColor = colorList.random()
        Logger.d("$randomColor")
        for (item in buttonList) item.setBackgroundColor(randomColor)
        setListeners(buttonList, randomColor)

    }
}