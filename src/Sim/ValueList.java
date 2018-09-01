package Sim;

import java.util.ArrayList;
import java.util.Random;

public class ValueList {
	class Value {
		String word;
		int count;
		
		Value(String str) {
			word = str;
			count = 1;
		}
	}
	
	class PickerItem {
		String word;
		int min;
		int max;
		
		PickerItem(String str, int mn, int mx) {
			word = str;
			min = mn;
			max = mx;
		}
	}
	
	private ArrayList<Value> values = new ArrayList<Value>();
	int totalCount = 0;
	
	public void addValue(String str) {
		++totalCount;
		boolean found = false;
		for(Value v: values) {
			if(v.word == str) {
				++v.count;
				found = true;
			}
		}
		if(!found) {
			Value val = new Value(str);
			values.add(val);
		}
	}
	
	public String pickValuePercent() {
		ArrayList<PickerItem> picker = new ArrayList<PickerItem>();
		int minPercentage = 0;
		int maxPercentage = 0;
		
		for(Value v: values) {
			maxPercentage += v.count * 1000000 / totalCount;
			PickerItem pItem = new PickerItem(v.word, minPercentage, maxPercentage);
			minPercentage = maxPercentage;
			picker.add(pItem);
		}
		
		Random rand = new Random();
		int randNum = rand.nextInt(1000000);
		
		for(PickerItem p: picker) {
			if(p.min <= randNum && p.max > randNum) {
				return p.word;
			}
		}
		
		return null;
	}
}
