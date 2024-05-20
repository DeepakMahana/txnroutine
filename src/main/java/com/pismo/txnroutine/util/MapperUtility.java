package com.pismo.txnroutine.util;

import org.modelmapper.ModelMapper;
import java.util.Objects;

public class MapperUtility {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static <S, T> T convertClass(S source, Class<T> destinationType) {
        if (Objects.isNull(source)) {
            return null;
        }
        return modelMapper.map(source, destinationType);
    }

}
