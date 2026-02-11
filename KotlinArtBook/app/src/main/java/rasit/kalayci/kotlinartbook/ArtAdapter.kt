package rasit.kalayci.kotlinartbook

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rasit.kalayci.kotlinartbook.databinding.RecyclerRowBinding

class ArtAdapter(val artList : ArrayList<Art>)  :RecyclerView.Adapter<ArtAdapter.ArtHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArtHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArtHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ArtHolder,
        position: Int
    ) {
        holder.binding.recyclerViewTextView.text = artList.get(position).name
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ArtActivity::class.java)
            holder.itemView.context.startActivity(intent)
            intent.putExtra("info","old")
            intent.putExtra("id",artList.get(position).id)

        }
    }

    override fun getItemCount(): Int {
        return artList.size

    }

    class ArtHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {
    }

}