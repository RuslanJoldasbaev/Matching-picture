package com.example.matchingpicture

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.matchingpicture.databinding.FragmentMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)

        setData(binding.findSimilarRv1, binding.findSimilarRv2)

    }

    private fun setData(
        rv1: RecyclerView, rv2: RecyclerView
    ) {
        val adapter1 = FindSimilarAdapter(true)
        rv1.adapter = adapter1
        adapter1.submitList(Constants.saykes[(requireActivity() as MainActivity).bookID].shuffled())

        val adapter2 = FindSimilarAdapter(false)
        rv2.adapter = adapter2
        adapter2.submitList(Constants.saykes[(requireActivity() as MainActivity).bookID].shuffled())

        var first = true
        var firstSelected: Pair<saykesModel, Int>? = null
        var secondSelected: Pair<saykesModel, Int>? = null

        adapter1.setOnItemClickListener {
            if (first) {
                firstSelected = Pair(it, it.variant1)
                //1 item saylandi
                first = first.not()
            } else {
                secondSelected = Pair(it, it.variant1)
                if (firstSelected?.first == secondSelected?.first && firstSelected?.second != secondSelected?.second) {
                    binding.trueAnswers.text = "${binding.trueAnswers.text.toString().toInt() + 1}"
                    adapter2.removeItem(it)
                    adapter1.removeItem(it)
                    // 2 item saylandi ham ekewide duris
                } else if (firstSelected?.first != secondSelected?.first) {
                    binding.wrongAnswers.text =
                        "${binding.wrongAnswers.text.toString().toInt() + 1}"
                }
                first = first.not()
                if ((adapter1.itemCount == 1 || adapter2.itemCount == 1) && first && firstSelected?.second != secondSelected?.second) {
                    Toast.makeText(
                        requireActivity(), "You win!", Toast.LENGTH_SHORT
                    ).show()
                }
                Log.d("TAG", "setData: ${adapter1.itemCount}, ${adapter2.itemCount}")
            }
        }

        adapter2.setOnItemClickListener {
            if (first) {
                firstSelected = Pair(it, it.variant2)
                first = first.not()
            } else {
                secondSelected = Pair(it, it.variant2)
                if (firstSelected?.first == secondSelected?.first && firstSelected?.second != secondSelected?.second) {
                    binding.trueAnswers.text = "${binding.trueAnswers.text.toString().toInt() + 1}"
                    adapter2.removeItem(it)
                    adapter1.removeItem(it)
                } else if (firstSelected?.second != secondSelected?.second) {
                    binding.wrongAnswers.text =
                        "${binding.wrongAnswers.text.toString().toInt() + 1}"
                }
                first = first.not()
                if ((adapter1.itemCount == 1 || adapter2.itemCount == 1) && first && firstSelected?.second != secondSelected?.second) {
                    Toast.makeText(
                        requireActivity(), "You win!", Toast.LENGTH_SHORT
                    ).show()
                }
                Log.d("TAG", "setData: ${adapter1.itemCount}, ${adapter2.itemCount}")
            }
        }
    }
}