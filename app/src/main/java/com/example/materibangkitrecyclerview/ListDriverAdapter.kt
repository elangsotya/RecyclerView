import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.materibangkitrecyclerview.DetailActivity
import com.example.materibangkitrecyclerview.Driver
import com.example.materibangkitrecyclerview.R

class ListDriverAdapter(private val listDriver: ArrayList<Driver>) : RecyclerView.Adapter<ListDriverAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_driver, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, team, photo) = listDriver[position]
        if (photo != null) {
            holder.imgPhoto.setImageResource(photo)
        }
        holder.tvName.text = name
        holder.tvTeam.text = team

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_driver", listDriver[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }



    interface OnItemClickCallback {
        fun onItemClicked(data: Driver)
    }

    override fun getItemCount(): Int = listDriver.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvTeam: TextView = itemView.findViewById(R.id.tv_item_team)
    }




}
