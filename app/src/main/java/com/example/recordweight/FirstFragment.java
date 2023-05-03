package com.example.recordweight;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.recordweight.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private RecordViewModel viewModel;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // viewModelを利用
        viewModel = new ViewModelProvider(requireActivity()).get(RecordViewModel.class);

        // BMI が計算されていれば表示
        if (viewModel.bmi != 0) {
            binding.weightTextView.setText("体重は" + Double.toString(viewModel.weight) + "kg です");
            binding.bmiTextView.setText("BMIは" + Double.toString(viewModel.bmi) + "です");
            binding.judgementTextView.setText("あなたは" + viewModel.judgement + "です");
        }

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}