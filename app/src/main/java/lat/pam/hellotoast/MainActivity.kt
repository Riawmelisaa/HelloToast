package lat.pam.hellotoast

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private var mCount = 0
      private val model: NameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Mengenalkan tombol dari layout ke sistem
        val mShowCount = findViewById<TextView>(R.id.show_count)
        val buttonCountUp = findViewById<Button>(R.id.button_count)
        val buttonToast = findViewById<Button>(R.id.button_toast)
        val buttonSwitchPage = findViewById<Button>(R.id.button_switchpage)

        val buttonBrowser = findViewById<Button>(R.id.button_browser)
        val buttonMap = findViewById<Button>(R.id.button_map)
        val buttonDial = findViewById<Button>(R.id.button_dial)


        buttonBrowser.setOnClickListener(View.OnClickListener {
            val intentbrowse = Intent(Intent.ACTION_VIEW)
            intentbrowse.setData(Uri.parse("https://www.google.com/"))
            startActivity(intentbrowse)
        })

        buttonMap.setOnClickListener(View.OnClickListener {
            val intentMap = Intent(Intent.ACTION_VIEW)
            intentMap.setData(Uri.parse("https://share.google/KHnjGyvqbH1SbXdMY"))
            startActivity(intentMap)
        })


        buttonDial.setOnClickListener(View.OnClickListener {
            val intentIg = Intent(Intent.ACTION_VIEW)
            intentIg.setData(Uri.parse("https://www.instagram.com/uinsgd.official?stkn=bmlqZDNkYTJrdXNw"))
            startActivity(intentIg)
        })


        buttonCountUp.setOnClickListener {
            val next = (model.currentName.value ?: 0) + 1
            mCount = next
            model.currentName.value = next
        }

        // 5. Aksi Tombol Lama: Toast
        buttonToast.setOnClickListener(View.OnClickListener {
            val tulisan: String = mShowCount?.text.toString()
            val toast: Toast = Toast.makeText(this, "Angka yang dimunculkan $tulisan", Toast.LENGTH_LONG)
            toast.show()
        })

        // 6. Aksi Tombol Lama: Switch Page ke MainActivity2
        buttonSwitchPage.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        })
        val nameObserver = Observer<Int> { newName ->
            mShowCount.text = newName.toString()
        }
        model.currentName.observe(this, nameObserver)
    }

}

