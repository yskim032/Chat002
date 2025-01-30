package com.example.myapplication


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import kotlin.math.sqrt

class FluidSimulationView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    private val thread: SimulationThread
    private val particles = mutableListOf<Particle>()
    private val paint = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.FILL
    }

    init {
        holder.addCallback(this)
        thread = SimulationThread(holder, this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        thread.running = true
        thread.start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        thread.running = false
        try {
            thread.join() // 안전하게 스레드 종료
        } catch (e: InterruptedException) {
            Log.e("FluidSimulationView", "Thread interruption", e)
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) {
            particles.add(Particle(event.x, event.y))
        }
        return true
    }

    fun updateSimulation() {
        val gravity = 0.5f
        val damping = 0.9f

        particles.forEach { particle ->
            particle.vy += gravity // Apply gravity
            particle.x += particle.vx
            particle.y += particle.vy

            // Collision with screen boundaries
            if (particle.x < 0 || particle.x > width) {
                particle.vx = -particle.vx * damping
                particle.x = particle.x.coerceIn(0f, width.toFloat())
            }
            if (particle.y < 0 || particle.y > height) {
                particle.vy = -particle.vy * damping
                particle.y = particle.y.coerceIn(0f, height.toFloat())
            }
        }

        // 메모리 관리: 오래된 입자 제거
        if (particles.size > 500) {
            particles.removeAt(0)
        }
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        canvas.drawColor(Color.WHITE) // Clear screen
        particles.forEach { particle ->
            canvas.drawCircle(particle.x, particle.y, 10f, paint)
        }
    }

    private class Particle(var x: Float, var y: Float, var vx: Float = 0f, var vy: Float = 0f)
}

class SimulationThread(private val surfaceHolder: SurfaceHolder, private val simulationView: FluidSimulationView) : Thread() {

    var running = false

    override fun run() {
        while (running) {
            val canvas: Canvas? = try {
                surfaceHolder.lockCanvas()
            } catch (e: Exception) {
                null
            }
            if (canvas != null) {
                synchronized(surfaceHolder) {
                    simulationView.updateSimulation()
                    simulationView.draw(canvas)
                }
                surfaceHolder.unlockCanvasAndPost(canvas)
            }
            sleep(16) // 60 FPS
        }
    }
}
