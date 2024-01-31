package com.gameon.generic;

import java.util.List;

public interface IRepository<Tentity, Tkey> {

    List<Tentity> getAll() throws Exception;

    Tentity find(Tkey k) throws Exception;

    void add(Tentity entity) throws Exception;

    void delete(Tentity entity) throws Exception;

    void save(Tentity e) throws Exception;

}
