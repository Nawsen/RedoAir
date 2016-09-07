package com.realdolmen.repository.beans;

import com.realdolmen.domain.ApplicationSettings;
import com.realdolmen.repository.ApplicationSettingsRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WVDAZ49 on 5/09/2016.
 */

@Stateless
public class ApplicationSettingsRepositoryBean extends AbstractBaseRepository<ApplicationSettings, Integer> implements ApplicationSettingsRepository {
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ApplicationSettings find(String key) {
        if (key != null) {
            Query query = getEntityManager().createQuery("SELECT ass FROM ApplicationSettings ass WHERE ass.key=:key");
            query.setParameter("key", key);
            List<ApplicationSettings> result = query.getResultList();
            if (result.size() > 0) {
                return result.get(0);
            }
        }
        return null;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public String findValue(String key) {
        if (key != null) {
            Query query = getEntityManager().createQuery("select ass.value from ApplicationSettings ass where ass.key =:key");
            query.setParameter("key", key);
            List<String> result = query.getResultList();
            if (result.size() > 0) {
                return result.get(0);
            }
        }
        return null;
    }

    @Override
    public Map<String, String> findValues(String... keys) {
        if (keys.length > 0) {
            Map<String, String> values = new HashMap<>();
            boolean first = true;
            StringBuilder builder = new StringBuilder();
            Map<String, String> params = new HashMap<>(keys.length);
            for (int i = 0; i < keys.length; i++) {
                if (first) {
                    builder.append("select ass.key,ass.value from ApplicationSettings ass where ass.key =:key").append(i).append(" ");
                    first = false;
                }
                builder.append("or ass.key =:key").append(i).append(" ");
                params.put(keys[i], "key" + i);
            }

            Query query = getEntityManager().createQuery(builder.toString());
            for (String key : params.keySet()) {
                query.setParameter(params.get(key), key);
            }
            List<Object[]> result = query.getResultList();
            if (result.size() > 0) {
                for (Object[] v : result) {
                    values.put((String) v[0], (String) v[1]);
                }
                return values;
            }
        }
        return null;
    }

    @Override
    public void saveDefault(ApplicationSettings applicationSettings) {
        update(applicationSettings);
    }
}
