package com.brain.bim.barinbim.exceptions;

import org.springframework.dao.DataAccessException;

/**
 * This exception is thrown in case of an error inside the StorageFacade.
 *
 * @author linsoli
 *
 */
public class StorageException extends DataAccessException {

  private static final long serialVersionUID = 1L;

  public StorageException(final String message) {
    super(message);
  }
}
