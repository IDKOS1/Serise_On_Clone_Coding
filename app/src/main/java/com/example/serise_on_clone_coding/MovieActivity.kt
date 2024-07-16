package com.example.serise_on_clone_coding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.serise_on_clone_coding.Data.MovieAdapter
import com.example.serise_on_clone_coding.databinding.MoviePageBinding
import com.example.serise_on_clone_coding.Data.Movies
import java.util.Collections

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: MoviePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MoviePageBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

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
        val adapter = MovieAdapter(Movies.movieList.values.toMutableList())
        binding.rcvHot.adapter = adapter
        binding.rcvHot.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        binding.rcvTrend.adapter = adapter
        binding.rcvTrend.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        binding.rcvTop.adapter = adapter
        binding.rcvTop.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        binding.rcvUpdated.adapter = adapter
        binding.rcvUpdated.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // 레이아웃 인플레이터 사용 코드
        /*
        // HOT & NEW 영화 리스트 생성
        //val hotMovie = findViewById<LinearLayout>(R.id.ll_hotMovie)
        //makeMovieList(hotMovie, false)

        // 실시간 트렌드  영화 리스트 생성
        val trendMovie = findViewById<LinearLayout>(R.id.ll_trendMovie)
        makeMovieList(trendMovie, false)

        val topMovie = findViewById<LinearLayout>(R.id.ll_top_movie)
        makeMovieList(topMovie, true)

        val updatedMovie = findViewById<LinearLayout>(R.id.ll_updated_movie)
        makeMovieList(updatedMovie, false)
        */

    }

    private fun makeMovieList(movieLayout: LinearLayout, isTopMovie: Boolean) {
        var style = R.layout.poster_list

        // 영화 객체 리스트화
        val movieList = Movies.movieList.values.toList()
        // 리스트 순서 섞기
        Collections.shuffle(movieList)
        //TopMovie면 전체 가져오기, 아닐 시 8개만 가져오기
        val randomMovies = if (isTopMovie) movieList else movieList.take(8)
        if (isTopMovie) {
            style = R.layout.top_list
        }

        for (movie in randomMovies) {
            val layout =
                layoutInflater.inflate(style, movieLayout, false)

            val imageView = layout.findViewById<ImageView>(R.id.poster!!)
            val textView = layout.findViewById<TextView>(R.id.title!!)

            imageView.setImageResource(movie.imgSrc)
            textView.text = movie.title

            layout.setOnClickListener {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("title", movie.title)
                startActivity(intent)
            }
            movieLayout.addView(layout)
        }
    }

}