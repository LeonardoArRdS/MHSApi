/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.medical.mhs.dados.mySql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author felip
 */
@Repository
public abstract class AbstractRepository {
    
    @Autowired
    private EntityManager em;

    
    public EntityManager getEntityManager(){
        return this.em;
    }


    public <T extends Object> Collection persist(Collection entities) {
        return this.bulkPersist(entities);
    }

    public <T extends Object> Collection merge(Collection entities) {
        return this.bulkMerge(entities);
    }
    
    public <T extends Object> Collection<Object> bulkPersist(Collection<T> entities) {

        final List<Object> savedEntities = new ArrayList<Object>(entities.size());
        int i = 0;
        for (T t : entities) {
            savedEntities.add(persist(t));
            i++;
            if (i % 1000 == 0) {
                // Flush a batch of inserts and release memory.
                getEntityManager().flush();
                getEntityManager().clear();
            }
        }
        return savedEntities;
    }

    //@Transactional
    public <T extends Object> Collection<Object> bulkMerge(Collection<T> entities) {
        final List<Object> savedEntities = new ArrayList<Object>(entities.size());
        int i = 0;
        for (T t : entities) {
            savedEntities.add(merge(t));
            i++;
            if (i % 1000 == 0) {
                // Flush a batch of inserts and release memory.
                getEntityManager().flush();
                getEntityManager().clear();
            }
        }

        return savedEntities;
    }

    public List selectAll(Class expectedEntity) {
        Query q = getEntityManager().createQuery("SELECT t FROM " + expectedEntity.getSimpleName() + " t");
        return q.getResultList();
    }

    public <T extends Object> T findById(Class<T> expectedEntity, Object id) {
        return (T) getEntityManager().find(expectedEntity, id);
    }

    @Deprecated
    public List selectByQuery(String query, Class type, Map<String, Object> parameters) {
        Query q = getEntityManager().createQuery(query, type);
        if (parameters.size() > 0) {
            parameters.entrySet().stream().forEach((parameter) -> {
                q.setParameter(parameter.getKey(), parameter.getValue());
            });
        }
        return q.getResultList();
    }

    public List selectByQuery(String query, Class type, Parametros parameters) {
        Query q = getEntityManager().createQuery(query, type);
        q = appendParameters(q, parameters);
        return q.getResultList();
    }

    protected <T extends Object> T getSingleResultByNativeQuery(String query, Class type, Parametros parameters) {
        Query q = getEntityManager().createNativeQuery(query, type);
        q = appendParameters(q, parameters);
        return (T) q.getSingleResult();
    }

    public <T extends Object> T getSingleResult(String query, Class type, Parametros parameters) {
        Query q = getEntityManager().createQuery(query, type);
        q = appendParameters(q, parameters);
        return (T) q.getSingleResult();
    }

    protected Query appendParameters(Query q, Parametros parameters) {

        if (parameters.size() > 0) {
            parameters.entrySet().stream().forEach((parameter) -> {
                q.setParameter(parameter.getKey(), parameter.getValue());
            });
        }

        return q;
    }

    @Deprecated
    public List selectByNativeQuery(String query, Class type, Map<String, Object> parameters) {
        Query q = getEntityManager().createNativeQuery(query, type);
        if (parameters.size() > 0) {
            parameters.entrySet().stream().forEach((parameter) -> {
                q.setParameter(parameter.getKey(), parameter.getValue());
            });
        }
        return q.getResultList();
    }

    protected List selectByNativeQuery(String query, Class type, Parametros parameters) {
        Query q = getEntityManager().createNativeQuery(query, type);
        q = appendParameters(q, parameters);
        return q.getResultList();
    }

    protected Query getByNativeQuery(String query) {
        return getEntityManager().createNativeQuery(query);
    }

    protected Query getByNativeQuery(String query, Class type) {
        return getEntityManager().createNativeQuery(query, type);
    }
    
    protected Query getByQuery(String query, Class type){
        return getEntityManager().createQuery(query, type);
    }

//    @Transactional
    protected Integer createNativeQuery(String query, Parametros parameters) {
        Query q = getEntityManager().createNativeQuery(query);

        q = appendParameters(q, parameters);

        return q.executeUpdate();
    }

    protected Boolean exists(Class type, Object id) {
        Object obj = getEntityManager().find(type, id);
        return !(obj == null);
    }

    @Deprecated
//    @Transactional
    public Integer createNativeQuery(String query, Map<String, Object> parameters) {
        Query q = getEntityManager().createNativeQuery(query);

        if (parameters.size() > 0) {
            parameters.entrySet().stream().forEach((parameter) -> {
                q.setParameter(parameter.getKey(), parameter.getValue());
            });
        }

        return q.executeUpdate();
    }

    private Object mergeToBulk(Object objectToMerge) {
        getEntityManager().merge(objectToMerge);
        return objectToMerge;
    }


    public Object merge(Object objectToMerge) {
        return getEntityManager().merge(objectToMerge);
    }

  
    public Object persist(Object objectToPersist) {
        getEntityManager().persist(objectToPersist);
        return objectToPersist;
    }

    public static class Parametros extends HashMap<String, Object> {

        public Parametros(String key, Object value) {
            super();
            this.put(key, value);
        }

        public Parametros() {
            super();
        }

    }

}
