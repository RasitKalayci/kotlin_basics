package rasit.kalayci.landmarkbook

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rasit.kalayci.landmarkbook.databinding.RecycleRowBinding

class LandmarkAdapter(val landmarkList : ArrayList<Landmark>): RecyclerView.Adapter<LandmarkAdapter.LandmarkHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LandmarkHolder {
       val binding = RecycleRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LandmarkHolder(binding)
    }

    override fun onBindViewHolder(
        holder: LandmarkHolder,
        position: Int
    ) {
        holder.binding.textView.text=landmarkList.get(position).name
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailsActivity::class.java)
            intent.putExtra("landmark",landmarkList.get(position))
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return landmarkList.size
    }

    class LandmarkHolder(val binding: RecycleRowBinding): RecyclerView.ViewHolder(binding.root){

    }
}