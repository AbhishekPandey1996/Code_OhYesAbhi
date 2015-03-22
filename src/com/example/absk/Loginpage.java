package com.example.absk;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;






public class Loginpage extends Activity implements OnClickListener {

	private ProgressDialog pDialog;

	private static String url_login = "localhost/php_files/login.php?&";

	JSONParser jsonParser = new JSONParser();
	EditText id, pass;
	Button login, register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		id = (EditText) findViewById(R.id.etemailid);
		pass = (EditText) findViewById(R.id.etpass);
		login = (Button) findViewById(R.id.blogin);
		register = (Button) findViewById(R.id.bnewuser);
		login.setOnClickListener(this);
		register.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.blogin:
			if (id.getText().length() > 0) {
				if (pass.getText().length() > 0) {

					new Login().execute();

				} else {

					Toast.makeText(getBaseContext(),
							"Password cannot be left empty", Toast.LENGTH_SHORT)
							.show();
				}
			} else {
				Toast.makeText(getBaseContext(), "Field can't be left empty",
						Toast.LENGTH_SHORT).show();
			}
			break;

		case R.id.bnewuser:

			Intent i = new Intent(Loginpage.this, NewUser.class);
			startActivity(i);
			break;
		}
	}

	class Login extends AsyncTask<String, String, String> {

		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Loginpage.this);
			pDialog.setMessage("Logging-in");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		
		@Override
		protected String doInBackground(String... arg) {
			// TODO Auto-generated method stub
		

			String logname = id.getText().toString();
			String logpass = pass.getText().toString();
			
			HttpClient httpClient = new DefaultHttpClient();
			url_login = url_login + "Email=" + logname
					+ "&Password=" + logpass ;
			HttpGet httpGet = new HttpGet(url_login);

			HttpResponse httpResponse;
			try {
				InputStream is = null;
				httpResponse = httpClient.execute(httpGet);

				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();
			} catch (Exception e) {
				Toast.makeText(getBaseContext(), e + " error occured.",
						Toast.LENGTH_LONG).show();
			}

						
			return null;
		}
		
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
			Toast.makeText(getBaseContext(), "Successfully Logged-in",
					Toast.LENGTH_SHORT);
		}

	}
}
