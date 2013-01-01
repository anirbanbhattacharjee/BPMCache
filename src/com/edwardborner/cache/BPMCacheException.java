package com.edwardborner.cache;

/**
 * Exception Class for BPMCache Exceptions
 * 
 * @author Edward Borner
 * @version 0.1
 */
public class BPMCacheException extends Exception {
    
    /**
     * Simple Constructor to pass the message to the parent (Exception)
     * 
     * @param message String    The error message
     */
    public BPMCacheException(String message) {
        super(message);
    }
    
}
