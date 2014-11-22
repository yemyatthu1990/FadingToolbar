package com.yemyatthu.fadingtoolbar;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivity extends ActionBarActivity {
  private final String SROLL_SAVE = "Scroll_State";
  private Drawable backGround;
  private ActionBar actionBar;
  private int mAlpha;
  private Toolbar mToolbar;
  private TextView mTitleView;
  private CustomScrollView mScrollView;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mAlpha = 0;
    if(savedInstanceState != null){
      mAlpha = savedInstanceState.getInt(SROLL_SAVE);
    }
    setContentView(R.layout.activity_main);
    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    mTitleView = (TextView) findViewById(R.id.detail_title);
    mScrollView = (CustomScrollView) findViewById(R.id.detail_scroll);
    setSupportActionBar(mToolbar);
    actionBar = getSupportActionBar();
    backGround = mToolbar.getBackground();
    actionBar.setBackgroundDrawable(backGround);
    backGround.setAlpha(mAlpha);
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setHomeAsUpIndicator(R.drawable.ic_ab_back_mtrl_am_alpha);
    actionBar.setTitle(mTitleView.getText());
    mToolbar.getParent().bringChildToFront(mToolbar);
    int titleColor = Color.argb(mAlpha, Color.red(Color.WHITE), Color.green(Color.WHITE),
        Color.blue(Color.WHITE));
    mToolbar.setTitleTextColor(titleColor);

    mScrollView.setOnScrollViewListener(new CustomScrollView.OnScrollViewListener() {

      @Override
      public void onScrollChanged(CustomScrollView v, int l, int t, int oldl, int oldt) {
        mAlpha = makeAwsomeActionBar(t);

      }

    });
  }


  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt(SROLL_SAVE,mAlpha);
  }

  public int makeAwsomeActionBar(int t){
    final int headerHeight =
        findViewById(R.id.detail_image).getHeight()+mTitleView.getHeight() - actionBar.getHeight();
    final float ratio = (float) Math.min(Math.max(t, 0), headerHeight) / headerHeight;
    final int newAlpha = (int) (ratio * 255);
    backGround.setAlpha(newAlpha);
    int titleColor = Color.argb(newAlpha, Color.red(Color.WHITE), Color.green(Color.WHITE),
        Color.blue(Color.WHITE));
    mToolbar.setTitleTextColor(titleColor);
    return newAlpha;
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu,menu);
    return super.onCreateOptionsMenu(menu);
  }
}
