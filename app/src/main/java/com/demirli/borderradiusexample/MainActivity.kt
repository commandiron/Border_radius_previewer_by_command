package com.demirli.borderradiusexample

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var cardView: MaterialCardView

    private lateinit var etList: List<EditText>

    private lateinit var sbList: List<SeekBar>

    private var progressTopLeft = 0f
    private var progressTopRight = 0f
    private var progressBottomLeft= 0f
    private var progressBottomRight= 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardView = findViewById(R.id.rounded_cardview)

        etList = listOf(top_left_et,top_right_et,bottom_left_et,bottom_right_et)
        radiusTextChanged(etList)

        sbList = listOf(top_left_sb,top_right_sb,bottom_left_sb,bottom_right_sb)
        radiusSeekBarChanged(sbList)
    }

    fun radiusSeekBarChanged(sbList: List<SeekBar>){

        sbList.forEach { it.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                when(seekBar?.id){
                    top_left_sb.id -> {progressTopLeft = progress.toFloat()
                    setRadiusValuesWithSeekBar(progressTopLeft,progressTopRight,progressBottomLeft,progressBottomRight)}
                    top_right_sb.id -> {progressTopRight = progress.toFloat()
                        setRadiusValuesWithSeekBar(progressTopLeft,progressTopRight,progressBottomLeft,progressBottomRight)}
                    bottom_left_sb.id -> {progressBottomLeft = progress.toFloat()
                        setRadiusValuesWithSeekBar(progressTopLeft,progressTopRight,progressBottomLeft,progressBottomRight)}
                    bottom_right_sb.id -> {progressBottomRight = progress.toFloat()
                        setRadiusValuesWithSeekBar(progressTopLeft,progressTopRight,progressBottomLeft,progressBottomRight)}
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        }) }
    }

    fun setRadiusValuesWithSeekBar(progressTopLeft: Float,progressTopRight: Float,progressBottomLeft: Float,progressBottomRight: Float){

        val shapeAppearanceModel = ShapeAppearanceModel.builder()
            .setTopLeftCorner(CornerFamily.ROUNDED,progressTopLeft*3)
            .setTopRightCorner(CornerFamily.ROUNDED,progressTopRight*3)
            .setBottomLeftCorner(CornerFamily.ROUNDED,progressBottomLeft*3)
            .setBottomRightCorner(CornerFamily.ROUNDED,progressBottomRight*3).build()

        val shape = MaterialShapeDrawable(shapeAppearanceModel)
        ViewCompat.setBackground(cardView,shape)
    }


    fun radiusTextChanged(etList: List<EditText>){

        etList.forEach { it.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(!s!!.isEmpty()){
                    setRadiusValuesWithText()
                }
            }
        })}
    }

    fun setRadiusValuesWithText(){

        val shapeAppearanceModel = ShapeAppearanceModel.builder().
            setTopLeftCorner(CornerFamily.ROUNDED,etList[0].text.toString().toFloat())
            .setTopRightCorner(CornerFamily.ROUNDED,etList[1].text.toString().toFloat())
            .setBottomLeftCorner(CornerFamily.ROUNDED,etList[2].text.toString().toFloat())
            .setBottomRightCorner(CornerFamily.ROUNDED,etList[3].text.toString().toFloat()).build()

        val shape = MaterialShapeDrawable(shapeAppearanceModel)
        ViewCompat.setBackground(cardView,shape)
    }
}
