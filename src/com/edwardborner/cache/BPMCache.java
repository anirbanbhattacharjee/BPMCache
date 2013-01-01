package com.edwardborner.cache;

import com.ibm.websphere.cache.DistributedMap;
import com.ibm.websphere.cache.EntryInfo;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * A class to expose the functionality of the WebSphere Dynacache module.
 * The jar can be added as a managed file and accessed via a connector or 
 * JavaScript.
 * 
 * @author Edward Borner
 * @version 0.1
 */
public class BPMCache {
    
    // The JNDI name for the Object Cache Instance
    private static String JNDI_NAME = "";
    
    // The defulat sharing policy is set to SHARED_PUSH which pushes to other JVMs
    private static int DEFAULT_SHARING_POLICY = EntryInfo.SHARED_PUSH;
    
    // The DistributedMap which holds the cache
    private DistributedMap map;
    
    /**
     * Initialise the Distributed Map.
     * You cannot pass anything into the constructor from BPM.
     */
    public BPMCache() { 
        map = null;
    }
    
    /**
     * Initialise the Distributed Map by retrieving it by JNDI name.
     * The JNDI name is stored in a static variable so that we know if we can
     * re-use the existing map. If the JNDI names are different then it will
     * get the map for the new JNDI name.
     * 
     * @param jndi  String  The JNDI name for the Object Cache Instance 
     */
    private void initialiseCacheMap(String jndi) throws BPMCacheException {
        System.out.println("Calling: BPMCache.initialiseCacheMap");
        // Check to see if the JNDI name saved is the same as the one passed in.
        // If it isn't then set it to the new JNDI name. Also reset the map
        // so that it is forced to get the new Object Cache Instace.
        if(!JNDI_NAME.equals(jndi)) {
            JNDI_NAME = jndi;
            map = null;
        }
        
        if(map == null) {
            try {
                InitialContext context = new InitialContext();
                map = (DistributedMap)context.lookup(JNDI_NAME);
            } catch(NamingException e){
                throw new BPMCacheException("Failed to retreive the Object Cache Instance with JNDI: " + JNDI_NAME);
            }
        }
        System.out.println("Complete: BPMCache.initialiseCacheMap");
    }
    
    /**
     * Put an item on the Distributed Map (cache)
     * 
     * @param jndi              String  JNDI name of the Object Cache Instance
     * @param key               String  String value representing the key for the item
     * @param value             Object  Value to be cached.
     * @param priority          int     The priority of the cached item (higher priority items are disposed of last)
     * @param timeToLive        int     The number of seconds until the item is invalid
     * @param inactivityTime    int     The number of seconds unused before the item is invalid
     * @throws BPMCacheException 
     */
    public void put(String jndi, String key, Object value, int priority, int timeToLive, int inactivityTime) throws BPMCacheException {
        System.out.println("Calling: BPMCache.put");
        // Call the initialise incase its the first access
        initialiseCacheMap(jndi);
        
        // Put the value in the Distributed Map
        map.put(key, value, priority, timeToLive, inactivityTime, DEFAULT_SHARING_POLICY, null);
    }
    
    /**
     * Get an item out of the Distributed Map (cache)
     * 
     * @param jndi      String  JNDI name of the Object Cache Instance
     * @param key       String  String value representing the key for the item to retrieve
     * @return          The Object relating the the key or null if the key does not exist
     * @throws BPMCacheException
     */
    public Object get(String jndi, String key) throws BPMCacheException {
        // Call the initialise incase its the first access
        initialiseCacheMap(jndi);
        // Return the value retrieved from the key
        return map.get(key);
    }
    
    /**
     * Check to see if the provided key is in the Distributed Map
     * 
     * @param jndi      String  JNDI name of the Object Cache Instance
     * @param key       String  String value representing the key for the item to retrieve
     * @return          true if the key is found, false if the key is not found
     * @throws BPMCacheException 
     */
    public boolean containsKey(String jndi, String key) throws BPMCacheException {
        // Call the initialise incase its the first access
        initialiseCacheMap(jndi);
        // Return if the Distributed Map contains the provided key
        return map.containsKey(key);
    }
    
    /**
     * Manually invalidate the value represented by the key passed in
     * 
     * @param jndi      String  JNDI name of the Object Cache Instance
     * @param key       String  String value representing the key for the item to retrieve
     * @throws BPMCacheException 
     */
    public void invalidate(String jndi, String key) throws BPMCacheException {
        // Call the initialise incase its the first access        
        initialiseCacheMap(jndi);
        // Invalidate the cached value represented by the key value
        map.invalidate(key);
    }
    
    /**
     * Clear the cache
     * Empty cache of all elements
     * 
     * @param jndi      String  JNDI name of the Object Cache Instance
     * @throws BPMCacheException 
     */
    public void clearCache(String jndi) throws BPMCacheException {
        // Call the initialise incase its the first access
        initialiseCacheMap(jndi);
        // Clear the map
        map.clear();
    }
}
