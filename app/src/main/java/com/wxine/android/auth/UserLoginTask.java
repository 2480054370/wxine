package com.wxine.android.auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.wxine.android.model.User;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserLoginTask extends AsyncTask<String, Void, Boolean> {
    private Context context;
    private SharedPreferences sp;

    public UserLoginTask(Context context) {
        this.context = context;
    }

    ////该方法运行在UI线程当中,并且运行在UI线程当中 可以对UI空间进行设置
    @Override
    protected void onPreExecute() {
        sp = context.getSharedPreferences("profile", Context.MODE_PRIVATE);
        super.onPreExecute();
    }

    //这里的Integer参数对应AsyncTask中的第一个参数    这里主要进行异步操作
    //可以调用publishProgress方法触发onProgressUpdate对UI进行操作 , 对应AsyncTask的第二个参数
    //这里的String返回值对应AsyncTask的第三个参数 ， 即onPostExecute，在doInBackground执行完后运行
    @Override
    protected Boolean doInBackground(String... params) {
        String account = sp.getString("account", "");
        String password = sp.getString("password", "");
        User user = new User();
        JSONObject jsonObject = null;
        /*try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new FastJsonHttpMessageConverter());
            String account = sp.getString("account", "");
            String password = sp.getString("password", "");
            Log.v("account=========:", account);
            Log.v("password========:", password);
            JSONObject jsonObject = restTemplate.getForObject("http://10.0.2.2:8080/wxine_m/login.htm?account={account}&password={password}",
                    JSONObject.class, account, password);

            String code = jsonObject.getString("code");
            String msg = jsonObject.getString("msg");
            Log.v("========:", "==code==" + code + "==msg==" + msg);

            JSONObject ouser = jsonObject.getJSONObject("user");
            user = JSON.parseObject(ouser.toString(), User.class, Feature.AllowISO8601DateFormat);
            Log.v("=========:", "==sessionid==" + user.getSessionid());

            if (StringUtils.isNotBlank(user.getSessionid())) {
                //登录成功，更新sessionid
                Editor editor = sp.edit();
                editor.putString("sessionid", user.getSessionid());
                editor.putString("name", user.getName());
                editor.putString("image", user.getImage());
                editor.commit();
            } else {
                Intent i = new Intent(context, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }

            return true;
        } catch (RestClientException e) {
            Log.v("rest_error:", e.toString());
        } catch (JSONException e) {
            Log.v("json_error:", e.getMessage());
        }*/

        String url = "http://10.0.2.2:8080/wxine_m/login.htm";
        try {
            String result = post(url, "account=" + account + "&password=" + password);
            Log.v("resultresuresult:", result);
            jsonObject = new JSONObject(result);

            String code = jsonObject.getString("code");
            String msg = jsonObject.getString("msg");
            Log.v("========:", "==code==" + code + "==msg==" + msg);

            JSONObject ouser = jsonObject.getJSONObject("user");
            user = JSON.parseObject(ouser.toString(), User.class, Feature.AllowISO8601DateFormat);
            Log.v("=========:", "==sessionid==" + user.getSessionid());

            if (StringUtils.isNotBlank(user.getSessionid())) {
                //登录成功，更新sessionid
                Editor editor = sp.edit();
                editor.putString("sessionid", user.getSessionid());
                editor.putString("account", account);
                editor.putString("password", password);
                editor.putString("name", user.getName());
                editor.putString("image", user.getImage());
                editor.commit();
            } else {
                Intent i = new Intent(context, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.JSONException e) {
            e.printStackTrace();
            Intent i = new Intent(context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }

        return false;
    }

    // Given a URL, establishes an HttpUrlConnection and retrieves
// the web page content as a InputStream, which it returns as
// a string.
    private String post(String posturl, String param) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 10240;

        try {
            URL url = new URL(posturl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            if (StringUtils.isNotBlank(param)) {
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                out.writeBytes(param);
            }
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("=======", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        /*byte[] b = new byte[count];
        int readCount = 0; // 已经成功读取的字节的个数
        while (readCount < count) {
            readCount += in.read(bytes, readCount, count - readCount);
        }*/


        //byte[] buffer = new byte[1024];
        //StringBuffer sb = new StringBuffer();
        //while(stream.read(buffer,0,1024) != -1) {
        //    sb.append(new String(buffer));
        //}

        String str = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        StringBuffer sb = new StringBuffer();
        while ((str = reader.readLine()) != null) {
            sb.append(str).append("\n");
        }


        //Reader reader = null;
        //reader = new InputStreamReader(stream, "UTF-8");
        //char[] buffer = new char[len];
        //reader.read(buffer);
        //return new String(buffer);
        return new String(sb);
    }

}
