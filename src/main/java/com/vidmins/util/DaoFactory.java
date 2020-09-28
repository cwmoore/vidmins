package com.vidmins.util;

import com.vidmins.persistence.GenericDao;

/**
 * A factory for GenericDao's
 *
 * @author pwaite
 * @author cwmoore
 *
 */
public class DaoFactory {

    /**
     * static class empty, private constructor
     */
    private DaoFactory() {}

    /**
     * Create a generic data access object of type (type)
     * @param type the entity type
     * @return the GenericDao&lt;type&gt;
     */
    public static GenericDao createDao(Class type) {

        return new GenericDao(type);
    }
}
