/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.autoclient.github.interfaces;

/**
 *
 * @author Jakub
 */
public interface RepositoryId {
  public String getUsername();
  public String getName();
  @Override
  public String toString();
}
