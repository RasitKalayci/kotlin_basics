package rasit.kalayci.retrofitkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import rasit.kalayci.retrofitkotlin.R
import rasit.kalayci.retrofitkotlin.model.CryptoModel
import java.util.Locale

class CryptoAdapter(private var cryptoList: ArrayList<CryptoModel>) : RecyclerView.Adapter<CryptoAdapter.CryptoHolder>() {

    private var initialCryptoList: ArrayList<CryptoModel> = ArrayList(cryptoList)

    class CryptoHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textName: TextView = view.findViewById(R.id.textName)
        val textPrice: TextView = view.findViewById(R.id.textPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return CryptoHolder(view)
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.textName.text = cryptoList[position].currency
        holder.textPrice.text = cryptoList[position].price
    }

    fun filter(query: String) {
        val lowerCaseQuery = query.lowercase(Locale.getDefault())
        cryptoList = if (lowerCaseQuery.isEmpty()) {
            ArrayList(initialCryptoList)
        } else {
            val filteredList = ArrayList<CryptoModel>()
            for (item in initialCryptoList) {
                if (item.currency.lowercase(Locale.getDefault()).contains(lowerCaseQuery)) {
                    filteredList.add(item)
                }
            }
            filteredList
        }
        notifyDataSetChanged()
    }

    fun updateList(newList: List<CryptoModel>) {
        initialCryptoList = ArrayList(newList)
        cryptoList = ArrayList(newList)
        notifyDataSetChanged()
    }
}