package com.aigestudio.wheelpicker.demo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aigestudio.wheelpicker.IWheelPicker;
import com.aigestudio.wheelpicker.WheelPicker;
import com.aigestudio.wheelpicker.widgets.WheelDatePicker;

import java.util.Calendar;

/**
 * @author AigeStudio 2015-12-06
 * @author AigeStudio 2016-07-08
 */
public class PreviewActivity extends Activity implements WheelPicker.OnItemSelectedListener, View.OnClickListener {

    private WheelPicker wheelLeft;
    private WheelPicker wheelCenter;
    private WheelPicker wheelRight;

    private Button gotoBtn;
    private Integer gotoBtnItemIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_preview);

        wheelLeft = (WheelPicker) findViewById(R.id.main_wheel_left);
        wheelLeft.setOnItemSelectedListener(this);
        wheelCenter = (WheelPicker) findViewById(R.id.main_wheel_center);
        wheelCenter.setOnItemSelectedListener(this);
        wheelRight = (WheelPicker) findViewById(R.id.main_wheel_right);
        wheelRight.setOnItemSelectedListener(this);

        gotoBtn = (Button) findViewById(R.id.goto_btn);
        randomlySetGotoBtnIndex();
        gotoBtn.setOnClickListener(this);
    }

    private void initPickerView(IWheelPicker picker) {
        picker.setAtmospheric(true);
        picker.setCurved(false);
        picker.setCyclic(false);
        picker.setIndicator(true);
        picker.setIndicatorColor(Color.parseColor("#cdc1bebe"));
        picker.setIndicatorSize(dp2px(getContext(), 1));
        picker.setItemSpace(dp2px(getContext(), 32));
        picker.setItemTextColor(Color.parseColor("#757575"));
        picker.setItemTextSize(dp2px(getContext(), 18));
        picker.setSelectedItemTextColor(Color.parseColor("#99414141"));
        picker.setVisibleItemCount(5);
    }
    private void initDatePicker(WheelDatePicker datePicker) {
        initPickerView(datePicker);
        datePicker.getTextViewDay().setVisibility(View.GONE);
        datePicker.getTextViewYear().setVisibility(View.GONE);
        datePicker.getTextViewMonth().setVisibility(View.GONE);
//        datePicker.setSelectedYear(1992);
//        datePicker.setSelectedMonth(12);
//        datePicker.setSelectedDay(7);
        datePicker.setYearFrame(1900, Calendar.getInstance().get(Calendar.YEAR));
    }

    private void randomlySetGotoBtnIndex() {
        gotoBtnItemIndex = (int) (Math.random() * wheelCenter.getData().size());
        gotoBtn.setText("Goto '" + wheelCenter.getData().get(gotoBtnItemIndex) + "'");
    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        String text = "";
        switch (picker.getId()) {
            case R.id.main_wheel_left:
                text = "Left:";
                break;
            case R.id.main_wheel_center:
                text = "Center:";
                break;
            case R.id.main_wheel_right:
                text = "Right:";
                break;
        }
        Toast.makeText(this, text + String.valueOf(data), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        wheelCenter.setSelectedItemPosition(gotoBtnItemIndex);
        randomlySetGotoBtnIndex();
    }

}