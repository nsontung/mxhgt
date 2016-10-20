package com.mxhgt.sc.main

import com.mxhgt.sc.thread.MongoThread

/**
  * Created by tungns on 10/7/16.
  */
object Main {
  def main(args: Array[String]): Unit = {
    println("Worker is starting...")

    val t = new Thread(new MongoThread())
    t.start();

  }


}
