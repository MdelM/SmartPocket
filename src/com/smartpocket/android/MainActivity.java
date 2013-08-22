package com.smartpocket.android;


import android.os.Bundle;

import com.smartpocket.android.R;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;


public class MainActivity extends SherlockFragmentActivity {

	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mMenuTitles;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		getWindow().requestFeature((int)com.actionbarsherlock.view.Window.FEATURE_ACTION_BAR_OVERLAY);	
		setContentView(R.layout.activity_main);
		
		mTitle=mDrawerTitle=getTitle();
		mMenuTitles=getResources().getStringArray(R.array.fragment_item);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		mDrawerList.setAdapter(new DrawerListAdapter());
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		
		// enable ActionBar app icon to behave as action to toggle nav drawer
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		
		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_navigation_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				getSupportActionBar().setTitle(mTitle);
				supportInvalidateOptionsMenu(); // creates call to
												// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle(mDrawerTitle);
			
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		if(savedInstanceState==null){
			init(); //Go to the default item
		}
		if(firstLaunch()){
			//selectItem(3); //Select the item to Initialize all data in cache
		}
		
		
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}
	
	private boolean firstLaunch() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onBackPressed() {
	
		if (mDrawerLayout.isDrawerOpen(mDrawerList)){
			mDrawerLayout.closeDrawer(Gravity.LEFT);
		}
		else{
			super.onBackPressed();
		}
			
	}

	void init(){
		selectItem(0);
		
		mDrawerLayout.setVisibility(View.VISIBLE);
		mDrawerLayout.openDrawer(Gravity.LEFT);
	}

	
	@Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	            case android.R.id.home:
	                if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
	                    mDrawerLayout.closeDrawer(mDrawerList);
	                } else {
	                    mDrawerLayout.openDrawer(mDrawerList);
	                }
	                
	        }
	        return true;
	 }
	
	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			  view.setSelected(true);
			selectItem(position);
		}
	}
	
	private void selectItem(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		
		switch (position) {
		case 0:
			/*if (getLoggedInUser() == null) {
				fragment = new LoginFragment();
			} else {
				fragment = new ProfileFragment();
			}
			break;*/
		case 1:
			//fragment = new SessionParentFragment();
			break;
		case 2:
			//fragment = new SpeakerParentFragment();
			break;
		case 3:
//			fragment = new AgendaFragment();
			break;
		case 4:
		//	fragment = new SocialStreamFragment();
			break;
		case 5:
			//	fragment = new MapFragment();
				break;
		case 6:
			//	fragment = new AboutFragment();
				break;
		case 8:
				/*fragment = new InitialisationFragment();
				((InitialisationFragment)fragment).setListener(this);*/
				break;
		default:
			break;
		}

		FragmentManager fragmentManager = getSupportFragmentManager();
		if (fragment == null) {
			return;
		}
		
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).addToBackStack(position+"").commit();

		mDrawerList.setItemChecked(position, true);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	
	
	class DrawerListAdapter extends BaseAdapter {

		private static final int TYPE_PROFILE = 0;
		private static final int TYPE_ITEM = 1;
		private LayoutInflater mInflater;

		//private Participant user;

		public int getCount() {
			return mMenuTitles.length+1;
		}

		/*public DrawerListAdapter(Participant user) {
			mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			this.user = user;
		}*/

		@Override
		public Object getItem(int position) {
			if(position==0){
				return 0;
			}else
			return mMenuTitles[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			int type = getItemViewType(position);
			if (convertView == null) {
				holder = new ViewHolder();
				switch (type) {
				case TYPE_ITEM:
					convertView = mInflater.inflate(R.layout.drawer_list_item,
							null);
					holder.textView = (TextView) convertView
							.findViewById(R.id.tv_drawer_item);
					
					holder.imgView = (ImageView) convertView
							.findViewById(R.id.img_drawer);
				int index=position;
				switch (index) {
				case 1:
					holder.imgView.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_body));
					break;
				case 2:
					holder.imgView.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_body));
					break;
				case 3:
					holder.imgView.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_body));
					break;
				case 4:
					holder.imgView.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_body));
					break;
				case 5:
					holder.imgView.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_body));
					break;
				
				case 6:
					holder.imgView.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_body));
					break;
				default:
					break;
				}
					holder.textView.setText(mMenuTitles[index-1]);
					
					break;
				/*case TYPE_PROFILE:
					convertView = mInflater
							.inflate(R.layout .item_profile, null);
					holder.textView = (TextView) convertView
							.findViewById(R.id.tv_username);
					holder.imgView = (ImageView) convertView
							.findViewById(R.id.img_profile); 

					if (user == null) {
						holder.textView.setText("Please Login");
					} else {
						holder.textView.setText(user.getFirstname()+" "+user.getLastname());
						Picasso.with(MainActivity.this).load(user.getPhoto()).placeholder(R.drawable.ic_action_profile)
								.into(holder.imgView);
					}

					break;*/
				}
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			return convertView;
		}

		@Override
		public int getItemViewType(int position) {
			return (position == 0) ? TYPE_PROFILE : TYPE_ITEM;
		}

		@Override
		public int getViewTypeCount() {
			return 2;
		}

		public class ViewHolder {
			public TextView textView;
			public ImageView imgView;
		}

	}


}
