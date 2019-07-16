package com.example.excercise.application.datatransformer;

public interface DataTransformer<T, K> {
    void write(T inputData);

    K read(Class<K> outputData);
}
