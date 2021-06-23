package fr.lbc.test.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.lbc.test.LBCApplication
import fr.lbc.test.databinding.FragmentDetailBinding
import fr.lbc.test.detail.DetailViewModel.ImageResult
import fr.lbc.test.detail.injection.DetailModule
import fr.lbc.test.utils.AutoFitGridLayoutManager
import javax.inject.Inject

class DetailFragment : Fragment() {

    @Inject lateinit var controller: DetailController
    @Inject lateinit var viewModel: DetailViewModel

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val DISPLAY_LOADER = 0
        private const val DISPLAY_ERROR = 1
        private const val DISPLAY_IMAGES = 2
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LBCApplication.getComponent(requireContext())
            .plus(DetailModule(this))
            .inject(this)

        binding.imageRecyclerView.layoutManager = AutoFitGridLayoutManager(requireContext(), 500)
        binding.imageRecyclerView.adapter = ImageAdapter()

        arguments?.getInt("albumId")?.let { albumId ->
            binding.imageViewFlipper.displayedChild = DISPLAY_LOADER
            controller.loadAlbumDetail(albumId)
        }

        binding.goBackButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        startObserving()
    }

    private fun startObserving() {
        viewModel.loadingResult.observe(viewLifecycleOwner, { result ->
            when (result) {
                is ImageResult.LoadedImages -> {
                    (binding.imageRecyclerView.adapter as ImageAdapter).imageList = result.imageList
                    binding.imageViewFlipper.displayedChild = DISPLAY_IMAGES
                }
                ImageResult.LoadingError -> binding.imageViewFlipper.displayedChild = DISPLAY_ERROR
            }
        })
    }
}
