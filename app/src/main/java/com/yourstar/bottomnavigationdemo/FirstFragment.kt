package com.yourstar.bottomnavigationdemo

import android.animation.ObjectAnimator
import android.animation.TypeConverter
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.first_fragment.*


class FirstFragment : Fragment() {

    companion object {
        fun newInstance() = FirstFragment()
    }

    private lateinit var viewModel: FirstViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //如果用 this 的话，那这个viewModel的范围就是本页面，如果想要在整个应用程序都有效，就设为 requireActivity
//        viewModel = ViewModelProviders.of(requireActivity()).get(FirstViewModel::class.java)
        viewModel = ViewModelProviders.of(this).get(FirstViewModel::class.java)

        imageView.rotation = viewModel.rotationPosition

        var objectAnimator = ObjectAnimator.ofObject(imageView, "rotation", null, 0f, 0f)
        objectAnimator.duration = 500

        imageView.setOnClickListener {
            if (!objectAnimator.isRunning){
                objectAnimator.setFloatValues(imageView.rotation, imageView.rotation + 100)
                viewModel.rotationPosition += 100
                objectAnimator.start()
            }
        }
    }

}
