package com.vidmins.messaging;

import com.vidmins.entity.InputError;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Controller for user input error messages
 *
 * @author cwmoore
 */

public class InputErrors implements Iterable<InputError> {
    private List<InputError> errors;

    public InputErrors() {
        clearAll();
    }

    public void clearAll() {
        errors = new ArrayList<>();
    }

    public void addError(InputError error) {
        errors.add(error);
    }

    public void removeError(InputError error) {
        errors.remove(errors.indexOf(error));
    }

    public Iterator<InputError> iterator() {
        return errors.iterator();
    }
}
