package rasit.kalayci.superkahraman

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rasit.kalayci.superkahraman.databinding.RecycleRowBinding

class SuperKahramanAdapter(val superkahramanListesi: ArrayList<Superkahraman>): RecyclerView.Adapter<SuperKahramanAdapter.SuperKahramanViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperKahramanViewHolder {
       val binding = RecycleRowBinding.inflate(Layout0Inflater.from(parent.context),parent,false)
        return SuperKahramanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SuperKahramanViewHolder, position: Int) {
holder.binding.textViewrecyclewiew.text=superkahramanListesi[position].isim
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, TanitimAktivitesi::class.java)

            holder.itemView.context.startActivity((intent))
        }
    }

    override fun getItemCount(): Int {
        return superkahramanListesi.size
    }

    class SuperKahramanViewHolder(val binding : RecycleRowBinding): RecyclerView.ViewHolder(binding.root){

    }


}