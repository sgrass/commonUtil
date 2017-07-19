package org.cx.designpattern.proxy;

public class ProxySubject implements Subject {

  //代理类持有一个委托类的对象引用  
  private Subject delegate;  
    
  public ProxySubject(Subject delegate) {  
   this.delegate = delegate;  
  }  
  
  @Override
  public void dealTask(String taskName) {
    long stime = System.currentTimeMillis();   
    //将请求分派给委托类处理  
    delegate.dealTask(taskName);  
    
    long ftime = System.currentTimeMillis();   
    System.out.println("执行任务耗时"+(ftime - stime)+"毫秒");    
  }

}
