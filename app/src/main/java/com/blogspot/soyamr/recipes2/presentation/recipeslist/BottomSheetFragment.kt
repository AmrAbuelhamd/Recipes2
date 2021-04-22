package com.blogspot.soyamr.recipes2.presentation.recipeslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.blogspot.soyamr.recipes2.R
import com.blogspot.soyamr.recipes2.utils.Constants.KEY
import com.blogspot.soyamr.recipes2.domain.entities.SortType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton

class BottomSheetFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)

        view.findViewById<MaterialButton>(R.id.sortByName).setOnClickListener {
            findNavController().previousBackStackEntry?.savedStateHandle?.set<String>(
                KEY,
                SortType.ByName.key
            )
            dismiss()
        }
        view.findViewById<MaterialButton>(R.id.sortByDate).setOnClickListener {
            findNavController().previousBackStackEntry?.savedStateHandle?.set<String>(
                KEY,
                SortType.ByDate.key
            )
            dismiss()
        }

        return view
    }

}