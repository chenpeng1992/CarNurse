package com.carnurse.ui;

import java.util.ArrayList;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.carnurse.app.R;
import com.carnurse.ui.helper.ChildFragment;
import com.carnurse.ui.helper.ChildFragmentAdapter;

public class MainActivity extends FragmentActivity {
	
	private RadioGroup _radioGroup;
	private ViewPager _viewPager;
	private ArrayList<Fragment> _fragmentList;
	private int _selectedIndex;
	private Resources _resources;
	private long firstBackPressedTime=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		_resources=this.getResources();
		
		InitializeUI();
	}
	
	private void InitializeUI(){
		_radioGroup=(RadioGroup)this.findViewById(R.id.navigationGroup);
		_radioGroup.setOnCheckedChangeListener(new RadioGroupOnCheckedChangeListener());
		
		Fragment recommend=ChildFragment.getInstance(R.layout.recommend);
		Fragment near=ChildFragment.getInstance(R.layout.near);
		Fragment message=ChildFragment.getInstance(R.layout.message);
		Fragment more=ChildFragment.getInstance(R.layout.more);

		_fragmentList=new ArrayList<Fragment>();
		_fragmentList.add(recommend);
		_fragmentList.add(near);
		_fragmentList.add(message);
		_fragmentList.add(more);
		
		_viewPager=(ViewPager)findViewById(R.id.viewPager);
		_viewPager.setAdapter(new ChildFragmentAdapter(getSupportFragmentManager(), _fragmentList));
		_viewPager.setOnPageChangeListener(new ViewPagerOnPageChangeListener());
		_viewPager.setCurrentItem(0);
	}
	
	@Override
	public void onBackPressed() {
		long currentBackPressedTime = System.currentTimeMillis();
		if(currentBackPressedTime - firstBackPressedTime > 2000){
			Toast.makeText(this, this.getString(R.string.pressback_tiptext), Toast.LENGTH_SHORT).show();
			firstBackPressedTime = currentBackPressedTime;
		}else{
			this.finish();
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(0);
		}
	}
	
	public class RadioGroupOnCheckedChangeListener implements OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
			int index=0;
			switch (checkedId) {  
            case R.id.rab_recommend:
            	index=0;  
                break;  
            case R.id.rab_near:
            	index=1;  
                break;  
            case R.id.rab_message:
            	index=2;   
                break;  
            case R.id.rab_more:
            	index=3;   
                break;  
            default:  
                break;  
            }
			_viewPager.setCurrentItem(index);
		}
		
	}
	
	public class ViewPagerOnPageChangeListener implements OnPageChangeListener{

		@Override
		public void onPageSelected(int index) {
			setRadioButtonStyle(index, _resources.getColor(R.color.menu_color_checked), _resources.getColor(R.color.menu_color_unchecked));
			_selectedIndex = index;  
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}
		
		//设置导航栏按钮的样式 文字颜色和选中状态
		private void setRadioButtonStyle(int checkedItemIndex, int checkedColor, int unCheckedColor){
			int childCount = _radioGroup.getChildCount();
			if(childCount==0)
				return;
			for(int i=0; i<childCount;i++){
				RadioButton btn =(RadioButton)_radioGroup.getChildAt(i);
				if(btn==null)
					continue;
				if(i==checkedItemIndex){
					btn.setTextColor(checkedColor);
					btn.setChecked(true);
				}
				else{
					btn.setTextColor(unCheckedColor);
					btn.setChecked(false);
				}
			}
		}
		
	}

}
