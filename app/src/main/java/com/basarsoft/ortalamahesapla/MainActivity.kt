package com.basarsoft.ortalamahesapla

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.new_lesson_layout.*
import kotlinx.android.synthetic.main.new_lesson_layout.view.*

class MainActivity : AppCompatActivity() {

    private val LESSONS = arrayOf("Maths", "Physic", "English", "Alghorithm", "History")
    private var allLessonInformation:ArrayList<Lessons> = ArrayList(5)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, LESSONS)
// otomatik şekide edit texti doldurmak
        etLesson.setAdapter(adapter)

// eğer dersler listesi boş ise hesapla butonu saklanır, değilse görünür yapılır
        if (rootLayout.childCount == 0) {
            btnCalculate.visibility = View.INVISIBLE

        } else btnCalculate.visibility = View.VISIBLE




        btnAdd.setOnClickListener {

            if (!etLesson.text.isNullOrEmpty()) {

                // xml kodlarını java kodlarına çeviren yapıdır inflaterler
                var inflater = LayoutInflater.from(this)

                /*var inflater2 = layoutInflater
                    var inflater3 = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    inflater3.inflate()*/

                var newLessonView = inflater.inflate(R.layout.new_lesson_layout, null)
// otomatik şekilde edit text i doldurma
                newLessonView.etNewLesson.setAdapter(adapter)

                // statik alandan kullanıcının girdiği verileri alma

                var lessonName = etLesson.text.toString()
                var lessonCredit = spnLessonCredit.selectedItem.toString()
                var lessonGrade = spnLessonGrade.selectedItem.toString()


                //dinamik oluşturulacak layoutta bulunan view öğelerine aşağıdaki değerler atanır
                //böylece yeni layoutumuz kullanıcının girdiği değerler ile oluşturulmuş olacaktır


                newLessonView.etNewLesson.setText(lessonName)
                newLessonView.spnNewLessonCredit.setSelection(
                    getToSpinnerValue(
                        spnLessonCredit,
                        lessonCredit
                    )
                )
                newLessonView.spnLessonNewGrade.setSelection(
                    getToSpinnerValue(
                        spnLessonGrade,
                        lessonGrade
                    )
                )

                newLessonView.btnDelete.setOnClickListener {
                    rootLayout.removeView(newLessonView)

                    if (rootLayout.childCount == 0) {
                        btnCalculate.visibility = View.INVISIBLE

                    } else btnCalculate.visibility = View.VISIBLE

                }

                rootLayout.addView(newLessonView)

                btnCalculate.visibility = View.VISIBLE

                sifirla()
            } else {

                Toast.makeText(this, "PLEASE ENTER THE LESSON NAME", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun sifirla() {
        etLesson.setText("")
        spnLessonCredit.setSelection(0)
        spnLessonGrade.setSelection(0)
    }



    // bu fonksiyon ile spinnerda direkt olarak alamadığımız değerlerin indekslerini bularak bu indekslere göre alıyoruz.
    // çünkü spinnerlara direkt olarak string değer atanamaz
    fun getToSpinnerValue(spinner: Spinner, searchValue: String): Int {

        var index = 0
        for (i in 0..spinner.count) {
            if (spinner.getItemAtPosition(i).toString().equals(searchValue)) {
                index = i
                break

            }
        }
        return index
    }

    fun ortalamaHesapla(view: View) {


        var totalGrade:Double = 0.0
        var totalCredit:Double = 0.0

        for (i in 0..rootLayout.childCount-1){

            var tekSatir = rootLayout.getChildAt(i)

            var geciciDers = Lessons(tekSatir.etNewLesson.text.toString(),
                ((tekSatir.spnNewLessonCredit.selectedItemPosition)+1).toString(),
                tekSatir.spnLessonNewGrade.selectedItem.toString())

            allLessonInformation.add(geciciDers)
        }
        for (oankiDers in allLessonInformation){

            totalGrade+=harfiNotaCevir(oankiDers.lessonGrade) * (oankiDers.lessonCredit.toDouble())
            totalCredit+=oankiDers.lessonCredit.toDouble()

        }

        Toast.makeText(this,"Ortalama : " + (totalGrade/totalCredit),Toast.LENGTH_LONG).show()
        allLessonInformation.clear()
    }

    fun harfiNotaCevir(gelenNotHarfDegeri:String) : Double{

        var deger = 0.0

        when(gelenNotHarfDegeri){
            "AA" -> deger = 4.0
            "BA" -> deger = 3.5
            "BB" -> deger = 3.0
            "CB" -> deger = 2.5
            "CC" -> deger = 2.0
            "DC" -> deger = 1.5
            "DD" -> deger = 1.0
            "FF" -> deger = 0.0
        }
        return deger

    }

}