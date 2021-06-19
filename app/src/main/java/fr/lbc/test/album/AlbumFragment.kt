package fr.lbc.test.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import fr.lbc.test.LBCApplication
import fr.lbc.test.album.injection.AlbumModule
import fr.lbc.test.databinding.FragmentAlbumBinding
import javax.inject.Inject

class AlbumFragment : Fragment() {

    private var _binding: FragmentAlbumBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var viewModel: AlbumViewModel
    @Inject lateinit var controller: AlbumController

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

        controller.loadAlbums()

        viewModel.loadingResult.observe(viewLifecycleOwner, Observer { message ->

        })
    }
}
