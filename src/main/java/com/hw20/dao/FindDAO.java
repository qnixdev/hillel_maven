package com.hw20.dao;

import java.util.List;

public interface FindDAO<C> {
    public C find(int id);

    public C findOneBy(String param, String criteria);

    public List<C> findAll();
}