package com.example.serise_on_clone_coding

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Collections

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 뒤로가기 버튼 설정
        val closeButton = findViewById<ImageView>(R.id.iv_close)
        closeButton.setOnClickListener {
            finish()
        }


        // 영화 제목, 제목으로 영화 객체 찾기
        val movieTitle = intent.getStringExtra("title")
        val movie = Movies.movieList[movieTitle]


        // 랜덤 영화 포스터 이미지 트레일러 이미지로 설정
        val movieList = Movies.movieList.values.toList()

        Collections.shuffle(movieList)
        val randomMovies = movieList.take(3)

        val trailerImages = listOf(
            findViewById<ImageView>(R.id.iv_trailer_2),
            findViewById<ImageView>(R.id.iv_trailer_3),
            findViewById<ImageView>(R.id.iv_trailer_4)
        )

        for ((index, image) in randomMovies.take(3).withIndex()) {
            val trailerResource = resources.getIdentifier(image.imgSrc, "drawable", packageName)
            trailerImages[index].setImageResource(trailerResource)
        }


        // 또 다른 추천영화 생성
        var style = R.layout.poster_list
        val recommendMovie = findViewById<LinearLayout>(R.id.ll_recommend_movie)
        for (recommendMovieItem in movieList.take(8)) {
            val layout =
                layoutInflater.inflate(style, recommendMovie, false)

            val imageView = layout.findViewById<ImageView>(R.id.poster!!)
            val resourceId = resources.getIdentifier(recommendMovieItem.imgSrc, "drawable", packageName)
            val textView = layout.findViewById<TextView>(R.id.title!!)

            imageView.setImageResource(resourceId)
            textView.text = recommendMovieItem.title

            layout.setOnClickListener {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("title", recommendMovieItem.title)
                startActivity(intent)
            }
            recommendMovie.addView(layout)
        }


        // 줄거리 더보기 클릭시 전체 줄거리 출력 및 아이콘 상하 반전
        val plotMore = findViewById<ImageView>(R.id.iv_plot_more)
        plotMore.setOnClickListener {
            // 줄거리 TextView
            val plot = findViewById<TextView>(R.id.tv_plot)
            if (plot.maxLines == 2) {
                plot.maxLines = Int.MAX_VALUE
                plotMore.rotationX = 180.0F
            } else {
                plot.maxLines = 2
                plotMore.rotationX = 0.0F
            }
        }

        // 상품 정보 더보기 클릭 시 전체 상품 정보 출력
        val infoMore = findViewById<ImageView>(R.id.iv_info_more)
        infoMore.setOnClickListener{
            val productInfo = findViewById<LinearLayout>(R.id.ll_product_info)
            val imageQuality = findViewById<LinearLayout>(R.id.ll_image_quality)
            val useInfo = findViewById<LinearLayout>(R.id.ll_use_info)
            val refundInfo = findViewById<LinearLayout>(R.id.ll_refund_info)

            if (productInfo.visibility == LinearLayout.GONE) {
                productInfo.visibility = LinearLayout.VISIBLE
                imageQuality.visibility = LinearLayout.VISIBLE
                useInfo.visibility = LinearLayout.VISIBLE
                refundInfo.visibility = LinearLayout.VISIBLE
                infoMore.rotationX = 180.0F
            } else {
                productInfo.visibility = LinearLayout.GONE
                imageQuality.visibility = LinearLayout.GONE
                useInfo.visibility = LinearLayout.GONE
                refundInfo.visibility = LinearLayout.GONE
                infoMore.rotationX = 0.0F
            }
        }


        if (movie != null) {
            // 미리보기 이미지 설정
            val trailerImage = findViewById<ImageView>(R.id.iv_trailer)
            val trailerResource = resources.getIdentifier(movie.imgSrc, "drawable", packageName)
            trailerImage.setImageResource(trailerResource)

            // 미리보기 이미지 클릭 시 유튜브 검색 링크로 이동
            val url = "https://www.youtube.com/results?search_query=${movieTitle}"
            trailerImage.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }

            // 트레일러1 메인 포스터 이미지 설정
            val trailer1 = findViewById<ImageView>(R.id.iv_trailer_1)
            trailer1.setImageResource(trailerResource)

            // 타이틀 설정
            val titleTextView = findViewById<TextView>(R.id.tv_title)
            titleTextView.text = movie.title

            // 별점 설정
            val rate = findViewById<TextView>(R.id.tv_rate)
            rate.text = movie.rating.toString()

            // 개봉 년도 설정
            val releaseDate = findViewById<TextView>(R.id.tv_release_date)
            releaseDate.text = movie.releaseDate.toString()

            // 상영 시간 설정
            val runningTime = findViewById<TextView>(R.id.tv_running_time)
            runningTime.text = buildString {
                append(movie.runningTime)
                append("분")
            }

            // 관람 등급
            val ageRating = findViewById<TextView>(R.id.tv_age)
            // movie.ageRating 가 0 이면 전체
            val age =
                if (movie.ageRating == 0) {
                    "전체"
                } else {
                    movie.ageRating.toString() + "세"
                }
            ageRating.text = buildString {
                append(age)
                append(" 이용가")
            }

            // 감독 설정
            val director = findViewById<TextView>(R.id.tv_director)
            director.text = movie.director

            // 배우 설정
            val actors = findViewById<TextView>(R.id.tv_lead_actor)
            actors.text = movie.leadActor

            // 줄거리 설정
            val plot = findViewById<TextView>(R.id.tv_plot)
            plot.text = movie.plot
        }
    }
}