package fr.lbc.test.album

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import fr.lbc.test.LBCApplication
import fr.lbc.test.album.AlbumViewModel.AlbumResult.LoadedAlbums
import fr.lbc.test.album.AlbumViewModel.AlbumResult.LoadingError
import fr.lbc.test.album.injection.AlbumModule
import fr.lbc.test.databinding.FragmentAlbumBinding
import javax.inject.Inject

class AlbumFragment : Fragment(), AlbumListener {

    private var _binding: FragmentAlbumBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var viewModel: AlbumViewModel
    @Inject lateinit var controller: AlbumController

    companion object {
        private const val DISPLAY_LOADER = 0
        private const val DISPLAY_ERROR = 1
        private const val DISPLAY_ALBUMS = 2
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LBCApplication.getComponent(requireContext())
            .plus(AlbumModule(this))
            .inject(this)

        binding.albumRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.albumRecyclerView.adapter = AlbumAdapter(this)
        binding.albumViewFlipper.displayedChild = DISPLAY_LOADER
        controller.loadAlbums()

        startObserving()

        binding.errorButton.setOnClickListener {
            controller.loadAlbums()
        }
    }

    override fun onAlbumSelected(albumId: Int) {
        Log.println(Log.DEBUG, "Album", "Album #$albumId selected")
    }

    private fun startObserving() {
        viewModel.loadingResult.observe(viewLifecycleOwner, { loadingResult ->
            when (loadingResult) {
                is LoadedAlbums -> {
                    (binding.albumRecyclerView.adapter as AlbumAdapter).albumList = loadingResult.groupedImageList
                    binding.albumViewFlipper.displayedChild = DISPLAY_ALBUMS
                }
                LoadingError -> binding.albumViewFlipper.displayedChild = DISPLAY_ERROR
            }
        })
    }
}
