package com.edwardborner.cache;

import teamworks.TWBeanInfo;

/**
 * This is used to describe the methods in the BPMCache class
 * The descriptions are used in the IBM BPM connectors to display the
 * correct field names and types.
 * 
 * @author Edward Borner
 * @version 0.1
 */
@SuppressWarnings("StaticNonFinalUsedInInitialization")
public class BPMCacheBeanInfo extends TWBeanInfo {

  private static final String CLASS_NAME = BPMCacheBeanInfo.class.getName();

  private static Class beanClass = BPMCache.class;

  private static TWBeanInfo.MethodInfo[] bpmCacheBeanInfoBeanMethods = new TWBeanInfo.MethodInfo[5];

  public BPMCacheBeanInfo() {
    super(bpmCacheBeanInfoBeanMethods);
  }

  @Override
  public Class getBeanClass() {
    return beanClass;
  }

  @Override
  protected void handleException(Throwable exception) {
    // Swallow
  }

  static {
    // put Method  
    bpmCacheBeanInfoBeanMethods[0] = new TWBeanInfo.MethodInfo(beanClass, "put", "put(String, String, TWObject, int, int, int)");
    bpmCacheBeanInfoBeanMethods[0].addArgument(String.class, "JNDI Name");
    bpmCacheBeanInfoBeanMethods[0].addArgument(String.class, "Cache Key");
    bpmCacheBeanInfoBeanMethods[0].addArgument(Object.class, "Cache Value");
    bpmCacheBeanInfoBeanMethods[0].addArgument(Integer.class, "Priority");
    bpmCacheBeanInfoBeanMethods[0].addArgument(Integer.class, "Time to Live");
    bpmCacheBeanInfoBeanMethods[0].addArgument(Integer.class, "Inactivity Time");
    
    // get Method
    bpmCacheBeanInfoBeanMethods[1] = new TWBeanInfo.MethodInfo(beanClass, "get", "get(String, String)");
    bpmCacheBeanInfoBeanMethods[1].addArgument(String.class, "JNDI Name");
    bpmCacheBeanInfoBeanMethods[1].addArgument(String.class, "Cache Key");
    
    // containsKey Method
    bpmCacheBeanInfoBeanMethods[2] = new TWBeanInfo.MethodInfo(beanClass, "containsKey", "containsKey(String, String)");
    bpmCacheBeanInfoBeanMethods[2].addArgument(String.class, "JNDI Name");
    bpmCacheBeanInfoBeanMethods[2].addArgument(String.class, "Cache Key");
    
    // invalidate Method
    bpmCacheBeanInfoBeanMethods[3] = new TWBeanInfo.MethodInfo(beanClass, "invalidate", "invalidate(String, String)");
    bpmCacheBeanInfoBeanMethods[3].addArgument(String.class, "JNDI Name");
    bpmCacheBeanInfoBeanMethods[3].addArgument(String.class, "Cache Key");
    
    
    // clearCache Method
    bpmCacheBeanInfoBeanMethods[4] = new TWBeanInfo.MethodInfo(beanClass, "clearCache", "clearCache(String)");
    bpmCacheBeanInfoBeanMethods[4].addArgument(String.class, "JNDI Name");
  }
}
