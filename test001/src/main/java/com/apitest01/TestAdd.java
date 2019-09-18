package com.apitest01;

import org.testng.annotations.Test;

public class TestAdd {

    public int add(int i,int j){
        int m;
        m=i+j;

        return m;
    }
    @Test
    public   void testadd(){

        if(2==add(0,2)){
            System.out.println("校验通过");
        }
        else{
            System.out.println("校验失败");
        }

    }
}
