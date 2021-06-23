package fr.lbc.test.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.lbc.test.databinding.ItemImageBinding

class ImageAdapter : RecyclerView.Adapter<ImageViewHolder>() {

    var imageList: List<DetailViewModel.ImageUI> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size

}

class ImageViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(image: DetailViewModel.ImageUI) {
        Picasso.get().load(image.url).into(binding.imageView)
        binding.imageTitle.text = image.title
    }
}
