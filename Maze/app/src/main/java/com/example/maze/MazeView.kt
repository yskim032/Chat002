package com.example.maze

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View

class MazeView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val cellSize = 50f
    private val paint = Paint()
    private val maze = Array(10) { IntArray(10) } // 10x10 미로
    private var playerX = 0
    private var playerY = 0

    init {
        // 0은 길, 1은 벽
        generateMaze()
    }

    private fun generateMaze() {
        // 간단한 미로 생성
        // 0은 길, 1은 벽
        maze[0][0] = 0 // 시작점
        maze[9][9] = 0 // 도착점
        // 여기에 미로 생성 알고리즘 구현
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 미로 그리기
        for (i in maze.indices) {
            for (j in maze[i].indices) {
                if (maze[i][j] == 1) {
                    paint.color = Color.BLACK
                } else {
                    paint.color = Color.WHITE
                }
                canvas.drawRect(
                    j * cellSize,
                    i * cellSize,
                    (j + 1) * cellSize,
                    (i + 1) * cellSize,
                    paint
                )
            }
        }

        // 플레이어 그리기
        paint.color = Color.RED
        canvas.drawCircle(
            playerX * cellSize + cellSize/2,
            playerY * cellSize + cellSize/2,
            cellSize/3,
            paint
        )
    }

    fun movePlayer(direction: Int) {
        val newX = when(direction) {
            KeyEvent.KEYCODE_DPAD_LEFT -> playerX - 1
            KeyEvent.KEYCODE_DPAD_RIGHT -> playerX + 1
            else -> playerX
        }

        val newY = when(direction) {
            KeyEvent.KEYCODE_DPAD_UP -> playerY - 1
            KeyEvent.KEYCODE_DPAD_DOWN -> playerY + 1
            else -> playerY
        }

        // 이동이 가능한지 확인
        if (newX in 0..9 && newY in 0..9 && maze[newY][newX] == 0) {
            playerX = newX
            playerY = newY
            invalidate()
        }
    }
}
