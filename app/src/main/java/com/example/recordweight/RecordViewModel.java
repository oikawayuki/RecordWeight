package com.example.recordweight;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.time.LocalDateTime;

public class RecordViewModel extends ViewModel {
    public double weight;
    public double bmi;
    public String judgement;

}
