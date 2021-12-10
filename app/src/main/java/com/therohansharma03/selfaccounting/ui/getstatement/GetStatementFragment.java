package com.therohansharma03.selfaccounting.ui.getstatement;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.therohansharma03.selfaccounting.R;
import com.therohansharma03.selfaccounting.StatementByCommodity;
import com.therohansharma03.selfaccounting.StatementByDate;
import com.therohansharma03.selfaccounting.StatementByFirstName;
import com.therohansharma03.selfaccounting.StatementByUniqueID;
import com.therohansharma03.selfaccounting.StatementGetAll;
import com.therohansharma03.selfaccounting.allResponses.Seller;

import java.util.Calendar;
import java.util.List;

public class GetStatementFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    Intent i;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_get_statement, container, false);

        Button GetQueryWiseData = root.findViewById(R.id.GS_Statement_GetQueryWiseData);
        Switch GetAllSellerData = root.findViewById(R.id.GS_Statement_Btn_GetAll);
        EditText UniqueID = root.findViewById(R.id.GS_Statement_By_Unique_ID);
        EditText FirstName = root.findViewById(R.id.GS_Statement_By_FirstName);
        EditText Commodity = root.findViewById(R.id.GS_Statement_By_Commodity);
        EditText FromDate = root.findViewById(R.id.GS_Statement_By_FromDate);
        EditText ToDate = root.findViewById(R.id.GS_Statement_By_ToDate);
        EditText LastThreeMonths = root.findViewById(R.id.GS_Statement_By_LastThreeMonths);


        Calendar calendar = Calendar.getInstance();
        final int Year=calendar.get(calendar.YEAR);
        final int month =calendar.get(calendar.MONTH);
        final int dayofmonth =calendar.get(calendar.DAY_OF_MONTH);


        FromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int Year, int month, int dayofmonth) {
                        month+=1;
                        String date = Year+"-"+month+"-"+dayofmonth;
                        FromDate.setText(date);
                    }
                },Year,month,dayofmonth);
                datePickerDialog.show();
            }
        });

        ToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int Year, int month, int dayofmonth) {
                        month+=1;
                        String date = Year+"-"+month+"-"+dayofmonth;
                        ToDate.setText(date);
                    }
                },Year,month,dayofmonth);
                datePickerDialog.show();
            }
        });

        GetAllSellerData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GetAllSellerData.isChecked() == true) {
                    Intent i = new Intent(getActivity(), StatementGetAll.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getContext(), "Get All off !", Toast.LENGTH_SHORT).show();
                }

            }
        });

        GetQueryWiseData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (UniqueID.getText().toString().isEmpty() && FirstName.getText().toString().isEmpty() && Commodity.getText().toString().isEmpty() && FromDate.getText().toString().isEmpty() && ToDate.getText().toString().isEmpty() && LastThreeMonths.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill any One of the field to proceed !", Toast.LENGTH_SHORT).show();
                }  else {
                    if (!UniqueID.getText().toString().isEmpty()) {
                        Intent i = new Intent(getActivity(), StatementByUniqueID.class);
                        i.putExtra("UniqueID", UniqueID.getText().toString());
                        startActivity(i);
                    }
                    if (!FirstName.getText().toString().isEmpty()) {
                        Intent i = new Intent(getActivity(), StatementByFirstName.class);
                        i.putExtra("FirstName", FirstName.getText().toString());
                        startActivity(i);
                    }
                    if (!Commodity.getText().toString().isEmpty()) {
                        Intent i = new Intent(getActivity(), StatementByCommodity.class);
                        i.putExtra("Commodity", Commodity.getText().toString());
                        startActivity(i);
                    }
                    if (!(FromDate.getText().toString().isEmpty() && ToDate.getText().toString().isEmpty())) {
                        Intent i = new Intent(getActivity(), StatementByDate.class);
                        i.putExtra("Fromdate", FromDate.getText().toString());
                        i.putExtra("Todate", ToDate.getText().toString());
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(getActivity(), "Please fill any One of the field to proceed !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return root;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }

//    private void ShowDatePickerDialog() {
//        DatePickerDialog datePickerDialog = new DatePickerDialog(
//                getContext(),
//                new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                        i1 += 1;
//                        if (i1 < 10 && i2 < 10) {
//                            month = "0" + i1;
//                            dayofmonth = "0" + i2;
//                            date = String.valueOf(i + "-" + month + "-" + dayofmonth);
//                        } else if (i1 < 10 && i2 > 10) {
//                            month = "0" + i1;
//                            date = String.valueOf(i + "-" + month + "-" + i2);
//                        } else if (i2 < 10 && i1 > 10) {
//                            dayofmonth = "0" + i2;
//                            date = String.valueOf(i + "-" + i1 + "-" + dayofmonth);
//                        } else {
//                            date = String.valueOf(i + "-" + i1 + "-" + i2);
//                        }
//                    }
//                },
//                Calendar.getInstance().get(Calendar.YEAR),
//                Calendar.getInstance().get(Calendar.MONTH),
//                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
//        );
//        datePickerDialog.show();
//    }


}