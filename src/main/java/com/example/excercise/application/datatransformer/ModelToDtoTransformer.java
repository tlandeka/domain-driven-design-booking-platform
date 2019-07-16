package com.example.excercise.application.datatransformer;

import org.modelmapper.ModelMapper;

public class ModelToDtoTransformer<T, K> implements DataTransformer<T, K> {

    private ModelMapper modelMapper;

    public ModelToDtoTransformer(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    private T inputData;

    public void write(T inputData) {
        this.inputData = inputData;
    }

    public K read(Class<K> outputData) {
        return modelMapper.map(inputData, outputData);
    }

    public K createOutputModel(Class<K> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

}


