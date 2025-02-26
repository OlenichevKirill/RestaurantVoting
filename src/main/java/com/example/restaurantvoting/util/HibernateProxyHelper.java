package com.example.restaurantvoting.util;

import lombok.experimental.UtilityClass;
import org.hibernate.proxy.HibernateProxy;

@UtilityClass
public class HibernateProxyHelper {

    /**
     * Get the class of an instance or the underlying class
     * of a proxy (without initializing the proxy!)
     */
    public static Class getClassWithoutInitializingProxy(Object object) {
        return (object instanceof HibernateProxy proxy) ?
                proxy.getHibernateLazyInitializer().getPersistentClass() : object.getClass();
    }
}
