package com.ai.yc.ucpublic.util;



public class OperationCodeFactory {
	
	private OperationCodeFactory(){};
	

	private static class OperationCodeHolder{
		private static final OperationCodeFactory INSTANCE = new OperationCodeFactory();
	}

	public static final OperationCodeFactory getInstance(){
		return OperationCodeHolder.INSTANCE;
	}

	public synchronized int getOperationCode(){
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
             random = random + 0.1;
        } for (int i = 0; i < 6; i++) {
             num = num * 10;
        }
        return (int) ((random * num));
	}

}
