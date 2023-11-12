package DAO;

import java.util.List;

/**
 *
 * @author Duong Minh Binh
 */
public interface myInterFace<EntityType, KeyType> {

    public void insert(EntityType entity);

    public void update(EntityType entity);

    public void delete(KeyType id);

    public boolean checkID(KeyType id);

    EntityType selectById(KeyType id);

    List<EntityType> selectAll();

    List<EntityType> selectBySql(String sql, Object... args);
}
