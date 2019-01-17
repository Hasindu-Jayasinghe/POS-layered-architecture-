package lk.ijse.dep.app.dao;

import lk.ijse.dep.app.entity.SuperEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDAO<T extends SuperEntity, ID> extends SuperDAO {

    Optional<T> find(ID key) throws SQLException;

    Optional<List<T>> findAll() throws SQLException;

    boolean save(T entity) throws SQLException;

    boolean update(T entity) throws SQLException;

    boolean delete(ID key) throws SQLException;

}
