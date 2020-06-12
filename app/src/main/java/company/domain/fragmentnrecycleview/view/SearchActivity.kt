package company.domain.fragmentnrecycleview.view

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.lifecycle.Observer

import company.domain.fragmentnrecycleview.R
import company.domain.fragmentnrecycleview.adapter.LocationAdapter
import company.domain.fragmentnrecycleview.viewModel.SearchActivityViewModel

import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    private val viewModel: SearchActivityViewModel by viewModels()
    private lateinit var adapter: LocationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        iv_search.setOnClickListener {
            if (et_search.text!!.isNotEmpty())
                viewModel.searchLocation(et_search.text.toString())

        }

        viewModel.showProgress.observe(this, Observer {
            if (it) search_progress.visibility = VISIBLE
            else search_progress.visibility = GONE

        })

        viewModel.locationList.observe(this, Observer {
            adapter.setLocationList(it)

        })

        adapter = LocationAdapter(this)
        rv_search.adapter = adapter

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

    }
}
