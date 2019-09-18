package com.apitest01;

import com.alibaba.fastjson.JSONObject;
import com.apitest01.api.Client;
import com.apitest01.api.Request;
import com.apitest01.api.Response;
import com.apitest01.api.Client.Config;
import com.apitest01.api.Request.Builder;

public class Test002 {

    public static void main(String[] args) {

        Client client = new Client.Config().https(1).build();

        //默认请求头
        JSONObject Header = new JSONObject();
        Header.put("Origin", "http://sitea-pc.dev-game.happy.online");

        //PC用户端登陆接口01
        Request request01 = new Request.Builder()
                .url("http://game-api.dev-game.happy.online/v1/user/login")
                .post()
                .headers(Header)
                .body("{\"AccountName\":\"site20005\",\"Password\":\"a123456\",\"Captcha\":\"6288\",\"CaptchaID\":\"SuzSD68GAcRt88NqKkju\"}")
                .build();
        Response res01 = client.execute(request01);
        System.out.println(res01.json());
        JSONObject ge01 = (JSONObject)res01.json();
        String username = ge01.getJSONObject("data").getString("ShowAccountName");
        System.out.println("账号:"+username);
        String getBalance = ge01.getJSONObject("data").getString("Balance");
        System.out.println("余额:"+getBalance);
        String token = ge01.getString("token");
        System.out.println("token:"+token);

        //token放请求头
        Header.put("token", token);

        //银行转账汇款充值接口02
        Request request02 = new Request.Builder()
                .url("http://game-api.dev-game.happy.online/v1/recharge/transfer")
                .post()
                .headers(Header)
                .body("{\"PayAccountID\":1,\"Amount\":\"500\",\"Depositor\":\"500\",\"Captcha\":\"\",\"CaptchaID\":\"VNYstSqyBvV2YFsD12o8\",\"Remark\":\"\"}")
                .build();
        Response res02 = client.execute(request02);
        System.out.println(res02.json());
        JSONObject ge02 =(JSONObject)res02.json();
        int code = ge02.getInteger("code");
        System.out.println("code:"+code);
        if (code == 0)
        {
            System.out.println("充值订单提交成功！");
        }
        else
        {
            System.out.println("充值订单提交失败！");
        }

        //提现接口03
        Request request03 = new Request.Builder()
                .url("http://game-api.dev-game.happy.online/v1/withdraw/withdraw")
                .post()
                .headers(Header)
                .body("{\n" +
                        "\t\"Balance\": \"100\",\n" +
                        "\t\"BankPassword\": \"x123\",\n" +
                        "\t\"CaptchaID\":\"1234\",\n" +
                        "\t\"Captcha\":\"1234\"\n" +
                        "}")
                .build();
        Response res03 = client.execute(request03);
        System.out.println(res03.json());
        JSONObject ge03 =(JSONObject)res03.json();
        code = ge03.getInteger("code");
        System.out.println("code:"+code);
        if (code == 0)
        {
            System.out.println("提现订单提交成功！");
        }
        else
        {
            System.out.println("提现订单提交失败！");
        }

        //下注接口04

        Request request04 = new Request.Builder()
                .url("http://game-api.dev-game.happy.online/v1/lottery/lottery_unclose_periods")
                .post()
                .headers(Header)
                .body("{\"LotteryID\": 1004 }")
                .build();
        Response res04 =client.execute(request04);
        System.out.println(res04.json());
        JSONObject ge04 =(JSONObject)res04.json();
        String gettmp = ge04.getJSONObject("data").getJSONArray("0").getJSONObject(0).getString("Number");
        System.out.println(gettmp);

        Request request05 = new Request.Builder()
                .url("http://game-api.dev-game.happy.online/v1/lottery/lottery_bet")
                .post()
                .headers(Header)
                .body("{\"LotteryID\":1004,\"LotteryNo\":\"201909111210\",\"Orders\":[{\"PlayUUID\":\"1004_1_8_1_1\",\"BetValues\":\"||||6\",\"PriceType\":\"2yuan\",\"PriceMultiple\":1,\"Rebate\":\"0.000\",\"BetValueView\":\"定位胆||||6\",\"BetNumber\":1}],\"AdditionalOrders\":[],\"AdditionalType\":0}")
                .build();
        Response res05 =client.execute(request05);
        System.out.println(res05.json());
        JSONObject ge05 =(JSONObject)res05.json();
        code = ge05.getInteger("code");
        System.out.println("code:"+code);
        if (code == 0)
        {
            System.out.println("下注成功！");
        }
        else
        {
            System.out.println("下注失败！");
        }
    }
}
