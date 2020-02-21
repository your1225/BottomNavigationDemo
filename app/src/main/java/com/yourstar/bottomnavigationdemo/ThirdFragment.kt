package com.yourstar.bottomnavigationdemo

import android.animation.ObjectAnimator
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.first_fragment.*
import kotlin.random.Random


class ThirdFragment : Fragment() {

    companion object {
        fun newInstance() = ThirdFragment()
    }

    private lateinit var viewModel: ThirdViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.third_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ThirdViewModel::class.java)

        imageView.x = imageView.x + viewModel.dx

        val objectAnimator = ObjectAnimator.ofObject(imageView, "x", null, 0f, 0f)
        objectAnimator.duration = 500

        imageView.setOnClickListener {
            if (!objectAnimator.isRunning) {
                val dx: Float = if (Random.nextBoolean()) 100f else -100f

                objectAnimator.setFloatValues(imageView.x, imageView.x + dx)
                viewModel.dx += dx
                objectAnimator.start()
            }
        }
    }

}
