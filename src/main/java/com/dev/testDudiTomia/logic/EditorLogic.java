package com.dev.testDudiTomia.logic;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.springframework.web.bind.annotation.RequestParam;

public class EditorLogic {
	private String text;
	Stack<String> oldSteps = new Stack<String>(); 
	Stack<String> nextSteps = new Stack<String>();

	public EditorLogic() {
		super();
	}

	public String print() {
		return text;
	}
	public void setText(String text) {
		oldSteps.push(this.text);
		this.text = text;
	}

	public void add(String newText){
		if(this.text == null){
			this.text = "";
		}
		this.text = this.text + newText;
	}
	public void add(@RequestParam("newText") String newText,@RequestParam("position") int position)throws Exception{
		validPostion(position);
		String temp = "";
		for (int i = 0; i < text.length(); i++) {
			if (i == position) { 
				temp += newText;
			}
			temp += text.charAt(i);
		}
		setText(temp);
	}
	public void delete(int fromPosition,int toPosition)throws Exception{
		validPostionBetween(fromPosition, toPosition);
		String temp = "";
		int i = 0;
		while(i < text.length()){
			if(i == fromPosition){
				i = toPosition;
			}
			temp += text.charAt(i);
			i++;	
			
		}
		setText(temp);
	}	
	public void italic(int fromPosition,int toPosition)throws Exception{
		validPostionBetween(fromPosition, toPosition);
		setText(addTag(fromPosition, toPosition, "i"));
	}
	public void bold(int fromPosition,int toPosition)throws Exception{
		validPostionBetween(fromPosition, toPosition);
		setText(addTag(fromPosition, toPosition, "b"));
	}
	public void underLine(int fromPosition,int toPosition)throws Exception{
		validPostionBetween(fromPosition, toPosition);
		setText(addTag(fromPosition, toPosition, "u"));
	}
	public void undo(){
		nextSteps.push(this.text);
		if(!oldSteps.isEmpty())
			this.text = oldSteps.pop();
		else
			this.text = "";
	}
	public void redo(){
		if(!nextSteps.isEmpty())
			this.text = nextSteps.pop();
	}
	private String addTag(int fromPosition, int toPosition, String tag)throws Exception {
		validPostionBetween(fromPosition, toPosition);
		String temp = "";
		for (int i = 0; i < text.length(); i++) {
			if (i == fromPosition) { 
				temp += "<"+tag+">";
			}
			if (i == toPosition) { 
				temp += "</"+tag+">";
			}
			temp += text.charAt(i);
		}
		return temp;
	}
	private Boolean validPostionBetween(int fromPosition, int toPosition)throws Exception{
		if(fromPosition > toPosition && validPostion(toPosition)){
			throw new Exception("Invalid input");
		}
		return true;
	}
	private Boolean validPostion(int position)throws Exception{
		if(this.text == null){
			throw new Exception("text null");
		}
		if(this.text.length() <= position){
			throw new Exception("Invalid input");
		}
		return true;
	}

}
