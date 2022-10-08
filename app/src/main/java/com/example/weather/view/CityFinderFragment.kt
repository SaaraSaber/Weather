package com.example.weather.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.CITY_ID
import com.example.weather.R
import com.example.weather.adapter.CityFinderAdapter
import com.example.weather.databinding.FragmentCityFinderBinding
import com.example.weather.viewModel.CityFinderViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CityFinderFragment : Fragment(), CityFinderAdapter.CityEventListener {
    private lateinit var binding: FragmentCityFinderBinding
    private val viewModel: CityFinderViewModel by viewModel()
    val adapter = CityFinderAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCityFinderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SharedPreference()

        binding.searchBtn.setOnClickListener {
            if (binding.inputCityNameEd.text.isNullOrEmpty())
                Toast.makeText(requireContext(), R.string.empty, Toast.LENGTH_SHORT)
                    .show()
            else {
                initRecyclerView()
                val cityName = binding.inputCityNameEd.text.toString().trim()
                viewModel.cityFinder(requireActivity(), cityName)

                viewModel.liveData.observe(viewLifecycleOwner) {

                    Log.i("TAG", "onViewCreated: " + it.toString())
                    Toast.makeText(requireContext(), R.string.success, Toast.LENGTH_SHORT).show()
                    adapter.cityLists = it
                    adapter.cityEventListener = this
                }
            }
        }

    }

    private var cityId: String? = null
    private fun SharedPreference() {
        viewModel.readCityIdFromSharedPreference()
        viewModel.cityId.observe(viewLifecycleOwner) {
            if (it == "0")
                return@observe
            cityId = it

        }
    }

    private fun initRecyclerView() {
        binding.resultRv.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.resultRv.adapter = adapter
    }

    override fun onClickCityListener(cityId: String) {
        view?.let {
            val bundle = Bundle()
            bundle.putString(CITY_ID, cityId)
            findNavController()
                .navigate(R.id.action_cityFinderFragment_to_weatherDetailFragment, bundle)
        }
    }


    override fun onSaveCityId(cityId: String) {
        viewModel.saveCityIdFromSharedPreference(cityId)
    }

}