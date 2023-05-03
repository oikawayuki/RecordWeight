package com.example.recordweight;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.recordweight.databinding.FragmentSecondBinding;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    List<NumberPicker> height = new ArrayList<>();
    List<NumberPicker> weight = new ArrayList<>();
    private RecordViewModel viewModel;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        height.add(binding.heightNum1);
        height.add(binding.heightNum2);
        height.add(binding.heightNum3);
        height.add(binding.heightNum4);
        setPickerValue(height);

        weight.add(binding.weightNum1);
        weight.add(binding.weightNum2);
        weight.add(binding.weightNum3);
        weight.add(binding.weightNum4);
        setPickerValue(weight);

        // viewModelを利用
        viewModel = new ViewModelProvider(requireActivity()).get(RecordViewModel.class);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double heightValue = getPickerValue(height);
                double weightValue = getPickerValue(weight);
                double bmi = 0;
                if (heightValue != 0) {
                    bmi = Math.floor(weightValue/heightValue/heightValue*10000*10) / 10;
                }
                String judgement = checkBmi(bmi);
                moveToFirstFragment(weightValue,bmi, judgement);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setPickerValue(List<NumberPicker> pickers) {
        pickers.get(0).setMinValue(0);
        pickers.get(0).setMaxValue(2);
        for (int i = 1; i < pickers.size(); i++) {
            pickers.get(i).setMinValue(0);
            pickers.get(i).setMaxValue(9);
        }

    }
    private double getPickerValue(List<NumberPicker> pickers) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < pickers.size(); i++) {
            if (i == 3) {
                stringBuilder.append(".");
            }
            stringBuilder.append(pickers.get(i).getValue());
        }
        return Double.parseDouble(stringBuilder.toString());
    }

    private String checkBmi(double bmi) {
        if (bmi < 18.5) {
            return "低体重";
        } else if (18.5 <= bmi && bmi < 25) {
            return "普通体重";
        } else if (25 <= bmi && bmi < 30) {
            return "肥満（1度）";
        } else if (30 <= bmi && bmi < 35) {
            return "肥満（2度）";
        } else if (35 <= bmi && bmi < 40) {
            return "肥満（3度）";
        } else {
            return "肥満（4度）";
        }
    }

    private void moveToFirstFragment(double weight, double bmi, String judgement) {
        viewModel.weight = weight;
        viewModel.bmi = bmi;
        viewModel.judgement = judgement;
        NavHostFragment.findNavController(SecondFragment.this)
                .navigate(R.id.action_SecondFragment_to_FirstFragment);
    }
}