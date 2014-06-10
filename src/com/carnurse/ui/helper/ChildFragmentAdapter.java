package com.carnurse.ui.helper;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ChildFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> _fragmentList;
	
	public ChildFragmentAdapter(FragmentManager fm){
		super(fm);
	}
	
	public ChildFragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragmentList){
		super(fm);
		this._fragmentList=fragmentList;
	}
	
	@Override
	public Fragment getItem(int index) {
		if(_fragmentList == null)
			return null;
		else
		    return _fragmentList.get(index);
	}

	@Override
	public int getCount() {
		if(_fragmentList == null)
			return 0;
		else
		    return _fragmentList.size();
	}

	@Override
	public int getItemPosition(Object object) {
		return super.getItemPosition(object);
	}

}
