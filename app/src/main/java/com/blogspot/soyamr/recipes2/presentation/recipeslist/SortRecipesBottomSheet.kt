package com.blogspot.soyamr.recipes2.presentation.recipeslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.blogspot.soyamr.recipes2.R
import com.blogspot.soyamr.recipes2.domain.entities.SortType
import com.blogspot.soyamr.recipes2.utils.Constants.BUNDLE_KEY
import com.blogspot.soyamr.recipes2.utils.Constants.REQUEST_KEY
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton

class SortRecipesBottomSheet : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)

        view.findViewById<MaterialButton>(R.id.sortByName).setOnClickListener {
            parentFragmentManager.setFragmentResult(
                REQUEST_KEY,
                bundleOf(BUNDLE_KEY to SortType.ByName.key)
            )
            dismiss()
        }
        view.findViewById<MaterialButton>(R.id.sortByDate).setOnClickListener {
            parentFragmentManager.setFragmentResult(
                REQUEST_KEY,
                bundleOf(BUNDLE_KEY to SortType.ByDate.key)
            )
            dismiss()
        }

        return view
    }

}