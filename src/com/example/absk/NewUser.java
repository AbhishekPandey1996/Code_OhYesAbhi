package com.example.absk;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewUser extends Activity {

	String url = null;
	private ProgressDialog pDialog;
	String paramString;
	private static String url_create_product = "http://loanme.comule.com/index.php?&";

	JSONParser jsonParser = new JSONParser();
	EditText regname, regpass, regconfirmpass, regmobno;
	Button register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		regname = (EditText) findViewById(R.id.etregid);
		regpass = (EditText) findViewById(R.id.etregpass);
		regconfirmpass = (EditText) findViewById(R.id.etregconfirmpass);
		regmobno = (EditText) findViewById(R.id.etregmobno);
		register = (Button) findViewById(R.id.bregister);

		register.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (regname.getText().length() > 0) {
					if (regmobno.getText().length() > 9) {
						if (regpass.getText().length() > 0) {
							if (regpass
									.getText()
									.toString()
									.equals(regconfirmpass.getText().toString())) {

								new CreateNewProduct().execute();

							} else {

								Toast.makeText(getBaseContext(),
										"Password Didn't Match",
										Toast.LENGTH_SHORT).show();
							}
						} else {

							Toast.makeText(getBaseContext(),
									"Password cannot be left empty",
									Toast.LENGTH_SHORT).show();

						}
					} else {
						Toast.makeText(getBaseContext(), "Enter Valid No.",
								Toast.LENGTH_SHORT).show();
					}

				}

				else {
					Toast.makeText(getBaseContext(),
							"Field can't be left empty", Toast.LENGTH_SHORT)
							.show();
				}
			}

		});

	}

	class CreateNewProduct extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(NewUser.this);
			pDialog.setMessage("Registering");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... arg) {
			// TODO Auto-generated method stub

			String entryname = regname.getText().toString();
			String entrypass = regpass.getText().toString();
			String entrymob = regmobno.getText().toString();
			// String totallend = null, totalborrow = null, bemail = null,
			// bamount = null, bdate = null, iemail = null, iamount = null,
			// idate = null;
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("Email", entryname));
			params.add(new BasicNameValuePair("Password", entrypass));
			params.add(new BasicNameValuePair("Mobile_No", entrymob));
			/**
			 * params.add(new BasicNameValuePair("TL", totallend));
			 * params.add(new BasicNameValuePair("TB", totalborrow));
			 * params.add(new BasicNameValuePair("B_Email", bemail));
			 * params.add(new BasicNameValuePair("B_Amount", bamount));
			 * params.add(new BasicNameValuePair("B_Date", bdate));
			 * params.add(new BasicNameValuePair("I_Email", bemail));
			 * params.add(new BasicNameValuePair("I_Amount", bamount));
			 * params.add(new BasicNameValuePair("I_Date", bdate));
			 **/

			HttpClient httpClient = new DefaultHttpClient();
			paramString = URLEncodedUtils.format(params, "utf-8");
			// url = url_create_product+ paramString;

			// /Email=sdhgfj&Password=adfn&Mobile_No=12334"
			String emailid = regname.getText().toString();
			String passw = regpass.getText().toString();
			String mob = regmobno.getText().toString();

			url_create_product = url_create_product + "Email=" + emailid
					+ "&Password=" + passw + "&Mobile_No=" + mob;
			HttpGet httpGet = new HttpGet(url_create_product);

			try {
				HttpResponse httpResponse;
				InputStream is = null;
				httpResponse = httpClient.execute(httpGet);

				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();
			} catch (Exception e) {
				Toast.makeText(getBaseContext(), e + " error occured.",
						Toast.LENGTH_LONG).show();
			}

			/**
			 * try {
			 * 
			 * JSONObject json = jsonParser.makeHttpRequest( url_create_product,
			 * "GET", params); } catch (Exception e) { e.printStackTrace(); }
			 **/
			return null;
		}

		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
			Toast.makeText(getBaseContext(), "Successfully Registered",
					Toast.LENGTH_SHORT);

			Intent i = new Intent(NewUser.this, Loginpage.class);
			startActivity(i);

		}
	}

}
