package rasit.kalayci.kotlininstagram.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import rasit.kalayci.kotlininstagram.databinding.RecyclerRowBinding
import rasit.kalayci.kotlininstagram.model.Post

class FeedRecyclerAdapter(val postList: ArrayList<Post>): RecyclerView.Adapter<FeedRecyclerAdapter.PostHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostHolder {
       val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PostHolder,
        position: Int
    ) {
       holder.binding.recyclerCommentText.text = postList.get(position).comment
        holder.binding.recyclerViewText.text = postList.get(position).userEmail
        Picasso.get().load(postList.get(position).downloadUrl).into(holder.binding.recyclerImageView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    class PostHolder(val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root){

    }

}

