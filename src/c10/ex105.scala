package c10

import java.beans.{PropertyChangeEvent, PropertyChangeListener, PropertyChangeSupport}

/**
  * Created by Eugene on 4/5/2017.
  */
trait PropertyChangeSupportLike {
  private val support = new PropertyChangeSupport(this)

  def addPropertyChangeListener(listener: PropertyChangeListener) = {
    support.addPropertyChangeListener(listener)
  }

  def removePropertyChangeListener(listener: PropertyChangeListener) = {
    support.removePropertyChangeListener(listener)
  }

  def getPropertyChangeListeners: Array[PropertyChangeListener] = {
    support.getPropertyChangeListeners
  }

  def addPropertyChangeListener(propertyName: String, listener: PropertyChangeListener) = {
    support.addPropertyChangeListener(propertyName, listener)
  }

  def removePropertyChangeListener(propertyName: String, listener: PropertyChangeListener) = {
    support.removePropertyChangeListener(propertyName, listener)
  }

  def getPropertyChangeListeners(propertyName: String): Array[PropertyChangeListener] = {
    support.getPropertyChangeListeners(propertyName)
  }

  def firePropertyChange(propertyName: String, oldValue: Any, newValue: Any) = {
    support.firePropertyChange(propertyName, oldValue, newValue)
  }

  def firePropertyChange(propertyName: String, oldValue: Int, newValue: Int) = {
    support.firePropertyChange(propertyName, oldValue, newValue)
  }

  def firePropertyChange(propertyName: String, oldValue: Boolean, newValue: Boolean) = {
    support.firePropertyChange(propertyName, oldValue, newValue)
  }

  def firePropertyChange(event: PropertyChangeEvent) = {
    support.firePropertyChange(event)
  }

  def fireIndexedPropertyChange(propertyName: String, index: Int, oldValue: Any, newValue: Any) = {
    support.fireIndexedPropertyChange(propertyName, index, oldValue, newValue)
  }

  def fireIndexedPropertyChange(propertyName: String, index: Int, oldValue: Int, newValue: Int) = {
    support.fireIndexedPropertyChange(propertyName, index, oldValue, newValue)
  }

  def fireIndexedPropertyChange(propertyName: String, index: Int, oldValue: Boolean, newValue: Boolean) = {
    support.fireIndexedPropertyChange(propertyName, index, oldValue, newValue)
  }

  def hasListeners(propertyName: String): Boolean = {
    support.hasListeners(propertyName)
  }
}
