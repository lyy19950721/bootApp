package com.mipo.common.design.test;

@FunctionalInterface
public interface Handler<T> {

  T execute(String str);
}
