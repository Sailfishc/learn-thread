//package com.sailfish.akka;
//
//import akka.actor.UntypedActor;
//
///**
// * @author sailfish
// * @create 2017-05-19-上午11:57
// */
//public class Greeter extends UntypedActor {
//
//    public static enum Msg {
//        GREET, DONE;
//    }
//
//    @Override
//    public void onReceive(Object o) throws Throwable {
//        if (o == Msg.GREET) {
//            System.out.println("hello world");
//            getSender().tell(Msg.DONE, getSelf());
//        } else {
//            unhandled(o);
//        }
//    }
//
//
//}
