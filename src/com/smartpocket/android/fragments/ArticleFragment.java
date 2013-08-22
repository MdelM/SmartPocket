/**
 * 
 */
package com.smartpocket.android.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.smartpocket.android.R;
import com.smartpocket.android.parser.JSONParser;
import com.smartpocket.android.utils.ConstantValues;

/**
 * @author Mishka
 * This class will allow to manage (CRUD queries) on article table
 *
 */
public class ArticleFragment extends SherlockFragment{

	EditText edid,edname;
	Button btsave,btcancel;
	ListView lv;
	
	private ProgressDialog pDialog;
	//creating Json parser object
	JSONParser jParser=new JSONParser();
	
	ArrayList<HashMap<String,String>> liste_des_articles;
	
	private static String url_all_articles=ConstantValues.BASE_URL + "getarticles.php";
	
	//JSON Node names
	private static final String TAG_SUCCESS="success";
	private static final String TAG_articles="article";
	private static final String TAG_idarticle="idarticle";
	private static final String TAG_namearticle="namearticle";
	
	JSONArray articles=null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView=inflater.inflate(R.layout.article_layout,container,false);
		
		setHasOptionsMenu(true);
		String title=getResources().getStringArray(R.array.fragment_item)[0];
		getActivity().setTitle(title);
		
		edid=(EditText)rootView.findViewById(R.id.idarticle);
		edname=(EditText)rootView.findViewById(R.id.namearticle);
		btsave=(Button)rootView.findViewById(R.id.bt_save);
		btcancel=(Button)rootView.findViewById(R.id.bt_cancel);
		
		new LoadAllArticles().execute();
		/*ZoneGeo[] geoArray=ss.getZoneGeo();
		lv.setAdapter(new GeoAdapter(getSherlockActivity(),R.layout.zone_item1,geoArray));*/
				
		//Hashmap for the listview
		liste_des_articles=new ArrayList<HashMap<String,String>>();	
		
		lv=(ListView)rootView.findViewById(R.id.articlelist);
		
		
		return rootView;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getSherlockActivity().getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
	}

	/**
	 * Background Async Task to Load all articles by making HTTP Request
	 * */
	
	class LoadAllArticles extends AsyncTask<String,String,String>{

				
		@Override
		protected void onPostExecute(String result) {
			pDialog.dismiss();
			//updating UI from background Thread
			ListAdapter artadapter=new SimpleAdapter(
					getSherlockActivity(),liste_des_articles,
					R.layout.list_item,
					new String[]{TAG_namearticle,TAG_idarticle},
					new int[]{R.id.articlename,R.id.articleid}
					);
			//updating the listview
			lv.setAdapter(artadapter);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog=new ProgressDialog(getSherlockActivity());
			pDialog.setMessage("Telechargement des articles. Patience svp !...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		
		//Download all articles here
		@Override
		protected String doInBackground(String... arg0) {
			
			List<NameValuePair> params=new ArrayList<NameValuePair>();
			//getting JSON string from url
			JSONObject json=jParser.makeHttpRequest(url_all_articles, "GET", params);
			
			//check your logcat for json response
			Log.d("All articles : ",json.toString());
			try{
				//check if success
				int success=json.getInt(TAG_SUCCESS);
				if(success==1){
					//les articles ont ete trouves
					articles=json.getJSONArray(TAG_articles);
					//Looping through all articles
					for(int i=0;i<articles.length();i++){
						JSONObject c=articles.getJSONObject(i);
						
						//Storing each json item in variable
						String id_article=c.getString(TAG_idarticle);
						String name_article=c.getString(TAG_namearticle);
						
						//creating new hashmap
						HashMap<String,String> map= new HashMap<String,String>();
						//adding each child node to HashMap key ==> value
						map.put(TAG_idarticle, id_article);
						map.put(TAG_namearticle, name_article);
						
						//Adding HashList to ArrayList
						liste_des_articles.add(map);
					}
				}else{
					//write code here for adding articles
					
				}
				
			}catch(JSONException e){
				e.printStackTrace();
			}
			
			return null;
		}
		
	}
	
	
}
