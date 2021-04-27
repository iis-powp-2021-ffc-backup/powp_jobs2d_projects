package edu.kis.powp.jobs2d.drivers.observer;

public interface IPublisher {
  public void addSubscriber(ISubscriber subscriber);
  public ISubscriber[] getSubscribers();
  public void notifyObservers();
  public void clearObservers();
}
