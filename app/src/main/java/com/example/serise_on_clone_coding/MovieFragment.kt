package com.example.serise_on_clone_coding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.serise_on_clone_coding.Data.MovieAdapter
import com.example.serise_on_clone_coding.Data.MovieController
import com.example.serise_on_clone_coding.databinding.FragmentMovieBinding

class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater)

        val mainScrollView = binding.customScrollView
        val stickyLayout = binding.llUpperTapBar

        mainScrollView.run {
            header = stickyLayout
            stickListener = { _ ->
                Log.d("LOGGER_TAG", "stickListener")
            }
            freeListener = { _ ->
                Log.d("LOGGER_TAG", "freeListener")
            }
            initialPositionListener = {
                Log.d("LOGGER_TAG", "initial position")
                val transparentColor = resources.getColor(R.color.transparent)
                stickyLayout.setBackgroundColor(transparentColor)
            }
            isStartedScroll = {
                Log.d("LOGGER_TAG", "Started Scroll")
                stickyLayout.setBackgroundResource(R.drawable.final_bg_tp)
            }
            isMovieListListener = {
                Log.d("LOGGER_TAG", "Movie List")
                stickyLayout.setBackgroundResource(R.drawable.final_bg)
            }
            isNotMovieListListener = {
                Log.d("LOGGER_TAG", "NOT Movie List")
                stickyLayout.setBackgroundResource(R.drawable.final_bg_tp)
            }
        }

        // 리사이클러뷰 사용 코드
        val movies = MovieController.getMovies().toMutableList()
        val adapter = MovieAdapter(movies)

        adapter.itemClick = object : MovieAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(activity, DetailActivity::class.java).apply {
                    putExtra("title", movies[position].title)
                }
                startActivity(intent)
            }
        }
        binding.rcvHot.adapter = adapter
        binding.rcvHot.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        binding.rcvTrend.adapter = adapter
        binding.rcvTrend.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        binding.rcvTop.adapter = adapter
        binding.rcvTop.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        binding.rcvUpdated.adapter = adapter
        binding.rcvUpdated.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        return binding.root
    }
}