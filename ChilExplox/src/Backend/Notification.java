/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

/**
 *
 * @author matia
 */
public abstract class Notification 
{
  private String content;
  public abstract String getContent();
  public abstract void action();
  
}