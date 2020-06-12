package company.domain.fragmentnrecycleview.view

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

import company.domain.fragmentnrecycleview.R
import company.domain.fragmentnrecycleview.room.WeatherDatabase

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        WeatherDatabase.get(application)

        Handler().postDelayed({
            startActivity(Intent(this, SearchActivity::class.java))
            finish()

        }, 2000)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

    }
}
