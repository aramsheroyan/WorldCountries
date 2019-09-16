package com.aramsheroyan.worldcountries.ui.about

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.aramsheroyan.worldcountries.BuildConfig

import com.aramsheroyan.worldcountries.R
import kotlinx.android.synthetic.main.about_fragment.*

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.hide()
        return inflater.inflate(R.layout.about_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        githubImageView.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("https://github.com/aramsheroyan/WorldCountries")
            startActivity(intent)
        }

        versionTextView.text = "Version: ${BuildConfig.VERSION_NAME}"
    }

}
