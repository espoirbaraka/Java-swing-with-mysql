package controller;
import java.util.List;

public interface StudentDAO<Object> {
    public void save(Object entity);
    public void update(Object entity);
    public void delete(Object entity);
    public Object get(int id);
    public List<Object> list();
}
