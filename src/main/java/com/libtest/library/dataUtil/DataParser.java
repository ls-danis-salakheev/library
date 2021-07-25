package com.libtest.library.dataUtil;

import java.util.List;

public interface DataParser<T> {

    List<T> parseFrom(String root, String resource);
}
