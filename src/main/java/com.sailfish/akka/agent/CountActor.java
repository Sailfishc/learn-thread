//package com.sailfish.akka.agent;
//
//import akka.actor.UntypedAbstractActor;
//import akka.dispatch.Mapper;
//import scala.concurrent.Future;
//
///**
// * @author sailfish
// * @create 2017-05-25-上午11:59
// */
//public class CountActor extends UntypedAbstractActor {
//
//
//    Mapper addMapper = new Mapper<Integer, Integer>() {
//
//        @Override
//        public Integer apply(Integer i) {
//            return i + 1;
//        }
//    };
//
//
//    @Override
//    public void onReceive(Object msg) throws Throwable {
//
//        if (msg instanceof Integer) {
//            for (int i = 0; i < 1000; i++) {
//                //希望知道future什么时候结束
//                Future f = AgentDemo.countAgent.alter(addMapper);
//                AgentDemo.futures.add(f);
//            }
//            getContext().stop(getSelf());
//        } else {
//            unhandled(msg);
//        }
//    }
//}
