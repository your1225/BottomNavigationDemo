package com.yourstar.bottomnavigationdemo

import android.animation.ObjectAnimator
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.first_fragment.*


class SecondFragment : Fragment() {

    companion object {
        fun newInstance() = SecondFragment()
    }

    private lateinit var viewModel: SecondViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.second_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SecondViewModel::class.java)

        imageView.scaleX = viewModel.scaleFactor
        imageView.scaleY = viewModel.scaleFactor

        var objectAnimatorX = ObjectAnimator.ofObject(imageView, "scaleX", null, 0f, 0f)
        var objectAnimatorY = ObjectAnimator.ofObject(imageView, "scaleY", null, 0f, 0f)
        objectAnimatorX.duration = 500
        objectAnimatorY.duration = 500

        imageView.setOnClickListener {
            if (!objectAnimatorX.isRunning){
                objectAnimatorX.setFloatValues(imageView.scaleX + 0.1f)
                objectAnimatorY.setFloatValues(imageView.scaleY + 0.1f)
                viewModel.scaleFactor += 0.1f
                objectAnimatorX.start()
                objectAnimatorY.start()
            }
        }
    }

}
