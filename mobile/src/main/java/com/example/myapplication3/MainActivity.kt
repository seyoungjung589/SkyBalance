package com.yourcompany.skybalance

import android.Manifest
import android.content.Intent // Intent 사용을 위해 필요
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var btnAddFlight: Button
    private lateinit var btnWriteDiary: Button
    private lateinit var tvSelectedDateFlights: TextView
    private lateinit var tvCurrentLocationInfo: TextView
    private lateinit var tvDestinationGuideContent: TextView

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val LOCATION_PERMISSION_REQUEST_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calendarView = findViewById(R.id.calendar_view)
        btnAddFlight = findViewById(R.id.btn_add_flight)
        btnWriteDiary = findViewById(R.id.btn_write_diary)
        tvSelectedDateFlights = findViewById(R.id.tv_selected_date_flights)
        tvCurrentLocationInfo = findViewById(R.id.tv_current_location_info)
        tvDestinationGuideContent = findViewById(R.id.tv_destination_guide_content)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val calendar = Calendar.getInstance()
        calendarView.setDate(calendar.timeInMillis, false, true)


        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, month, dayOfMonth)
            val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault())
            val formattedDate = dateFormat.format(selectedDate.time)
            tvSelectedDateFlights.text = "${formattedDate}의 비행 일정: (이 날짜의 비행 정보를 표시)"
        }

        btnAddFlight.setOnClickListener {
            val intent = Intent(this, AddFlightActivity::class.java)
            startActivity(intent)
        }

        btnWriteDiary.setOnClickListener {
            val intent = Intent(this, DiaryEntryActivity::class.java)
            val selectedDateMillis = calendarView.date
            intent.putExtra("selected_date_millis", selectedDateMillis)
            startActivity(intent)
        }

        checkLocationPermission()
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            getDeviceLocation()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getDeviceLocation()
                } else {
                    Toast.makeText(this, "위치 권한이 거부되어 목적지 정보 기능을 사용할 수 없습니다.", Toast.LENGTH_LONG).show()
                    tvCurrentLocationInfo.text = "현재 위치: (위치 권한 없음)"
                }
            }
        }
    }

    private fun getDeviceLocation() {
        try {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        val latitude = location.latitude
                        val longitude = location.longitude
                        tvCurrentLocationInfo.text = "현재 위치: 위도 ${latitude}, 경도 ${longitude}"
                        Log.d("Location", "현재 위치: 위도 $latitude, 경도 $longitude")

                        getCityNameFromLocation(latitude, longitude)

                        tvDestinationGuideContent.text = "현재 위치(${latitude}, ${longitude}) 기반의 목적지 정보가 여기에 표시됩니다. (맛집, 교통, 숙박, 현지 팁 등)"
                    } else {
                        tvCurrentLocationInfo.text = "현재 위치: (위치 정보를 가져올 수 없습니다.)"
                        Log.w("Location", "위치 정보를 가져올 수 없습니다.")
                    }
                }
                .addOnFailureListener { e ->
                    tvCurrentLocationInfo.text = "현재 위치: (위치 가져오기 오류: ${e.message})"
                    Log.e("Location", "위치 가져오기 오류: ${e.message}")
                }
        } catch (e: SecurityException) {
            Log.e("Location", "위치 권한 없음: ${e.message}")
        }
    }

    private fun getCityNameFromLocation(latitude: Double, longitude: Double) {
        val geocoder = Geocoder(this, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses != null && addresses.isNotEmpty()) {
                val cityName = addresses[0].locality
                val countryName = addresses[0].countryName

                val fullAddress = when {
                    cityName != null -> "$cityName, $countryName"
                    countryName != null -> countryName
                    else -> "알 수 없는 위치"
                }

                tvCurrentLocationInfo.text = "현재 위치: $fullAddress"
                Log.d("Location", "Geocoder 결과: $fullAddress")
                tvDestinationGuideContent.text = "현재 ${fullAddress}에 대한 목적지 정보가 여기에 표시됩니다. (맛집, 교통, 숙박, 현지 팁 등)"

            } else {
                Log.w("Location", "Geocoder: 주소를 찾을 수 없습니다.")
            }
        } catch (e: Exception) {
            Log.e("Location", "Geocoder 오류: ${e.message}")
        }
    }

    private fun loadDestinationGuide(latitude: Double, longitude: Double) {
        tvDestinationGuideContent.text = "현재 위치 (${latitude}, ${longitude})에 대한\n맛집, 교통, 숙박, 현지 팁 등 상세 정보가 로드됩니다."
    }
}
