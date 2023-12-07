import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.revise.R
import java.util.*

class MainActivity2 : AppCompatActivity() {

    private var timer: Timer? = null
    private var isTimerRunning = false
    private var seconds = 0

    private lateinit var btnstartStop: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        btnstartStop = findViewById(R.id.btnstartStop)
        textView = findViewById(R.id.textView)

        btnstartStop.setOnClickListener {
            if (isTimerRunning) {
                stopTimer()
            } else {
                startTimer()
            }
        }
    }

    private fun startTimer() {
        isTimerRunning = true
        btnstartStop.text = "Stop"
        timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                seconds++
                updateTimerText()
            }
        }, 0, 1000)
    }

    private fun stopTimer() {
        isTimerRunning = false
        btnstartStop.text = "Start"
        timer?.cancel()
    }

    private fun updateTimerText() {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        val timeString = String.format("%02d:%02d", minutes, remainingSeconds)

        Handler(Looper.getMainLooper()).post {
            textView.text = timeString
        }
    }
}