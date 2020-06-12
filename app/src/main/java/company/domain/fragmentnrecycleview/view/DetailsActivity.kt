package company.domain.fragmentnrecycleview.view

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.lifecycle.Observer

import company.domain.fragmentnrecycleview.R
import company.domain.fragmentnrecycleview.viewModel.DetailsActivityViewModel

import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    private val viewModel: DetailsActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        if (intent.hasExtra("name"))
            tv_location.text = intent.getStringExtra("name")

        if (intent.hasExtra("Location")) {
            //  Do network call
            val location = intent.getIntExtra("Location", 0)

            if (location >  0)
                viewModel.getWeather(location)

        }

        viewModel.showProgress.observe(this, Observer {
            if (it) details_progress.visibility = VISIBLE
            else details_progress.visibility = GONE

        })

        viewModel.response.observe(this, Observer {
            if (it != null)
                tv_temp.text = it.consolidated_weather[0].the_temp.toString()

        })
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

    }
}
