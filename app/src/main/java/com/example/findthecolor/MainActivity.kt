package com.example.findthecolor

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.ColorUtils
import androidx.databinding.DataBindingUtil
import com.example.findthecolor.databinding.ActivityMainBinding
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var level: Int = 0
    private var wonPhrases = listOf<String>(
        "Well Done!",
        "Good job!",
        "That's it!",
        "That's the one!"
    )
    private val colorList = listOf<Int>(
        Color.RED,
        Color.DKGRAY,
        Color.BLUE,
        Color.CYAN,
        Color.GREEN,
        Color.MAGENTA,
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Logger.addLogAdapter(AndroidLogAdapter())


        startGame()


    }

    @ColorInt
    fun darkenColor(@ColorInt color: Int, factor: Float): Int {
        return Color.HSVToColor(FloatArray(3).apply {
            Color.colorToHSV(color, this)
            this[2] *= factor
        })
    }


    private fun getDifficulty(level: Int): Float{
        val difficultyList = listOf<Float>(0.5f, 0.6f, 0.8f, 0.85f, 0.9f, 0.99f)
        return when (level) {
            in 0..3 -> difficultyList[0]
            in 4..8 -> difficultyList[1]
            in 9..12 -> difficultyList[2]
            in 13..16 -> difficultyList[3]
            in 17..20 -> difficultyList[4]
            else -> difficultyList[5]
        }


    }



    private fun setListeners(buttonList: List<Button>, randomColor: Int) {
        val chosen = buttonList.random()
        chosen.setBackgroundColor(darkenColor(randomColor, getDifficulty(level)))
        level++
        binding.levelText.text = "Level $level"

        for (item in buttonList)
            item.setOnClickListener { buttonPressed(item, chosen) }
    }


    private fun buttonPressed(item: Button, chosen: Button) {
        if (item != chosen) {
            //They got it wrong :(
            restartGame()
        } else {
            //They got it right!
            binding.statusText.text = wonPhrases.random()
            startGame()
        }
    }

    private fun restartGame() {
        level = 0
        binding.statusText.text = "Try again :("
        startGame()
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
        val randomColor = colorList.random()
        Logger.d("$randomColor")
        for (item in buttonList) item.setBackgroundColor(randomColor)
        setListeners(buttonList, randomColor)

    }


}

