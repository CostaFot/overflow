package com.feelsokman.androidtemplate.ui.fragments.host

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.feelsokman.androidtemplate.databinding.FragmentHostBinding
import com.feelsokman.androidtemplate.di.component.AppComponent
import com.feelsokman.androidtemplate.di.getComponent
import com.feelsokman.androidtemplate.overflow.AdapterHuman
import com.feelsokman.androidtemplate.ui.base.BaseFragment
import com.feelsokman.androidtemplate.ui.fragments.host.viewmodel.ViewModelDude
import com.feelsokman.androidtemplate.utilities.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_host.*
import javax.inject.Inject

class FragmentDude : BaseFragment() {

    @Inject internal lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<ViewModelDude> { viewModelFactory }
    private var _binding: FragmentHostBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        injectDependencies(context)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHostBinding.inflate(inflater, container, false)
        return binding.root
    }

    lateinit var adapterHuman: AdapterHuman

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterHuman = AdapterHuman(emptyList())
        recyclerview.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        recyclerview.adapter = adapterHuman

        viewModel.humanData.observe(viewLifecycleOwner) {
            adapterHuman.items = it
            adapterHuman.notifyDataSetChanged()
        }

        button.setOnClickListener {
            viewModel.startFlow()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun injectDependencies(context: Context) {
        (context as AppCompatActivity).application.getComponent<AppComponent>().inject(this)
    }
}
