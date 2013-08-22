/**
 * 
 */
package com.smartpocket.android.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.smartpocket.android.R;
import com.smartpocket.android.model.Article;

/**
 * @author Mishka
 * Adapter for all article's list
 *
 */
public class SmartArticleListAdapter extends BaseAdapter{

	List<Article> items;
	ArrayList<HashMap<String, String>> hitems;
	LayoutInflater inflater;
	
	private SmartArticleListAdapter(Context ctx,List<Article> items){
		this.items=items;
		this.inflater=LayoutInflater.from(ctx);
	}
	
/*	public SmartArticleListAdapter(Context ctx,
			ArrayList<HashMap<String, String>> hitems) {
		this.hitems=hitems;
		this.inflater=LayoutInflater.from(ctx);
	}*/

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void setArticles(List<Article> items){
		this.items=items;
	}

	
	public class ViewHolder{
		ImageView image;
		TextView name;
		TextView id;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		if(convertView == null){
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.list_item, null);
			
			holder.image=(ImageView)convertView.findViewById(R.id.list_img_item);
			holder.name=(TextView)convertView.findViewById(R.id.articlename);
			holder.id=(TextView)convertView.findViewById(R.id.articleid);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		
		holder.name.setText(items.get(position).getNomArticle());
		holder.id.setText(items.get(position).getIdArticle());
		
		return convertView;
	}

}
