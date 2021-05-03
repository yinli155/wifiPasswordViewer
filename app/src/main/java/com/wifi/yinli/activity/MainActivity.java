package com.wifi.yinli.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.wifi.yinli.R;
import com.wifi.yinli.utils.RootCmd;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import com.google.android.material.snackbar.Snackbar;
<<<<<<< HEAD
import java.io.FileOutputStream;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
=======
>>>>>>> 9b9bf754f025e235bf4b6004ca4831e93f1b9ef0

public class MainActivity extends AppCompatActivity {

    private ListView listview;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
<<<<<<< HEAD
		super.onCreate(savedInstanceState);
=======
	super.onCreate(savedInstanceState);
>>>>>>> 9b9bf754f025e235bf4b6004ca4831e93f1b9ef0
        setContentView(R.layout.activity_main);
        listview = findViewById(R.id.activitymainListView1);
        try {
            if (Build.VERSION.SDK_INT > 25) {
                RootCmd.execRootCmd(Build.VERSION.SDK_INT >= 30 ? "cp /data/misc/apexdata/com.android.wifi/WifiConfigStore.xml " + getExternalCacheDir().getPath() : "cp /data/misc/wifi/WifiConfigStore.xml " + getExternalCacheDir().getPath());
<<<<<<< HEAD
                setTxt(getTxt(getExternalCacheDir().getPath() + "/" + "WifiConfigStore.xml").replace("<null name=\"PreSharedKey\" />", "<string name=\"PreSharedKey\">&quot;无密码&quot;</string>"), getExternalCacheDir().getPath() + "/" + "WifiConfigStore.xml");
                InputStream stream = new FileInputStream(new File(getExternalCacheDir().getPath() + "/" + "WifiConfigStore.xml"));
=======
                InputStream stream = new FileInputStream(new File(getExternalCacheDir().getPath() + "/WifiConfigStore.xml"));
>>>>>>> 9b9bf754f025e235bf4b6004ca4831e93f1b9ef0
                getXml(stream);
            } else {
                getConf(RootCmd.execRootCmd("cat /data/misc/wifi/*.conf"));
            }
        } catch (XmlPullParserException e) {
            AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("XML解析出现异常")
                .setMessage(e.toString())
                .setPositiveButton("知道了", null)
                .create();
            dialog.show();
        } catch (IOException e) {
            AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("IO异常")
                .setMessage(e.toString())
                .setPositiveButton("知道了", null)
                .create();
            dialog.show();
        }
    }
    private void initShow(final List<Map<String, String>> list) {
        List<Map<String, String>> list2 = list;
        listview.setAdapter(new SimpleAdapter(this, list2, R.layout.item, new String[]{"name", "key"}, new int[]{R.id.name, R.id.key}));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4) {
                    Map<String, String> map = list.get(p3);
                    ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    cm.setText(map.get("truekey"));
<<<<<<< HEAD
                    if (map.get("truekey").equals("无密码")) {
                        Snackbar.make(findViewById(android.R.id.content), "这个WiFi不需要密码", 3000).show();
                    } else {
                        Snackbar.make(findViewById(android.R.id.content), "已复制 < " +  map.get("name").replace("WiFi名称:", "") + " > 的密码", 3000).show();
                    }

=======
                    Snackbar.make(findViewById(android.R.id.content), "已复制" +  map.get("name") + "的密码", Snackbar.LENGTH_SHORT).show();
>>>>>>> 9b9bf754f025e235bf4b6004ca4831e93f1b9ef0
                }
            });
        if (list.size() == 0) {
            AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("获取失败")
                .setMessage("无root权限或从未连接过WiFi")
                .setPositiveButton("知道了", null)
                .create();
            dialog.show();
        }
    }
    private void getConf(String str) {
        ArrayList arrayList = new ArrayList();
        Matcher matcher = Pattern.compile("network=\\{([^\\}]+)\\}", 32).matcher(str);
        while (matcher.find()) {
            String group = matcher.group();
            Matcher matcher2 = Pattern.compile("ssid=\"([^\"]+)\"").matcher(group);
            Matcher matcher3 = Pattern.compile("ssid=[a-fA-F0-9]+").matcher(group);
            if (matcher2.find()) {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("name", matcher2.group(1));
                Matcher matcher4 = Pattern.compile("psk=\"([^\"]+)\"").matcher(group);
                if (matcher4.find()) {
                    hashMap.put("name", new StringBuffer().append("WiFi名称:").append(matcher2.group(1)).toString());
                    hashMap.put("key", String.format("%s", new Object[]{matcher4.group(1)}));
                    hashMap.put("truekey", matcher4.group(1));
                    arrayList.add(hashMap);
                }
            } else if (matcher3.find()) {
                HashMap<String, String> hashMap2 = new HashMap<String, String>();
                Matcher matcher5 = Pattern.compile("psk=\"([^\"]+)\"").matcher(group);
                if (matcher5.find()) {
                    hashMap2.put("name", new StringBuffer().append("WiFi名称:").append(getSSID(group)).toString());
                    hashMap2.put("key", String.format("%s", new Object[]{matcher5.group(1)}));
                    hashMap2.put("truekey", matcher5.group(1));
                    arrayList.add(hashMap2);
                }
            }
        }
        initShow(arrayList);
    }
    private String getSSID(String str) {
        String decode = URLDecoder.decode(str.substring(str.indexOf("configKey") + 9, str.lastIndexOf("-")));
        String substring = decode.substring(5, decode.length());
        return substring.substring(0, substring.length() - 2);
    }
    private void getXml(InputStream is) throws XmlPullParserException, FileNotFoundException, IOException {
        ArrayList arrayList = new ArrayList();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(is, "utf-8");
        for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
            String name = newPullParser.getName();
            switch (eventType) {
                case 2:
                    if ("string".equals(name)) {
                        if ("SSID".equals(newPullParser.getAttributeValue((String) null, "name"))) {
                            newPullParser.next();
                            String trim = newPullParser.getText().trim();
                            hashMap.put("name", String.format("WiFi名称:%s", new Object[]{trim.substring(1, trim.length() - 1)}));
                            if (hashMap.size() == 3) {
                                arrayList.add(hashMap);
                                hashMap = new HashMap<String, String>();
                            }
                        }
                        if (!"PreSharedKey".equals(newPullParser.getAttributeValue((String) null, "name"))) {
                            break;
                        } else {
                            newPullParser.next();
                            String trim2 = newPullParser.getText().trim();
                            String substring = trim2.substring(1, trim2.length() - 1);
                            hashMap.put("key", String.format("%s", new Object[]{substring}));
                            hashMap.put("truekey", substring);
                            if (hashMap.size() != 3) {
                                break;
                            } else {
                                arrayList.add(hashMap);
                                hashMap = new HashMap<String, String>();
                                break;
                            }
                        }
                    } else {
                        break;
                    }
            }
        }
        initShow(arrayList);

<<<<<<< HEAD
    }
    private void setTxt(String text, String inname) {
        try {
            FileOutputStream newtxt = new FileOutputStream(inname);
            newtxt.write(text.getBytes());
            newtxt.close();
        } catch (Exception e) {

        }
=======
>>>>>>> 9b9bf754f025e235bf4b6004ca4831e93f1b9ef0
    }
    private String getTxt(String filename) {
        String result = "";
        try {
            InputStream fis = new FileInputStream(filename);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            result = new String(buffer);
            fis.close();
        } catch (Exception e) {

        }
        return result;
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_main_1:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
	}
    private long firstBackTime;
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - firstBackTime > 2000) {
            Snackbar.make(findViewById(android.R.id.content), "再按一次返回键退出程序", 2000).show();
            firstBackTime = System.currentTimeMillis();
            return;
        }
        super.onBackPressed();
    }

}
