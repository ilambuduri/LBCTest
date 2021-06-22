package fr.lbc.test.album

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.lbc.test.databinding.ItemAlbumBinding

interface AlbumListener {
    fun onAlbumSelected(albumId: Int)
}

class AlbumAdapter(
    private val listener: AlbumListener
) : RecyclerView.Adapter<AlbumViewHolder>() {

    var albumList: List<AlbumViewModel.AlbumUI> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(listener, binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(albumList[position])
    }

    override fun getItemCount(): Int = albumList.size
}

class AlbumViewHolder(
    private val listener: AlbumListener,
    private val binding: ItemAlbumBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(album: AlbumViewModel.AlbumUI) {
        binding.root.setOnClickListener {
            listener.onAlbumSelected(album.albumId)
        }
        try {
            Picasso.get().load(album.previewImageList[0]).into(binding.firstImageView)
            Picasso.get().load(album.previewImageList[1]).into(binding.secondImageView)
            Picasso.get().load(album.previewImageList[2]).into(binding.thirdImageView)
            Picasso.get().load(album.previewImageList[3]).into(binding.fourthImageView)
        } catch (ex: IndexOutOfBoundsException) {
            Log.println(Log.DEBUG, "Picasso loading", ex.message ?: ex.toString())
        }
    }

}
