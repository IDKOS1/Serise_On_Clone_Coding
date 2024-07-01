package com.example.serise_on_clone_coding

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MovieActivity : AppCompatActivity() {
    private lateinit var movieList: MutableList<Movie>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.movie_page)

        initMovieList()

        // HOT & NEW 영화 리스트 생성
        val hotMovie = findViewById<LinearLayout>(R.id.ll_hotMovie)
        makeMovieList(hotMovie, false)

        // 실시간 트렌드  영화 리스트 생성
        val trendMovie = findViewById<LinearLayout>(R.id.ll_trendMovie)
        makeMovieList(trendMovie, false)

        val topMovie = findViewById<LinearLayout>(R.id.ll_top_movie)
        makeMovieList(topMovie, true)
    }

    fun makeMovieList(movieLayout: LinearLayout, isTopMovie: Boolean) {

        var style = R.layout.poster_list
        if(isTopMovie) style = R.layout.top_list
        for (movie in movieList) {
            val constraintLayout =
                layoutInflater.inflate(style, movieLayout, false)

            val imageView = constraintLayout.findViewById<ImageView>(R.id.poster!!)
            val resourceId = resources.getIdentifier(movie.imgSrc, "drawable",packageName)
            val textView = constraintLayout.findViewById<TextView>(R.id.title!!)

            imageView.setImageResource(resourceId)
            textView.text = movie.title

            movieLayout.addView(constraintLayout)
        }
    }

    fun initMovieList() {
        movieList = mutableListOf(
            Movie(
                "퍼스트맨",
                "mv_firstman",
                7.85,
                2018,
                141,
                12,
                "데이미언 셔젤",
                "라이언 고슬링, 클레어 포이, 제이슨 클락, 카일 챈들러, 패트릭 후짓, 코리 스톨, 시아란 힌즈, 루카스 하스, 크리스토퍼 애봇, 에단 엠브리",
                "이제껏 누구도 경험하지 못한 세계에 도전한 우주비행사 닐(라이언 고슬링)은, 거대한 위험 속에서 극한의 위기를 체험하게 된다. 전세계가 바라보는 가운데, 그는 새로운 세상을 열 첫 발걸음을 내딛는데… 이제, 세계는 달라질 것이다."
            ),
            Movie(
                "인사이드 아웃 2",
                "mv_insideout",
                9.02,
                2024,
                96,
                0,
                "켈시 맨",
                "에이미 포엘러, 마야 호크, 루이스 블랙, 필리스 스미스, 토니 헤일",
                "디즈니·픽사의 대표작 <인사이드 아웃> 새로운 감정과 함께 돌아오다! 13살이 된 라일리의 행복을 위해 매일 바쁘게 머릿속 감정 컨트롤 본부를 운영하는 ‘기쁨’, ‘슬픔’, ‘버럭’, ‘까칠’, ‘소심’. 그러던 어느 날, 낯선 감정인 ‘불안’, ‘당황’, ‘따분’, ‘부럽’이가 본부에 등장하고, 언제나 최악의 상황을 대비하며 제멋대로인 ‘불안’이와 기존 감정들은 계속 충돌한다. 결국 새로운 감정들에 의해 본부에서 쫓겨나게 된 기존 감정들은 다시 본부로 돌아가기 위해 위험천만한 모험을 시작하는데… 2024년, 전 세계를 공감으로 물들인 유쾌한 상상이 다시 시작된다!"
            ),
            Movie(
                "퓨리오사: 매드맥스 사가",
                "mv_furiosa",
                8.93,
                2024,
                148,
                15,
                "조지 밀러",
                "안야 테일러 조이, 크리스 헴스워스 , 톰 버크",
                "문명 붕괴 45년 후, 황폐해진 세상 속 누구에게도 알려지지 않은 풍요가 가득한 ‘녹색의 땅’에서 자란 ‘퓨리오사’(안야 테일러-조이)는 바이커 군단의 폭군 ‘디멘투스’(크리스 헴스워스)의 손에 모든 것을 잃고 만다. 가족도 행복도 모두 빼앗기고 세상에 홀로 내던져진 ‘퓨리오사’는 반드시 고향으로 돌아가겠다는 어머니와의 약속을 지키기 위해 인생 전부를 건 복수를 시작하는데... ‘매드맥스’ 시리즈의 전설적인 사령관 ‘퓨리오사’의 대서사시 마침내 분노가 깨어난다!"
            ),
            Movie(
                "어바웃 타임",
                "mv_about_time",
                9.26,
                2013,
                123,
                15,
                "리차드 커티스",
                "도널 글리슨, 레이첼 맥아담스",
                "모태솔로 팀(돔놀 글리슨)은 성인이 된 날, 아버지(빌 나이)로부터 놀랄만한 가문의 비밀을 듣게 된다. 바로 시간을 되돌릴 수 있는 능력이 있다는 것! 그것이 비록 히틀러를 죽이거나 여신과 뜨거운 사랑을 할 수는 없지만, 여자친구는 만들어 줄 순 있으리.. 꿈을 위해 런던으로 간 팀은 우연히 만난 사랑스러운 여인 메리에게 첫눈에 반하게 된다. 그녀의 사랑을 얻기 위해 자신의 특별한 능력을 마음껏 발휘하는 팀. 어설픈 대시, 어색한 웃음은 리와인드! 뜨거웠던 밤은 더욱 뜨겁게 리플레이! 꿈에 그리던 그녀와 매일매일 최고의 순간을 보낸다. 하지만 그와 그녀의 사랑이 완벽해질수록 팀을 둘러싼 주변 상황들은 미묘하게 엇갈리고, 예상치 못한 사건들이 여기저기 나타나기 시작하는데… 어떠한 순간을 다시 살게 된다면, 과연 완벽한 사랑을 이룰 수 있을까?"
            ),
            Movie(
                "라라랜드",
                "mv_lalaland",
                8.92,
                2016,
                127,
                12,
                "데이미언 셔젤",
                "라이언 고슬링, 엠마 스톤",
                "꿈을 꾸는 사람들을 위한 별들의 도시 ‘라라랜드’. 재즈 피아니스트 ‘세바스찬’(라이언 고슬링)과 배우 지망생 ‘미아’(엠마 스톤), 인생에서 가장 빛나는 순간 만난 두 사람은 미완성인 서로의 무대를 만들어가기 시작한다."
            ),
            Movie(
                "타짜",
                "mv_tazza",
                9.24,
                2006,
                139,
                19,
                "최동훈",
                "조승우, 김혜수, 백윤식, 유해진",
                "큰거 한 판에 인생은 예술이 된다! 목숨을 걸 수 없다면, 배팅 하지마라!\n 인생을 건 한판 승부  낯선 자를 조심해라..!"
            ),
            Movie(
                "친절한 금자씨",
                "mv_lady_vengeance",
                7.59,
                2005,
                112,
                19,
                "박찬욱",
                "이영애, 최민식",
                "주변 사람들의 시선을 단번에 사로잡을 만큼 뛰어난 미모의 소유자인 '금자'(이영애)는 스무 살에 죄를 짓고 감옥에 가게 된다. 어린 나이, 너무나 아름다운 외모로 인해 검거되는 순간에도 언론에 유명세를 치른다. 13년 동안 교도소에 복역하면서 누구보다 성실하고 모범적인 수감생활을 보내는 금자. '친절한 금자씨'라는 말도 교도소에서마저 유명세를 떨치던 그녀에게 사람들이 붙여준 별명이다. 그녀는 자신의 주변 사람들을 한 명, 한 명, 열심히 도와주며 13년간의 복역생활을 무사히 마친다. 출소하는 순간, 금자는 그 동안 자신이 치밀하게 준비해온 복수 계획을 펼쳐 보인다. 그녀가 복수하려는 인물은 자신을 죄인으로 만든 백선생(최민식). 교도소 생활 동안 그녀가 친절을 베풀며 도왔던 동료들은 이제 다양한 방법으로 금자의 복수를 돕는다. 이금자와 백선생. 과연 13년 전 둘 사이에는 무슨 일이 있었고, 복수하려는 이유는 무엇일까. 그리고 이 복수의 끝은 어떻게 될 것인가."
            ),
            Movie(
                "위플래쉬",
                "mv_whiplash",
                8.88,
                2015,
                106,
                106,
                "데이미언 셔젤",
                "마일즈 텔러, J.K. 시몬스",
                "뉴욕의 명문 셰이퍼 음악학교에서 최고의 스튜디오 밴드에 들어가게 된 신입생 '앤드류' 최고의 지휘자이지만 동시에 최악의 폭군인 '플레쳐'교수는 폭언과 학대로 '앤드류'를 한계까지 몰아붙이고 또 몰아붙인다. 드럼 주위로 뚝뚝 떨어지는 피, 빠르게 달리는 선율 뒤로 아득해지는 의식, 그 순간, 드럼에 대한 앤드류의 집착과 광기가 폭발한다. 최고의 연주를 위한 완벽한 스윙이 시작된다!"
            ),
            Movie(
                "바빌론",
                "mv_babylon",
                8.4,
                2023,
                189,
                19,
                "데이미언 셔젤",
                "브레드 피트, 마고 로비, 디에고 칼바",
                "황홀하면서도 위태로운 고대 도시, '바빌론'에 비유되던 할리우드. '꿈' 하나만을 위해 모인 사람들이 이를 쟁취하기 위해 벌이는 강렬하면서도 매혹적인 이야기"
            ),
            Movie(
                "중경삼림",
                "mv_chungking_express",
                9.03,
                1995,
                102,
                15,
                "왕가위",
                "임청하, 양조위, 왕페이, 금성무",
                "1994년 홍콩, “내 사랑의 유통기한은 만 년으로 하고 싶다” 만우절의 이별 통보가 거짓말이길 바라며 술집을 찾은 경찰 223 고단한 하루를 보내고 술집에 들어온 금발머리의 마약밀매상 \"그녀가 떠난 후 이 방의 모든 것들이 슬퍼한다\" 여자친구가 남긴 이별 편지를 외면하고 있는 경찰 663 편지 속에 담긴 그의 아파트 열쇠를 손에 쥔 단골집 점원 페이 네 사람이 만들어낸 두 개의 로맨스 새로운 사랑을 만나는 방법에 대한 독특한 상상력"
            )
            // 미드나잇 인 파리, 그랜드 부다페스트 호텔, 곡성, 클레멘타인, 7광구, 디 워, 타이타닉, 미드웨이, 슈렉,
        )
    }
}