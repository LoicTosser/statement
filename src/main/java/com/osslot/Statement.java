package com.osslot;

import java.io.File;

public class Statement {

  public static void main(String[] args) {
    if (args.length != 1 || args[0] == null) {
      throw new IllegalArgumentException("Input file name should be set as first argument");
    }

    String fileName = args[0];
    File inputFile = new File(fileName);
    if (!inputFile.exists()) {
      throw new IllegalArgumentException("Input file " + fileName + " does not exist");
    }

    System.out.println("Ready to check duplicates in " + fileName);
  }
}