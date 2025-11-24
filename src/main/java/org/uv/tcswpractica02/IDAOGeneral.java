package org.uv.tcswpractica02;


import java.util.List;

public interface IDAOGeneral <T,ID>{
    public boolean guardar(T pojo);
    public T eliminar(ID id);
    public T modificar(T pojo,ID id);
    public T findByID(ID id);
    public List<T> findAll();
    
}