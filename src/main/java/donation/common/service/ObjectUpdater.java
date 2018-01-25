package donation.common.service;

import java.lang.reflect.InvocationTargetException;

public interface ObjectUpdater {
    public void update(Object objectToUpdate, Object newObjectData) throws InvocationTargetException, IllegalAccessException;
}
