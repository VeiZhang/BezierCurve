package com.excellence.beziercurve;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ShoppingActivity extends AppCompatActivity {
	
	private ImageButton		mImageButton	= null;
	private ImageView		mImageView		= null;
	private RelativeLayout	mLayout			= null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopping);
		
		mImageButton = (ImageButton) findViewById(R.id.imageButton);
		mImageView = (ImageView) findViewById(R.id.imageView);
		mLayout = (RelativeLayout) findViewById(R.id.activity_shopping);
		
		mImageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				BezierAnimation.AddToCart(mImageButton, mImageView, ShoppingActivity.this, mLayout, 3);
			}
		});
		
		mImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AddCartAniamtion.AddToCart(mImageView, mImageButton, ShoppingActivity.this, mLayout, 2);
			}
		});
	}
}
