package com.enigma.simpletodo.ui.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.enigma.simpletodo.databinding.FragmentFormTodoBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FormTodoBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentFormTodoBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormTodoBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}