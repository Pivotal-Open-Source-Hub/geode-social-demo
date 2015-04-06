package io.pivotal.happysocial.model;

import com.gemstone.gemfire.cache.EntryOperation;
import com.gemstone.gemfire.cache.PartitionResolver;

public class PostPartitionResolver implements PartitionResolver<Object, Object> {

  @Override
  public void close() {
  }

  @Override
  public String getName() {
    return getClass().getSimpleName();
  }

  @Override
  public Object getRoutingObject(EntryOperation<Object, Object> entry) {
    Object key = entry.getKey();
    if (key instanceof PostId) {
      return ((PostId) key).getPerson();
    }
    
    return key;
  }
}
