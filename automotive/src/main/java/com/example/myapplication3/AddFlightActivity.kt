package com.yourcompany.skybalance // ★★★ 여기에 당신의 패키지 이름을 입력하세요 (이전 파일들과 동일하게) ★★★

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddFlightActivity : AppCompatActivity() {

    // UI 요소들을 Kotlin 코드와 연결하기 위한 변수 선언
    private lateinit var etFlightNumber: EditText
    private lateinit var etDepartureAirport: EditText
    private lateinit var etArrivalAirport: EditText
    private lateinit var btnSaveFlight: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_flight) // activity_add_flight.xml 레이아웃과 연결

        // XML 레이아웃의 UI 요소들을 ID를 통해 찾아서 변수에 할당 (초기화)
        etFlightNumber = findViewById(R.id.et_flight_number)
        etDepartureAirport = findViewById(R.id.et_departure_airport)
        etArrivalAirport = findViewById(R.id.et_arrival_airport)
        btnSaveFlight = findViewById(R.id.btn_save_flight)

        // "비행 일정 저장" 버튼 클릭 리스너 설정
        btnSaveFlight.setOnClickListener {
            // 각 EditText에서 사용자 입력 값 가져오기
            val flightNumber = etFlightNumber.text.toString().trim()
            val departureAirport = etDepartureAirport.text.toString().trim()
            val arrivalAirport = etArrivalAirport.text.toString().trim()

            // 입력값 유효성 검사 (모든 필드가 비어있지 않은지 확인하는 간단한 예시)
            if (flightNumber.isEmpty() || departureAirport.isEmpty() || arrivalAirport.isEmpty()) {
                Toast.makeText(this, "모든 비행 정보를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                // TODO: 여기에 실제 비행 정보를 데이터베이스에 저장하는 로직을 구현합니다.
                // (이 예제에서는 실제 저장은 구현되어 있지 않습니다. 단순히 토스트 메시지만 띄웁니다.)

                Toast.makeText(this, "비행 일정 '${flightNumber}'이 성공적으로 저장되었습니다!", Toast.LENGTH_LONG).show()

                // 현재 Activity를 종료하고 이전 Activity (MainActivity)로 돌아갑니다.
                finish()
            }
        }
    }
}
