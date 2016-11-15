package com.excellence.beziercurve;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.excellence.baseadapterlibrary.CommonAdapter;
import com.excellence.baseadapterlibrary.ViewHolder;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
	
	private String[] mActions = {"贝塞尔动画", "二阶贝塞尔曲线"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		List<String> action = Arrays.asList(mActions);
		GridView gridView = (GridView) findViewById(R.id.gridview);
		gridView.setAdapter(new StringAdapter(this, action, android.R.layout.simple_list_item_1));
		gridView.setOnItemClickListener(this);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = null;
		switch (position) {
			case 0 :
				intent = new Intent(this, ShoppingActivity.class);
				break;
			
			case 1 :
				intent = new Intent(this, SencondBezierActivity.class);
				break;
		}
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
	
	private class StringAdapter extends CommonAdapter<String> {
		public StringAdapter(Context context, List<String> datas, int layoutId) {
			super(context, datas, layoutId);
		}
		
		@Override
		public void convert(ViewHolder viewHolder, String item, int position) {
			viewHolder.setText(android.R.id.text1, item);
		}
	}
}
