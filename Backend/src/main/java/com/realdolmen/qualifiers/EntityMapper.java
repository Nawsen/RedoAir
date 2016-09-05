package com.realdolmen.qualifiers;

import com.realdolmen.domain.MapperType;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Created by WVDAZ49 on 5/09/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, TYPE, METHOD})
@Qualifier
public @interface EntityMapper {
    @Nonbinding
    MapperType type();
}
