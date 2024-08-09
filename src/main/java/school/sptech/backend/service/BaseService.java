package school.sptech.backend.service;

import java.util.List;

public interface BaseService<T, ID>{
    T criar(T entity);
    T atualizar(ID id, T entity);
    Void deletar(ID id);
    T porId(ID id);
    List<T> listar();
}
