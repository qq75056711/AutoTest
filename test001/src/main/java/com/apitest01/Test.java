package com.apitest01;

import com.alibaba.fastjson.JSONObject;
import com.apitest01.api.Client;
import com.apitest01.api.Request;
import com.apitest01.api.Response;
import com.apitest01.api.Client.Config;
import com.apitest01.api.Request.Builder;
//import xyz.migoo.authen.GoogleAuthenticator;

public class Test {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		
		 Client client = new Client.Config().https(1).build();
		 
/*		 		 Request request = new Request.Builder()
				 .url("https://qaapie33e690ab2de05dc.blockfun.one/api/info")
				 .post()
				 .data("{\"token\": \"d52f8ef6-7013-7ab5-08e2-f8fbbf849dffC92Rxz1kzOSun55DBRb17Yy0To8r0pQe7oWzThGvyEQkdzJ37XMf5Z9\"}")
				 .build();
		 Response res = client.execute(request);
		 System.out.println(res.json());
		 for(Header header : res.headers()){
			 System.out.println(header.getName() + "=" + header.getValue());
		 }

		 JSONObject ggg = (JSONObject)res.json();
		 //String g= ggg.getJSONObject("data").getJSONObject("versionInfo").getString("publishDate");
		 System.out.println(ggg.getJSONObject("email"));
*/
		 //login01输出账号密码
		 JSONObject data01 = new JSONObject();
		 data01.put("email", "75056711@qq.com");
		 data01.put("pwd", "srvbChPXknD8ZBz4//JZApddRpHn4peH0f4pNG+YuTQxHyu1OXR3QwW/mGvDxyFViEaGkJzYDoKt9T1AQUPmnHxQo3CwD6WGwmjpm/Xk0aINwKmU64+2FH6AsAo3knqh+COaW6/d2q1EdSP2TgHmU6EH0VURhQkOmiIlpBo+z54EyoGC4ZeQSLRlP8w7geCbuhitgoRd8j87drEzTeA0H6fWSfF7p8At7oMSn1BuFwMwefyZog3ebbYGIr051kabLB8C2yAnT6dhltrTkl/h7+kfArl5KE5EzVm59JGmZ38WaK0AKvRohok7pMg2F7cdrETKiFupDtfTh+Ugz+lN9Q==");
		 
		 Request request01 = new Request.Builder()
				 .url("https://qaapie33e690ab2de05dc.blockfun.one/api/login")
				 .post()
				 .data(data01)
				 .build();
		 Response res04 = client.execute(request01);
		 System.out.println(res04.json());
		 JSONObject ge = (JSONObject)res04.json();
		 String gettmp = ge.getJSONObject("data").getJSONArray("0").getJSONObject(0).getString("tmp_token");
		 System.out.println("tmp_token="+gettmp);
		 
		 //login02二次验证
		 JSONObject data02 = new JSONObject();
		 data02.put("tmp_token", gettmp);
		 data02.put("code","12345" );
		 Request login02 = new Request.Builder()
				 .url("https://qaapie33e690ab2de05dc.blockfun.one/api/m/loginWithPhone")
				 .post()
				 .data(data02)
				 .build();
		 Response res05 = client.execute(login02);
		 System.out.println(res05.json());
		 JSONObject gt = (JSONObject)res05.json();
		 String gettoken = gt.getJSONObject("data").getString("token");
		 System.out.println("token="+gettoken);
		 
		 //获取用户信息
		 JSONObject data03 = new JSONObject();
		 data03.put("token", gettoken);
		 Request userinfo = new Request.Builder()
				 .url("https://qaapie33e690ab2de05dc.blockfun.one/api/info")
				 .post()
				 .data(data03)
				 .build();
		 Response res06 = client.execute(userinfo);
		 System.out.println(res06.json());
		 
		 //提取提现withdrawalToken
		 JSONObject data = new JSONObject();
		 data.put("token", gettoken);
		 data.put("coin", "ETH");
		 data.put("amount", "0.0000001");
/*		 Request.Builder b = new Request.Builder();
				b.url("https://qaapie33e690ab2de05dc.blockfun.one/api/v2/withdrawalCheck")
				 .post()
				 .data(data);		 
		 Response res02 = client.execute(b.build());
*/
		 Request request = new Request.Builder()
				 .url("https://qaapie33e690ab2de05dc.blockfun.one/api/v2/withdrawalCheck")
				 .post()
				 .data(data)
				 .build();
		 Response res03 = client.execute(request);
		 System.out.println(res03.json());
		 JSONObject gg = (JSONObject)res03.json();
		 String getwt = gg.getJSONObject("data").getString("withdrawalToken");
		 
		 System.out.println("withdrawalToken="+getwt);
		 
		 //提现操作
	//	 String googleCode = GoogleAuthenticator.generateAuthCode("M4A5XLQZ24EOF5XO");
	//	 System.out.println("750谷歌验证:"+googleCode);
		 
		 JSONObject data04 = new JSONObject();
		 data04.put("amount", "0.0000001");
		 data04.put("address", "0xe3c0b6f69f1c1229805990e7ca638afbafc78fe2");
		 data04.put("label", "test-750");
		 data04.put("coin", "ETH");
		 data04.put("coinType", "ETH");
		 data04.put("token", gettoken);
		 data04.put("withdrawalToken",getwt);
		 data04.put("phoneCode", "12345");
		 data04.put("emailCode", "12345");
	//	 data04.put("googleCode", googleCode);
		 Request withdrawal = new Request.Builder()
				 .url("https://qaapie33e690ab2de05dc.blockfun.one/api/v2/withdrawal")
				 .post()
				 .data(data04)
				 .build();
		 
		 Response res07 = client.execute(withdrawal);
		 System.out.println(res07.json());
		 JSONObject gc = (JSONObject)res07.json();
		 float error_code = gc.getFloat("error_code");
		 
		 if(error_code == 200)
		 {
			 System.out.println("提现成功！");
		 }
		 else
		 {
			 System.out.println("提现失败！");
		 }
	}
}
