package com.realdolmen.repository.beans;

import com.realdolmen.repository.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.BadRequestException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractBaseRepository<T, PK extends Serializable> implements Repository<T, PK> {
    @PersistenceContext()
    private EntityManager entityManager;

    private Class<?> entityClass;

    public EntityManager getEntityManager() {

        return entityManager;
    }
    public void setEm(EntityManager em) {
        this.entityManager = em;
    }

    @SuppressWarnings("unchecked")
    public Class<?> getEntityClass() {
        if (entityClass == null) {
            this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return entityClass;
    }

    public void setEntityClass(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findByPrimaryKey(Long id) {
            T t = entityManager.find((Class<T>) getEntityClass(),id);
            if (t ==null){
                throw new IllegalArgumentException("PrimaryKey must excist!");
            }
            return t;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() {
        Query query = entityManager.createQuery("Select entity FROM " + getEntityClass().getSimpleName() + " entity");
        return query.getResultList();
    }

    @Override
    public T update(T t) {
        return entityManager.merge(t);
    }

    @Override
    public T insert(T t) {
        entityManager.persist(t);
        return t;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void deleteById(PK id) {
        T ref = (T) entityManager.getReference(getEntityClass(), id);
        if (ref != null) {
            delete(ref);
        }

    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public void flush() {
        entityManager.flush();
    }

}