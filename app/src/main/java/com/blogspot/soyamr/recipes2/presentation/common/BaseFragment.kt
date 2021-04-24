package com.blogspot.soyamr.recipes2.presentation.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.blogspot.soyamr.recipes2.R
import com.blogspot.soyamr.recipes2.databinding.NoInternetConnectionLayoutBinding

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    private var _binding: VB? = null
    val viewBinding get() = _binding!!
    lateinit var viewsOnScreen: Group

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        viewsOnScreen = viewBinding.root.findViewById<Group>(R.id.viewsOnScreen)
        return viewBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    lateinit var errorState: NoInternetConnectionLayoutBinding

    fun showNoInternetError(unit: Unit?) {
        showErrorViews()
        errorState.root.findViewById<TextView>(R.id.errorTitleTextView).text =
            getText(R.string.no_internet)
        errorState.root.findViewById<TextView>(R.id.errorTitleDetailsTextView).text =
            getText(R.string.no_internet_text)
    }

    fun showSomethingWentWrong(unit: Unit?) {
        showErrorViews()
        errorState.root.findViewById<TextView>(R.id.errorTitleTextView).text =
            getText(R.string.something_went_wrong)
        errorState.root.findViewById<TextView>(R.id.errorTitleDetailsTextView).text =
            getText(R.string.something_went_wrong_text)
    }

    fun showErrorViews(show: Boolean = true) {
        if (!show) {
            errorState.root.visibility = View.GONE
            viewsOnScreen.visibility = View.VISIBLE
        } else {
            errorState.root.visibility = View.VISIBLE
            viewsOnScreen.visibility = View.GONE
        }
    }
}