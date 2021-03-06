package company.domain.fragmentnrecycleview.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import company.domain.fragmentnrecycleview.R
import company.domain.fragmentnrecycleview.network.model.LocationItem
import company.domain.fragmentnrecycleview.view.DetailsActivity

import kotlinx.android.synthetic.main.rv_location_child.view.*

class LocationAdapter(private val context: Context) :
    RecyclerView.Adapter<LocationAdapter.ViewHolder>() {
    private var list: List<LocationItem> = ArrayList()

    fun setLocationList(list: List<LocationItem>) {
        this.list = list
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].title
        holder.latLng.text = list[position].latt_long
        holder.rootView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)

            intent.putExtra("Location", list[position].woeid)
            intent.putExtra("name", list[position].title)
            context.startActivity(intent)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.rv_location_child, parent, false))

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.tv_location_name!!
        val latLng = view.tv_lat_lng!!
        val rootView = view.child_root!!

    }
}