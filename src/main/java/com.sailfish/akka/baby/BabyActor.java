//package com.sailfish.akka.baby;
//
//import akka.actor.UntypedAbstractActor;
//import akka.event.Logging;
//import akka.event.LoggingAdapter;
//import akka.japi.Procedure;
//
///**
// * // TODO: 2017/5/25 由于版本问题有些API和之前的不兼容，暂时没改完
// * 内置状态转换
// *      1、onreceive方法负责接受外部消息
// *      2、具体处理根据onreceive方法的状态调用内部方法
// * @author sailfish
// * @create 2017-05-25-上午11:16
// */
//public class BabyActor extends UntypedAbstractActor{
//
//    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
//
//    public static enum Msg{
//        SLEEP, PLAY, CLOSE;
//    }
//
//    //  生气状态
//    Procedure<Object> angry = new Procedure<Object>() {
//
//        @Override
//        public void apply(Object o) throws Exception {
//
//        }
//    };
//
//
//    //  开心状态
//    Procedure<Object> happy = new Procedure<Object>() {
//
//        @Override
//        public void apply(Object o) throws Exception {
//
//
//        }
//    };
//
//
//    @Override
//    public void onReceive(Object msg) throws Throwable {
//
//        if (msg == Msg.SLEEP) {
//
//            // TODO: 2017/5/25  注意become函数，之前版本接收的是一个Procedure作为参数，在2.5版本中没有了，等待核实
////            getContext().become();
//        } else if (msg == Msg.PLAY) {
//
//        } else {
//            unhandled(msg);
//        }
//    }
//}
