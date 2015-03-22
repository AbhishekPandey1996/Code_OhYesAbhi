package com.example.absk;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

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

public class newtrans extends Activity implements OnClickListener {

	JSONParser jsonParser = new JSONParser();
	String paramString;
	private ProgressDialog pDialog;
	EditText to, amount, date;
	Button borrow, lend;
	private static String url_add_trans = "http://loanme.comule.com/index.php?&";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_borrow);
		borrow = (Button) findViewById(R.id.bnewborrow);
		lend = (Button) findViewById(R.id.bnewlend);
		date = (EditText) findViewById(R.id.etnewdate);
		to = (EditText) findViewById(R.id.etnewborrowname);
		amount = (EditText) findViewById(R.id.etnewborrowamount);

		borrow.setOnClickListener(this);
		lend.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.bnewborrow:

			if (to.getText().length() > 0) {
				if (date.getText().length() > 0) {

					if (amount.getText().length() > 0) {

						new Borrow().execute();

					} else {

						Toast.makeText(getBaseContext(),
								"Amount cannot be Zero", Toast.LENGTH_SHORT)
								.show();
					}
				} else {
					Toast.makeText(getBaseContext(),
							"Feild can't be  left empty", Toast.LENGTH_SHORT)
							.show();
				}

			} else {
				Toast.makeText(getBaseContext(), "Feild can't be  left empty",
						Toast.LENGTH_SHORT).show();
			}

			break;
		case R.id.bnewlend:

			break;
		}

	}

	class Borrow extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(newtrans.this);
			pDialog.setMessage("Adding new Transaction");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... arg) {
			// TODO Auto-generated method stub

			String newname = to.getText().toString();
			String newamount = amount.getText().toString();
			String newdate = date.getText().toString();
			
			HttpClient httpClient = new DefaultHttpClient();
			url_add_trans = url_add_trans + "B_Email=" + newname
					+ "&B_Amount=" + newamount + "&B_Date=" + newdate;
			HttpGet httpGet = new HttpGet(url_add_trans);

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

			return null;
		}
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
			Toast.makeText(getBaseContext(), "Successfully Added",
					Toast.LENGTH_SHORT);

			Intent i = new Intent(newtrans.this, Loginpage.class);
			startActivity(i);

		}

	}

}
