package com.lanbots.test;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Fragment2 extends Fragment {
	
	private ListView listView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment2, container,false);
		listView = (ListView)view.findViewById(R.id.find);
		init();
		return view;
	}
	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onActivityCreated(savedInstanceState);
	}

	private void init() {
	List<String> items = new ArrayList<String>();
//	for (int i = 0; i < 2; i++) {
//	if(i == 0){
//	items.add("¿Î³Ì±í");
//	}else{
//	items.add("¿¼ÊÔ");
//	}
	for(int i = 1; i <= 10; i++){
		items.add("Item" + i);

	}

	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, items);
	listView.setAdapter(adapter);
	}

}
