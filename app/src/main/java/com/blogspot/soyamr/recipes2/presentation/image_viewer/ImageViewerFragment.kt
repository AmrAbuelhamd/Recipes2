package com.blogspot.soyamr.recipes2.presentation.image_viewer

import android.Manifest
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.blogspot.soyamr.recipes2.R
import com.blogspot.soyamr.recipes2.databinding.FragmentImageViewerBinding
import com.blogspot.soyamr.recipes2.presentation.common.BaseFragment
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageViewerFragment :
    BaseFragment<FragmentImageViewerBinding>(FragmentImageViewerBinding::inflate) {

    private val viewModel: ImageViewerViewModel by viewModels()

    private val args: ImageViewerFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load(args.url).into(viewBinding.imageView);
        setListeners()

    }

    private fun setListeners() {
        viewModel.isLoading.observe(viewLifecycleOwner, ::changeLoadingState)
        viewModel.msg.observe(viewLifecycleOwner, ::showMessage)

        viewBinding.downloadButton.setOnClickListener {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                askPermissions()
            } else {
                startDownloading()
            }
        }
    }

    private fun showMessage(msg: String?) {
        msg?.let {
            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
//            viewBinding.downloadButton.isEnabled = true
//            viewBinding.progressBar.visibility = View.GONE
        }
    }

    private fun changeLoadingState(isLoading: Boolean?) {
        isLoading?.let {
            if (it) {
                viewBinding.downloadButton.isEnabled = false
                viewBinding.progressBar.visibility = View.VISIBLE
            } else {
                viewBinding.downloadButton.isEnabled = true
                viewBinding.progressBar.visibility = View.GONE
            }
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    fun askPermissions() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            ) {
                AlertDialog.Builder(requireContext())
                    .setTitle(R.string.permission_required)
                    .setMessage(R.string.permission_required_message)
                    .setPositiveButton(R.string.accept) { dialog, id ->
                        ActivityCompat.requestPermissions(
                            requireActivity(),
                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE
                        )
                        requireActivity().finish()
                    }
                    .setNegativeButton(R.string.deny) { dialog, id -> dialog.cancel() }
                    .show()
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE
                )
            }
        } else {
            startDownloading()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    startDownloading()
                }
                return
            }
        }
    }

    fun startDownloading() {
        viewModel.downloadImage(args.url)
    }

    companion object {
        private const val MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1
    }
}