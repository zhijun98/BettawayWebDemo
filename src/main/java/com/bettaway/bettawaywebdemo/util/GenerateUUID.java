/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettaway.bettawaywebdemo.util;

import java.util.UUID;

/**
 *
 * @author Zhijun Zhang
 */
public class GenerateUUID {
  
  public static final void main(String... aArgs){
    //generate random UUIDs
    UUID idOne = UUID.randomUUID();
    UUID idTwo = UUID.randomUUID();
    log("UUID One: " + idOne);
    log("UUID Two: " + idTwo);
  }
  
  private static void log(Object aObject){
    System.out.println( String.valueOf(aObject) );
  }
    
}
