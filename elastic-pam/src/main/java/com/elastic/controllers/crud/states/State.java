package com.elastic.controllers.crud.states;

public class State {

	private MainCrudState state;

	public State() {
		state = null;
	}

	public MainCrudState getState() {
		return state;
	}

	public void setState(MainCrudState state) {
		this.state = state;
	}

}