package com.therohansharma03.selfaccounting.ui.settings;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.therohansharma03.selfaccounting.AccountSetting;
import com.therohansharma03.selfaccounting.R;
import com.therohansharma03.selfaccounting.GeneralSetting;

public class SettingFragment extends Fragment {

  TextView DirectingtoGeneralSetting;
 TextView DirectingtoAccountSetting;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_setting,container,false);

        DirectingtoGeneralSetting = root.findViewById(R.id.sGeneral_setting);
        DirectingtoAccountSetting = root.findViewById(R.id.sRegister_User_Emailid);

        DirectingtoGeneralSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), GeneralSetting.class);
                startActivity(i);
            }
        });
        DirectingtoAccountSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AccountSetting.class);
                startActivity(i);
            }
        });
        return root;




    }

}